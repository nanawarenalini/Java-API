package com.TimeTrackerApp.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.TimeTrackerApp.core.domain.Employee;
import com.TimeTrackerApp.service.EmployeeService;

@Controller
@RequestMapping(value="employee")
public class EmployeeController extends BaseController {

	@Autowired
	private EmployeeService employeeService;
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Employee> save(@RequestBody Employee employee) {
		
		employeeService.save(employee);
		
		return new ResponseEntity<Employee>(HttpStatus.CREATED);
	}
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Employee>> getAll() {
		
		List<Employee> employees = employeeService.getAll();
		
		return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value="/get", method=RequestMethod.GET)
	public ResponseEntity<Employee> getById(@RequestParam(value="id") Integer id) {
		
		Employee employee = employeeService.getById(id);
		
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Employee> delete(@PathVariable Integer id) {
		
		employeeService.delete(id);
		
		return new ResponseEntity<Employee>(HttpStatus.GONE);
	}
	
	@ResponseBody
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Employee> update(@RequestBody Employee employee, @PathVariable Integer id) {
		
		employeeService.update(employee,id);
		return new ResponseEntity<Employee>(HttpStatus.OK);
	}
}
