package org.dsu.dao.restaurant;

import org.dsu.dao.api.AbstractNamedDao;
import org.dsu.domain.model.Restaurant;
import org.springframework.stereotype.Repository;

@Repository
public class RestaurantDAOImpl extends AbstractNamedDao<Restaurant> implements RestaurantDAO {

	@Override
	public void deleteRelations(Long id) {
		// TODO Auto-generated method stub
		
	}

}
