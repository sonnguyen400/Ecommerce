package com.nhs.individual.Repository;

import com.nhs.individual.Domain.ShopOrder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopOrderRepository extends JpaRepository<ShopOrder, Integer> {
    List<ShopOrder> findAllByUser_Id(Integer userId, Pageable pageable);

}
