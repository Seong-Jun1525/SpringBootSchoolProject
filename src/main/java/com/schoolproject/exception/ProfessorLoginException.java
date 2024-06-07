package com.schoolproject.exception;

@SuppressWarnings("serial")
public class ProfessorLoginException extends RuntimeException {
	public ProfessorLoginException(String message) {
        super(message);
    }
}