package com.nhs.individual.domain;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(VariationOption.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class VariationOption_ {

	
	/**
	 * @see com.nhs.individual.domain.VariationOption#productItems
	 **/
	public static volatile ListAttribute<VariationOption, ProductItem> productItems;
	
	/**
	 * @see com.nhs.individual.domain.VariationOption#id
	 **/
	public static volatile SingularAttribute<VariationOption, Integer> id;
	
	/**
	 * @see com.nhs.individual.domain.VariationOption
	 **/
	public static volatile EntityType<VariationOption> class_;
	
	/**
	 * @see com.nhs.individual.domain.VariationOption#value
	 **/
	public static volatile SingularAttribute<VariationOption, String> value;
	
	/**
	 * @see com.nhs.individual.domain.VariationOption#variation
	 **/
	public static volatile SingularAttribute<VariationOption, Variation> variation;

	public static final String PRODUCT_ITEMS = "productItems";
	public static final String ID = "id";
	public static final String VALUE = "value";
	public static final String VARIATION = "variation";

}

