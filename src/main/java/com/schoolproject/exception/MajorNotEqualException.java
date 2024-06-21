package com.schoolproject.exception;

@SuppressWarnings("serial")
public class MajorNotEqualException extends RuntimeException {
	public MajorNotEqualException(String message) {
        super(message);
    }
}
