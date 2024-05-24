package com.nhs.individual.Service;

import com.nhs.individual.Domain.ShopOrderStatus;
import com.nhs.individual.Repository.ShopOrderStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopOrderStatusService {
    @Autowired
    ShopOrderStatusRepository shopOrderStatusRepository;
    public ShopOrderStatus updateOrderStatus(ShopOrderStatus shopOrderStatus){
        return shopOrderStatusRepository.save(shopOrderStatus);
    }
}
