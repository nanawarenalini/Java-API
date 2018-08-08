 package com.TimeTrackerApp.core.exception;

import java.util.Set;

import javax.validation.ConstraintViolation;

public class ValidationFailureException extends CoreException{

	private static final long serialVersionUID = 1L;

	private Set<ConstraintViolation<?>> constraintViolation;
	
	public ValidationFailureException(String errorCode, Set<ConstraintViolation<?>> constraintViolation) {
		super(errorCode);
		this.constraintViolation = constraintViolation;
	}
	
	public Set<ConstraintViolation<?>> getConstraintViolation() {
		return constraintViolation;
	}

}
