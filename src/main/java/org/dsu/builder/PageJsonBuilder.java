package org.dsu.builder;

import org.dsu.json.PageJson;

public class PageJsonBuilder {

	private int start;
	private int size;
	private String findingValue;
	private String sortingField;
	private boolean sortAsc;
	
	public PageJsonBuilder start(int start) {
		this.start = start;
		return this;
	}
	
	public PageJsonBuilder size(int size) {
		this.size = size;
		return this;
	}
	
	public PageJsonBuilder findingValue(String findingValue) {
		this.findingValue = findingValue;
		return this;
	}
	
	public PageJsonBuilder sortingField(String sortingField) {
		this.sortingField = sortingField;
		return this;
	}
	
	public PageJsonBuilder sortAsc(boolean sortAsc) {
		this.sortAsc = sortAsc;
		return this;
	}
	
	public PageJson build() {
		PageJson page = new PageJson();
		page.setStart(start);
		page.setSize(size);
		page.setFindingValue(findingValue);
		page.setSortingField(sortingField);
		page.setSortAsc(sortAsc);
		return page;
	}
	
}
