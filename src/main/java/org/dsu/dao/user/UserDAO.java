package org.dsu.dao.user;

import org.dsu.dao.api.NamedDAO;
import org.dsu.domain.model.User;

public interface UserDAO extends NamedDAO<User> {
	
	/** Get a user from database by the login value
	 * @param login - the value of login name
	 * @return - user entity or null if it didn't find
	 * @throws - IllegalArgumentException if was found more than one user
	 */
	User findUserByLogin(String login);

}
