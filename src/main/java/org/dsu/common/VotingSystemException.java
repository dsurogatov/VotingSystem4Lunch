/**
 * 
 */
package org.dsu.common;

import java.util.ArrayList;
import java.util.List;

import org.dsu.json.FieldErrorJSON;

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
	
	public static void throwValidationError(List<FieldErrorJSON> fieldErrors) {
		VotingSystemException res = new VotingSystemException(ExceptionType.VALIDATE_ERROR);
		res.setFieldErrors(fieldErrors);
		throw res;
	}
	
	private ExceptionType type;
	private Class<?> entityClass;
	private List<FieldErrorJSON> fieldErrors = new ArrayList<>();
	
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

	public List<FieldErrorJSON> getFieldErrors() {
		return new ArrayList<>(fieldErrors);
	}

	public void setFieldErrors(List<FieldErrorJSON> fieldErrors) {
		this.fieldErrors = new ArrayList<>(fieldErrors);
	}
	
}
