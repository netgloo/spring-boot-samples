package com.springboot.quickstart.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.quickstart.common.CommonResponse;
import com.springboot.quickstart.common.UriConstants;
import com.springboot.quickstart.exceptions.CommonException;
import com.springboot.quickstart.handler.RegisterHandler;
import com.springboot.quickstart.model.Register;
import com.springboot.quickstart.model.UsersList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@Api(value = "register-api", description = "Register")
@RequestMapping(value = "/quickstart")
@RestController
public class RegisterController {

	@Autowired
	RegisterHandler handler;

	public static final Logger LOGGER = LoggerFactory.getLogger(RegisterController.class);

	@ApiOperation(value = "User Register")
	@ApiResponse(code = 200, message = "Successfully Registered the User")
	@RequestMapping(value = UriConstants.REGISTER, method = RequestMethod.POST)
	public ResponseEntity<CommonResponse> registerUser(@RequestBody Register register) {
		try {
			CommonResponse response = handler.registerUser(register);
			return new ResponseEntity<CommonResponse>(response, HttpStatus.OK);
		} catch (Exception exception) {
			return CommonException.returnException(exception, CommonResponse.class,
					UriConstants.QUICKSTART + "/" + UriConstants.REGISTER);
		}

	}

	@ApiOperation(value = "GetAll Users")
	@ApiResponse(code = 200, message = "Successfully retrieving the Users")
	@RequestMapping(value = UriConstants.USERS, method = RequestMethod.POST)
	public ResponseEntity<UsersList> getAllUsers(@RequestHeader String authToken) {
		try {
			UsersList response = handler.getAllUsers(authToken);
			return new ResponseEntity<UsersList>(response, HttpStatus.OK);
		} catch (Exception exception) {
			return CommonException.returnException(exception, UsersList.class,
					UriConstants.QUICKSTART + "/" + UriConstants.USERS);
		}

	}

}
