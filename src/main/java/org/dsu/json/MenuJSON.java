package org.dsu.json;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.dsu.dto.api.BaseNamedDTO;

public class MenuJSON {

	private BaseNamedDTO resturantRef;
	private Date date;
	private List<DishJSON> dishes = new ArrayList<>();
	private List<Long> changedDishesIds = new ArrayList<>();
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
	
	public int getDishesCount() {
		return dishes.size();
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

	public List<Long> getChangedDishesIds() {
		return changedDishesIds;
	}

	public void setChangedDishesIds(List<Long> changedDishesIds) {
		this.changedDishesIds = changedDishesIds;
	}

}
