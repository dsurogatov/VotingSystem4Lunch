package org.dsu.dao.dish;

import java.time.LocalDate;

import org.dsu.dao.api.NamedDAO;
import org.dsu.domain.model.Dish;
import org.dsu.domain.model.MenuItem;

public interface DishDAO extends NamedDAO<Dish> {
	
	MenuItem getMenuItemByDishAndDate(Dish dish, LocalDate date);

}
