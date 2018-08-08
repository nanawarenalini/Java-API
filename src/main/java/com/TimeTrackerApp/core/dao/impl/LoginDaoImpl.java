package com.TimeTrackerApp.core.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.TimeTrackerApp.core.dao.LoginDao;
import com.TimeTrackerApp.core.domain.LoginRequest;
import com.TimeTrackerApp.core.domain.User;

@Repository
public class LoginDaoImpl implements LoginDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public User getLogin(LoginRequest loginRequest) {

		Session session = sessionFactory.getCurrentSession();
		
		Criteria criteria = session.createCriteria(User.class);
		
		criteria.add(Restrictions.eq(User.EMAIL_ID, loginRequest.getEmailId()));
		criteria.add(Restrictions.eq(User.PASSWORD, loginRequest.getPassword()));
		
		User user = (User) criteria.uniqueResult();
		
		return user;
	}

}
