package org.dsu.service.restaurant;

import org.dsu.common.ExceptionType;
import org.dsu.common.VotingSystemException;
import org.dsu.dao.api.CrudDAO;
import org.dsu.dao.restaurant.RestaurantDAO;
import org.dsu.domain.model.Restaurant;
import org.dsu.dto.model.RestaurantDTO;
import org.dsu.service.api.AbstractNamedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
@Transactional
public class RestaurantServiceImpl extends AbstractNamedService<RestaurantDTO, Restaurant> implements RestaurantService {

	@Autowired
	private RestaurantDAO dao;

	@Override
	protected CrudDAO<Restaurant> getDao() {
		return dao;
	}
	
	@Override
	public void delete(Long id) {
		Assert.notNull(id);
		Restaurant restaurant = dao.findById(id);
		if (restaurant == null) {
			return;
		}
		
		if(!restaurant.getDishes().isEmpty()) {
			throw new VotingSystemException(ExceptionType.DENY_REMOVE_ENTITY_WITH_RELATIVES);
		}

		super.delete(id);
	}
}
