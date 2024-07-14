package com.nhs.individual.views;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;

@StaticMetamodel(Accountstatisticsview.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Accountstatisticsview_ {

	
	/**
	 * @see com.nhs.individual.views.Accountstatisticsview#totalStatus1
	 **/
	public static volatile SingularAttribute<Accountstatisticsview, BigDecimal> totalStatus1;
	
	/**
	 * @see com.nhs.individual.views.Accountstatisticsview#totalAccounts
	 **/
	public static volatile SingularAttribute<Accountstatisticsview, Long> totalAccounts;
	
	/**
	 * @see com.nhs.individual.views.Accountstatisticsview#totalStatus3
	 **/
	public static volatile SingularAttribute<Accountstatisticsview, BigDecimal> totalStatus3;
	
	/**
	 * @see com.nhs.individual.views.Accountstatisticsview
	 **/
	public static volatile EntityType<Accountstatisticsview> class_;
	
	/**
	 * @see com.nhs.individual.views.Accountstatisticsview#totalStatus2
	 **/
	public static volatile SingularAttribute<Accountstatisticsview, BigDecimal> totalStatus2;

	public static final String TOTAL_STATUS1 = "totalStatus1";
	public static final String TOTAL_ACCOUNTS = "totalAccounts";
	public static final String TOTAL_STATUS3 = "totalStatus3";
	public static final String TOTAL_STATUS2 = "totalStatus2";

}

