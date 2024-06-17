package com.schoolproject.exception;

@SuppressWarnings("serial")
public class StudentPointException extends RuntimeException {
    public StudentPointException(String message) {
        super(message);
    }
}