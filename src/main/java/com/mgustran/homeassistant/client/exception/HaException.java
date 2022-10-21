package com.mgustran.homeassistant.client.exception;

public class HaException extends Exception {

    public HaException(String message) {
        super(message);
    }

    public HaException(String message, Throwable cause) {
        super(message, cause);
    }

    public HaException(Throwable cause) {
        super(cause);
    }
}
