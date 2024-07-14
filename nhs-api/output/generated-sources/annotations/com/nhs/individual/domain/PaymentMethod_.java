package com.nhs.individual.domain;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(PaymentMethod.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class PaymentMethod_ {

	
	/**
	 * @see com.nhs.individual.domain.PaymentMethod#provider
	 **/
	public static volatile SingularAttribute<PaymentMethod, String> provider;
	
	/**
	 * @see com.nhs.individual.domain.PaymentMethod#name
	 **/
	public static volatile SingularAttribute<PaymentMethod, String> name;
	
	/**
	 * @see com.nhs.individual.domain.PaymentMethod#id
	 **/
	public static volatile SingularAttribute<PaymentMethod, Integer> id;
	
	/**
	 * @see com.nhs.individual.domain.PaymentMethod
	 **/
	public static volatile EntityType<PaymentMethod> class_;

	public static final String PROVIDER = "provider";
	public static final String NAME = "name";
	public static final String ID = "id";

}

