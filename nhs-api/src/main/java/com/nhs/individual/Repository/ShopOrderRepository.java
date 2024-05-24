package com.nhs.individual.Repository;

import com.nhs.individual.Domain.ShopOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ShopOrderRepository extends JpaRepository<ShopOrder,Integer> {
    Collection<ShopOrder> findAllByUser_id(Integer userId);
}
