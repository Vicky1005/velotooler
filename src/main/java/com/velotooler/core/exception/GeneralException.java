package com.velotooler.core.exception;

public abstract class GeneralException extends RuntimeException {

    public GeneralException() {
    }

    public GeneralException(String message) {
        super(message);
    }

    public GeneralException(String message, Throwable cause) {
        super(message, cause);
    }

    public GeneralException(Throwable cause) {
        super(cause);
    }
}
