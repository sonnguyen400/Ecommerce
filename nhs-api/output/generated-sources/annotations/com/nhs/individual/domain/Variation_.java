package com.nhs.individual.domain;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Variation.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Variation_ {

	
	/**
	 * @see com.nhs.individual.domain.Variation#options
	 **/
	public static volatile ListAttribute<Variation, VariationOption> options;
	
	/**
	 * @see com.nhs.individual.domain.Variation#name
	 **/
	public static volatile SingularAttribute<Variation, String> name;
	
	/**
	 * @see com.nhs.individual.domain.Variation#id
	 **/
	public static volatile SingularAttribute<Variation, Integer> id;
	
	/**
	 * @see com.nhs.individual.domain.Variation
	 **/
	public static volatile EntityType<Variation> class_;

	public static final String OPTIONS = "options";
	public static final String NAME = "name";
	public static final String ID = "id";

}

