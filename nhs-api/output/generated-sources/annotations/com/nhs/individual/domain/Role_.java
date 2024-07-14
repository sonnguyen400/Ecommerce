package com.nhs.individual.domain;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.CollectionAttribute;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Role.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Role_ {

	
	/**
	 * @see com.nhs.individual.domain.Role#name
	 **/
	public static volatile SingularAttribute<Role, String> name;
	
	/**
	 * @see com.nhs.individual.domain.Role#id
	 **/
	public static volatile SingularAttribute<Role, Integer> id;
	
	/**
	 * @see com.nhs.individual.domain.Role#accounts
	 **/
	public static volatile CollectionAttribute<Role, Account> accounts;
	
	/**
	 * @see com.nhs.individual.domain.Role
	 **/
	public static volatile EntityType<Role> class_;

	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String ACCOUNTS = "accounts";

}

