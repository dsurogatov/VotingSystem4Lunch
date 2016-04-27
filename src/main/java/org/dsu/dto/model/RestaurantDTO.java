package org.dsu.dto.model;

import org.dsu.dto.api.BaseNamedDTO;

public class RestaurantDTO extends BaseNamedDTO {

	private Integer voteCnt;

	public Integer getVoteCnt() {
		return voteCnt;
	}

	public void setVoteCnt(Integer voteCnt) {
		this.voteCnt = voteCnt;
	}
}
