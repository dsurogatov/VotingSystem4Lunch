/**
 * 
 */
package org.dsu.domain.api;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author nescafe
 *
 */
@MappedSuperclass
public abstract class AbstractIdEntity implements IdEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;

	public AbstractIdEntity() {
		super();
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof AbstractIdEntity))
			return false;

		AbstractIdEntity that = (AbstractIdEntity) o;

		if (getId() != null ? !getId().equals(that.getId())
				: that.getId() != null)
			return false;

		return true;
	}

	public int hashCode() {
		return (getId() != null ? getId().hashCode() : 0);
	}
	
}
