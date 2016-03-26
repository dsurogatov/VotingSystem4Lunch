/**
 * 
 */
package org.dsu.json;

import java.util.ArrayList;
import java.util.List;

/**
 * @author nescafe
 * DTO for set validation of error
 */
public class ValidationErrorJSON {
	 
    private final List<FieldErrorJSON> fieldErrors = new ArrayList<>();
 
    public ValidationErrorJSON() {
 
    }
 
    public void addFieldError(String path, String message) {
        FieldErrorJSON error = new FieldErrorJSON(path, message);
        fieldErrors.add(error);
    }

	public List<FieldErrorJSON> getFieldErrors() {
		return new ArrayList<FieldErrorJSON>(fieldErrors);
	}
 
}