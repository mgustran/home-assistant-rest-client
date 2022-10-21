package com.mgustran.homeassistant.client.exception;

public class HaConfigurationException extends RuntimeException {

    public HaConfigurationException(String message) {
        super(message);
    }

    public HaConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }

    public HaConfigurationException(Throwable cause) {
        super(cause);
    }
}
