package com.schoolproject.exception;

@SuppressWarnings("serial")
public class AlreadyExistsStudentException extends RuntimeException {
	public AlreadyExistsStudentException(String message) {
        super(message);
    }
}
