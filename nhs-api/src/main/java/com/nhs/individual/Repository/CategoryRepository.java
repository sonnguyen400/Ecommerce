package com.nhs.individual.Repository;

import com.nhs.individual.Domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
    public Collection<Category> findAllByParentCategoryId(Integer id);
}
