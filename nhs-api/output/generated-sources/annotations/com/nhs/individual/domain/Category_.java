package com.nhs.individual.domain;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Category.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Category_ {

	
	/**
	 * @see com.nhs.individual.domain.Category#parent
	 **/
	public static volatile SingularAttribute<Category, Category> parent;
	
	/**
	 * @see com.nhs.individual.domain.Category#children
	 **/
	public static volatile ListAttribute<Category, Category> children;
	
	/**
	 * @see com.nhs.individual.domain.Category#name
	 **/
	public static volatile SingularAttribute<Category, String> name;
	
	/**
	 * @see com.nhs.individual.domain.Category#description
	 **/
	public static volatile SingularAttribute<Category, String> description;
	
	/**
	 * @see com.nhs.individual.domain.Category#id
	 **/
	public static volatile SingularAttribute<Category, Integer> id;
	
	/**
	 * @see com.nhs.individual.domain.Category
	 **/
	public static volatile EntityType<Category> class_;
	
	/**
	 * @see com.nhs.individual.domain.Category#products
	 **/
	public static volatile ListAttribute<Category, Product> products;

	public static final String PARENT = "parent";
	public static final String CHILDREN = "children";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String ID = "id";
	public static final String PRODUCTS = "products";

}

