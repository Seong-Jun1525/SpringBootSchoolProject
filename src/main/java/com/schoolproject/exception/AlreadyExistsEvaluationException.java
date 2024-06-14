package com.schoolproject.exception;

@SuppressWarnings("serial")
public class AlreadyExistsEvaluationException extends RuntimeException {
	public AlreadyExistsEvaluationException(String message) {
		super(message);
	}
}