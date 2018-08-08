package com.TimeTrackerApp.core.exception;

public class ErrorCode {
	
	public static class UserError{
		
		public static final String USER_NOT_FOUND = "EC_US_01";
		
		public static final String USER_NOT_AUTHETICATED = "EC_US_02";
	}
	
	public static class EmployeeError{
		
		public static final String EMPLOYEE_NOT_FOUND = "EC_EMP_01";
		
		public static final String EMAIL_NOT_VALID = "EC_EMP_02";
	}

	public static class AttendanceError{
		
		public static final String ATTENDANCE_NOT_FOUND = "EC_ATD_01";
		
	}
}
