package com.springboot.quickstart.service;

import com.springboot.quickstart.dataTransfer.Login;
import com.springboot.quickstart.dataTransfer.LoginResponse;

public interface LoginService {

	public LoginResponse userLogin(Login login);

}
