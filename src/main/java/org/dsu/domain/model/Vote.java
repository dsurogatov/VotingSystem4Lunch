package org.dsu.domain.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.dsu.domain.api.AbstractIdEntity;

@Entity
@Table(name="VOTE", 
	uniqueConstraints = @UniqueConstraint(columnNames = {"USER_ID", "RESTAURANT_ID", "VOTE_DATE"}))
public class Vote extends AbstractIdEntity {

	@ManyToOne
	@JoinColumn(name="USER_ID", nullable=false)
	private User user;

	@ManyToOne
    @JoinColumn(name="RESTAURANT_ID", nullable=false)
    private Restaurant restaurant;
	
	@Column(name="VOTE_DATE", nullable=false)
	private LocalDate date;
	
	@Column(name="VOTE_TIME", nullable=false)	
	private LocalTime time;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}
}
