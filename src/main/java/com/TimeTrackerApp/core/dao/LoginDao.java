package com.TimeTrackerApp.core.dao;

import com.TimeTrackerApp.core.domain.LoginRequest;
import com.TimeTrackerApp.core.domain.User;

public interface LoginDao {

	User getLogin(LoginRequest loginRequest);

}
