package com.springboot.quickstart.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.quickstart.dataTransfer.Login;
import com.springboot.quickstart.dataTransfer.LoginResponse;
import com.springboot.quickstart.service.LoginService;

@Service
public class LoginHandlerImpl implements LoginHandler {

	@Autowired
	LoginService loginService;

	@Override
	public LoginResponse userLogin(Login login) {
		LoginResponse response = null;
		try {
			response = loginService.userLogin(login);
		} catch (Exception exception) {
			throw exception;
		}
		return response;
	}

}
