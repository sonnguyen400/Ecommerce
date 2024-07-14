package com.nhs.individual.domain;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.Instant;

@StaticMetamodel(RefreshToken.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class RefreshToken_ {

	
	/**
	 * @see com.nhs.individual.domain.RefreshToken#expiredDate
	 **/
	public static volatile SingularAttribute<RefreshToken, Instant> expiredDate;
	
	/**
	 * @see com.nhs.individual.domain.RefreshToken#id
	 **/
	public static volatile SingularAttribute<RefreshToken, Integer> id;
	
	/**
	 * @see com.nhs.individual.domain.RefreshToken
	 **/
	public static volatile EntityType<RefreshToken> class_;
	
	/**
	 * @see com.nhs.individual.domain.RefreshToken#account
	 **/
	public static volatile SingularAttribute<RefreshToken, Account> account;
	
	/**
	 * @see com.nhs.individual.domain.RefreshToken#token
	 **/
	public static volatile SingularAttribute<RefreshToken, String> token;

	public static final String EXPIRED_DATE = "expiredDate";
	public static final String ID = "id";
	public static final String ACCOUNT = "account";
	public static final String TOKEN = "token";

}

