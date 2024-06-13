package com.nhs.individual.Repository;

import com.nhs.individual.Domain.UserPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPaymentRepository extends JpaRepository<UserPayment,Integer> {
    List<UserPayment> findAllByUser_Id(Integer userId);
}
