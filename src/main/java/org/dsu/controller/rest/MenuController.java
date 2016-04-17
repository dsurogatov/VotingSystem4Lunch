package org.dsu.controller.rest;

import java.time.LocalDate;

import javax.validation.Valid;

import org.dsu.common.DateUtils;
import org.dsu.json.MenuJSON;
import org.dsu.service.menu.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
		LocalDate localDate = DateUtils.parse2Date(date);
		return menuService.getMenyByRestaurantId(id, localDate);
	}
	
//	@RequestMapping(value = {"/api/v1/menu/restaurant-{id}"}, method = RequestMethod.GET)
//    public MenuJSON getMenyByRestaurantId(@PathVariable("id") Long id) {
//		
//		// московская дата!
//		LocalDate date = LocalDate.now();
//		return menuService.getMenyByRestaurantId(id, date);
//	}
	
	@RequestMapping(value = "/api/v1/menu", method = RequestMethod.POST)
    public MenuJSON saveMenu(@Valid @RequestBody MenuJSON dto) {
		menuService.updateMenu(dto);
		LocalDate menuDate = dto.getDate();
        return menuService.getMenyByRestaurantId(dto.getResturantRef().getId(), menuDate);
    }
	
	@RequestMapping(value = {"/api/v1/dish/{id}"}, method = RequestMethod.DELETE)
    public void deleteDish(@PathVariable("id") Long id) {
		menuService.deleteDish(id);
	}
	
}
