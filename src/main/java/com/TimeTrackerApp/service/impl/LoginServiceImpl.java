package com.TimeTrackerApp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.TimeTrackerApp.core.dao.LoginDao;
import com.TimeTrackerApp.core.domain.LoginRequest;
import com.TimeTrackerApp.core.domain.User;
import com.TimeTrackerApp.core.domain.UserPrincipal;
import com.TimeTrackerApp.core.exception.AuthFailureException;
import com.TimeTrackerApp.core.exception.ErrorCode;
import com.TimeTrackerApp.core.exception.ResourceNotFoundException;
import com.TimeTrackerApp.service.LoginService;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginDao loginDao;

	@Override
	public UserPrincipal getLogin(LoginRequest loginRequest) {

		UserPrincipal principal= new  UserPrincipal();
		
		User user = loginDao.getLogin(loginRequest);
		
		if(user == null) {
			throw new AuthFailureException(ErrorCode.UserError.USER_NOT_AUTHETICATED);
		} 
		else {
			principal.setToken(this.getToken());
		}
		return principal;
	}
	
	private String getToken(){
		String key ="hgHJJH$%#%$#%^#^%#444544JHGGHH";
		
		JWTClaimsSet claimsSet = new JWTClaimsSet();
		//claimsSet.setIssuerClaim("Java Classes");
		//claimsSet.setSubjectClaim("Narender@gmail.com");
		//claimsSet.setCustomClaim("Role", "ROLE_ADMIN");
		//claimsSet.setCustomClaim("userName", "a");
		//claimsSet.setCustomClaim("userId", "a");
		
		JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);
		
		SignedJWT sjt = new SignedJWT(header, claimsSet);
		
		JWSSigner jss= new MACSigner(key.getBytes());
		
		try {
			sjt.sign(jss);
		} catch (JOSEException e) {
			e.printStackTrace();
		}
		
		String token =sjt.serialize();
		return token;
	}
	
}
