package com.nhs.individual.service;

import com.nhs.individual.Domain.ShopOrder;
import com.nhs.individual.Domain.ShopOrderStatus;
import com.nhs.individual.repository.ShopOrderStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopOrderStatusService {
    @Autowired
    ShopOrderStatusRepository repository;
    public ShopOrderStatus updateOrderStatus(Integer orderId,ShopOrderStatus status){
        ShopOrder order=new ShopOrder();
        order.setId(orderId);
        status.setOrder(order);
        return repository.save(status);
    }

}
