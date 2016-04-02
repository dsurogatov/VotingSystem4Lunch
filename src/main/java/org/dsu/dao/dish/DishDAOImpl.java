package org.dsu.dao.dish;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Query;

import org.dsu.common.DateUtils;
import org.dsu.common.ExceptionType;
import org.dsu.common.VotingSystemException;
import org.dsu.dao.api.AbstractNamedDao;
import org.dsu.domain.model.Dish;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

@Repository
public class DishDAOImpl extends AbstractNamedDao<Dish> implements DishDAO {

	@Override
	public void deleteRelations(Long id) {
		String sqlQuery = " DELETE FROM MenuItem o \n" +
				" WHERE o.dish.id = :dish_id \n";	
		Query query = entityManager.createQuery(sqlQuery);
		query.setParameter("dish_id", id);
		query.executeUpdate();		
	}

	@Override
	public BigDecimal getPriceByDishAndDate(Dish dish, LocalDate date) {
		Assert.notNull(dish);
		Assert.notNull(dish.getId());
		Assert.notNull(date);
		
		// TODO check time zones
		String qlQuery = "SELECT o.price FROM MenuItem o WHERE o.dish.id = :dish_id AND o.date = :date ";
		Query query = entityManager.createQuery(qlQuery);
		query.setParameter(0, dish.getId()).setParameter(1, DateUtils.asDate(date));
		@SuppressWarnings("unchecked")
		List<BigDecimal> prices = query.getResultList();
		
		if(prices.isEmpty()) {
			return null;
		} else if(prices.size() > 1) {
			throw new VotingSystemException(ExceptionType.WRONG_DATA_DB);
		} 
		return prices.iterator().next();
	}

}
