package com.nhs.individual.repository;

import com.nhs.individual.Domain.EmbeddedId.ShopOrderStatusId;
import com.nhs.individual.Domain.ShopOrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopOrderStatusRepository extends JpaRepository<ShopOrderStatus, ShopOrderStatusId> {
}
