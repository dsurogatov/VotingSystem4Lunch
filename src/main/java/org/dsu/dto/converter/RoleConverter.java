package org.dsu.dto.converter;

import java.util.HashSet;
import java.util.Set;

import org.dsu.domain.model.Authority;
import org.dsu.domain.model.Role;
import org.dsu.dto.model.RoleDTO;

public class RoleConverter  extends AbstractConverter<Role, RoleDTO>  {

	@Override
	public RoleDTO toDTO(Role entity) {
		RoleDTO retDTO = super.toDTO(entity);
		
		if(entity.getAuthorities().contains(Authority.ROLE_ADMIN)){
			retDTO.setAdmin(true);
		}
		if(entity.getAuthorities().contains(Authority.ROLE_LOGIN)){
			retDTO.setLogin(true);
		}
		
		return retDTO;
	}
	
	@Override
	public Role toEntity(RoleDTO dto) {
		Role ret = super.toEntity(dto);
		
		Set<Authority> authorities = new HashSet<>();
		if(dto.isAdmin()) {
			authorities.add(Authority.ROLE_ADMIN);
		}
		if(dto.isLogin()) {
			authorities.add(Authority.ROLE_LOGIN);
		}
		
		if(!authorities.isEmpty()){
			ret.setAuthorities(authorities);
		}
		
		return ret;		
	}
}
