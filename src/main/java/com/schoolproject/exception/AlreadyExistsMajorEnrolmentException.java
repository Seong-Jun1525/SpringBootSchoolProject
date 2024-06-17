package com.schoolproject.exception;

@SuppressWarnings("serial")
public class AlreadyExistsMajorEnrolmentException extends RuntimeException {
	public AlreadyExistsMajorEnrolmentException(String message) {
		super(message);
	}
}