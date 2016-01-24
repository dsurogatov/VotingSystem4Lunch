/**
 * 
 */
package org.dsu.dto.api;

/**
 * @author nescafe
 * Define DTO with a name field
 */
public interface NamedDTO extends IdDTO {

	/**
	 * @return name 
	 */
	String getName();
}
