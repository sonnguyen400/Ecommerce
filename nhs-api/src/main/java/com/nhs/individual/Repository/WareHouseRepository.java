package com.nhs.individual.Repository;

import com.nhs.individual.Domain.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WareHouseRepository extends JpaRepository<Warehouse,Integer> {
}
