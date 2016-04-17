package org.dsu.service;

import org.dsu.common.VotingSystemException;
import org.dsu.dao.api.CrudDAO;
import org.dsu.dao.restaurant.RestaurantDAO;
import org.dsu.domain.api.IdEntity;
import org.dsu.domain.model.Restaurant;

public class ServiceHelper {
	
	private ServiceHelper() {
		
	}

	public static <I extends IdEntity> I findEntityByIdThrowExceptionIfNotFinded(Long id, CrudDAO<I> dao, Class<I> clazz) {
		I instance = dao.findById(id);
		if (instance == null) {
			VotingSystemException.throwEntityNotFound(clazz);
		}
		return instance;
	}
	
	public static Restaurant findRestaurant(Long id, RestaurantDAO dao) {
		return findEntityByIdThrowExceptionIfNotFinded(id, dao, Restaurant.class);
	}
}
