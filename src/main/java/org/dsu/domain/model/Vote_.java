package org.dsu.domain.model;

import java.time.LocalDate;

import javax.persistence.metamodel.SingularAttribute;

@javax.persistence.metamodel.StaticMetamodel(Vote.class)
public class Vote_ {

	public static volatile SingularAttribute<Vote, LocalDate> date;
	public static volatile SingularAttribute<Vote, Restaurant> restaurant;
    public static volatile SingularAttribute<Vote, User> user;
}
