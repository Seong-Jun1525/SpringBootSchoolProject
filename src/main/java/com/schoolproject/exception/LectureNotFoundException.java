package com.schoolproject.exception;

@SuppressWarnings("serial")
public class LectureNotFoundException extends RuntimeException {
	public LectureNotFoundException(String message) {
        super(message);
    }
}
