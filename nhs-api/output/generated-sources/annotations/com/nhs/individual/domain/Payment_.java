package com.nhs.individual.domain;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Payment.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Payment_ {

	
	/**
	 * @see com.nhs.individual.domain.Payment#provider
	 **/
	public static volatile SingularAttribute<Payment, String> provider;
	
	/**
	 * @see com.nhs.individual.domain.Payment#name
	 **/
	public static volatile SingularAttribute<Payment, String> name;
	
	/**
	 * @see com.nhs.individual.domain.Payment#id
	 **/
	public static volatile SingularAttribute<Payment, Integer> id;
	
	/**
	 * @see com.nhs.individual.domain.Payment
	 **/
	public static volatile EntityType<Payment> class_;

	public static final String PROVIDER = "provider";
	public static final String NAME = "name";
	public static final String ID = "id";

}

