package com.nhs.individual.views;

import com.nhs.individual.domain.Product;
import com.nhs.individual.views.EmbeddedId.ProductDateId;
import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(MostProductOrder.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class MostProductOrder_ {

	
	/**
	 * @see com.nhs.individual.views.MostProductOrder#product
	 **/
	public static volatile SingularAttribute<MostProductOrder, Product> product;
	
	/**
	 * @see com.nhs.individual.views.MostProductOrder#orderPerDay
	 **/
	public static volatile SingularAttribute<MostProductOrder, OrderPerDay> orderPerDay;
	
	/**
	 * @see com.nhs.individual.views.MostProductOrder#qty
	 **/
	public static volatile SingularAttribute<MostProductOrder, Integer> qty;
	
	/**
	 * @see com.nhs.individual.views.MostProductOrder#id
	 **/
	public static volatile SingularAttribute<MostProductOrder, ProductDateId> id;
	
	/**
	 * @see com.nhs.individual.views.MostProductOrder
	 **/
	public static volatile EntityType<MostProductOrder> class_;

	public static final String PRODUCT = "product";
	public static final String ORDER_PER_DAY = "orderPerDay";
	public static final String QTY = "qty";
	public static final String ID = "id";

}

