package com.schoolproject.exception;

@SuppressWarnings("serial")
public class IsEmptyEnrolmentException extends RuntimeException {
	public IsEmptyEnrolmentException(String message) {
        super(message);
    }
}