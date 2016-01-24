/**
 * 
 */
package org.dsu.dao.api;

/**
 * @author nescafe Define the columns name and the order for sorting in query
 */
public class SortProp {

	private String columnName;
	private boolean asc;

	public SortProp(String columnName, boolean asc) {
		super();
		this.columnName = columnName;
		this.asc = asc;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public boolean isAsc() {
		return asc;
	}

	public void setAsc(boolean asc) {
		this.asc = asc;
	}
}
