package com.nhs.individual.domain;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.CollectionAttribute;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Warehouse.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Warehouse_ {

	
	/**
	 * @see com.nhs.individual.domain.Warehouse#address
	 **/
	public static volatile SingularAttribute<Warehouse, Address> address;
	
	/**
	 * @see com.nhs.individual.domain.Warehouse#name
	 **/
	public static volatile SingularAttribute<Warehouse, String> name;
	
	/**
	 * @see com.nhs.individual.domain.Warehouse#id
	 **/
	public static volatile SingularAttribute<Warehouse, Integer> id;
	
	/**
	 * @see com.nhs.individual.domain.Warehouse#detail
	 **/
	public static volatile SingularAttribute<Warehouse, String> detail;
	
	/**
	 * @see com.nhs.individual.domain.Warehouse
	 **/
	public static volatile EntityType<Warehouse> class_;
	
	/**
	 * @see com.nhs.individual.domain.Warehouse#warehouseItems
	 **/
	public static volatile CollectionAttribute<Warehouse, WarehouseItem> warehouseItems;

	public static final String ADDRESS = "address";
	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String DETAIL = "detail";
	public static final String WAREHOUSE_ITEMS = "warehouseItems";

}

