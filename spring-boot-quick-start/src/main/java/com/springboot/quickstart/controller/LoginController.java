package com.springboot.quickstart.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.quickstart.common.UriConstants;
import com.springboot.quickstart.dataTransfer.Login;
import com.springboot.quickstart.dataTransfer.LoginResponse;
import com.springboot.quickstart.exceptions.CommonException;
import com.springboot.quickstart.handler.LoginHandler;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@Api(value = "login-api", description = "Login")
@RequestMapping(value = "/quickStart")
@RestController
public class LoginController {

	@Autowired
	LoginHandler handler;
	public static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

	@ApiOperation(value = "User Login Verify")
	@ApiResponse(code = 200, message = "Successfully Verified the User")
	@RequestMapping(value = UriConstants.LOGIN, method = RequestMethod.POST)
	public ResponseEntity<LoginResponse> userLogin(@RequestBody Login login) {
		try {
			LoginResponse response = handler.userLogin(login);
			return new ResponseEntity<LoginResponse>(response, HttpStatus.OK);
		} catch (Exception exception) {
			return CommonException.returnException(exception, LoginResponse.class,
					UriConstants.QUICKSTART + "/" + UriConstants.LOGIN);
		}

	}
}