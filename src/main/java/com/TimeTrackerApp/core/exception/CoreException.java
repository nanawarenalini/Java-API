package com.TimeTrackerApp.core.exception;

public class CoreException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String errorCode;
	
	public CoreException(String errorCode) {
		this.errorCode=errorCode;
	}
	
	public CoreException(String errorCode, String message) {
		super(message);
		this.errorCode=errorCode;
	}
	
	public CoreException(String message , Throwable cause) {
		super(message,cause);
	}
	
	public CoreException(String errorCode, String message , Throwable cause) {
		super(message,cause);
		this.errorCode=errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

}
