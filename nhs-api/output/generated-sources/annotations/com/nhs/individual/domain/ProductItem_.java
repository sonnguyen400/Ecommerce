package com.nhs.individual.domain;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.CollectionAttribute;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;

@StaticMetamodel(ProductItem.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class ProductItem_ {

	
	/**
	 * @see com.nhs.individual.domain.ProductItem#product
	 **/
	public static volatile SingularAttribute<ProductItem, Product> product;
	
	/**
	 * @see com.nhs.individual.domain.ProductItem#productId
	 **/
	public static volatile SingularAttribute<ProductItem, Integer> productId;
	
	/**
	 * @see com.nhs.individual.domain.ProductItem#originalPrice
	 **/
	public static volatile SingularAttribute<ProductItem, BigDecimal> originalPrice;
	
	/**
	 * @see com.nhs.individual.domain.ProductItem#price
	 **/
	public static volatile SingularAttribute<ProductItem, BigDecimal> price;
	
	/**
	 * @see com.nhs.individual.domain.ProductItem#options
	 **/
	public static volatile CollectionAttribute<ProductItem, VariationOption> options;
	
	/**
	 * @see com.nhs.individual.domain.ProductItem#warehouses
	 **/
	public static volatile ListAttribute<ProductItem, WarehouseItem> warehouses;
	
	/**
	 * @see com.nhs.individual.domain.ProductItem#id
	 **/
	public static volatile SingularAttribute<ProductItem, Integer> id;
	
	/**
	 * @see com.nhs.individual.domain.ProductItem
	 **/
	public static volatile EntityType<ProductItem> class_;
	
	/**
	 * @see com.nhs.individual.domain.ProductItem#picture
	 **/
	public static volatile SingularAttribute<ProductItem, String> picture;

	public static final String PRODUCT = "product";
	public static final String PRODUCT_ID = "productId";
	public static final String ORIGINAL_PRICE = "originalPrice";
	public static final String PRICE = "price";
	public static final String OPTIONS = "options";
	public static final String WAREHOUSES = "warehouses";
	public static final String ID = "id";
	public static final String PICTURE = "picture";

}

