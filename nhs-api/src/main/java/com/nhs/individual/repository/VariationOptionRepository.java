package com.nhs.individual.repository;

import com.nhs.individual.Domain.VariationOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VariationOptionRepository extends JpaRepository<VariationOption,Integer> {
}
