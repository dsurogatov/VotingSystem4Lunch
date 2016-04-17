package org.dsu.dao.restaurant;

import org.dsu.dao.api.AbstractNamedDAO;
import org.dsu.domain.model.Restaurant;
import org.springframework.stereotype.Repository;

@Repository
public class RestaurantDAOImpl extends AbstractNamedDAO<Restaurant> implements RestaurantDAO {

	@Override
	public void deleteRelations(Long id) {
		// do nothing, forbid delete the restaurant with releations
	}

}
