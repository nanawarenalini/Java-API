package com.TimeTrackerApp.service;

import java.util.List;

import com.TimeTrackerApp.core.domain.Employee;

public interface EmployeeService {

	void save(Employee employee);

	List<Employee> getAll();

	Employee getById(Integer id);

	void delete(Integer id);

	void update(Employee employee, Integer id);

}
