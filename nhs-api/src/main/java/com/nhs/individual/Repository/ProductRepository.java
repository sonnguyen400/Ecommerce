package com.nhs.individual.Repository;

import com.nhs.individual.Domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    Collection<Product> findAllByCategory_id(Integer categoryId);

}
