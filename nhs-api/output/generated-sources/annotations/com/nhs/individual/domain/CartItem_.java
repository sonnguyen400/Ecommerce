package com.nhs.individual.domain;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(CartItem.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class CartItem_ {

	
	/**
	 * @see com.nhs.individual.domain.CartItem#productItem
	 **/
	public static volatile SingularAttribute<CartItem, ProductItem> productItem;
	
	/**
	 * @see com.nhs.individual.domain.CartItem#qty
	 **/
	public static volatile SingularAttribute<CartItem, Integer> qty;
	
	/**
	 * @see com.nhs.individual.domain.CartItem#id
	 **/
	public static volatile SingularAttribute<CartItem, Integer> id;
	
	/**
	 * @see com.nhs.individual.domain.CartItem
	 **/
	public static volatile EntityType<CartItem> class_;
	
	/**
	 * @see com.nhs.individual.domain.CartItem#user
	 **/
	public static volatile SingularAttribute<CartItem, User> user;

	public static final String PRODUCT_ITEM = "productItem";
	public static final String QTY = "qty";
	public static final String ID = "id";
	public static final String USER = "user";

}

