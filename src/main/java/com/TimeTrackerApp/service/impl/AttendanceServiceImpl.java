package com.TimeTrackerApp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.TimeTrackerApp.core.dao.AttendanceDao;
import com.TimeTrackerApp.core.dao.EmployeeDao;
import com.TimeTrackerApp.core.domain.Attendance;
import com.TimeTrackerApp.core.domain.AttendanceRequest;
import com.TimeTrackerApp.core.domain.Employee;
import com.TimeTrackerApp.core.exception.ErrorCode;
import com.TimeTrackerApp.core.exception.ResourceNotFoundException;
import com.TimeTrackerApp.service.AttendanceService;

@Service
@Transactional
public class AttendanceServiceImpl implements AttendanceService {

	@Autowired
	private AttendanceDao attendanceDao;
	
	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public List<Attendance> getAll() {

		List<Attendance> attendanceList = attendanceDao.getAll();
		return attendanceList;
	}

	@Override
	public void save(AttendanceRequest attendance) {

		Employee employee = employeeDao.checkEmail(attendance.getEmailId());
		
		if(employee == null) {
			throw new ResourceNotFoundException(ErrorCode.EmployeeError.EMPLOYEE_NOT_FOUND);
		}
		else {
			attendanceDao.save(attendance);
		}
	}

	@Override
	public List<Attendance> getByQuantity(Integer index, String emailId) {

		List<Attendance> attendenceList = null;
		
		if(index == null) {
			attendenceList = attendanceDao.getByEmail(emailId);
		}
		else {
			attendenceList = attendanceDao.getByQuantity(index, emailId);
		}
		return attendenceList;
	}

	@Override
	public void update(AttendanceRequest attendanceReq, Integer id) {
	

		Attendance attendance = attendanceDao.getById(id);
		
		if(attendance == null) {
			throw new ResourceNotFoundException(ErrorCode.AttendanceError.ATTENDANCE_NOT_FOUND);
		}
		
		else {
			attendanceDao.update(attendanceReq, id);
		}
	}

	@Override
	public void delete(Integer id) {

		attendanceDao.delete(id);
	}
}
