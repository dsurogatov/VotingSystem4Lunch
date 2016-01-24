package org.dsu.dao.role;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.dsu.dao.api.AbstractNamedDao;
import org.dsu.dao.api.PageProp;
import org.dsu.dao.api.SortProp;
import org.dsu.domain.model.Role;
import org.dsu.domain.model.User_Role;
import org.dsu.domain.model.User_RoleId;
import org.dsu.dto.model.RoleDTO;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl extends AbstractNamedDao<Role> implements RoleDao {

	@Override
	public void deleteRelations(Long id) {
		String sqlQuery = " DELETE FROM User_Role o \n" +
				" WHERE o.pk.role.id = :role_id \n";	
		Query query = entityManager.createQuery(sqlQuery);
		query.setParameter("role_id", id);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RoleDTO> findByUserId(Long idUser, PageProp page, SortProp sort) {
		if (page == null || page.getFirstResult() < 0 || page.getMaxResult() < 0) {
			return new ArrayList<>();
		}
		
		String sqlQuery = " SELECT o.pk.role FROM User_Role o \n" +
				" WHERE o.pk.user.id = :user_id \n";
		if (sort != null && StringUtils.isNotBlank(sort.getColumnName())) {
			sqlQuery += " ORDER BY o.role." + sort.getColumnName() + (sort.isAsc() ? " ASC " : " DESC ");
		}
		
		Query query = entityManager.createQuery(sqlQuery);
		query.setParameter("user_id", idUser);
		query.setFirstResult(page.getFirstResult());
		query.setMaxResults(page.getMaxResult());
		return query.getResultList();
	}

	@Override
	public void saveUserRole(User_Role user_role) {
		entityManager.merge(user_role);
		flush();
	}

	@Override
	public void deleteUserRole(User_RoleId pk) {
//		String sqlQuery = " DELETE FROM User_Role o \n" +
//				" WHERE o.pk.user.id = :user_id AND o.pk.role.id = :role_id \n";	
//		Query query = entityManager.createQuery(sqlQuery);
//		query.setParameter("user_id", idUser);
//		query.setParameter("role_id", idRole);
//		query.executeUpdate();
		
		User_Role userRole = entityManager.find(User_Role.class, pk);
		if(userRole == null) {
			return;
		}
		entityManager.remove(userRole);
	}
}
