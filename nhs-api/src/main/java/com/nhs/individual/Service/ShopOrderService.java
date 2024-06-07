package com.nhs.individual.Service;

import com.nhs.individual.Constant.OrderStatus;
import com.nhs.individual.Domain.ShopOrder;
import com.nhs.individual.Domain.ShopOrderStatus;
import com.nhs.individual.Repository.ShopOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ShopOrderService {
    @Autowired
    ShopOrderRepository orderRepository;
    @Autowired
    AuthService authService;
    public ShopOrder createOrder(ShopOrder order) {
        ShopOrderStatus shopOrderStatus=new ShopOrderStatus();
        shopOrderStatus.setStatus(OrderStatus.PENDING);
        shopOrderStatus.setOrder(order);
        order.setStatus(List.of(shopOrderStatus));
        order.getOrderLines().forEach(line->line.setOrder(order));
        return orderRepository.save(order);
    }

    public Collection<ShopOrder> findAllByUserId(int userId, Pageable pageable) {
        return orderRepository.findAllByUser_Id(userId,pageable);
    }

}
