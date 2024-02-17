package com.nhs.individual.Repository;

import com.nhs.individual.Domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    Collection<Product> findAllByCategory_id(Integer categoryId);
    @Query(value = "Select * from product where id in (Select distinct product_id from product_item where warehouse_id=?1)",nativeQuery = true)
    Collection<Product> findAllByWarehouseId(Integer warehouseId);
}
