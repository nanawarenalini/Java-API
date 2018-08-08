package com.TimeTrackerApp.core.dao;

import com.TimeTrackerApp.core.domain.LoginRequest;
import com.TimeTrackerApp.core.domain.User;

public interface UserDao {

	void save(User user);

	User getLogin(LoginRequest loginRequest);

	User checkEmail(String emailId);

}
