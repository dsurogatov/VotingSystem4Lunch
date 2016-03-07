package org.dsu.domain.model;

public enum Authority {

	ROLE_LOGIN("Login", "Allows the user to log in"), 
	ROLE_ADMIN("Admin", "Allows the admin environment");

	private String authorityName;
	private String description;

	private Authority(String authorityName, String description) {
		this.authorityName = authorityName;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public String getAuthorityName() {
		return authorityName;
	}
}
