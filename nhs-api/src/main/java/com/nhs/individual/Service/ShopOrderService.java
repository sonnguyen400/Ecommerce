package com.nhs.individual.Service;

import com.nhs.individual.Domain.OrderStatus;
import com.nhs.individual.Domain.ShopOrder;
import com.nhs.individual.Repository.ShopOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ShopOrderService {
    @Autowired
    ShopOrderRepository orderRepository;
    @Autowired
    AuthService authService;
    public ShopOrder createOrder(ShopOrder order) {
        order.setUser(authService.getCurrentUser());
        return orderRepository.save(order);
    }

    public Collection<ShopOrder> findAllByUserId(int userId) {
        return orderRepository.findAllByUser_id(userId);
    }
    public ShopOrder purchasingOrder(ShopOrder order) {
        OrderStatus orderStatus=new OrderStatus();
        return orderRepository.save(order);
    }
}
