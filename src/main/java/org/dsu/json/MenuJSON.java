package org.dsu.json;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.dsu.dto.api.BaseNamedDTO;

import com.fasterxml.jackson.annotation.JsonFormat;

public class MenuJSON {
	
	private BaseNamedDTO resturantRef;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")  
	private LocalDate date;
	
	private List<DishJSON> dishes = new ArrayList<>();
	private List<Long> changedDishesIds = new ArrayList<>();
	private boolean editable;

	public BaseNamedDTO getResturantRef() {
		return resturantRef;
	}

	public void setResturantRef(BaseNamedDTO resturantRef) {
		this.resturantRef = resturantRef;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
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
