package org.dsu.domain.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.dsu.domain.api.AbstractNamedEntity;

@Entity
public class Restaurant extends AbstractNamedEntity {

	@OneToMany(mappedBy = "restaurant")
	private Set<Dish> dishes;

	public Set<Dish> getDishes() {
		return dishes;
	}

	public void setDishes(Set<Dish> dishes) {
		this.dishes = dishes;
	}

}
