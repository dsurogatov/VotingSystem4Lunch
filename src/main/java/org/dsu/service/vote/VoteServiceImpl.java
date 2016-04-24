package org.dsu.service.vote;

import java.time.LocalDate;
import java.time.LocalTime;

import org.dsu.common.DateUtils;
import org.dsu.common.ExceptionType;
import org.dsu.common.VotingSystemException;
import org.dsu.dao.restaurant.RestaurantDAO;
import org.dsu.dao.user.UserDAO;
import org.dsu.dao.vote.VoteDAO;
import org.dsu.domain.model.Restaurant;
import org.dsu.domain.model.User;
import org.dsu.domain.model.Vote;
import org.dsu.dto.model.RestaurantDTO;
import org.dsu.json.VoteJSON;
import org.dsu.service.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
@Transactional
public class VoteServiceImpl implements VoteService {
	
	@Autowired
	private RestaurantDAO restaurantDAO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private VoteDAO voteDAO;
	
	@Value( "${last_time_of_voting}" )
	private String lastTimeOfVoting;

	@Override
	public void vote(VoteJSON voteJSON) {
		
		Assert.notNull(voteJSON);
		Assert.notNull(voteJSON.getResturantRef());
		Assert.notNull(voteJSON.getResturantRef().getId());
		Assert.notNull(voteJSON.getDateTime());
		
		// get the restaurant, if not found then throw exception
		Restaurant restaurant = ServiceHelper.findRestaurant(voteJSON.getResturantRef().getId(), restaurantDAO);

		// get user if from security context
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userDAO.findUserByLogin(userDetails.getUsername());
		if(user == null) {
			VotingSystemException.throwEntityNotFound(User.class);
		}
		
		// convert datetime string from dto to LocalDateTime
		LocalDate dateVote = voteJSON.getDateTime().toLocalDate();
		LocalTime timeVote = voteJSON.getDateTime().toLocalTime();
		
		// find vote by user, date, restaurant
		Vote vote = voteDAO.findVoteByUserDateRestaurant(user, dateVote, restaurant);
		if(vote != null) {
		
			// then compare time with 11:00
			LocalTime endOfTimeVoting = DateUtils.parse2Time(lastTimeOfVoting);
			if(timeVote.compareTo(endOfTimeVoting) >= 0) {
				throw new VotingSystemException(ExceptionType.END_OF_VOTING_TIME);
			} else {
				vote.setTime(timeVote);
			}
		} else {
			vote = new Vote();
			vote.setDate(dateVote);
			vote.setTime(timeVote);
			vote.setRestaurant(restaurant);
			vote.setUser(user);
		}
		voteDAO.save(vote);
	}

	@Override
	public RestaurantDTO getElectedRestaurant(LocalDate date) {
		// TODO Auto-generated method stub
		return null;
	}

}
