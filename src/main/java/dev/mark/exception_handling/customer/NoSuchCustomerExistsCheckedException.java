package dev.mark.exception_handling.customer;

public class NoSuchCustomerExistsCheckedException extends Exception {
    private String message;

    public NoSuchCustomerExistsCheckedException() {}

    public NoSuchCustomerExistsCheckedException(String msg) {
        super(msg);
        this.message = msg;
    }
}