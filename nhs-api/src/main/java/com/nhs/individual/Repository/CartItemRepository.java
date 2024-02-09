package com.nhs.individual.Repository;

import com.nhs.individual.Domain.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface CartItemRepository extends JpaRepository<CartItem,Integer> {
    public Collection<CartItem> findAllByCart_id(Integer id);
}
