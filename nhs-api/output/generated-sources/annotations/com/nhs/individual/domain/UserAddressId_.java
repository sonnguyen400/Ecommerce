package com.nhs.individual.domain;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EmbeddableType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(UserAddressId.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class UserAddressId_ {

	
	/**
	 * @see com.nhs.individual.domain.UserAddressId
	 **/
	public static volatile EmbeddableType<UserAddressId> class_;
	
	/**
	 * @see com.nhs.individual.domain.UserAddressId#userId
	 **/
	public static volatile SingularAttribute<UserAddressId, Integer> userId;
	
	/**
	 * @see com.nhs.individual.domain.UserAddressId#addressId
	 **/
	public static volatile SingularAttribute<UserAddressId, Integer> addressId;

	public static final String USER_ID = "userId";
	public static final String ADDRESS_ID = "addressId";

}

