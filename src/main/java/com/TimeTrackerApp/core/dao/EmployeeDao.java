package com.TimeTrackerApp.core.dao;

import java.util.List;

import com.TimeTrackerApp.core.domain.Employee;

public interface EmployeeDao {

	void save(Employee employee);

	List<Employee> getAll();

	Employee getByEmailId(Integer id);

	void delete(Integer id);

	Employee checkEmail(String emailId);

	void update(Employee employee, Integer id);

	Employee getById(Integer id);

}
