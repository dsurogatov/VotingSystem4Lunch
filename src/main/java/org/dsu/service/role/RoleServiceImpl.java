package org.dsu.service.role;

import java.util.List;

import org.dsu.common.VotingSystemException;
import org.dsu.dao.api.CrudDao;
import org.dsu.dao.api.PageProp;
import org.dsu.dao.api.SortProp;
import org.dsu.dao.role.RoleDao;
import org.dsu.dao.user.UserDao;
import org.dsu.domain.model.Role;
import org.dsu.domain.model.User;
import org.dsu.domain.model.User_Role;
import org.dsu.domain.model.User_RoleId;
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
	private RoleDao dao;
	@Autowired
	private UserDao userDao;
	
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
		
		return dao.findByUserId(user_id, 
				new PageProp(page.getStart(), page.getSize()), 
				new SortProp(page.getSortingField(), page.isSortAsc())
				);
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

}
