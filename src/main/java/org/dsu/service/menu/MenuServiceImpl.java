package org.dsu.service.menu;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.dsu.common.DateUtils;
import org.dsu.common.ExceptionType;
import org.dsu.common.VotingSystemException;
import org.dsu.dao.dish.DishDAO;
import org.dsu.dao.menuitem.MenuItemDAO;
import org.dsu.dao.restaurant.RestaurantDAO;
import org.dsu.domain.model.Authority;
import org.dsu.domain.model.Dish;
import org.dsu.domain.model.MenuItem;
import org.dsu.domain.model.Restaurant;
import org.dsu.dto.converter.ConverterUtils;
import org.dsu.json.DishJSON;
import org.dsu.json.FieldErrorJSON;
import org.dsu.json.MenuJSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MenuServiceImpl.class);

	@Autowired
	private MessageSource messageSource;
	@Autowired
	private RestaurantDAO restaurantDAO;
	@Autowired
	private DishDAO dishDAO;
	@Autowired
	private MenuItemDAO menuItemDAO;

	@Override
	public MenuJSON getMenyByRestaurantId(Long restaurantId, LocalDate menuDate) {
		Assert.notNull(restaurantId);
		Assert.notNull(menuDate);

		// get restaurant
		// check if not exist
		Restaurant restaurant = restaurantDAO.findById(restaurantId);
		if (restaurant == null) {
			VotingSystemException.throwEntityNotFound(Restaurant.class);
		}

		// define dishDTO's list
		List<DishJSON> dishes = new ArrayList<>();

		// get dishes from the restaurant
		// go through dishes
		for (Dish idxDish : restaurant.getDishes()) {

			// create DishJSON, set id and name
			DishJSON dish = new DishJSON();
			dish.setId(idxDish.getId());
			dish.setName(idxDish.getName());

			// get price for the dish on menuDate from DishDAO
			MenuItem menuItem = dishDAO.getMenuItemByDishAndDate(idxDish, menuDate);
			if (menuItem != null) {
				dish.setPrice(menuItem.getPrice());
			}

			dishes.add(dish);
		}

		// create MenuDTO object, set restaurant, dishes, and date
		MenuJSON menu = new MenuJSON();
		menu.setResturantRef(ConverterUtils.toDTO(restaurant));
		menu.setDishes(dishes);
		menu.setDate(DateUtils.asDate(menuDate));

		// get roles, if they contain ADMIN role when the editable field will be set 
		@SuppressWarnings("unchecked")
		Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication()
		        .getAuthorities();
		for (SimpleGrantedAuthority idxAuthority : authorities) {
			if (idxAuthority.getAuthority().equals(Authority.ROLE_ADMIN.name())) {
				menu.setEditable(true);
				break;
			}
		}

		return menu;
	}

	@Override
	public void updateMenu(MenuJSON menu) {
		Assert.notNull(menu);
		Assert.notNull(menu.getResturantRef());
		Assert.notNull(menu.getResturantRef().getId());
		if(menu.getDishesCount() > 5){
			throw new VotingSystemException(ExceptionType.MAX_DISH_IN_RESTAURANT);
		}
		
		// define dishes filter container
		DishFilterContainer dishContainer = new DishFilterContainer(messageSource);
		// filter dishes 
		dishContainer.filter(menu.getDishes(), menu.getChangedDishesIds());
		// and validate 
		if(dishContainer.hasError()) {
			VotingSystemException.throwValidationError(dishContainer.getFieldErrors());
		}
		

		// get restaurant, if null then throw exception
		Restaurant restaurant = restaurantDAO.findById(menu.getResturantRef().getId());
		if (restaurant == null) {
			VotingSystemException.throwEntityNotFound(Restaurant.class);
		}
		
		// get dishes from the restaurant
		// make map {id, dish}
		Map<Long, Dish> dishesInDB = new HashMap<>();
		for (Dish idxDish : restaurant.getDishes()) {
			dishesInDB.put(idxDish.getId(), idxDish);
		}

		// go through new dishes list
		for(DishJSON idxNewDishJSON : dishContainer.getNewDishes()) {
			Dish dish = new Dish();
			dish.setName(idxNewDishJSON.getName());
			dish.setRestaurant(restaurant);
			dish = dishDAO.save(dish);
			
			//idxNewDishJSON.setId(dish.getId());
			if(idxNewDishJSON.getPrice().compareTo(BigDecimal.ZERO) > 0) {
				MenuItem item = new MenuItem();
				item.setPrice(idxNewDishJSON.getPrice());
				item.setDate(menu.getDate());
				item.setDish(dish);
				menuItemDAO.save(item);
			}
		}

		// go through changed dishes
		LocalDate menuDate = DateUtils.asLocalDate(menu.getDate());
		LOGGER.info("date is {}, localdate is {}", menu.getDate(), menuDate);
		for(DishJSON idxEditDish : dishContainer.getChangedDishes()) {
		
			// get dish from map, set name, merge
			Dish dish = dishesInDB.get(idxEditDish.getId());
			if(dish == null) {
				continue;
			}
			dish.setName(idxEditDish.getName());
			dishDAO.save(dish);
		
			// get MenuItem for dish and date
			MenuItem menuItem = dishDAO.getMenuItemByDishAndDate(dish, menuDate);
		
			// if menuItem is null then create new instance and save to DB if price more than zero
			if(idxEditDish.getPrice().compareTo(BigDecimal.ZERO) > 0) {
				if(menuItem == null) {
					menuItem = new MenuItem();
					menuItem.setDate(menu.getDate());
					menuItem.setDish(dish);
				}
				menuItem.setPrice(idxEditDish.getPrice());
				menuItemDAO.save(menuItem);
			} else {

				// else if menuItem is not null than remove it
				if(menuItem != null) {
					menuItemDAO.delete(menuItem.getId());
				}
			}
		}
	}

	@Override
	public void deleteDish(Long dishId) {
		Assert.notNull(dishId);

		dishDAO.delete(dishId);
	}

}

class DishFilterContainer {

	private final List<DishJSON> newDishes = new ArrayList<>();
	private final List<DishJSON> changedDishes = new ArrayList<>();
	private final List<DishJSON> unChangedDishes = new ArrayList<>();
	private final List<FieldErrorJSON> fieldErrors = new ArrayList<>();
	private final Validator validator;
	MessageSource messageSource;

	DishFilterContainer(MessageSource messageSource) {
		this.messageSource = messageSource;
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	boolean needUpdate(DishJSON dish, List<Long> changedDishesIds) {
		if (dish.getId() == null || dish.getId() != 0l) {
			return true;
		} else if (changedDishesIds.contains(dish.getId())) {
			return true;
		} else {
			return false;
		}
	}

	void filter(List<DishJSON> dishes, List<Long> changedDishesIds) {
		Locale currentLocale = LocaleContextHolder.getLocale();
		
		// go through dishes
		for(DishJSON idxDishJSON : dishes){
			if(needUpdate(idxDishJSON, changedDishesIds)) {
		
				// validate, if not valid then add error, continue
				Set<ConstraintViolation<DishJSON>> constraintViolations = validator.validate(idxDishJSON);
				if(!constraintViolations.isEmpty()) {
					for(ConstraintViolation<DishJSON> idxViolation: constraintViolations) {
						String localizedFieldName = messageSource.getMessage(idxViolation.getPropertyPath().toString(), null, currentLocale);
						String localizedMessage = messageSource.getMessage(idxViolation.getMessage(), null, currentLocale);
						FieldErrorJSON fieldError = new FieldErrorJSON(localizedFieldName, localizedMessage);
						fieldErrors.add(fieldError);
					}
				} else if(!fieldErrors.isEmpty()) {
					continue;
				}
			} else {
		
				// else if the dish donn't need update than add it to unchanged array, continue
				unChangedDishes.add(idxDishJSON);
				continue;
			}

		
			// if dish don't have id then it'll be added to new dishes list
			if(idxDishJSON.getId() == null || idxDishJSON.getId() == 0l){
				newDishes.add(idxDishJSON);
			} else {
				changedDishes.add(idxDishJSON);
			}
		}
	}
	
	boolean hasError() {
		return !fieldErrors.isEmpty();
	}

	public List<FieldErrorJSON> getFieldErrors() {
		return fieldErrors;
	}

	public List<DishJSON> getNewDishes() {
		return newDishes;
	}

	public List<DishJSON> getChangedDishes() {
		return changedDishes;
	}
}
