package com.nhs.individual.Service;

import com.nhs.individual.Constant.OrderStatus;
import com.nhs.individual.Domain.ShopOrder;
import com.nhs.individual.Domain.ShopOrderStatus;
import com.nhs.individual.Repository.ShopOrderRepository;
import org.hibernate.boot.model.source.spi.Sortable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.Collection;
import java.util.List;

@Service
public class ShopOrderService {
    @Autowired
    ShopOrderRepository orderRepository;

    public ShopOrder createOrder(ShopOrder order) {
        ShopOrderStatus orderStatus=new ShopOrderStatus();
        orderStatus.setStatus(OrderStatus.PENDING);
        order.setStatus(List.of(orderStatus));
        orderStatus.setOrder(order);
        return orderRepository.save(order);
    }

    public Collection<ShopOrder> findAllByUserId(int userId, Pageable pageable) {
        return orderRepository.findAllByUser_id(userId,pageable);
    }
    public ShopOrder purchasingOrder(ShopOrder order) {
        return orderRepository.save(order);
    }
}
