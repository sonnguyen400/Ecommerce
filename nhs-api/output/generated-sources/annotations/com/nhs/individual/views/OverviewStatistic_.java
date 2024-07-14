package com.nhs.individual.views;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(OverviewStatistic.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class OverviewStatistic_ {

	
	/**
	 * @see com.nhs.individual.views.OverviewStatistic#available_payment_method
	 **/
	public static volatile SingularAttribute<OverviewStatistic, Long> available_payment_method;
	
	/**
	 * @see com.nhs.individual.views.OverviewStatistic#number_of_users
	 **/
	public static volatile SingularAttribute<OverviewStatistic, Long> number_of_users;
	
	/**
	 * @see com.nhs.individual.views.OverviewStatistic#revenue
	 **/
	public static volatile SingularAttribute<OverviewStatistic, Long> revenue;
	
	/**
	 * @see com.nhs.individual.views.OverviewStatistic#number_of_warehouse
	 **/
	public static volatile SingularAttribute<OverviewStatistic, Long> number_of_warehouse;
	
	/**
	 * @see com.nhs.individual.views.OverviewStatistic#number_of_category
	 **/
	public static volatile SingularAttribute<OverviewStatistic, Long> number_of_category;
	
	/**
	 * @see com.nhs.individual.views.OverviewStatistic#number_of_products
	 **/
	public static volatile SingularAttribute<OverviewStatistic, Long> number_of_products;
	
	/**
	 * @see com.nhs.individual.views.OverviewStatistic
	 **/
	public static volatile EntityType<OverviewStatistic> class_;

	public static final String AVAILABLE_PAYMENT_METHOD = "available_payment_method";
	public static final String NUMBER_OF_USERS = "number_of_users";
	public static final String REVENUE = "revenue";
	public static final String NUMBER_OF_WAREHOUSE = "number_of_warehouse";
	public static final String NUMBER_OF_CATEGORY = "number_of_category";
	public static final String NUMBER_OF_PRODUCTS = "number_of_products";

}

