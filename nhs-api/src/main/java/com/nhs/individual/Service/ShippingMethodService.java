package com.nhs.individual.Service;

import com.nhs.individual.Domain.ShippingMethod;
import com.nhs.individual.Repository.ShippingMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ShippingMethodService {
    @Autowired
    private ShippingMethodRepository shippingMethodRepository;
    public Collection<ShippingMethod> findAll(){
        return shippingMethodRepository.findAll();
    }
}
