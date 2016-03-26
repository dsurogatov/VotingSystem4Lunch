package org.dsu.controller.rest;

import java.util.List;

import javax.validation.Valid;

import org.dsu.dto.model.RestaurantDTO;
import org.dsu.json.CountJSON;
import org.dsu.json.PageJSON;
import org.dsu.service.restaurant.RestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestaurantController {

private static final Logger LOGGER = LoggerFactory.getLogger(RestaurantController.class);
	
	@Autowired
	private RestaurantService restaurantService;

	@RequestMapping(value = {"/api/v1/restaurant"}, method = RequestMethod.GET)
	public List<RestaurantDTO> getUsers(@Valid PageJSON page, BindingResult result) throws MethodArgumentNotValidException {
		LOGGER.debug("The page is - " + page);
		// странно, просто аннотация валид не бросает это исключение, но 400 код возвращает
		if(result.hasErrors()) {
			throw new MethodArgumentNotValidException(null, result);
		}

		return restaurantService.findByPage(page);
	}
	
	@RequestMapping(value = {"/api/v1/restaurant/count"}, method = RequestMethod.GET)
    public CountJSON getUsersCnt() {
		
		return new CountJSON(restaurantService.count());
	}
	
	@RequestMapping(value = {"/api/v1/restaurant/count/{findingValue}"}, method = RequestMethod.GET)
    public CountJSON getUsersCntByName(@PathVariable("findingValue") String findingValue) {
		
		return new CountJSON(restaurantService.countByName(findingValue));
	}
	
	@RequestMapping(value = {"/api/v1/restaurant/id/{id}"}, method = RequestMethod.GET)
    public RestaurantDTO getUsersById(@PathVariable("id") Long id) {
		
		return restaurantService.findById(id);
	}
	
	// -- EDIT methods
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/api/v1/restaurant", method = RequestMethod.POST)
    public RestaurantDTO add(@Valid @RequestBody RestaurantDTO dto) {
		RestaurantDTO added = restaurantService.create(dto);
        return added;
    }
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/api/v1/restaurant", method = RequestMethod.PUT)
    public RestaurantDTO update(@Valid @RequestBody RestaurantDTO dto) {
		RestaurantDTO updated = restaurantService.update(dto);
        return updated;
    }
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = {"/api/v1/restaurant/{id}"}, method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable("id") Long id) {
		restaurantService.delete(id);
	}
	
}
