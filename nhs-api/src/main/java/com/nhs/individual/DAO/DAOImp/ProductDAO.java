package com.nhs.individual.DAO.DAOImp;

import com.nhs.individual.Domain.Product;
import com.nhs.individual.Domain.Variation;
import com.nhs.individual.Domain.VariationOption;
import com.nhs.individual.Domain.Variation_;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public class ProductDAO {
    @PersistenceContext
    private EntityManager entityManager;
    public Collection<Product> findProducts(){
        CriteriaBuilder builder= entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query=builder.createQuery(Product.class);
        Subquery<VariationOption> subquery= query.subquery(VariationOption.class);

        Root<VariationOption> variationOptionRoot=subquery.from(VariationOption.class);
        Join<VariationOption, Variation> variationOptionJoin= variationOptionRoot.join("variation");
        subquery.where(builder.equal(variationOptionJoin.get(Variation_.NAME),))
        Root<Product> productRoot= query.from(Product.class);

        return List.of();
    }

}
