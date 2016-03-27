package org.dsu.controller.rest;

import java.util.Date;

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

	@RequestMapping(value = {"/api/v1/menu/restaurant-{id}/{date}"}, method = RequestMethod.GET)
    public MenuJSON getMenyByRestaurantId(@PathVariable("id") Long id, @PathVariable("date") Date date) {
		
		return menuService.getMenyByRestaurantId(id, date);
	}
	
	@RequestMapping(value = {"/api/v1/menu/restaurant-{id}"}, method = RequestMethod.GET)
    public MenuJSON getMenyByRestaurantId(@PathVariable("id") Long id) {
		
		// московская дата!
		Date date = new Date();
		return menuService.getMenyByRestaurantId(id, date);
	}
	
}
