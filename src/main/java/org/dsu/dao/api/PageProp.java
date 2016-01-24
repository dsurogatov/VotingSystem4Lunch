/**
 * 
 */
package org.dsu.dao.api;

/**
 * @author nescafe Define params for the setFirstResult and the setMaxResult
 *         methods in the EntityManager
 */
public class PageProp {

	private int firstResult;
	private int maxResult;
	
	public PageProp(int firstResult, int maxResult) {
		super();
		this.firstResult = firstResult;
		this.maxResult = maxResult;
	}
	
	public int getFirstResult() {
		return firstResult;
	}

	public void setFirstResult(int firstResult) {
		this.firstResult = firstResult;
	}

	public int getMaxResult() {
		return maxResult;
	}

	public void setMaxResult(int maxResult) {
		this.maxResult = maxResult;
	}
}
