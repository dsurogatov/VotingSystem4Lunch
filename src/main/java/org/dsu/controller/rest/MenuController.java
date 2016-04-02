package org.dsu.controller.rest;

import java.time.LocalDate;

import org.dsu.common.ThreadLocalDateFormat;
import org.dsu.json.MenuJSON;
import org.dsu.service.menu.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MenuController {
	
	@Autowired
	private MenuService menuService;

	// date format is yyyy-MM-dd
	@RequestMapping(value = {"/api/v1/menu/restaurant-{id}/{date}"}, method = RequestMethod.GET)
    public MenuJSON getMenyByRestaurantId(@PathVariable("id") Long id, @PathVariable("date") String date) {
		// convert to date
		LocalDate localDate = ThreadLocalDateFormat.threadSafeFormat2Date(date);
		return menuService.getMenyByRestaurantId(id, localDate);
	}
	
	@RequestMapping(value = {"/api/v1/menu/restaurant-{id}"}, method = RequestMethod.GET)
    public MenuJSON getMenyByRestaurantId(@PathVariable("id") Long id) {
		
		// московская дата!
		LocalDate date = LocalDate.now();
		return menuService.getMenyByRestaurantId(id, date);
	}
	
}