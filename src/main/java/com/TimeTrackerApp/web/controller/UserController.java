package com.TimeTrackerApp.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.TimeTrackerApp.core.domain.User;
import com.TimeTrackerApp.service.UserService;

@Controller
public class UserController extends BaseController {

	@Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ResponseEntity<User> save(@RequestBody User user) {
		
		userService.save(user);
		
		return new ResponseEntity<User>(HttpStatus.CREATED);
	}
}
