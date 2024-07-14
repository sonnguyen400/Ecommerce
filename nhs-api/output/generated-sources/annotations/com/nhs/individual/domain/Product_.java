package com.nhs.individual.domain;

import com.nhs.individual.constant.ProductStatus;
import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Product.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Product_ {

	
	/**
	 * @see com.nhs.individual.domain.Product#productItems
	 **/
	public static volatile ListAttribute<Product, ProductItem> productItems;
	
	/**
	 * @see com.nhs.individual.domain.Product#name
	 **/
	public static volatile SingularAttribute<Product, String> name;
	
	/**
	 * @see com.nhs.individual.domain.Product#description
	 **/
	public static volatile SingularAttribute<Product, String> description;
	
	/**
	 * @see com.nhs.individual.domain.Product#id
	 **/
	public static volatile SingularAttribute<Product, Integer> id;
	
	/**
	 * @see com.nhs.individual.domain.Product#category
	 **/
	public static volatile SingularAttribute<Product, Category> category;
	
	/**
	 * @see com.nhs.individual.domain.Product
	 **/
	public static volatile EntityType<Product> class_;
	
	/**
	 * @see com.nhs.individual.domain.Product#categoryId
	 **/
	public static volatile SingularAttribute<Product, Integer> categoryId;
	
	/**
	 * @see com.nhs.individual.domain.Product#picture
	 **/
	public static volatile SingularAttribute<Product, String> picture;
	
	/**
	 * @see com.nhs.individual.domain.Product#status
	 **/
	public static volatile SingularAttribute<Product, ProductStatus> status;
	
	/**
	 * @see com.nhs.individual.domain.Product#manufacturer
	 **/
	public static volatile SingularAttribute<Product, String> manufacturer;

	public static final String PRODUCT_ITEMS = "productItems";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String ID = "id";
	public static final String CATEGORY = "category";
	public static final String CATEGORY_ID = "categoryId";
	public static final String PICTURE = "picture";
	public static final String STATUS = "status";
	public static final String MANUFACTURER = "manufacturer";

}

