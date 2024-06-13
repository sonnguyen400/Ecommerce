package com.nhs.individual.Specification.ISpecification;

import com.nhs.individual.Domain.*;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.List;

public interface IProductSpecification extends GeneralSpecification<Product> {
    static Specification<Product> inCategory(Integer categoryId){
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(Product_.CATEGORY_ID),categoryId);
    }
    static Specification<Product> inWarehouse(Integer warehouseId,Integer quantity){
        return (root, criteriaQuery, criteriaBuilder) -> {
            Join<Product, ProductItem> productProductItem=root.join("product_item");
            Join<ProductItem, WarehouseItem> productWarehouseItem=productProductItem.join("product_item_in_warehouse");
            return criteriaBuilder.greaterThan(productWarehouseItem.get(WarehouseItem_.QTY),quantity);
        };
    }
    static Specification<Product> priceLimit(BigDecimal minPrice, BigDecimal maxPrice) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            Join<Product, ProductItem> productProductItem=root.join(Product_.PRODUCT_ITEMS);
            return criteriaBuilder.between(productProductItem.get(ProductItem_.PRICE),minPrice,maxPrice);
        };
    }

    static Specification<Product> relativeName(String name){
        return (root, criteriaQuery, criteriaBuilder) -> {
            String[] extract=name.trim().split("[._ |]");
            Predicate[] predicates=new Predicate[extract.length+1];
            predicates[0]=criteriaBuilder.like(root.get(Product_.NAME),"%"+name.trim()+"%");
            for(int i=1;i<predicates.length;i++){
                predicates[i]=criteriaBuilder.like(root.get(Product_.NAME),"%"+extract[i-1]+"%");
            }
            return criteriaBuilder.and(predicates);
        };
    }
    //    select distinct pia.product_id from product_item pia
//    where not exists((select pi1.variation_option_id
//            from product_item_options pi1
//            where pi1.product_item_id=pia.id
//            and pi1.variation_option_id not in (2,4,16,18)
//      ));
    static Specification<Product> hasOption(List<Integer> optionIds){
        return ((root, cq, cb) -> {
            Subquery<Integer> productIdQuery=cq.subquery(Integer.class);
            Root<ProductItem> productItemRoot=productIdQuery.from(ProductItem.class);
            productIdQuery.select(productItemRoot.get(ProductItem_.PRODUCT_ID)).distinct(true);

            Subquery<Integer> variationQuery=cq.subquery(Integer.class);
            Root<ProductItem> variationRoot=variationQuery.from(ProductItem.class);
            Join<ProductItem,VariationOption> variationJoin=variationRoot.join(ProductItem_.OPTIONS);
            Integer[] arr=new Integer[optionIds.size()];
            variationQuery.select(variationJoin.get(VariationOption_.ID)).where(
                    cb.equal(productItemRoot.get(ProductItem_.ID),variationRoot.get(ProductItem_.ID)),
                    variationJoin.get(VariationOption_.ID).in(optionIds.toArray()).not());
            productIdQuery.where(cb.exists(variationQuery).not());

            return root.get(Product_.ID).in(productIdQuery).not();
        });
    }
}
