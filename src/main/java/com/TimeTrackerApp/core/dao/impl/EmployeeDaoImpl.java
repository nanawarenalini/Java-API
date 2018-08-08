package com.TimeTrackerApp.core.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.TimeTrackerApp.core.dao.EmployeeDao;
import com.TimeTrackerApp.core.domain.Employee;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(Employee employee) {

		Session session = sessionFactory.getCurrentSession();
		
		employee.setActive(true);
		session.save(employee);
	}

	@Override
	public List<Employee> getAll() {

		Session session = sessionFactory.getCurrentSession();
		
		Criteria criteria = session.createCriteria(Employee.class);
		criteria.add(Restrictions.eq(Employee.ACTIVE, Boolean.TRUE));
		
		@SuppressWarnings("unchecked")
		List<Employee> employees = criteria.list();
		
		return employees;
	}

	@Override
	public Employee getByEmailId(Integer id) {

		Session session = sessionFactory.getCurrentSession();
		
		Employee employee = (Employee) session.get(Employee.class, id);
		
		return employee;
	}

	@Override
	public void delete(Integer id) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Employee employee = (Employee) session.get(Employee.class, id);
		
		employee.setActive(false);
	}

	@Override
	public Employee checkEmail(String emailId) {

		Session session = sessionFactory.getCurrentSession();
		
		Criteria criteria = session.createCriteria(Employee.class);
		
		criteria.add(Restrictions.eq(Employee.EMAIL_ID, emailId));
		criteria.add(Restrictions.eq(Employee.ACTIVE, true));
		
		Employee employee = (Employee) criteria.uniqueResult();
		
		return employee;
	}

	@Override
	public void update(Employee employee, Integer id) {

		Session session = sessionFactory.getCurrentSession();
		
		Employee employee1 = (Employee) session.load(Employee.class, id);
		
		employee1.setFirstName(employee.getFirstName());
		employee1.setLastName(employee.getLastName());
		employee1.setEmailId(employee.getEmailId());
		employee1.setGender(employee.getGender());
		employee1.setImagePath(employee.getImagePath());
		
		session.update(employee1);
	}

	@Override
	public Employee getById(Integer id) {

		Session session = sessionFactory.getCurrentSession();
		
		Employee employee = (Employee) session.get(Employee.class, id);
		
		return employee;
	}
}
