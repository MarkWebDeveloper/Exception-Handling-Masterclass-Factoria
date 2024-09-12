package dev.mark.exception_handling.student;

public class StudentNotFoundException extends Exception {

    public StudentNotFoundException(String msg) {
        super(msg);
    }
}