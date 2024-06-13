package com.nhs.individual.Service;

import com.nhs.individual.Domain.PaymentMethod;
import com.nhs.individual.Repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    public List<PaymentMethod> findAll(){
        return paymentRepository.findAll();
    }

}
