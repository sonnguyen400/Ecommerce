package com.nhs.individual.Repository;

import com.nhs.individual.Domain.Variation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VariationRepository extends JpaRepository<Variation,Integer> {
}
