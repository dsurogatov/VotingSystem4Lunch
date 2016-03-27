package org.dsu.service.menu;

import java.util.Date;

import org.dsu.json.MenuJSON;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {

	@Override
	public MenuJSON getMenyByRestaurantId(Long restaurantId, Date menuDate) {
		Assert.notNull(restaurantId);
		Assert.notNull(menuDate);
		
		// get restaurant
		// check if not exist
		
		// define dishDTO's list
		// get dishes from the restaurant
		// go through dishes
		// create DishJSON, set id and name
		// get price for the dish on menuDate from DishDAO
		// if it isn't null it'll be put in DTO's
		
		// create MenuDTO object, set restaurant, dishes, and date
		
		// get roles, if they contain ADMIN role when the editable field will be set 
		
		// TODO Auto-generated method stub
		return null;
	}

}
