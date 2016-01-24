/**
 * 
 */
package org.dsu.common;

/**
 * @author nescafe
 * Main project's exception
 */
public class VotingSystemException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public static void throwEntityNotFound(Class<?> entityClass) {
		VotingSystemException res = new VotingSystemException(ExceptionType.ENTITY_NOT_FINDED);
		res.setEntityClass(entityClass);
		throw res;
	}
	
	private ExceptionType type;
	private Class<?> entityClass;
	
	public VotingSystemException(ExceptionType type) {
		this(type, null);
	}

	public VotingSystemException(ExceptionType type, Exception couse) {
		super(couse);
		this.type = type;
	}

	/** Get exception's type
	 * @return
	 */
	public ExceptionType getType() {
		return type;
	}

	public Class<?> getEntityClass() {
		return entityClass;
	}

	private void setEntityClass(Class<?> entityClass) {
		this.entityClass = entityClass;
	}
	
}
