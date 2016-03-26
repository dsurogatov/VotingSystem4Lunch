package org.dsu.controller.rest;

import java.util.List;

import javax.validation.Valid;

import org.dsu.dto.model.RoleDTO;
import org.dsu.json.CountJSON;
import org.dsu.json.PageJSON;
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
public class RoleController {

	@Autowired
	private RoleService roleService;

	@RequestMapping(value = {"/api/v1/role"}, method = RequestMethod.GET)
	public List<RoleDTO> getRoles(@Valid PageJSON page, BindingResult result) throws MethodArgumentNotValidException {
		if(result.hasErrors()) {
			throw new MethodArgumentNotValidException(null, result);
		}
		
		return roleService.findByPage(page);
	}
	
	@RequestMapping(value = {"/api/v1/role/count"}, method = RequestMethod.GET)
    public CountJSON getRolesCnt() {
		
		return new CountJSON(roleService.count());
	}
	
	@RequestMapping(value = {"/api/v1/role/count/{findingValue}"}, method = RequestMethod.GET)
    public CountJSON getRolesCntByName(@PathVariable("findingValue") String findingValue) {
		
		return new CountJSON(roleService.countByName(findingValue));
	}
	
	@RequestMapping(value = {"/api/v1/role/id/{id}"}, method = RequestMethod.GET)
    public RoleDTO getRolesById(@PathVariable("id") Long id) {
		
		return roleService.findById(id);
	}
	
	// -- EDIT methods
	@RequestMapping(value = "/api/v1/role", method = RequestMethod.POST)
    public RoleDTO add(@Valid @RequestBody RoleDTO dto) {
		RoleDTO added = roleService.create(dto);
        return added;
    }
	
	@RequestMapping(value = "/api/v1/role", method = RequestMethod.PUT)
    public RoleDTO update(@Valid @RequestBody RoleDTO dto) {
		RoleDTO updated = roleService.update(dto);
        return updated;
    }
	
	@RequestMapping(value = {"/api/v1/role/{id}"}, method = RequestMethod.DELETE)
    public void deleteRole(@PathVariable("id") Long id) {
		roleService.delete(id);
	}
}
