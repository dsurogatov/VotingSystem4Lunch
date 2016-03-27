package org.dsu.domain.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.dsu.domain.api.AbstractNotUniqNamedEntity;

@Entity
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
