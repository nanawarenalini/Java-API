package com.TimeTrackerApp.core.exception;

public class ErrorInfo {
	
	private String message;
	
	private String propertyName;
	
	private Object invalidValue;

	public ErrorInfo() {
		super();
	}

	public ErrorInfo(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public Object getInvalidValue() {
		return invalidValue;
	}

	public void setInvalidValue(Object invalidValue) {
		this.invalidValue = invalidValue;
	}
	

}
