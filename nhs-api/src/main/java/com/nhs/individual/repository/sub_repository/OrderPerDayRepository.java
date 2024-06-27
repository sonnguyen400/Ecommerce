package com.nhs.individual.repository.sub_repository;

import com.nhs.individual.views.OrderPerDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
@Repository
public interface OrderPerDayRepository extends JpaRepository<OrderPerDay, Date> {
}
