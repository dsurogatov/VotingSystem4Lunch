package org.dsu.service.menu;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.dsu.common.DateUtils;
import org.dsu.common.VotingSystemException;
import org.dsu.dao.dish.DishDAO;
import org.dsu.dao.restaurant.RestaurantDAO;
import org.dsu.domain.model.Authority;
import org.dsu.domain.model.Dish;
import org.dsu.domain.model.Restaurant;
import org.dsu.dto.converter.ConverterUtils;
import org.dsu.json.DishJSON;
import org.dsu.json.MenuJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {
	
	@Autowired
	private RestaurantDAO restaurantDAO;
	@Autowired
	private DishDAO dishDAO;

	@Override
	public MenuJSON getMenyByRestaurantId(Long restaurantId, LocalDate menuDate) {
		Assert.notNull(restaurantId);
		Assert.notNull(menuDate);
		
		// get restaurant
		// check if not exist
		Restaurant restaurant = restaurantDAO.findById(restaurantId);
		if(restaurant == null) {
			VotingSystemException.throwEntityNotFound(Restaurant.class);
		}
		
		// define dishDTO's list
		List<DishJSON> dishes = new ArrayList<>();
		
		// get dishes from the restaurant
		// go through dishes
		for(Dish idxDish : restaurant.getDishes()) {
		
			// create DishJSON, set id and name
			DishJSON dish = new DishJSON();
			dish.setId(idxDish.getId());
			dish.setName(idxDish.getName());
		
			// get price for the dish on menuDate from DishDAO
			BigDecimal price = dishDAO.getPriceByDishAndDate(idxDish, menuDate);
			dish.setPrice(price);
			
			dishes.add(dish);
		}
		
		// create MenuDTO object, set restaurant, dishes, and date
		MenuJSON menu = new MenuJSON();
		menu.setResturantRef(ConverterUtils.toDTO(restaurant));
		menu.setDishes(dishes);
		menu.setDate(DateUtils.asDate(menuDate));
		
		// get roles, if they contain ADMIN role when the editable field will be set 
		@SuppressWarnings("unchecked")
		Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>)    SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		for(SimpleGrantedAuthority idxAuthority : authorities){
			if(idxAuthority.getAuthority().equals(Authority.ROLE_ADMIN.name())) {
				menu.setEditable(true);
				break;
			}
		}
		
		return menu;
	}

}
