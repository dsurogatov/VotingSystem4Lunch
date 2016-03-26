package org.dsu.controller.rest;

import java.util.List;

import javax.validation.Valid;

import org.dsu.dto.model.UserDTO;
import org.dsu.json.CountJSON;
import org.dsu.json.PageJSON;
import org.dsu.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = {"/api/v1/user"}, method = RequestMethod.GET)
	public List<UserDTO> getUsers(@Valid PageJSON page, BindingResult result) throws MethodArgumentNotValidException {
		LOGGER.debug("The page is - " + page);
		// странно, просто аннотация валид не бросает это исключение, но 400 код возвращает
		if(result.hasErrors()) {
			throw new MethodArgumentNotValidException(null, result);
		}

		return userService.findByPage(page);
	}
	
	@RequestMapping(value = {"/api/v1/user/count"}, method = RequestMethod.GET)
    public CountJSON getUsersCnt() {
		
		return new CountJSON(userService.count());
	}
	
	@RequestMapping(value = {"/api/v1/user/count/{findingValue}"}, method = RequestMethod.GET)
    public CountJSON getUsersCntByName(@PathVariable("findingValue") String findingValue) {
		
		return new CountJSON(userService.countByName(findingValue));
	}
	
	@RequestMapping(value = {"/api/v1/user/id/{id}"}, method = RequestMethod.GET)
    public UserDTO getUsersById(@PathVariable("id") Long id) {
		
		return userService.findById(id);
	}
	
	// -- EDIT methods
	@RequestMapping(value = "/api/v1/user", method = RequestMethod.POST)
    public UserDTO add(@Valid @RequestBody UserDTO dto) {
		UserDTO added = userService.create(dto);
        return added;
    }
	
	@RequestMapping(value = "/api/v1/user", method = RequestMethod.PUT)
    public UserDTO update(@Valid @RequestBody UserDTO dto) {
		UserDTO updated = userService.update(dto);
        return updated;
    }
	
	@RequestMapping(value = {"/api/v1/user/{id}"}, method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable("id") Long id) {
		userService.delete(id);
	}
}
