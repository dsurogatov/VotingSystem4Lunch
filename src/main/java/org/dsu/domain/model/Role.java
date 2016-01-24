package org.dsu.domain.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.dsu.domain.api.AbstractNamedEntity;

@Entity
@Table(name="t_role")
public class Role extends AbstractNamedEntity {

	private boolean admin;

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
}
