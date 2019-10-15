package com.springboot.quickstart.common;

public class CommonResponse {

	private Long id;
	private String message;

	public CommonResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CommonResponse(Long id, String message) {
		super();
		this.id = id;
		this.message = message;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "CommonResponse [id=" + id + ", message=" + message + "]";
	}

}
