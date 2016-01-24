package org.dsu.dao.role;

import java.util.List;

import org.dsu.dao.api.NamedDao;
import org.dsu.dao.api.PageProp;
import org.dsu.dao.api.SortProp;
import org.dsu.domain.model.Role;
import org.dsu.domain.model.User_Role;
import org.dsu.domain.model.User_RoleId;
import org.dsu.dto.model.RoleDTO;

public interface RoleDao extends NamedDao<Role> {

	List<RoleDTO> findByUserId(Long idUser, PageProp page, SortProp sort);
	void saveUserRole(User_Role user_role);
	void deleteUserRole(User_RoleId pk);
}
