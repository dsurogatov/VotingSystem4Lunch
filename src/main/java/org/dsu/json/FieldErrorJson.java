/**
 * 
 */
package org.dsu.json;

/**
 * @author nescafe
 * Object for save validation error message
 */
public class FieldErrorJson {

	private final String field;
    private final String message;
 
    public FieldErrorJson(String field, String message) {
        this.field = field;
        this.message = message;
    }

	public String getField() {
		return field;
	}

	public String getMessage() {
		return message;
	}
}
