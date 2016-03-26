package org.dsu.service.restaurant;

import org.dsu.dao.api.CrudDao;
import org.dsu.dao.restaurant.RestaurantDAO;
import org.dsu.domain.model.Restaurant;
import org.dsu.dto.model.RestaurantDTO;
import org.dsu.service.api.AbstractNamedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RestaurantServiceImpl extends AbstractNamedService<RestaurantDTO, Restaurant> implements RestaurantService {

	@Autowired
	private RestaurantDAO dao;

	@Override
	protected CrudDao<Restaurant> getDao() {
		return dao;
	}
	
}
