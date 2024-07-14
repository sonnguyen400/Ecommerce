package com.nhs.individual.domain.EmbeddedId;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EmbeddableType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ProductItemInWarehouseId.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class ProductItemInWarehouseId_ {

	
	/**
	 * @see com.nhs.individual.domain.EmbeddedId.ProductItemInWarehouseId#warehouseId
	 **/
	public static volatile SingularAttribute<ProductItemInWarehouseId, Integer> warehouseId;
	
	/**
	 * @see com.nhs.individual.domain.EmbeddedId.ProductItemInWarehouseId#productItemId
	 **/
	public static volatile SingularAttribute<ProductItemInWarehouseId, Integer> productItemId;
	
	/**
	 * @see com.nhs.individual.domain.EmbeddedId.ProductItemInWarehouseId
	 **/
	public static volatile EmbeddableType<ProductItemInWarehouseId> class_;

	public static final String WAREHOUSE_ID = "warehouseId";
	public static final String PRODUCT_ITEM_ID = "productItemId";

}

