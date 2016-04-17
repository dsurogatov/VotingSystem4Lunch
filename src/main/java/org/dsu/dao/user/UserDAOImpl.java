package org.dsu.dao.user;

import java.util.List;

import javax.persistence.Query;

import org.dsu.dao.api.AbstractNamedDAO;
import org.dsu.domain.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl extends AbstractNamedDAO<User> implements UserDAO {

	@Override
	public void deleteRelations(Long id) {
		String sqlQuery = " DELETE FROM User_Role o \n" +
				" WHERE o.pk.user.id = :user_id \n";	
		Query query = entityManager.createQuery(sqlQuery);
		query.setParameter("user_id", id);
		query.executeUpdate();		
	}

	@Override
	public User findUserByLogin(String login) {
		String hqlQuery = "SELECT o FROM User o WHERE o.login = :login";
		
		@SuppressWarnings("unchecked")
		List<User> userList = entityManager.createQuery(hqlQuery).setParameter("login", login).getResultList();
		
		if(userList.isEmpty()) {
			return null;
		} else if(userList.size() == 1){
			return userList.iterator().next();
		} else {
			throw new IllegalArgumentException(String.format("The value of login '%s' return more than one user. ", login));
		}
	}

}
