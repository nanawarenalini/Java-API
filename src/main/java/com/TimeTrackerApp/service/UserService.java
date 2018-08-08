package com.TimeTrackerApp.service;

import com.TimeTrackerApp.core.domain.LoginRequest;
import com.TimeTrackerApp.core.domain.User;

public interface UserService {

	void save(User user);

	User getLogin(LoginRequest loginRequest);

}
