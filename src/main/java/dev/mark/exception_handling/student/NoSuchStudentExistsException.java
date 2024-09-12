package dev.mark.exception_handling.student;

public class NoSuchStudentExistsException extends RuntimeException {

    public NoSuchStudentExistsException(String msg) {
        super(msg);
    }
}