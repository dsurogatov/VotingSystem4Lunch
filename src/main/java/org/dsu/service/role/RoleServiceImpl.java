package org.dsu.service.role;

import java.util.List;
import java.util.Set;

import org.dsu.common.VotingSystemException;
import org.dsu.dao.api.CrudDao;
import org.dsu.dao.api.PageProp;
import org.dsu.dao.api.SortProp;
import org.dsu.dao.role.RoleDAO;
import org.dsu.dao.user.UserDAO;
import org.dsu.domain.model.Authority;
import org.dsu.domain.model.Role;
import org.dsu.domain.model.User;
import org.dsu.domain.model.User_Role;
import org.dsu.domain.model.User_RoleId;
import org.dsu.dto.converter.ConverterUtils;
import org.dsu.dto.model.RoleDTO;
import org.dsu.json.PageJson;
import org.dsu.service.api.AbstractNamedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
@Transactional
public class RoleServiceImpl extends AbstractNamedService<RoleDTO, Role> implements RoleService {

	@Autowired
	private RoleDAO dao;
	@Autowired
	private UserDAO userDao;
	
	@Override
	protected CrudDao<Role> getDao() {
		return dao;
	}

	@Override
	public List<RoleDTO> findByUser(Long user_id, PageJson page) {
		Assert.notNull(user_id);
		
		if(userDao.findById(user_id) == null){
			VotingSystemException.throwEntityNotFound(User.class);
		}
		
		return ConverterUtils.toDTOList(dao.findByUserId(user_id, 
				new PageProp(page.getStart(), page.getSize()), 
				new SortProp(page.getSortingField(), page.isSortAsc())
				));
	}

	@Override
	public void createUserRole(Long id_user, Long id_role) {
		Assert.notNull(id_user);
		Assert.notNull(id_role);
		
		User user = userDao.findById(id_user);
		if(user == null){
			VotingSystemException.throwEntityNotFound(User.class);
		}
		Role role = dao.findById(id_role);
		if(role == null){
			VotingSystemException.throwEntityNotFound(Role.class);
		}
		
		User_RoleId user_roleId = new User_RoleId();
		user_roleId.setUser(user);
		user_roleId.setRole(role);
		User_Role user_role = new User_Role();
		user_role.setPk(user_roleId);
		dao.saveUserRole(user_role);
	}

	@Override
	public void deleteUserRole(Long id_user, Long id_role) {
		Assert.notNull(id_user);
		Assert.notNull(id_role);
		
		User user = userDao.findById(id_user);
		if(user == null){
			return;
		}
		Role role = dao.findById(id_role);
		if(role == null){
			return;
		}
		
		User_RoleId user_roleId = new User_RoleId();
		user_roleId.setUser(user);
		user_roleId.setRole(role);
		
		dao.deleteUserRole(user_roleId);
	}

	@Override
	public Set<Authority> getAuthoritiesByUser(Long userId) {
		Assert.notNull(userId);
		
		User user = userDao.findById(userId);
		if(user == null){
			VotingSystemException.throwEntityNotFound(User.class);
		}
		
		// get user authorities
		return dao.loadAuthoritiesByUserId(userId);
	}

}
