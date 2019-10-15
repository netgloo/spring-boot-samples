package com.springboot.quickstart.handler;

import com.springboot.quickstart.common.CommonResponse;
import com.springboot.quickstart.model.Register;
import com.springboot.quickstart.model.UsersList;

public interface RegisterHandler {

	public CommonResponse registerUser(Register register);

	public UsersList getAllUsers(String authToken);

}
