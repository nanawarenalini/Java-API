package com.TimeTrackerApp.service;

import com.TimeTrackerApp.core.domain.LoginRequest;
import com.TimeTrackerApp.core.domain.UserPrincipal;

public interface LoginService {

	UserPrincipal getLogin(LoginRequest loginRequest);

}
