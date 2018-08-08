package com.TimeTrackerApp.core.exception;

public class AuthFailureException  extends CoreException{

	private static final long serialVersionUID = -5444330705111765746L;
	
	public AuthFailureException(String errorCode) {
		super(errorCode);
	}
	
	public AuthFailureException(String errorCode , String message) {
		super(errorCode ,message);
	}
}
