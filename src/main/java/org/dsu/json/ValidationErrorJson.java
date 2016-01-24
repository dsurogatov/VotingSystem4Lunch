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
public class ValidationErrorJson {
	 
    private final List<FieldErrorJson> fieldErrors = new ArrayList<>();
 
    public ValidationErrorJson() {
 
    }
 
    public void addFieldError(String path, String message) {
        FieldErrorJson error = new FieldErrorJson(path, message);
        fieldErrors.add(error);
    }

	public List<FieldErrorJson> getFieldErrors() {
		return new ArrayList<FieldErrorJson>(fieldErrors);
	}
 
}