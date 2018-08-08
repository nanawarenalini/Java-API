package com.TimeTrackerApp.core.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.TimeTrackerApp.core.dao.UserDao;
import com.TimeTrackerApp.core.domain.LoginRequest;
import com.TimeTrackerApp.core.domain.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(User user) {

		Session session = sessionFactory.getCurrentSession();
		
		session.save(user);
	}

	@Override
	public User getLogin(LoginRequest loginRequest) {

		Session session = sessionFactory.getCurrentSession();
		
		Criteria criteria = session.createCriteria(User.class);
		
		criteria.add(Restrictions.eq(User.EMAIL_ID, loginRequest.getEmailId()));
		criteria.add(Restrictions.eq(User.PASSWORD, loginRequest.getPassword()));
		
		User user = (User) criteria.uniqueResult(); 
		return user;
	}

	@Override
	public User checkEmail(String emailId) {

		Session session = sessionFactory.getCurrentSession();
		
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq(User.EMAIL_ID, emailId));
		
		User user1 = (User) criteria.uniqueResult();
		return user1;
	}
	
	
}
