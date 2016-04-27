package org.dsu.service.vote;

import java.time.LocalDate;
import java.util.List;

import org.dsu.dto.model.RestaurantDTO;
import org.dsu.json.VoteJSON;

public interface VoteService {

	void vote(VoteJSON voteJSON);
	List<RestaurantDTO> getVotedResult(LocalDate date);
}
