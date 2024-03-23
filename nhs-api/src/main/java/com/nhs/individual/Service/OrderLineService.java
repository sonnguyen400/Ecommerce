package com.nhs.individual.Service;

import com.nhs.individual.Repository.OrderLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderLineService {
    @Autowired
    private OrderLineRepository orderLineRepository;
}
