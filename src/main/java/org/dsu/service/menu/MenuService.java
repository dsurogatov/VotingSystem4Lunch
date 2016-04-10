package org.dsu.service.menu;

import java.time.LocalDate;

import org.dsu.json.MenuJSON;

public interface MenuService {

	MenuJSON getMenyByRestaurantId(Long restaurantId, LocalDate menuDate);
	void updateMenu(MenuJSON menu);
	void deleteDish(Long id);
}
