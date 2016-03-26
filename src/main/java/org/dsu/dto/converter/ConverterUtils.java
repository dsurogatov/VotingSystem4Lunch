/**
 * 
 */
package org.dsu.dto.converter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dsu.common.ExceptionType;
import org.dsu.common.VotingSystemException;
import org.dsu.domain.api.IdEntity;
import org.dsu.domain.model.Restaurant;
import org.dsu.domain.model.Role;
import org.dsu.domain.model.User;
import org.dsu.dto.api.IdDTO;
import org.dsu.dto.model.RestaurantDTO;
import org.dsu.dto.model.RoleDTO;
import org.dsu.dto.model.UserDTO;

/**
 * @author nescafe Some usefull methods to covert objects, entities and dtos
 * 
 **/
public class ConverterUtils {

	private static final Map<Class<? extends IdDTO>, Converter<? extends IdEntity, ? extends IdDTO>> dtoCoverters = new HashMap<>();
	private static final Map<Class<? extends IdEntity>, Converter<? extends IdEntity, ? extends IdDTO>> entityCoverters = new HashMap<>();

	static {
		UserConverter userConverter = new UserConverter();
		dtoCoverters.put(UserDTO.class, userConverter);
		entityCoverters.put(User.class, userConverter);

		RoleConverter roleConverter = new RoleConverter();
		dtoCoverters.put(RoleDTO.class, roleConverter);
		entityCoverters.put(Role.class, roleConverter);

		RestaurantConverter restaurantConverter = new RestaurantConverter();
		dtoCoverters.put(RestaurantDTO.class, restaurantConverter);
		entityCoverters.put(Restaurant.class, restaurantConverter);
	}

	/** Convert entity to dto
	 * @param entity
	 * @return
	 */
	public static <I extends IdEntity, D extends IdDTO> D toDTO(I entity) {
		if (entity == null) {
			return null;
		}
		if (!entityCoverters.containsKey(entity.getClass())) {
			throw new VotingSystemException(ExceptionType.CONVERTER_NOT_FINDED);
		}

		@SuppressWarnings("unchecked")
		Converter<I, D> converter = (Converter<I, D>) entityCoverters.get(entity.getClass());
		return converter.toDTO(entity);
	}
	
	/** Convert entity list to dto list
	 * @param entityList
	 * @return
	 */
	public static <I extends IdEntity, D extends IdDTO> List<D> toDTOList(List<I> entityList) {
		if (entityList == null) {
			return null;
		}
		
		List<D> retList = new ArrayList<>();
		for(I entity : entityList) {
			retList.add(toDTO(entity));
		}
		return retList;
	}

	/** Convert dto to entity
	 * @param dto
	 * @return
	 */
	public static <I extends IdEntity, D extends IdDTO> I toEntity(D dto) {
		if (dto == null) {
			return null;
		}
		if (!dtoCoverters.containsKey(dto.getClass())) {
			throw new VotingSystemException(ExceptionType.CONVERTER_NOT_FINDED);
		}

		@SuppressWarnings("unchecked")
		Converter<I, D> converter = (Converter<I, D>) dtoCoverters.get(dto.getClass());
		return converter.toEntity(dto);
	}

	private ConverterUtils() {
	}
}
