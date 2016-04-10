package org.dsu.domain.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.dsu.domain.api.AbstractNotUniqNamedEntity;

@Entity
@Table(name="DISH", 
	uniqueConstraints = @UniqueConstraint(name = "UK_RESTAURANT_NAME", columnNames = {"RESTAURANT_ID", "NAME"}))
public class Dish extends AbstractNotUniqNamedEntity  {

	@ManyToOne
    @JoinColumn(name="RESTAURANT_ID", nullable=false)
    private Restaurant restaurant;

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
}
