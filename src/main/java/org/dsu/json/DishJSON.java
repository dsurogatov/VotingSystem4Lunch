package org.dsu.json;

import java.math.BigDecimal;

import org.dsu.dto.api.BaseNamedDTO;

public class DishJSON extends BaseNamedDTO {

	private BigDecimal price;

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}
