package com.technologylatam.config;

public enum ConfigParams {
    EXPLICIT_WAIT("explicit.wait");

    public final String value;

    private ConfigParams(String value) {
        this.value = value;
    }
}
