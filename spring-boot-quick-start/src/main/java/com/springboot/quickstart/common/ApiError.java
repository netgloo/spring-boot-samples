package com.springboot.quickstart.common;

import java.util.Date;

import org.springframework.http.HttpStatus;

public class ApiError {

	public HttpStatus error;
	public String message;
	public Integer status;
	public String path;
	public Date timestamp;
	public String response;

	public ApiError() {

	}

	public ApiError(HttpStatus error, String message, int status, String path, Date timestamp) {
		super();
		this.error = error;
		this.message = message;
		this.status = status;
		this.path = path;
		this.timestamp = timestamp;
	}

	public ApiError(HttpStatus error, Integer HttpStatus) {
		super();
		this.error = error;
		this.message = "No message available";
	}

	public HttpStatus getError() {
		return error;
	}

	public void setError(HttpStatus error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

}