package com.nhs.individual.views;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;
import java.time.LocalDate;

@StaticMetamodel(Prospectiveuser.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Prospectiveuser_ {

	
	/**
	 * @see com.nhs.individual.views.Prospectiveuser#totalAmount
	 **/
	public static volatile SingularAttribute<Prospectiveuser, BigDecimal> totalAmount;
	
	/**
	 * @see com.nhs.individual.views.Prospectiveuser#firstname
	 **/
	public static volatile SingularAttribute<Prospectiveuser, String> firstname;
	
	/**
	 * @see com.nhs.individual.views.Prospectiveuser#phoneNumber
	 **/
	public static volatile SingularAttribute<Prospectiveuser, String> phoneNumber;
	
	/**
	 * @see com.nhs.individual.views.Prospectiveuser#gender
	 **/
	public static volatile SingularAttribute<Prospectiveuser, String> gender;
	
	/**
	 * @see com.nhs.individual.views.Prospectiveuser#dateOfBirth
	 **/
	public static volatile SingularAttribute<Prospectiveuser, LocalDate> dateOfBirth;
	
	/**
	 * @see com.nhs.individual.views.Prospectiveuser#id
	 **/
	public static volatile SingularAttribute<Prospectiveuser, Integer> id;
	
	/**
	 * @see com.nhs.individual.views.Prospectiveuser#totalOrders
	 **/
	public static volatile SingularAttribute<Prospectiveuser, Long> totalOrders;
	
	/**
	 * @see com.nhs.individual.views.Prospectiveuser
	 **/
	public static volatile EntityType<Prospectiveuser> class_;
	
	/**
	 * @see com.nhs.individual.views.Prospectiveuser#userId
	 **/
	public static volatile SingularAttribute<Prospectiveuser, Integer> userId;
	
	/**
	 * @see com.nhs.individual.views.Prospectiveuser#email
	 **/
	public static volatile SingularAttribute<Prospectiveuser, String> email;
	
	/**
	 * @see com.nhs.individual.views.Prospectiveuser#picture
	 **/
	public static volatile SingularAttribute<Prospectiveuser, String> picture;
	
	/**
	 * @see com.nhs.individual.views.Prospectiveuser#lastname
	 **/
	public static volatile SingularAttribute<Prospectiveuser, String> lastname;

	public static final String TOTAL_AMOUNT = "totalAmount";
	public static final String FIRSTNAME = "firstname";
	public static final String PHONE_NUMBER = "phoneNumber";
	public static final String GENDER = "gender";
	public static final String DATE_OF_BIRTH = "dateOfBirth";
	public static final String ID = "id";
	public static final String TOTAL_ORDERS = "totalOrders";
	public static final String USER_ID = "userId";
	public static final String EMAIL = "email";
	public static final String PICTURE = "picture";
	public static final String LASTNAME = "lastname";

}

