package com.nhs.individual.Repository;

import com.nhs.individual.Domain.EmbeddedId.ProductItemInWarehouseId;
import com.nhs.individual.Domain.WarehouseItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseItemRepository extends JpaRepository<WarehouseItem, ProductItemInWarehouseId> {

}
