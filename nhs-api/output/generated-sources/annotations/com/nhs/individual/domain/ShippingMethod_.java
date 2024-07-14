package com.nhs.individual.domain;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;

@StaticMetamodel(ShippingMethod.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class ShippingMethod_ {

	
	/**
	 * @see com.nhs.individual.domain.ShippingMethod#price
	 **/
	public static volatile SingularAttribute<ShippingMethod, BigDecimal> price;
	
	/**
	 * @see com.nhs.individual.domain.ShippingMethod#name
	 **/
	public static volatile SingularAttribute<ShippingMethod, String> name;
	
	/**
	 * @see com.nhs.individual.domain.ShippingMethod#id
	 **/
	public static volatile SingularAttribute<ShippingMethod, Integer> id;
	
	/**
	 * @see com.nhs.individual.domain.ShippingMethod
	 **/
	public static volatile EntityType<ShippingMethod> class_;

	public static final String PRICE = "price";
	public static final String NAME = "name";
	public static final String ID = "id";

}

