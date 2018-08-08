package com.TimeTrackerApp.service.impl;

import java.util.List;

import javax.validation.ConstraintViolation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.TimeTrackerApp.core.dao.EmployeeDao;
import com.TimeTrackerApp.core.domain.Employee;
import com.TimeTrackerApp.core.exception.ErrorCode;
import com.TimeTrackerApp.core.exception.ResourceNotFoundException;
import com.TimeTrackerApp.core.exception.ValidationFailureException;
import com.TimeTrackerApp.service.EmployeeService;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public void save(Employee employee) {

		String emailId = employee.getEmailId();
		
		Employee employee1 = employeeDao.checkEmail(emailId);
		
		if(employee1 == null) {
			employeeDao.save(employee);
		} else {
			//throw new ValidationFailureException(ErrorCode.EmployeeError.EMAIL_NOT_VALID);
		}
	}

	@Override
	public List<Employee> getAll() {

		List<Employee> employees = employeeDao.getAll();
		return employees;
	}

	@Override
	public Employee getById(Integer id) {

		Employee employee = employeeDao.getById(id);
		
		if(employee == null) {
			throw new ResourceNotFoundException(ErrorCode.EmployeeError.EMPLOYEE_NOT_FOUND);
		}
		
		return employee;
	}

	@Override
	public void delete(Integer id) {
		
		employeeDao.delete(id);
	}

	@Override
	public void update(Employee employee, Integer id) {
		
		Employee employee1 = employeeDao.getById(id);
		
		if(employee1 == null) {
			throw new ResourceNotFoundException(ErrorCode.EmployeeError.EMPLOYEE_NOT_FOUND);
		}
		
		else {
			employeeDao.update(employee, id);
		}
	}
}
