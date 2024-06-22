package com.schoolproject.exception;

@SuppressWarnings("serial")
public class EvaluationNotFoundException extends RuntimeException {
	public EvaluationNotFoundException(String message) {
        super(message);
    }
}
