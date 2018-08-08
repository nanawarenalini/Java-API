package com.TimeTrackerApp.core.exception;

public class ResourceNotFoundException  extends CoreException{

	private static final long serialVersionUID = -5444330705111765746L;
	
	public ResourceNotFoundException(String errorCode) {
		super(errorCode);
	}
	
	public ResourceNotFoundException(String errorCode , String message) {
		super(errorCode ,message);
	}
}
