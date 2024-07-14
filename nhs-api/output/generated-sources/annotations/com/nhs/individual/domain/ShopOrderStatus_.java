package com.nhs.individual.domain;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.sql.Date;

@StaticMetamodel(ShopOrderStatus.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class ShopOrderStatus_ {

	
	/**
	 * @see com.nhs.individual.domain.ShopOrderStatus#note
	 **/
	public static volatile SingularAttribute<ShopOrderStatus, String> note;
	
	/**
	 * @see com.nhs.individual.domain.ShopOrderStatus#shopOrderId
	 **/
	public static volatile SingularAttribute<ShopOrderStatus, Integer> shopOrderId;
	
	/**
	 * @see com.nhs.individual.domain.ShopOrderStatus#updateAt
	 **/
	public static volatile SingularAttribute<ShopOrderStatus, Date> updateAt;
	
	/**
	 * @see com.nhs.individual.domain.ShopOrderStatus#id
	 **/
	public static volatile SingularAttribute<ShopOrderStatus, Integer> id;
	
	/**
	 * @see com.nhs.individual.domain.ShopOrderStatus#detail
	 **/
	public static volatile SingularAttribute<ShopOrderStatus, String> detail;
	
	/**
	 * @see com.nhs.individual.domain.ShopOrderStatus
	 **/
	public static volatile EntityType<ShopOrderStatus> class_;
	
	/**
	 * @see com.nhs.individual.domain.ShopOrderStatus#order
	 **/
	public static volatile SingularAttribute<ShopOrderStatus, ShopOrder> order;
	
	/**
	 * @see com.nhs.individual.domain.ShopOrderStatus#status
	 **/
	public static volatile SingularAttribute<ShopOrderStatus, Integer> status;

	public static final String NOTE = "note";
	public static final String SHOP_ORDER_ID = "shopOrderId";
	public static final String UPDATE_AT = "updateAt";
	public static final String ID = "id";
	public static final String DETAIL = "detail";
	public static final String ORDER = "order";
	public static final String STATUS = "status";

}

