package org.dsu.dao.role;

import java.util.List;
import java.util.Set;

import org.dsu.dao.api.NamedDAO;
import org.dsu.dao.api.PageProp;
import org.dsu.dao.api.SortProp;
import org.dsu.domain.model.Authority;
import org.dsu.domain.model.Role;
import org.dsu.domain.model.User_Role;
import org.dsu.domain.model.User_RoleId;

public interface RoleDAO extends NamedDAO<Role> {

	List<Role> findByUserId(Long idUser, PageProp page, SortProp sort);
	void saveUserRole(User_Role user_role);
	void deleteUserRole(User_RoleId pk);
	/** Load authorities by user id
	 * @param idUser
	 * @return set of authorities
	 */
	Set<Authority> loadAuthoritiesByUserId(Long idUser);
}
