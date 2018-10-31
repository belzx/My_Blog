package com.lizhi.exception;

public class LZException  extends RuntimeException{
    public LZException() {
        super();
    }

    public LZException(String message) {
        super(message);
    }

    public LZException(String message, Throwable cause) {
        super(message, cause);
    }

    public LZException(Throwable cause) {
        super(cause);
    }

    protected LZException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
