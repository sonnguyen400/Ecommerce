package com.nhs.individual.Service;

import com.nhs.individual.Repository.OrderStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderStatusService {
    @Autowired
    OrderStatusRepository orderStatusRepository;
}
