package com.springboot.quickstart.exceptions;

import com.springboot.quickstart.common.ErrorCode;

public class InvalidRequestException extends MainRestException {

	private static final long serialVersionUID = 1L;

	public InvalidRequestException(ErrorCode errorCode, Object... args) {
		super(errorCode, args);
	}

	public InvalidRequestException(ErrorCode errorCode, Throwable underlyingException, Object... args) {
		super(errorCode, underlyingException, args);
	}

}