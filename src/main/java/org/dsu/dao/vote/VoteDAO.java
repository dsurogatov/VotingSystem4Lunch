package org.dsu.dao.vote;

import java.time.LocalDate;

import org.dsu.dao.api.CrudDAO;
import org.dsu.domain.model.Restaurant;
import org.dsu.domain.model.User;
import org.dsu.domain.model.Vote;

public interface VoteDAO extends CrudDAO<Vote> {

	Vote findVoteByUserDateRestaurant(User user, LocalDate date, Restaurant restaurant);
}
