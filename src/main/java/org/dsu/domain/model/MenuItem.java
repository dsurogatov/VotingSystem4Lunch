package org.dsu.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.dsu.domain.api.AbstractIdEntity;

@Entity
@Table(name="MENU_ITEM", 
	uniqueConstraints = @UniqueConstraint(columnNames = {"DISH_ID", "ITEM_DATE"}))
public class MenuItem extends AbstractIdEntity {

	@ManyToOne
    @JoinColumn(name="DISH_ID", nullable=false)
    private Dish dish;
	
	@Column(precision=12, scale=2, nullable=false)
	private BigDecimal price;
	
	@Column(name="ITEM_DATE", nullable=false)
	private LocalDate date;

	public Dish getDish() {
		return dish;
	}

	public void setDish(Dish dish) {
		this.dish = dish;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
}
