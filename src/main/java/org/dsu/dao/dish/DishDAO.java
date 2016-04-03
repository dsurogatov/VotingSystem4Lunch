package org.dsu.dao.dish;

import java.time.LocalDate;

import org.dsu.dao.api.NamedDao;
import org.dsu.domain.model.Dish;
import org.dsu.domain.model.MenuItem;

public interface DishDAO extends NamedDao<Dish> {
	
	MenuItem getMenuItemByDishAndDate(Dish dish, LocalDate date);

}
