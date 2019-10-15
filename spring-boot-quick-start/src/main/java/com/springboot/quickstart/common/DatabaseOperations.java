package com.springboot.quickstart.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseOperations {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public Long getNextVal() {
		return Long.valueOf(jdbcTemplate.queryForObject("select nextval('quickstart.sequence_id')", String.class));
	}

}
