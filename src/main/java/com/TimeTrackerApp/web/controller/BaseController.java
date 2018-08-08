package com.TimeTrackerApp.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.validation.ConstraintViolation;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.TimeTrackerApp.core.exception.AuthFailureException;
import com.TimeTrackerApp.core.exception.ErrorInfo;
import com.TimeTrackerApp.core.exception.ResourceNotFoundException;
import com.TimeTrackerApp.core.exception.ValidationFailureException;

public class BaseController implements MessageSourceAware {

	private MessageSource messageSource;
	
	@ExceptionHandler(ResourceNotFoundException.class)
	protected ResponseEntity<String> handleResourcesNotFoundException(
			ResourceNotFoundException notFoundException) {
		String errorCode = notFoundException.getErrorCode();
		String msg = getMessage(errorCode);
		return new ResponseEntity<String>(msg, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ValidationFailureException.class)
	protected ResponseEntity<List<ErrorInfo>> handleValidationFailureException(
			ValidationFailureException validationFailureException) {
		List<ErrorInfo> errors = new ArrayList<ErrorInfo>();
		for(ConstraintViolation<?> cr:validationFailureException.getConstraintViolation()){
			
			ErrorInfo errorInfo = new ErrorInfo(cr.getMessage());
			errorInfo.setInvalidValue(cr.getInvalidValue());
			errorInfo.setPropertyName(cr.getPropertyPath().toString());
			errors.add(errorInfo);
		}
		return new ResponseEntity<List<ErrorInfo>>(errors, HttpStatus.BAD_REQUEST);
	}
	
	/*@ExceptionHandler(ValidationFailureException.class)
	protected ResponseEntity<String> handleValidationException(
			ValidationFailureException notValidException) {
		String errorCode = notValidException.getErrorCode();
		String msg = getMessage(errorCode);
		return new ResponseEntity<String>(msg, HttpStatus.BAD_REQUEST);
	}*/
	
	@ExceptionHandler(AuthFailureException.class)
	protected ResponseEntity<String> handleAuthException(
			AuthFailureException notFountException) {
		String errorCode = notFountException.getErrorCode();
		String msg = getMessage(errorCode);
		return new ResponseEntity<String>(msg, HttpStatus.BAD_REQUEST);
	}
	
	private String getMessage(String errorCode) {
		String msg=messageSource.getMessage(errorCode, null, Locale.US);
		return msg;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource= messageSource;
	}

	
}
