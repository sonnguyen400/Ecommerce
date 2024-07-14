package com.nhs.individual.domain;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.Instant;

@StaticMetamodel(ShopOrderPayment.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class ShopOrderPayment_ {

	
	/**
	 * @see com.nhs.individual.domain.ShopOrderPayment#orderNumber
	 **/
	public static volatile SingularAttribute<ShopOrderPayment, String> orderNumber;
	
	/**
	 * @see com.nhs.individual.domain.ShopOrderPayment#updateAt
	 **/
	public static volatile SingularAttribute<ShopOrderPayment, Instant> updateAt;
	
	/**
	 * @see com.nhs.individual.domain.ShopOrderPayment#id
	 **/
	public static volatile SingularAttribute<ShopOrderPayment, Integer> id;
	
	/**
	 * @see com.nhs.individual.domain.ShopOrderPayment#type
	 **/
	public static volatile SingularAttribute<ShopOrderPayment, Payment> type;
	
	/**
	 * @see com.nhs.individual.domain.ShopOrderPayment
	 **/
	public static volatile EntityType<ShopOrderPayment> class_;
	
	/**
	 * @see com.nhs.individual.domain.ShopOrderPayment#order
	 **/
	public static volatile SingularAttribute<ShopOrderPayment, ShopOrder> order;
	
	/**
	 * @see com.nhs.individual.domain.ShopOrderPayment#status
	 **/
	public static volatile SingularAttribute<ShopOrderPayment, String> status;

	public static final String ORDER_NUMBER = "orderNumber";
	public static final String UPDATE_AT = "updateAt";
	public static final String ID = "id";
	public static final String TYPE = "type";
	public static final String ORDER = "order";
	public static final String STATUS = "status";

}

