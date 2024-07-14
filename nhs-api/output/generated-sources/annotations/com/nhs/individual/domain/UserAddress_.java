package com.nhs.individual.domain;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(UserAddress.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class UserAddress_ {

	
	/**
	 * @see com.nhs.individual.domain.UserAddress#isDefault
	 **/
	public static volatile SingularAttribute<UserAddress, Boolean> isDefault;
	
	/**
	 * @see com.nhs.individual.domain.UserAddress#address
	 **/
	public static volatile SingularAttribute<UserAddress, Address> address;
	
	/**
	 * @see com.nhs.individual.domain.UserAddress#phoneNumber
	 **/
	public static volatile SingularAttribute<UserAddress, String> phoneNumber;
	
	/**
	 * @see com.nhs.individual.domain.UserAddress#isBusiness
	 **/
	public static volatile SingularAttribute<UserAddress, Boolean> isBusiness;
	
	/**
	 * @see com.nhs.individual.domain.UserAddress#id
	 **/
	public static volatile SingularAttribute<UserAddress, UserAddressId> id;
	
	/**
	 * @see com.nhs.individual.domain.UserAddress
	 **/
	public static volatile EntityType<UserAddress> class_;
	
	/**
	 * @see com.nhs.individual.domain.UserAddress#user
	 **/
	public static volatile SingularAttribute<UserAddress, User> user;

	public static final String IS_DEFAULT = "isDefault";
	public static final String ADDRESS = "address";
	public static final String PHONE_NUMBER = "phoneNumber";
	public static final String IS_BUSINESS = "isBusiness";
	public static final String ID = "id";
	public static final String USER = "user";

}

