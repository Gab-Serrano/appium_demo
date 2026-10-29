package com.technologylatam.exception;

public class ConfigPropertiesException extends RuntimeException {

    public ConfigPropertiesException(String message){
        super(message);
    }

    public ConfigPropertiesException(String message, Throwable cause){
        super(message, cause);
    }
}
