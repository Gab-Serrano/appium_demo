package com.technologylatam.config;

public enum ConfigParams {
    EXPLICIT_WAIT("explicit.wait"),
    SCENARIO_01("data.provider.scenario.01"),
    SCENARIO_02("data.provider.scenario.02"),
    SCENARIO_04("data.provider.scenario.04"),
    DATA_FOLDER("data.provider.data.folder");

    public final String value;

    ConfigParams(String value) {
        this.value = value;
    }
}
