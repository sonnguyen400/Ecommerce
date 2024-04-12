package com.nhs.individual.Repository;

import com.nhs.individual.Domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {
    Collection<Cart> findAllByUser_Id(int id);
}
