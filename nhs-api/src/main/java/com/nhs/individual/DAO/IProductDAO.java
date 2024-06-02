package com.nhs.individual.DAO;

import com.nhs.individual.Constant.OrderStatus;
import com.nhs.individual.Domain.*;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.function.IntFunction;

@Repository
public interface IProductDAO extends Specification<Product> {
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
    static Specification<Product> priceLimit(BigDecimal from,BigDecimal to) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            Join<Product, ProductItem> productProductItem=root.join("product_item");
            return criteriaBuilder.between(productProductItem.get(ProductItem_.PRICE),from,to);
        };
    }
    static Specification<Product> hasVariation(Map<String,String> variation) {
        return (root, criteriaQuery, criteriaBuilder) -> {
//
            Subquery<Integer> productIds=criteriaQuery.subquery(Integer.class);
            Root<ProductItem> productItemRoot=productIds.from(ProductItem.class);
            Join<ProductItem,VariationOption> productOptionsJoin=productItemRoot.join(ProductItem_.OPTIONS);
            Join<VariationOption,Variation> variationJoin=productOptionsJoin.join(VariationOption_.VARIATION);
            List<Predicate> predicates= variation.entrySet().stream().map(entry->
                    {
                        System.out.println(entry.getKey()+"="+entry.getValue());
                        return criteriaBuilder.and(
                                criteriaBuilder.equal(productOptionsJoin.get(VariationOption_.VALUE), entry.getValue())
                                , criteriaBuilder.equal(variationJoin.get(Variation_.NAME), entry.getKey()));
                    }).toList();
            Predicate[] predicates1 = new Predicate[predicates.size()];
            for(int i = 0; i < predicates.size(); i++){
                predicates1[i] = predicates.get(i);
            }
//            Predicate predicate1=criteriaBuilder.and(criteriaBuilder.equal(productOptionsJoin.get(VariationOption_.VALUE),"6"),
//                    criteriaBuilder.equal(variationJoin.get(Variation_.NAME),"Ram"));
            productIds.select(productItemRoot.get(ProductItem_.PRODUCT_ID)).where(predicates1);
            return criteriaBuilder.in(root.get(Product_.ID)).value(productIds);


        };
    }
    static  Specification<Product> hasVariationClone(Map<String,String> variation){
        return (root, cq, cb) -> {
            Subquery<Integer> productOptionIds=cq.subquery(Integer.class);
            Root<ProductItem> productItemRoot=productOptionIds.from(ProductItem.class);
            Join<ProductItem,VariationOption> productOptionsJoin=productItemRoot.join(ProductItem_.OPTIONS);
            productOptionIds.select(productOptionsJoin.get(VariationOption_.ID)).distinct(true);
            productOptionIds.where(cb.equal(root.get(Product_.ID),productItemRoot.get(ProductItem_.PRODUCT_ID)));




            Subquery<Integer> variationOptionIds=cq.subquery(Integer.class);
            Root<VariationOption> optionRoot=variationOptionIds.from(VariationOption.class);
            Join<VariationOption,Variation> variationJoin=optionRoot.join(VariationOption_.VARIATION);
            List<Predicate> predicateList= variation.entrySet().stream().map(entry->
                    cb.and(
                            cb.equal(optionRoot.get(VariationOption_.VALUE), entry.getValue())
                            , cb.equal(variationJoin.get(Variation_.NAME), entry.getKey()))).toList();
            Predicate[] predicatesArr = new Predicate[predicateList.size()];
            for(int i = 0; i < predicateList.size(); i++){
                predicatesArr[i] = predicateList.get(i);
            }
            variationOptionIds.select(optionRoot.get(VariationOption_.ID)).where(cb.or(predicatesArr));

            Subquery<Integer> variationOptionQr=cq.subquery(Integer.class);
            Root<VariationOption> variationOptionRoot=variationOptionQr.from(VariationOption.class);
            variationOptionQr.select(variationOptionRoot.get(VariationOption_.ID))
                    .where(cb.and(variationOptionRoot.get(VariationOption_.ID).in(variationOptionIds),
                            variationOptionRoot.get(VariationOption_.ID).in(productOptionIds).not()));
            return cb.exists(variationOptionQr).not();
        };
    }
}
