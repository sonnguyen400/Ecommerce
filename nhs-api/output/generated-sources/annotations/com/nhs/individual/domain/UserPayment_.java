package com.nhs.individual.domain;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.Instant;

@StaticMetamodel(UserPayment.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class UserPayment_ {

	
	/**
	 * @see com.nhs.individual.domain.UserPayment#expiryDate
	 **/
	public static volatile SingularAttribute<UserPayment, Instant> expiryDate;
	
	/**
	 * @see com.nhs.individual.domain.UserPayment#isDefault
	 **/
	public static volatile SingularAttribute<UserPayment, Byte> isDefault;
	
	/**
	 * @see com.nhs.individual.domain.UserPayment#id
	 **/
	public static volatile SingularAttribute<UserPayment, Integer> id;
	
	/**
	 * @see com.nhs.individual.domain.UserPayment#accountNumber
	 **/
	public static volatile SingularAttribute<UserPayment, Integer> accountNumber;
	
	/**
	 * @see com.nhs.individual.domain.UserPayment
	 **/
	public static volatile EntityType<UserPayment> class_;
	
	/**
	 * @see com.nhs.individual.domain.UserPayment#user
	 **/
	public static volatile SingularAttribute<UserPayment, User> user;
	
	/**
	 * @see com.nhs.individual.domain.UserPayment#paymentType
	 **/
	public static volatile SingularAttribute<UserPayment, PaymentMethod> paymentType;

	public static final String EXPIRY_DATE = "expiryDate";
	public static final String IS_DEFAULT = "isDefault";
	public static final String ID = "id";
	public static final String ACCOUNT_NUMBER = "accountNumber";
	public static final String USER = "user";
	public static final String PAYMENT_TYPE = "paymentType";

}

