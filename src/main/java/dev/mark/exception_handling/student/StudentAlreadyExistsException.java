package dev.mark.exception_handling.student;

public class StudentAlreadyExistsException extends RuntimeException {

    public StudentAlreadyExistsException(String msg) {
        super(msg);
    }
}