package com.springboot.quickstart.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.quickstart.common.CommonResponse;
import com.springboot.quickstart.common.ErrorCode;
import com.springboot.quickstart.common.IPValidator;
import com.springboot.quickstart.common.JWTAuthentication;
import com.springboot.quickstart.exceptions.InvalidRequestException;
import com.springboot.quickstart.exceptions.InvalidTokenException;
import com.springboot.quickstart.model.Register;
import com.springboot.quickstart.model.UsersList;
import com.springboot.quickstart.service.RegisterService;

@Service
public class RegisterHandlerImpl implements RegisterHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(RegisterHandlerImpl.class);

	@Autowired
	IPValidator validator;

	@Autowired
	RegisterService service;

	@Autowired
	JWTAuthentication jwtAuthentication;

	@Override
	public CommonResponse registerUser(Register register) {
		CommonResponse response = null;
		try {
			if (validator.isNullOrEmpty(register)) {
				throw new InvalidRequestException(ErrorCode.INPUT_INVALID_PAYLOAD, "register");
			}
			response = service.registerUser(register);

		} catch (Exception exception) {
			throw exception;
		}
		return response;
	}

	@Override
	public UsersList getAllUsers(String authToken) {
		UsersList response = null;
		try {
			Long userId = jwtAuthentication.verifyToken(authToken);
			if (userId == null) {
				throw new InvalidTokenException(ErrorCode.AUTHORIZATION_ERROR, authToken);
			}
			response = service.getAllUsers();

		} catch (InvalidTokenException e) {
			System.out.println("iam here...");
			throw e;
		} catch (Exception e) {
			throw e;
		}
		return response;
	}

}
