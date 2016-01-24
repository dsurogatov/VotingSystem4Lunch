/**
 * 
 */
package org.dsu.dto.api;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author nescafe
 *
 */
public class BaseNamedDTO extends BaseIdDTO implements NamedDTO {

	@NotEmpty
	@Size(max=1000, message="validation.maxsize.field")
	private String name;
	
	/* (non-Javadoc)
	 * @see com.dsu.dto.NamableDTO#getName()
	 */
	@Override
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof BaseNamedDTO))
			return false;
		if (!super.equals(o))
			return false;

		BaseNamedDTO that = (BaseNamedDTO) o;

		if (getName() != null ? !getName().equals(that.getName()) : that
				.getName() != null)
			return false;

		return true;
	}

	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (getName() != null ? getName().hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "NamedDTO [id=" + getId() + ", name=" + name + "]";
	}
}
