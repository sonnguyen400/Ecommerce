package com.nhs.individual.DAO.DAOImp;

import com.nhs.individual.DAO.IProductDAO;
import com.nhs.individual.Domain.Product;
import com.nhs.individual.Domain.Variation;
import com.nhs.individual.Domain.VariationOption;
import com.nhs.individual.Domain.Variation_;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Repository
public class ProductDAO  {
    @PersistenceContext
    private EntityManager entityManager;
    public List<Product> findProducts(Integer innteger){
        CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery=criteriaBuilder.createQuery(Product.class);
        Root<Product> rootProduct=criteriaQuery.from(Product.class);
        criteriaQuery.where(IProductDAO.inCategory(innteger).toPredicate(rootProduct,criteriaQuery,criteriaBuilder));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
    public List<Product> findByVariation(Map<String,String> stringStringMap,Integer categoryId){
        CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery=criteriaBuilder.createQuery(Product.class);
        Root<Product> rootProduct=criteriaQuery.from(Product.class);
        criteriaQuery.where(IProductDAO.hasVariationClone(stringStringMap).toPredicate(rootProduct,criteriaQuery,criteriaBuilder));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

}
