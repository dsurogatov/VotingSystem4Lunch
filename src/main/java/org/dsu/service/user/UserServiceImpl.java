package org.dsu.service.user;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.dsu.dao.api.CrudDAO;
import org.dsu.dao.user.UserDAO;
import org.dsu.domain.model.User;
import org.dsu.dto.converter.ConverterUtils;
import org.dsu.dto.model.UserDTO;
import org.dsu.json.PageJSON;
import org.dsu.service.api.AbstractNamedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
@Transactional
public class UserServiceImpl extends AbstractNamedService<UserDTO, User> implements UserService {
	
	@Autowired
	private UserDAO dao;

	@Override
	protected CrudDAO<User> getDao() {
		return dao;
	}
	
	@Override
	public UserDTO findById(Long id) {
		Assert.notNull(id);

		return moveHashedPasswordInTheSameField(super.findById(id));
	}
	
	@Override
	@Transactional
	public UserDTO create(UserDTO instance) {
		Assert.notNull(instance);
		Assert.isNull(instance.getId());

		// convert the password to hash
		if (StringUtils.isNotBlank(instance.getPassword())) {
			PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(instance.getPassword());
			instance.setPassword(hashedPassword);
		}

		return moveHashedPasswordInTheSameField(super.create(instance));
	}

	@Override
	public UserDTO update(UserDTO instance) {
		Assert.notNull(instance);
		Assert.notNull(instance.getId());

		// convert the password to hash
		if (StringUtils.isNotBlank(instance.getPassword())) {
			PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(instance.getPassword());
			instance.setPassword(hashedPassword);
		} else if (StringUtils.isNotBlank(instance.getHashedPassword())) {
			instance.setPassword(instance.getHashedPassword());
		}
		
		return moveHashedPasswordInTheSameField(super.update(instance));
	}
	
	@Override
	public List<UserDTO> findByPage(PageJSON page) {
		List<UserDTO> userDTOList = super.findByPage(page);
		
		// clean password field
		for(UserDTO idxDTO : userDTOList) {
			idxDTO.setPassword("");
			idxDTO.setHashedPassword("");
		}
		return userDTOList;
	}
	
	private static UserDTO moveHashedPasswordInTheSameField(UserDTO userDTO) {
		userDTO.setHashedPassword("");
		if (StringUtils.isNotEmpty(userDTO.getPassword())) {
			userDTO.setHashedPassword(userDTO.getPassword());
			userDTO.setPassword("");
		}

		return userDTO;
	}

	@Override
	public UserDTO getUserByLogin(String login) {
		Assert.hasText(login);
		
		User user = dao.findUserByLogin(login);
		if(user == null) {
			return null;
		}
		
		return ConverterUtils.toDTO(user);
	}

}
