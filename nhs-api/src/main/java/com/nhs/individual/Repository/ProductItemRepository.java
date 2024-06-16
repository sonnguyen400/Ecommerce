package com.nhs.individual.Repository;

import com.nhs.individual.Domain.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface ProductItemRepository extends JpaRepository<ProductItem,Integer> {
    @Modifying
    @Query(value = "Update product_item set price=?2 where id",nativeQuery = true)
    void updateProductItem(Integer productItemId, BigDecimal price);
}
