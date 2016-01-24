/**
 * 
 */
package org.dsu.controller.rest;

import java.util.List;
import java.util.Locale;

import org.dsu.common.ExceptionType;
import org.dsu.common.VotingSystemException;
import org.dsu.json.ValidationErrorJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author nescafe
 * Exception handler for rest controllers
 */
@ControllerAdvice
public class RestErrorHandler {
 
    private static final Logger LOGGER = LoggerFactory.getLogger(RestErrorHandler.class);
    
    @Autowired
    private MessageSource messageSource;
 
    @ExceptionHandler(VotingSystemException.class)
    public ResponseEntity<String> handleMskyMoneyException(VotingSystemException ex) {
    	if (ex.getType() == ExceptionType.ENTITY_NOT_FINDED) {
    		LOGGER.debug("handling 404 error ***");
    		return new ResponseEntity<String>("Entity not found", HttpStatus.NOT_FOUND);
    	}
    	
    	if(ex.getCause() != null) {
    		LOGGER.error("The internal exception:", ex);
    	} else {
    		LOGGER.error("Type of exception is {}.", ex.getType());    		
    	}
    	return new ResponseEntity<String>("Internal error", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorJson processValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
 
        return processFieldErrors(fieldErrors);
    }
 
    private ValidationErrorJson processFieldErrors(List<FieldError> fieldErrors) {
        ValidationErrorJson dto = new ValidationErrorJson();
 
        for (FieldError fieldError: fieldErrors) {
            String localizedErrorMessage = resolveLocalizedErrorMessage(fieldError);
            dto.addFieldError(fieldError.getField(), localizedErrorMessage);
        }
 
        return dto;
    }
 
    private String resolveLocalizedErrorMessage(FieldError fieldError) {
        Locale currentLocale =  LocaleContextHolder.getLocale();
        String localizedErrorMessage = messageSource.getMessage(fieldError, currentLocale); 
//        If the message was not found, return the most accurate field error code instead.
//        You can remove this check if you prefer to get the default error message.
        if (localizedErrorMessage.equals(fieldError.getDefaultMessage())) {
//            String[] fieldErrorCodes = fieldError.getCodes();
//            localizedErrorMessage = fieldErrorCodes[0];
            localizedErrorMessage = messageSource.getMessage(fieldError.getDefaultMessage(), fieldError.getArguments(), currentLocale);
        }
        LOGGER.debug(localizedErrorMessage);
        
        return localizedErrorMessage;
    }
}