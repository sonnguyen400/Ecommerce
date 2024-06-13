package com.nhs.individual.Repository;

import com.nhs.individual.Domain.Product;
import io.micrometer.common.lang.NonNull;
import io.micrometer.common.lang.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer>, JpaSpecificationExecutor<Product> {
    Collection<Product> findAllByCategory_id(Integer categoryId);
    @Query(value = "Select * from product where id in (Select distinct product_id from product_item where warehouse_id=?1)",nativeQuery = true)
    Collection<Product> findAllByWarehouseId(Integer warehouseId);
    @NonNull
    Page<Product> findAll(@Nullable Specification<Product> specification, @NonNull Pageable pageable);
}
