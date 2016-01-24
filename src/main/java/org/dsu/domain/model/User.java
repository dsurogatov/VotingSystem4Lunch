/**
 * 
 */
package org.dsu.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.dsu.domain.api.AbstractNamedEntity;

/**
 * @author nescafe
 *
 */
@Entity
@Table(name="t_user")
public class User extends AbstractNamedEntity {

	@Column(name = "login", length = 100, unique = true, nullable = false)
	private String login;
	@Column(name = "password", length = 60)
	private String password;
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [getName()=" + getName() + ", getId()=" + getId() + ", login=" + login + ", password=" + password + "]";
	}
}
