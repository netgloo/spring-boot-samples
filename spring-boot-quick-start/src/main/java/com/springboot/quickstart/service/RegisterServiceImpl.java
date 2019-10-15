package com.springboot.quickstart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.springboot.quickstart.common.CommonResponse;
import com.springboot.quickstart.common.DatabaseOperations;
import com.springboot.quickstart.model.Register;
import com.springboot.quickstart.model.UsersList;

@Service
public class RegisterServiceImpl implements RegisterService {

	@Autowired
	JdbcTemplate jdbctemplate;

	@Autowired
	DatabaseOperations databaseOperations;

	@Override
	public CommonResponse registerUser(Register register) {
		CommonResponse response = null;
		Long userId = databaseOperations.getNextVal();

		String sql = "insert into quickstart.users(user_id,firstname,lastname,email,password) values(?,?,?,?,?) ";
		int rs = jdbctemplate.update(sql, userId, register.getFirstname(), register.getLastname(), register.getEmail(),
				register.getPassword());
		if (rs > 0) {
			response = new CommonResponse();
			response.setId(userId);
			response.setMessage("Successfully registered User");
		}

		return response;
	}

	@Override
	public UsersList getAllUsers() {
		UsersList response = new UsersList();
		List<Register> usersList = new ArrayList<>();
		Register user = null;
		Long count = null;
		try {
			String sql = "SELECT count(*) over() as count, user_id as userId,  firstname,  lastname,  email FROM quickstart.users ";
			List<Map<String, Object>> rows = jdbctemplate.queryForList(sql);
			count = (long) rows.size();
			for (Map<String, Object> rs : rows) {
				user = new Register();
				user.setFirstname((String) rs.get("firstname"));
				user.setLastname((String) rs.get("lastname"));
				user.setUserId((Long) rs.get("userId"));
				user.setEmail((String) rs.get("email"));
				usersList.add(user);
			}

			response.setUsers(usersList);
			response.setCount(count);
		} catch (Exception e) {

		}
		return response;
	}

}
