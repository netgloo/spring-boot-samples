package com.springboot.quickstart.common;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class IPValidator {

	public boolean isValidString(final String input) {
		if (input == null || input.trim().isEmpty()) {
			return false;
		}
		return true;
	}

	public boolean isNullOrEmpty(final Object input) {
		if (input == null) {
			return true;
		}
		if (input instanceof String) {
			String checkEmpty = (String) input;
			if (checkEmpty.trim().isEmpty()) {
				return true;
			}
		}
		return false;
	}

	public boolean isValidUUID(UUID input) {
		if (input == null) {
			return false;
		}
		return true;
	}
}
