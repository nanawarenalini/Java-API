package com.TimeTrackerApp.core.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.TimeTrackerApp.core.dao.AttendanceDao;
import com.TimeTrackerApp.core.domain.Attendance;
import com.TimeTrackerApp.core.domain.AttendanceRequest;
import com.TimeTrackerApp.core.domain.Employee;

@Repository
public class AttendanceDaoImpl implements AttendanceDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Attendance> getAll() {

		Session session = sessionFactory.getCurrentSession();
		
		Criteria criteria = session.createCriteria(Attendance.class);
		criteria.add(Restrictions.eq(Attendance.ACTIVE, true));
		
		@SuppressWarnings("unchecked")
		List<Attendance> attendanceList = criteria.list();
		
		return attendanceList;
	}


	@Override
	public void save(AttendanceRequest attendanceReq) {

		
		Session session = sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(Employee.class);
		criteria.add(Restrictions.eq(Employee.EMAIL_ID, attendanceReq.getEmailId()));
		
		Employee employee = (Employee) criteria.uniqueResult();
		
		Attendance attendance=new Attendance();
		
		attendance.setEndTime(attendanceReq.getEndTime());
		attendance.setStartTime(attendanceReq.getStartTime());
		attendance.setActive(true);
		
		attendance.setEmployee(employee);
		
		session.save(attendance);
	}

	@Override
	public List<Attendance> getByQuantity(Integer quantity, String emailId) {

		Session session = sessionFactory.getCurrentSession();
		
		Criteria criteria = session.createCriteria(Attendance.class);
		
		criteria.createAlias("employee", "employee", JoinType.LEFT_OUTER_JOIN);
		criteria.add(Restrictions.eq("employee.emailId", emailId));
		criteria.add(Restrictions.eq(Attendance.ACTIVE, true));
		criteria.setFirstResult(1);
		criteria.setMaxResults(quantity);
		
		@SuppressWarnings("unchecked")
		List<Attendance> attendanceList = criteria.list();
		
		return attendanceList;
	}


	@Override
	public Attendance getById(Integer id) {

		Session session = sessionFactory.getCurrentSession();
		
		Attendance attendance = (Attendance) session.get(Attendance.class, id);
		
		return attendance;
	}


	@Override
	public void update(AttendanceRequest attendanceReq, Integer id) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Attendance attendance = (Attendance) session.load(Attendance.class, id);
		
		attendance.setStartTime(attendanceReq.getStartTime());
		attendance.setEndTime(attendanceReq.getEndTime());
		attendance.setActive(true);
		
		Criteria criteria = session.createCriteria(Employee.class);
		criteria.add(Restrictions.eq(Employee.EMAIL_ID, attendanceReq.getEmailId()));
		
		Employee employee = (Employee) criteria.uniqueResult();
		
		attendance.setEmployee(employee);
		
		session.update(attendance);
	}


	@Override
	public void delete(Integer id) {

		Session session = sessionFactory.getCurrentSession();
		
		Attendance attendance = (Attendance) session.get(Attendance.class, id);
		
		attendance.setActive(false);
	}


	@Override
	public List<Attendance> getByEmail(String emailId) {

		Session session = sessionFactory.getCurrentSession();
		
		Criteria criteria = session.createCriteria(Attendance.class);
		
		criteria.createAlias("employee", "employee", JoinType.LEFT_OUTER_JOIN);
		criteria.add(Restrictions.eq("employee.emailId", emailId));
		criteria.add(Restrictions.eq(Attendance.ACTIVE, true));
		
		@SuppressWarnings("unchecked")
		List<Attendance> attendanceList = criteria.list();
		
		return attendanceList;
	}
}
