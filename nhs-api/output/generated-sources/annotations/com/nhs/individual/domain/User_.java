package com.nhs.individual.domain;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.CollectionAttribute;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.sql.Date;

@StaticMetamodel(User.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class User_ {

	
	/**
	 * @see com.nhs.individual.domain.User#firstname
	 **/
	public static volatile SingularAttribute<User, String> firstname;
	
	/**
	 * @see com.nhs.individual.domain.User#phoneNumber
	 **/
	public static volatile SingularAttribute<User, String> phoneNumber;
	
	/**
	 * @see com.nhs.individual.domain.User#gender
	 **/
	public static volatile SingularAttribute<User, String> gender;
	
	/**
	 * @see com.nhs.individual.domain.User#userAddresses
	 **/
	public static volatile CollectionAttribute<User, UserAddress> userAddresses;
	
	/**
	 * @see com.nhs.individual.domain.User#dateOfBirth
	 **/
	public static volatile SingularAttribute<User, Date> dateOfBirth;
	
	/**
	 * @see com.nhs.individual.domain.User#id
	 **/
	public static volatile SingularAttribute<User, Integer> id;
	
	/**
	 * @see com.nhs.individual.domain.User
	 **/
	public static volatile EntityType<User> class_;
	
	/**
	 * @see com.nhs.individual.domain.User#email
	 **/
	public static volatile SingularAttribute<User, String> email;
	
	/**
	 * @see com.nhs.individual.domain.User#picture
	 **/
	public static volatile SingularAttribute<User, String> picture;
	
	/**
	 * @see com.nhs.individual.domain.User#account
	 **/
	public static volatile SingularAttribute<User, Account> account;
	
	/**
	 * @see com.nhs.individual.domain.User#lastname
	 **/
	public static volatile SingularAttribute<User, String> lastname;

	public static final String FIRSTNAME = "firstname";
	public static final String PHONE_NUMBER = "phoneNumber";
	public static final String GENDER = "gender";
	public static final String USER_ADDRESSES = "userAddresses";
	public static final String DATE_OF_BIRTH = "dateOfBirth";
	public static final String ID = "id";
	public static final String EMAIL = "email";
	public static final String PICTURE = "picture";
	public static final String ACCOUNT = "account";
	public static final String LASTNAME = "lastname";

}

