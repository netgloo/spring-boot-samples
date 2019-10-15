package com.springboot.quickstart.exceptions;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.springboot.quickstart.common.ApiError;
import com.springboot.quickstart.common.ErrorMessage;

@SuppressWarnings("unchecked")
@Component
public class CommonException {
	private static final Logger LOGGER = LoggerFactory.getLogger(CommonException.class);

	public static <T> ResponseEntity<T> returnException(Exception exception, Class<T> classType, String path) {
		ApiError response = null;

		if (exception instanceof InvalidRequestException) {
			response = new ApiError(HttpStatus.BAD_REQUEST, ErrorMessage.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(),
					path, new Date());
			return (ResponseEntity<T>) new ResponseEntity<ApiError>(response, HttpStatus.BAD_REQUEST);
		}

		if (exception instanceof InvalidTokenException) {
			response = new ApiError(HttpStatus.UNAUTHORIZED, ErrorMessage.INVALID_TOKEN,
					HttpStatus.UNAUTHORIZED.value(), path, new Date());
			return (ResponseEntity<T>) new ResponseEntity<ApiError>(response, HttpStatus.UNAUTHORIZED);
		}

		return null;
	}

	public void printNullPointerException(String info, NullPointerException nullPointerException) {
		StringWriter sw = new StringWriter();
		nullPointerException.printStackTrace(new PrintWriter(sw));
		String exceptionAsString = sw.toString();
		LOGGER.error("Threw a nullPointerException while " + info + nullPointerException.getMessage());
		LOGGER.error("Exception StackTrace : ");
		LOGGER.error(exceptionAsString);
		throw new MainRestException(HttpStatus.INTERNAL_SERVER_ERROR.value(), ErrorMessage.NULL_POINTER_EXCEPTION);
	}

	public void printException(String info, Exception exception) {
		StringWriter sw = new StringWriter();
		exception.printStackTrace(new PrintWriter(sw));
		String exceptionAsString = sw.toString();
		LOGGER.error("Threw an SqlException while " + info);
		LOGGER.error("Exception StackTrace : ");
		LOGGER.error(exceptionAsString);
		throw new MainRestException(HttpStatus.INTERNAL_SERVER_ERROR.value(), ErrorMessage.SQL_EXCEPTION);
	}
}