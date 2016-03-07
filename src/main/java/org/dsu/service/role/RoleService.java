package org.dsu.service.role;

import java.util.List;
import java.util.Set;

import org.dsu.domain.model.Authority;
import org.dsu.dto.model.RoleDTO;
import org.dsu.json.PageJson;
import org.dsu.service.api.NamedService;

public interface RoleService extends NamedService<RoleDTO> {

	List<RoleDTO> findByUser(Long user_id, PageJson page);
	void createUserRole(Long id_user, Long id_role);
	void deleteUserRole(Long id_user, Long id_role);
	
	/** Get authorities by user id
	 * @param userId 
	 * @return set of authorities
	 * @throws IllegalArgumentException if userId is null
	 * @throws VotingSystemException if user hasn't found
	 */
	Set<Authority> getAuthoritiesByUser(Long userId);
}
