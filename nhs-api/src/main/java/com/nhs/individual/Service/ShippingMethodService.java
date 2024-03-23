package com.nhs.individual.Service;

import com.nhs.individual.Repository.ShippingMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShippingMethodService {
    @Autowired
    private ShippingMethodRepository shippingMethodRepository;
}
