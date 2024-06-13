package com.nhs.individual.Service;

import com.nhs.individual.Domain.UserPayment;
import com.nhs.individual.Repository.UserPaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserPaymentService {
    private final UserPaymentRepository userPaymentRepository;
    public List<UserPayment> findAllByUserId(Integer userId){
        return userPaymentRepository.findAllByUser_Id(userId);
    }
}
