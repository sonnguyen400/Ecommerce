package com.nhs.individual.domain;

import com.nhs.individual.constant.AccountProvider;
import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.CollectionAttribute;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Account.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Account_ {

	
	/**
	 * @see com.nhs.individual.domain.Account#password
	 **/
	public static volatile SingularAttribute<Account, String> password;
	
	/**
	 * @see com.nhs.individual.domain.Account#provider
	 **/
	public static volatile SingularAttribute<Account, AccountProvider> provider;
	
	/**
	 * @see com.nhs.individual.domain.Account#roles
	 **/
	public static volatile CollectionAttribute<Account, Role> roles;
	
	/**
	 * @see com.nhs.individual.domain.Account#id
	 **/
	public static volatile SingularAttribute<Account, Integer> id;
	
	/**
	 * @see com.nhs.individual.domain.Account
	 **/
	public static volatile EntityType<Account> class_;
	
	/**
	 * @see com.nhs.individual.domain.Account#user
	 **/
	public static volatile SingularAttribute<Account, User> user;
	
	/**
	 * @see com.nhs.individual.domain.Account#username
	 **/
	public static volatile SingularAttribute<Account, String> username;
	
	/**
	 * @see com.nhs.individual.domain.Account#status
	 **/
	public static volatile SingularAttribute<Account, Integer> status;
	
	/**
	 * @see com.nhs.individual.domain.Account#refreshToken
	 **/
	public static volatile CollectionAttribute<Account, RefreshToken> refreshToken;

	public static final String PASSWORD = "password";
	public static final String PROVIDER = "provider";
	public static final String ROLES = "roles";
	public static final String ID = "id";
	public static final String USER = "user";
	public static final String USERNAME = "username";
	public static final String STATUS = "status";
	public static final String REFRESH_TOKEN = "refreshToken";

}

