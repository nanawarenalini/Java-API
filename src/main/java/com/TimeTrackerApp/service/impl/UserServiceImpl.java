package com.TimeTrackerApp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.ValidationFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.TimeTrackerApp.core.dao.UserDao;
import com.TimeTrackerApp.core.domain.LoginRequest;
import com.TimeTrackerApp.core.domain.User;
import com.TimeTrackerApp.core.exception.AuthFailureException;
import com.TimeTrackerApp.core.exception.ErrorCode;
import com.TimeTrackerApp.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public void save(User user) {

		User user1 = userDao.checkEmail(user.getEmailId());
		
		if(user1 == null) {
		userDao.save(user);
		}
		else {
			throw new ValidationFailureException("Email Id already exist!");
		}
	}

	@Override
	public User getLogin(LoginRequest loginRequest) {

		User user = userDao.getLogin(loginRequest);
		
		if(user == null) {
			throw new AuthFailureException(ErrorCode.UserError.USER_NOT_AUTHETICATED);
		}
		return user;
	}
	
	
}
