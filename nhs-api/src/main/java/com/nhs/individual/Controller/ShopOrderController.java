package com.nhs.individual.Controller;

import com.nhs.individual.Constant.OrderStatus;
import com.nhs.individual.DAO.DAOImp.ShopOrderDAO;
import com.nhs.individual.DAO.IShopOrderDAO;
import com.nhs.individual.Domain.ShopOrder;
import com.nhs.individual.Domain.ShopOrder_;
import com.nhs.individual.Service.ShopOrderService;
import jakarta.servlet.ServletRequest;
import org.hibernate.query.SortDirection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.net.http.HttpRequest;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/order")
public class ShopOrderController {
    @Autowired
    ShopOrderService shopOrderService;
    @Autowired
    ShopOrderDAO shopOrderDAO;

    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("#order.userId==authentication.principal.userId")
    public ShopOrder createOrder(@RequestBody ShopOrder order) {
        return shopOrderService.createOrder(order);
    }
    @RequestMapping(method = RequestMethod.GET)
    public Collection<ShopOrder> findAllByUserId(@RequestParam(name = "userId",defaultValue = "1") Integer userId,
                                                 @RequestParam(name = "size",defaultValue = "10") Integer size,
                                                 @RequestParam(name = "from",required = false) String from,
                                                 @RequestParam(name = "to",defaultValue = "now()",required = false) String to,
                                                 @RequestParam(name = "page",defaultValue = "0") Integer page,
                                                 @RequestParam(name = "sortBy",defaultValue = ShopOrder_.ORDER_DATE) String sortBy,
                                                 @RequestParam(name = "order",defaultValue = "ASC") Sort.Direction direction,
                                                 @RequestParam(name="status",required = false) OrderStatus orderStatus
                                                 ) {
        return shopOrderDAO.findAll(userId,from,to,page,size,orderStatus,sortBy,direction);
    }
}
