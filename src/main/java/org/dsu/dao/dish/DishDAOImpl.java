package org.dsu.dao.dish;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Query;

import org.dsu.common.ExceptionType;
import org.dsu.common.VotingSystemException;
import org.dsu.dao.api.AbstractNamedDAO;
import org.dsu.domain.model.Dish;
import org.dsu.domain.model.MenuItem;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

@Repository
public class DishDAOImpl extends AbstractNamedDAO<Dish> implements DishDAO {

	@Override
	public void deleteRelations(Long id) {
		String sqlQuery = " DELETE FROM MenuItem o \n" +
				" WHERE o.dish.id = :dish_id \n";	
		Query query = entityManager.createQuery(sqlQuery);
		query.setParameter("dish_id", id);
		query.executeUpdate();		
	}

	@Override
	public MenuItem getMenuItemByDishAndDate(Dish dish, LocalDate date) {
		Assert.notNull(dish);
		Assert.notNull(dish.getId());
		Assert.notNull(date);
		
		String qlQuery = "SELECT o FROM MenuItem o WHERE o.dish.id = ?1 AND o.date = ?2 ";
		Query query = entityManager.createQuery(qlQuery);
		query.setParameter(1, dish.getId()).setParameter(2, date);
		@SuppressWarnings("unchecked")
		List<MenuItem> prices = query.getResultList();
		
		if(prices.isEmpty()) {
			return null;
		} else if(prices.size() > 1) {
			throw new VotingSystemException(ExceptionType.WRONG_DATA_DB);
		} 
		return prices.iterator().next();
	}

}
