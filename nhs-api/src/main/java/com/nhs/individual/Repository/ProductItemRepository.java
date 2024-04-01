package com.nhs.individual.Repository;

import com.nhs.individual.Domain.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ProductItemRepository extends JpaRepository<ProductItem,Integer> {
}
