package org.dsu.dto.model;

import org.dsu.dto.api.BaseNamedDTO;

public class RoleDTO extends BaseNamedDTO {

	boolean admin;

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
}
