package org.dsu.service.menu;

import java.util.Date;

import org.dsu.json.MenuJSON;

public interface MenuService {

	MenuJSON getMenyByRestaurantId(Long restaurantId, Date menuDate);
}
