package org.dsu.service.role;

import java.util.List;

import org.dsu.dto.model.RoleDTO;
import org.dsu.json.PageJson;
import org.dsu.service.api.NamedService;

public interface RoleService extends NamedService<RoleDTO> {

	List<RoleDTO> findByUser(Long user_id, PageJson page);
	void createUserRole(Long id_user, Long id_role);
	void deleteUserRole(Long id_user, Long id_role);
}
