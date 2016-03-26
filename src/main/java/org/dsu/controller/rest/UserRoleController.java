package org.dsu.controller.rest;

import java.util.List;

import javax.validation.Valid;

import org.dsu.dto.model.RoleDTO;
import org.dsu.json.PageJSON;
import org.dsu.json.RelationJSON;
import org.dsu.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRoleController {
	
	@Autowired
	private RoleService roleService;

	@RequestMapping(value = {"/api/v1/role/user/{user_id}"}, method = RequestMethod.GET)
	public List<RoleDTO> getUserRoles(@PathVariable("user_id") Long user_id, @Valid PageJSON page, BindingResult result) throws MethodArgumentNotValidException {
		if(result.hasErrors()) {
			throw new MethodArgumentNotValidException(null, result);
		}
		//PageJson page = ControllerUtil.deserializePage(json);
		List<RoleDTO> roles = roleService.findByUser(user_id, page);
		return roles;
	}
	
	@RequestMapping(value = "/api/v1/role/user", method = RequestMethod.POST)
    public void addRole2User(@RequestBody RelationJSON relation) {
		roleService.createUserRole(relation.getUserId(), relation.getRoleId());
    }
	
	@RequestMapping(value = {"/api/v1/role/user"}, method = RequestMethod.DELETE)
    public void deleteUser(@RequestBody RelationJSON relation) {
		roleService.deleteUserRole(relation.getUserId(), relation.getRoleId());
	}
}
