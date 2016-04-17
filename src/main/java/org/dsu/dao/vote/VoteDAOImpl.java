package org.dsu.dao.vote;

import java.time.LocalDate;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.dsu.dao.api.AbstractCrudDAO;
import org.dsu.domain.model.Restaurant;
import org.dsu.domain.model.User;
import org.dsu.domain.model.Vote;
import org.dsu.domain.model.Vote_;
import org.springframework.stereotype.Repository;

@Repository
public class VoteDAOImpl extends AbstractCrudDAO<Vote> implements VoteDAO {
	
	@Override
	public void deleteRelations(Long id) {
		// do nothing
	}

	@Override
	public Vote findVoteByUserDateRestaurant(User user, LocalDate date, Restaurant restaurant) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Vote> criteria = builder.createQuery(Vote.class);
		
		Root<Vote> from = criteria.from(Vote.class);
		
		Predicate conditionUser = builder.equal(from.get(Vote_.user), user);
		Predicate conditionDate = builder.equal(from.get(Vote_.date), date);
		Predicate conditionRestaurant = builder.equal(from.get(Vote_.restaurant), restaurant);
		
		criteria.where(builder.and(conditionUser, conditionDate, conditionRestaurant));
		
		TypedQuery<Vote> query = entityManager.createQuery(criteria); 
		try {
			Vote result = query.getSingleResult();
			return result;
		} catch(Exception e) {
			return null;
		}
	}

}
