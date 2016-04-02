package org.dsu.dao.dish;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.dsu.dao.api.NamedDao;
import org.dsu.domain.model.Dish;

public interface DishDAO extends NamedDao<Dish> {
	
	BigDecimal getPriceByDishAndDate(Dish dish, LocalDate date);

}
