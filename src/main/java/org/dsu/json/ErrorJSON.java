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
public class ErrorJSON {
	 
    private final List<FieldErrorJSON> fieldErrors = new ArrayList<>();
    private String title;
    private String message;
 
    public ErrorJSON() {
 
    }
 
    public void addFieldError(String path, String message) {
        FieldErrorJSON error = new FieldErrorJSON(path, message);
        fieldErrors.add(error);
    }

	public List<FieldErrorJSON> getFieldErrors() {
		return new ArrayList<FieldErrorJSON>(fieldErrors);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
 
}