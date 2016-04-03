package org.dsu.service.menu;

import java.time.LocalDate;

import org.dsu.json.MenuJSON;

public interface MenuService {

	MenuJSON getMenyByRestaurantId(Long restaurantId, LocalDate menuDate);
	MenuJSON updateMenu(MenuJSON menu);
	void deleteDish(Long id);
}
