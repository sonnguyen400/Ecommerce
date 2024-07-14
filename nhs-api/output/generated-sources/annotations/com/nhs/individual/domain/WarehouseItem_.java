package com.nhs.individual.domain;

import com.nhs.individual.domain.EmbeddedId.ProductItemInWarehouseId;
import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(WarehouseItem.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class WarehouseItem_ {

	
	/**
	 * @see com.nhs.individual.domain.WarehouseItem#productItem
	 **/
	public static volatile SingularAttribute<WarehouseItem, ProductItem> productItem;
	
	/**
	 * @see com.nhs.individual.domain.WarehouseItem#warehouseId
	 **/
	public static volatile SingularAttribute<WarehouseItem, Integer> warehouseId;
	
	/**
	 * @see com.nhs.individual.domain.WarehouseItem#qty
	 **/
	public static volatile SingularAttribute<WarehouseItem, Integer> qty;
	
	/**
	 * @see com.nhs.individual.domain.WarehouseItem#id
	 **/
	public static volatile SingularAttribute<WarehouseItem, ProductItemInWarehouseId> id;
	
	/**
	 * @see com.nhs.individual.domain.WarehouseItem#warehouse
	 **/
	public static volatile SingularAttribute<WarehouseItem, Warehouse> warehouse;
	
	/**
	 * @see com.nhs.individual.domain.WarehouseItem#SKU
	 **/
	public static volatile SingularAttribute<WarehouseItem, String> SKU;
	
	/**
	 * @see com.nhs.individual.domain.WarehouseItem
	 **/
	public static volatile EntityType<WarehouseItem> class_;

	public static final String PRODUCT_ITEM = "productItem";
	public static final String WAREHOUSE_ID = "warehouseId";
	public static final String QTY = "qty";
	public static final String ID = "id";
	public static final String WAREHOUSE = "warehouse";
	public static final String S_KU = "SKU";

}

