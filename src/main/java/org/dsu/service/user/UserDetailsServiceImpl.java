package org.dsu.service.user;

import java.util.HashSet;
import java.util.Set;

import org.dsu.domain.model.Authority;
import org.dsu.dto.model.UserDTO;
import org.dsu.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final static String HARDCODED_ADMIN = "admin#";
	private final static String HARDCODED_ADMIN_PASS = "#u2";

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	BCryptPasswordEncoder pe;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		if (HARDCODED_ADMIN.equals(login)) {
			return getHardcodedAdmin();
		}

		// с помощью нашего сервиса UserService получаем User
		UserDTO userDTO = userService.getUserByLogin(login);
		//System.out.println("login --- " + login);
		if (userDTO == null) {  
            throw new UsernameNotFoundException("User details not found with this login: " + login);  
        }  

		// указываем роли для этого пользователя
		// грузим авторити для юзера
		Set<Authority> authorities = roleService.getAuthoritiesByUser(userDTO.getId());
		Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
		for(Authority auth : authorities) {
			roles.add(new SimpleGrantedAuthority(auth.name()));
		}

		// на основании полученныйх даных формируем объект UserDetails
		// который позволит проверить введеный пользователем логин и пароль
		// и уже потом аутентифицировать пользователя
		UserDetails userDetails = new org.springframework.security.core.userdetails.User(userDTO.getLogin(), userDTO.getPassword(), roles);

		return userDetails;
	}

	private UserDetails getHardcodedAdmin() {
		Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
		for (Authority idxAuth : Authority.values()) {
			roles.add(new SimpleGrantedAuthority(idxAuth.name()));
		}

		return new org.springframework.security.core.userdetails.User(HARDCODED_ADMIN, pe.encode(HARDCODED_ADMIN_PASS), roles);

	}

}