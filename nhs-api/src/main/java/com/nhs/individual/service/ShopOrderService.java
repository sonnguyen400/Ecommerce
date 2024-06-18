package com.nhs.individual.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.nhs.individual.Domain.ShopOrder;
import com.nhs.individual.Domain.ShopOrderStatus;
import com.nhs.individual.constant.OrderStatus;
import com.nhs.individual.repository.ShopOrderRepository;
import com.nhs.individual.specification.SpecificationImp.ShopOrderSpecificationImp;
import com.nhs.individual.zalopay.config.ZaloConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ShopOrderService {
    @Autowired
    ZaloConfig zalopayconfig;
    private final ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    @Autowired
    ShopOrderRepository orderRepository;
    @Autowired
    ShopOrderSpecificationImp shopOrderSpecificationImp;
    @Autowired
    AuthService authService;
    public Optional<ShopOrder> findById(Integer id){
        return orderRepository.findById(id);
    }
    public List<ShopOrder> findAll(Integer userId, String dateFrom, String dateTo, Integer page, Integer size, OrderStatus orderStatus, String orderBy, Sort.Direction direction){
        return shopOrderSpecificationImp.findAll(userId,dateFrom,dateTo,page,size,orderStatus,orderBy,direction);
    }
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
