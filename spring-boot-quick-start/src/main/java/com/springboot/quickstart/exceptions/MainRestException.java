package com.springboot.quickstart.exceptions;

import com.springboot.quickstart.common.ErrorCode;

public class MainRestException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private ErrorCode errorCode;

	private Object[] messagePositionalArgs;

	private int errorCodes;

	public MainRestException(ErrorCode errorCode, Object... messagePositionalArgs) {
		this.errorCode = errorCode;
		this.messagePositionalArgs = messagePositionalArgs;
	}

	public MainRestException(ErrorCode errorCode, Throwable underlyingException, Object... messagePositionalArgs) {
		super(underlyingException);
		this.errorCode = errorCode;
		this.messagePositionalArgs = messagePositionalArgs;
	}

	public MainRestException(int errorCodes, Object... messagePositionalArgs) {
		this.errorCodes = errorCodes;
		this.messagePositionalArgs = messagePositionalArgs;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public int getErrorCodes() {
		return errorCodes;
	}

	public Object[] getMessagePositionalArgs() {
		return messagePositionalArgs;
	}

}