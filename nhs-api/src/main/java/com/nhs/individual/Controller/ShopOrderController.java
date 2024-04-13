package com.nhs.individual.Controller;

import com.nhs.individual.Domain.ShopOrder;
import com.nhs.individual.Service.ShopOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/order")
public class ShopOrderController {
    @Autowired
    ShopOrderService shopOrderService;

    @RequestMapping(method = RequestMethod.POST)
    public ShopOrder createOrder(@RequestBody ShopOrder order) {
        return shopOrderService.createOrder(order);
    }
}
