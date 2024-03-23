package com.nhs.individual.Repository;

import com.nhs.individual.Domain.ShopOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<ShopOrder,Integer> {
}
