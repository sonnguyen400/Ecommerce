package com.nhs.individual.domain;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;

@StaticMetamodel(OrderLine.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class OrderLine_ {

	
	/**
	 * @see com.nhs.individual.domain.OrderLine#productItem
	 **/
	public static volatile SingularAttribute<OrderLine, ProductItem> productItem;
	
	/**
	 * @see com.nhs.individual.domain.OrderLine#total
	 **/
	public static volatile SingularAttribute<OrderLine, BigDecimal> total;
	
	/**
	 * @see com.nhs.individual.domain.OrderLine#qty
	 **/
	public static volatile SingularAttribute<OrderLine, Integer> qty;
	
	/**
	 * @see com.nhs.individual.domain.OrderLine#id
	 **/
	public static volatile SingularAttribute<OrderLine, Integer> id;
	
	/**
	 * @see com.nhs.individual.domain.OrderLine
	 **/
	public static volatile EntityType<OrderLine> class_;
	
	/**
	 * @see com.nhs.individual.domain.OrderLine#order
	 **/
	public static volatile SingularAttribute<OrderLine, ShopOrder> order;

	public static final String PRODUCT_ITEM = "productItem";
	public static final String TOTAL = "total";
	public static final String QTY = "qty";
	public static final String ID = "id";
	public static final String ORDER = "order";

}

