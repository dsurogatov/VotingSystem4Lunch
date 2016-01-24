package org.dsu.json;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

public class PageJson {

	private int start;
	@Max(100)
	private int size;
	@Size(max=100, message="validation.maxsize.field")
	private String findingValue;
	@Size(max=100, message="validation.maxsize.field")
	private String sortingField;
	private boolean sortAsc;

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getFindingValue() {
		return findingValue;
	}

	public void setFindingValue(String findingValue) {
		this.findingValue = findingValue;
	}

	public String getSortingField() {
		return sortingField;
	}

	public void setSortingField(String sortingField) {
		this.sortingField = sortingField;
	}

	public boolean isSortAsc() {
		return sortAsc;
	}

	public void setSortAsc(boolean sortAsc) {
		this.sortAsc = sortAsc;
	}

	@Override
	public String toString() {
		return "PageJson [start=" + start + ", size=" + size + ", findingValue=" + findingValue + ", sortingField=" + sortingField + ", sortAsc=" + sortAsc
		        + "]";
	}
}
