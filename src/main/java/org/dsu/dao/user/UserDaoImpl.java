package org.dsu.dao.user;

import javax.persistence.Query;

import org.dsu.dao.api.AbstractNamedDao;
import org.dsu.domain.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends AbstractNamedDao<User> implements UserDao {

	@Override
	public void deleteRelations(Long id) {
		String sqlQuery = " DELETE FROM User_Role o \n" +
				" WHERE o.pk.user.id = :user_id \n";	
		Query query = entityManager.createQuery(sqlQuery);
		query.setParameter("user_id", id);
		query.executeUpdate();		
	}

}
