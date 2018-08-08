package com.TimeTrackerApp.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.TimeTrackerApp.core.domain.LoginRequest;
import com.TimeTrackerApp.core.domain.UserPrincipal;
import com.TimeTrackerApp.service.LoginService;

@Controller
@RequestMapping(value="login")
public class LoginController extends BaseController {

	@Autowired
	private LoginService loginService;
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<UserPrincipal> getLogin(@RequestBody LoginRequest loginRequest) {
		
		UserPrincipal principal = loginService.getLogin(loginRequest);
		
		return new ResponseEntity<UserPrincipal>(principal, HttpStatus.OK);
	}
	
	
}
