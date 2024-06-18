package com.nhs.individual.repository;

import com.nhs.individual.Domain.ShippingMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingMethodRepository extends JpaRepository<ShippingMethod,Integer> {

}
