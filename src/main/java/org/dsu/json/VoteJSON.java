package org.dsu.json;

import java.time.LocalDateTime;

import org.dsu.dto.api.BaseNamedDTO;

import com.fasterxml.jackson.annotation.JsonFormat;

public class VoteJSON {

	private BaseNamedDTO resturantRef;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm") 
	private LocalDateTime dateTime;
	
	public BaseNamedDTO getResturantRef() {
		return resturantRef;
	}
	public void setResturantRef(BaseNamedDTO resturantRef) {
		this.resturantRef = resturantRef;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
}
