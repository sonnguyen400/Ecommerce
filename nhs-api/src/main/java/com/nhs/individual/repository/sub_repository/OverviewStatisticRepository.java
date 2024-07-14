package com.nhs.individual.repository.sub_repository;

import com.nhs.individual.views.OrderPerDay;
import com.nhs.individual.views.OverviewStatistic;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OverviewStatisticRepository extends JpaRepository<OverviewStatistic,Long> {

}
