package com.nhs.individual.domain;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;
import java.util.Date;

@StaticMetamodel(ShopOrder.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class ShopOrder_ {

	
	/**
	 * @see com.nhs.individual.domain.ShopOrder#note
	 **/
	public static volatile SingularAttribute<ShopOrder, String> note;
	
	/**
	 * @see com.nhs.individual.domain.ShopOrder#total
	 **/
	public static volatile SingularAttribute<ShopOrder, BigDecimal> total;
	
	/**
	 * @see com.nhs.individual.domain.ShopOrder#address
	 **/
	public static volatile SingularAttribute<ShopOrder, Address> address;
	
	/**
	 * @see com.nhs.individual.domain.ShopOrder#orderLines
	 **/
	public static volatile ListAttribute<ShopOrder, OrderLine> orderLines;
	
	/**
	 * @see com.nhs.individual.domain.ShopOrder#shippingMethod
	 **/
	public static volatile SingularAttribute<ShopOrder, ShippingMethod> shippingMethod;
	
	/**
	 * @see com.nhs.individual.domain.ShopOrder#payment
	 **/
	public static volatile SingularAttribute<ShopOrder, ShopOrderPayment> payment;
	
	/**
	 * @see com.nhs.individual.domain.ShopOrder#id
	 **/
	public static volatile SingularAttribute<ShopOrder, Integer> id;
	
	/**
	 * @see com.nhs.individual.domain.ShopOrder
	 **/
	public static volatile EntityType<ShopOrder> class_;
	
	/**
	 * @see com.nhs.individual.domain.ShopOrder#user
	 **/
	public static volatile SingularAttribute<ShopOrder, User> user;
	
	/**
	 * @see com.nhs.individual.domain.ShopOrder#userId
	 **/
	public static volatile SingularAttribute<ShopOrder, Integer> userId;
	
	/**
	 * @see com.nhs.individual.domain.ShopOrder#orderDate
	 **/
	public static volatile SingularAttribute<ShopOrder, Date> orderDate;
	
	/**
	 * @see com.nhs.individual.domain.ShopOrder#status
	 **/
	public static volatile ListAttribute<ShopOrder, ShopOrderStatus> status;

	public static final String NOTE = "note";
	public static final String TOTAL = "total";
	public static final String ADDRESS = "address";
	public static final String ORDER_LINES = "orderLines";
	public static final String SHIPPING_METHOD = "shippingMethod";
	public static final String PAYMENT = "payment";
	public static final String ID = "id";
	public static final String USER = "user";
	public static final String USER_ID = "userId";
	public static final String ORDER_DATE = "orderDate";
	public static final String STATUS = "status";

}

