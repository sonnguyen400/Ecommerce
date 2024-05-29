package com.nhs.individual.Repository;

import com.nhs.individual.Domain.ShopOrder;
import org.hibernate.boot.model.source.spi.Sortable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.Collection;

@Repository
    public interface ShopOrderRepository extends JpaRepository<ShopOrder, Integer> {
    Collection<ShopOrder> findAllByUser_id(Integer userId, Pageable pageable);


}
