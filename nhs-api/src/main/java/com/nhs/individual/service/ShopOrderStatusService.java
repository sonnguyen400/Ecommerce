package com.nhs.individual.service;

import com.nhs.individual.constant.OrderStatus;
import com.nhs.individual.domain.ShopOrder;
import com.nhs.individual.domain.ShopOrderStatus;
import com.nhs.individual.exception.DataException;
import com.nhs.individual.repository.ShopOrderStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopOrderStatusService {
    @Autowired
    ShopOrderStatusRepository repository;
    public ShopOrderStatus updateOrderStatus(Integer orderId,ShopOrderStatus status){
        ShopOrder shopOrder=new ShopOrder();
        shopOrder.setId(orderId);
        status.setOrder(shopOrder);
        return repository.findCurrentStatusByOrderId(orderId).map(shopOrderStatus -> {
            if(status.getStatus()>shopOrderStatus.getStatus()){
                return repository.save(status);
            }else throw new DataException("Illegal status value");
        }).orElse(repository.save(status));
    }
    public ShopOrderStatus cancelOrder(int orderId,ShopOrderStatus status){
        ShopOrder shopOrder=new ShopOrder();
        shopOrder.setId(orderId);
        status.setStatus(OrderStatus.CANCEL.id);
        status.setOrder(shopOrder);
        return repository.findCurrentStatusByOrderId(orderId).map(shopOrderStatus -> {
            System.out.println(shopOrderStatus);
            if(shopOrderStatus.getStatus()>2) throw new DataException("Illegal status value");
            else return repository.save(status);
        }).orElse(repository.save(status));
    }

}
