package com.nhs.individual.specification.SpecificationImp;

import com.nhs.individual.domain.Product;
import com.nhs.individual.specification.ISpecification.IProductSpecification;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductSpecificationImp{
    @PersistenceContext
    private EntityManager entityManager;
    public List<Product> findProductAdvance(String name,Integer categoryId, List<Integer> optionIds, BigDecimal[] priceRange, Integer page, Integer size){
        CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery=criteriaBuilder.createQuery(Product.class);
        Root<Product> rootProduct=criteriaQuery.from(Product.class);
        List<Predicate> predicateList=new ArrayList<>();

        if(categoryId!=null) predicateList.add(IProductSpecification.inCategory(categoryId).toPredicate(rootProduct,criteriaQuery,criteriaBuilder));

        if(optionIds!=null) {
            predicateList.add(IProductSpecification.hasOption(optionIds).toPredicate(rootProduct,criteriaQuery,criteriaBuilder));
        }
        if(name!=null){
            predicateList.add(IProductSpecification.relativeName(name).toPredicate(rootProduct,criteriaQuery,criteriaBuilder));
        }

        if(priceRange!=null&&priceRange.length==2) predicateList.add(IProductSpecification.priceLimit(priceRange[0],priceRange[1]).toPredicate(rootProduct,criteriaQuery,criteriaBuilder));
        Predicate[] predicates=new Predicate[predicateList.size()];

        criteriaQuery.where(criteriaBuilder.or(predicateList.toArray(predicates)));
        return entityManager.createQuery(criteriaQuery).setFirstResult(page*size).setMaxResults(size).getResultList();
    }

}
