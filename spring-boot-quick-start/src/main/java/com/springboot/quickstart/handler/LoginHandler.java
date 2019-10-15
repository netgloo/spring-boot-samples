package com.springboot.quickstart.handler;

import com.springboot.quickstart.dataTransfer.Login;
import com.springboot.quickstart.dataTransfer.LoginResponse;

public interface LoginHandler {

	public LoginResponse userLogin(Login login);

}
