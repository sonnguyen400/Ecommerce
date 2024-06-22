package com.nhs.individual.controller;

import com.nhs.individual.constant.OrderStatus;
import com.nhs.individual.domain.ShopOrder;
import com.nhs.individual.domain.ShopOrderStatus;
import com.nhs.individual.domain.ShopOrder_;
import com.nhs.individual.exception.ResourceNotFoundException;
import com.nhs.individual.service.ShopOrderService;
import com.nhs.individual.service.ShopOrderStatusService;
import com.nhs.individual.specification.ISpecification.IShopOrderSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/order")
public class ShopOrderController {
    @Autowired
    ShopOrderService shopOrderService;
    @Autowired
    ShopOrderStatusService shopOrderStatusService;

    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("#order.userId==authentication.principal.userId")
    public ShopOrder createOrder(@RequestBody ShopOrder order) {
        return shopOrderService.createOrder(order);
    }
    @PreAuthorize("(#params.get('userId')!=null and #params.get('userId')==authentication.principal.userId) or hasRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public Collection<ShopOrder> findAll(@RequestParam Map<String,String> params) {
        Integer page= 0;
        Integer size=10;
        if(params.get("page")!=null){
            try{
                page=Integer.valueOf(params.get("page"));
                size=Integer.valueOf(params.get("size"));
            }catch (NumberFormatException e){
                throw new IllegalArgumentException("Page or size must be number");
            }
        }
        List<Specification<ShopOrder>> specifications=new ArrayList<Specification<ShopOrder>>();
        if(params.get("userId")!=null){
            try {
                specifications.add(IShopOrderSpecification.byUser(Integer.valueOf(params.get("userId"))));
            }catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid user id");
            }
            params.remove("userId");
        }
        if(params.get("status")!=null){
            OrderStatus status=OrderStatus.valueOf(params.get("status").toUpperCase());
            specifications.add(IShopOrderSpecification.byStatus(OrderStatus.valueOf(params.get("status").toUpperCase())));
        }
        if(params.get("address")!=null){
            specifications.add(IShopOrderSpecification.byAddress(params.get("address")));
        }
        Timestamp to=Timestamp.from(Instant.now());
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        try{
            if(params.get("from")!=null){
                Timestamp from=Timestamp.from(dateFormat.parse(params.get("from")).toInstant());
                if(params.get("to")!=null){
                    to=Timestamp.from(dateFormat.parse(params.get("to")).toInstant());
                }
                specifications.add(IShopOrderSpecification.fromToDate(from,to));
            }
        } catch (ParseException e) {
            throw new IllegalArgumentException("Illegal date format");
        }


        Sort sort=Sort.by("id").descending();
        if(params.get("newest")!=null&&params.get("newest").equalsIgnoreCase("ASC")){
            sort.ascending();
        }
        Pageable pageable=PageRequest.of(page,size,sort);
        return shopOrderService.findAll(specifications, pageable);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ShopOrder getOrderById(@PathVariable(name = "id") Integer id) {
        return shopOrderService.findById(id).orElseThrow(()-> new ResourceNotFoundException("Could not find order with id " + id));
    }

//    ShopOrderStatus control
    @RequestMapping(value = "/{orderId}/status",method = RequestMethod.POST)
    public ShopOrderStatus updateStatus(@PathVariable(name = "orderId") Integer orderId,
            @RequestBody ShopOrderStatus shopOrderStatus){
        return shopOrderStatusService.updateOrderStatus(orderId,shopOrderStatus);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/{orderId}/status/APPROVE",method = RequestMethod.POST)
    public ShopOrderStatus approveOrder(@PathVariable(name = "orderId") Integer orderId,
                                        @RequestBody ShopOrderStatus shopOrderStatus){
        shopOrderStatus.setStatus(OrderStatus.PREPARING.id);
        return shopOrderStatusService.updateOrderStatus(orderId,shopOrderStatus);
    }

    @RequestMapping(value = "/{orderId}/status/CANCEL",method = RequestMethod.POST)
    public ShopOrderStatus cancelOrder(@PathVariable(name = "orderId") Integer orderId,
                                      @RequestBody ShopOrderStatus shopOrderStatus){
        shopOrderStatus.setStatus(OrderStatus.DELIVERING.id);
        return shopOrderStatusService.updateOrderStatus(orderId,shopOrderStatus);
    }


}
