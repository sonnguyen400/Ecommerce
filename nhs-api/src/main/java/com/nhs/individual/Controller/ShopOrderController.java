package com.nhs.individual.Controller;

import com.nhs.individual.Domain.ShopOrder;
import com.nhs.individual.Service.ShopOrderService;
import org.hibernate.query.SortDirection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/order")
public class ShopOrderController {
    @Autowired
    ShopOrderService shopOrderService;

    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("#order.userId==authentication.principal.userId")
    public ShopOrder createOrder(@RequestBody ShopOrder order) {
        return shopOrderService.createOrder(order);
    }
    @RequestMapping(method = RequestMethod.GET)
    public Collection<ShopOrder> findAllByUserId(@RequestParam(name = "userId") Integer userId,
                                                 @RequestParam(name = "size",defaultValue = "10") Integer size,
                                                 @RequestParam(name = "page",defaultValue = "1") Integer page,
                                                 @RequestParam(name = "sortBy") String[] fieldName,
                                                 @RequestParam(name = "order",defaultValue = "asc") Sort.Direction direction) {
        PageRequest pageRequest=null;
        Sort sort=null;
        if(fieldName!=null) {
            sort=Sort.by(fieldName).and(Sort.by(direction));
        }
        if(sort!=null)  pageRequest=PageRequest.of(page,size,sort);
        else pageRequest=PageRequest.of(page,size);
        return shopOrderService.findAllByUserId(userId,pageRequest);
    }
}
