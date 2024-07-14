package com.nhs.individual.domain;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Address.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Address_ {

	
	/**
	 * @see com.nhs.individual.domain.Address#country
	 **/
	public static volatile SingularAttribute<Address, Country> country;
	
	/**
	 * @see com.nhs.individual.domain.Address#city
	 **/
	public static volatile SingularAttribute<Address, String> city;
	
	/**
	 * @see com.nhs.individual.domain.Address#postalCode
	 **/
	public static volatile SingularAttribute<Address, String> postalCode;
	
	/**
	 * @see com.nhs.individual.domain.Address#addressLine1
	 **/
	public static volatile SingularAttribute<Address, String> addressLine1;
	
	/**
	 * @see com.nhs.individual.domain.Address#addressLine2
	 **/
	public static volatile SingularAttribute<Address, String> addressLine2;
	
	/**
	 * @see com.nhs.individual.domain.Address#id
	 **/
	public static volatile SingularAttribute<Address, Integer> id;
	
	/**
	 * @see com.nhs.individual.domain.Address#region
	 **/
	public static volatile SingularAttribute<Address, String> region;
	
	/**
	 * @see com.nhs.individual.domain.Address#businessAddress
	 **/
	public static volatile SingularAttribute<Address, Byte> businessAddress;
	
	/**
	 * @see com.nhs.individual.domain.Address
	 **/
	public static volatile EntityType<Address> class_;
	
	/**
	 * @see com.nhs.individual.domain.Address#building
	 **/
	public static volatile SingularAttribute<Address, String> building;

	public static final String COUNTRY = "country";
	public static final String CITY = "city";
	public static final String POSTAL_CODE = "postalCode";
	public static final String ADDRESS_LINE1 = "addressLine1";
	public static final String ADDRESS_LINE2 = "addressLine2";
	public static final String ID = "id";
	public static final String REGION = "region";
	public static final String BUSINESS_ADDRESS = "businessAddress";
	public static final String BUILDING = "building";

}

