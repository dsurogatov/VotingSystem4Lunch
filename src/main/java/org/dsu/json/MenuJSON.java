package org.dsu.json;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.dsu.dto.api.BaseNamedDTO;

public class MenuJSON {

	private BaseNamedDTO resturantRef;
	private Date date;
	private List<DishJSON> dishes;
	private boolean editable;

	public BaseNamedDTO getResturantRef() {
		return resturantRef;
	}

	public void setResturantRef(BaseNamedDTO resturantRef) {
		this.resturantRef = resturantRef;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<DishJSON> getDishes() {
		return new ArrayList<>(dishes);
	}

	public void setDishes(List<DishJSON> dishes) {
		this.dishes = new ArrayList<>(dishes);
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}
}
