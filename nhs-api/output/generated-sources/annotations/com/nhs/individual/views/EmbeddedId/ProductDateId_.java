package com.nhs.individual.views.EmbeddedId;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EmbeddableType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.sql.Date;

@StaticMetamodel(ProductDateId.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class ProductDateId_ {

	
	/**
	 * @see com.nhs.individual.views.EmbeddedId.ProductDateId#date
	 **/
	public static volatile SingularAttribute<ProductDateId, Date> date;
	
	/**
	 * @see com.nhs.individual.views.EmbeddedId.ProductDateId#productId
	 **/
	public static volatile SingularAttribute<ProductDateId, Integer> productId;
	
	/**
	 * @see com.nhs.individual.views.EmbeddedId.ProductDateId
	 **/
	public static volatile EmbeddableType<ProductDateId> class_;

	public static final String DATE = "date";
	public static final String PRODUCT_ID = "productId";

}

