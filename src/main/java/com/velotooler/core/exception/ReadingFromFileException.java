package com.velotooler.core.exception;

public class ReadingFromFileException extends GeneralException {

    public ReadingFromFileException() {
    }

    public ReadingFromFileException(String message) {
        super(message);
    }

    public ReadingFromFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReadingFromFileException(Throwable cause) {
        super(cause);
    }
}
