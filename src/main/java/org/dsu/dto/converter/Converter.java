/**
 * 
 */
package org.dsu.dto.converter;

import org.dsu.domain.api.IdEntity;
import org.dsu.dto.api.IdDTO;

/**
 * @author nescafe
 * Defines base methods to covert between entity and dto 
 */
public interface Converter<I extends IdEntity, D extends IdDTO> {

	/** Convert entity to DTO object
	 * @param entity
	 * @return converted DTO object
	 */
	D toDTO(I entity);
	
	/** Convert DTO object to entity
	 * @param dto
	 * @return converted entity
	 */
	I toEntity(D dto);
}
