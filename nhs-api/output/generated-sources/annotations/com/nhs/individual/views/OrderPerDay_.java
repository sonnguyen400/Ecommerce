package com.nhs.individual.views;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.sql.Date;

@StaticMetamodel(OrderPerDay.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class OrderPerDay_ {

	
	/**
	 * @see com.nhs.individual.views.OrderPerDay#date
	 **/
	public static volatile SingularAttribute<OrderPerDay, Date> date;
	
	/**
	 * @see com.nhs.individual.views.OrderPerDay#number_orders_day
	 **/
	public static volatile SingularAttribute<OrderPerDay, Integer> number_orders_day;
	
	/**
	 * @see com.nhs.individual.views.OrderPerDay
	 **/
	public static volatile EntityType<OrderPerDay> class_;
	
	/**
	 * @see com.nhs.individual.views.OrderPerDay#products
	 **/
	public static volatile ListAttribute<OrderPerDay, MostProductOrder> products;

	public static final String DATE = "date";
	public static final String NUMBER_ORDERS_DAY = "number_orders_day";
	public static final String PRODUCTS = "products";

}

