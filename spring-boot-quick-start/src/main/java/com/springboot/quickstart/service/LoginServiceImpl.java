package com.springboot.quickstart.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.springboot.quickstart.common.JWTAuthentication;
import com.springboot.quickstart.dataTransfer.Login;
import com.springboot.quickstart.dataTransfer.LoginResponse;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	JdbcTemplate jdbctemplate;

	@Autowired
	JWTAuthentication jwtAuthentication;

	@Override
	public LoginResponse userLogin(Login login) {
		LoginResponse response = null;
		try {
			String sql = "select user_id as userId,firstname,lastname from quickstart.users where email = ? and password = ?";
			List<Map<String, Object>> rows = jdbctemplate.queryForList(sql, login.getEmail(), login.getPassword());
			for (Map<String, Object> rs : rows) {
				response = new LoginResponse();
				response.setFirstName((String) rs.get("firstname"));
				response.setLastName((String) rs.get("lastname"));
				response.setAuthToken(jwtAuthentication.getToken((Long) rs.get("userId")));
			}

		} catch (Exception e) {
		}
		return response;
	}

}
