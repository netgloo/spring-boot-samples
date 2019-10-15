package com.springboot.quickstart.exceptions;

import com.springboot.quickstart.common.ErrorCode;

public class InvalidTokenException extends MainRestException {

	private static final long serialVersionUID = 1L;

	public InvalidTokenException(ErrorCode errorCode, Object... args) {
		super(errorCode, args);
	}
}
