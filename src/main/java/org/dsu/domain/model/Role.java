package org.dsu.domain.model;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.dsu.domain.api.AbstractNamedEntity;

@Entity
@Table(name="t_role")
public class Role extends AbstractNamedEntity {

	@ElementCollection(targetClass = Authority.class, fetch = FetchType.EAGER)
	@CollectionTable(
	        name = "role_authority", 
	        joinColumns = @JoinColumn(name = "role_id")
	)
    @Column(name = "authority", nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<Authority> authorities;

	/** Get roles authorites
	 * @return - the set of authorites
	 */
	public Set<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}
}
