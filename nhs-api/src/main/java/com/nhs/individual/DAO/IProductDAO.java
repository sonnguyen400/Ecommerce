package com.nhs.individual.DAO;

import com.nhs.individual.Constant.OrderStatus;
import com.nhs.individual.Domain.*;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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
//    static Specification<Product> hasVariation(Map<String,String> variation) {
//        return (root, criteriaQuery, criteriaBuilder) -> {
//            Subquery<Integer> subquery=criteriaQuery.subquery(Integer.class);
//            Root<VariationOption> variationOptionRoot=subquery.from(VariationOption.class);
//            Join<VariationOption,Variation> optionVariationJoin= variationOptionRoot.join("variation");
//            Predicate[] predicate= (Predicate[]) variation.entrySet().stream().map(entry->
//                    criteriaBuilder.and(
//                            criteriaBuilder.equal(variationOptionRoot.get(VariationOption_.VALUE),entry.getValue())
//                            ,criteriaBuilder.equal(optionVariationJoin.get(Variation_.NAME),entry.getKey())
//                    )).toArray();
//            subquery.select(variationOptionRoot.get(Variation_.ID)).where(predicate);
//
//            Join<Product,ProductItem> productItemJoin= root.join("product_item");
//            Join<Product,ProductItem>
//        };
//    }
}
