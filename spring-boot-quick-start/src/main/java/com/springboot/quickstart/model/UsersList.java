package com.springboot.quickstart.model;

import java.util.List;

public class UsersList {

	private List<Register> users;
	private Long count;

	public UsersList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UsersList(List<Register> users, Long count) {
		super();
		this.users = users;
		this.count = count;
	}

	public List<Register> getUsers() {
		return users;
	}

	public void setUsers(List<Register> users) {
		this.users = users;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "UsersList [users=" + users + ", count=" + count + "]";
	}

}
