package org.dsu.domain.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
//@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "role_id"}))
public class User_Role /*extends AbstractIdEntity*/ {

	@EmbeddedId
	private User_RoleId pk = new User_RoleId();

	public User_RoleId getPk() {
		return pk;
	}

	public void setPk(User_RoleId pk) {
		this.pk = pk;
	}

}
