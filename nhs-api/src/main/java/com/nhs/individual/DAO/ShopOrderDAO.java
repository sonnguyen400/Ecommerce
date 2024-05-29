package com.nhs.individual.DAO;

import com.nhs.individual.Domain.ShopOrder;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShopOrderDAO{
    @Autowired
    EntityManager entityManager;
    public List<ShopOrder> findAllByUserId(Integer userId) {
        return List.of();

    }
}
