package com.springboot.quickstart.service;

import com.springboot.quickstart.common.CommonResponse;
import com.springboot.quickstart.model.Register;
import com.springboot.quickstart.model.UsersList;

public interface RegisterService {

	public CommonResponse registerUser(Register register);

	public UsersList getAllUsers();

}
