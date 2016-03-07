/**
 * 
 */
package org.dsu.service.user;

import org.dsu.dto.model.UserDTO;
import org.dsu.service.api.NamedService;

/**
 * @author nescafe
 *
 */
public interface UserService extends NamedService<UserDTO> {
	
	/** Get a user from persistence layer by the login
	 * @param login - the value of login name
	 * @return - user dto or null if didn't finded
	 */
	UserDTO getUserByLogin(String login);

}
