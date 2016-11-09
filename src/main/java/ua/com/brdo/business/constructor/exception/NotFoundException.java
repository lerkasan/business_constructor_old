package ua.com.brdo.business.constructor.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super();
    }

    public NotFoundException(String s) {
        super(s);
    }

    public NotFoundException(String s, Exception e) {
        super(s, e);
    }
}
