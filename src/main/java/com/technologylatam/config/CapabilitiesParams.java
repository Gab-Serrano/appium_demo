package com.technologylatam.config;

import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;

public enum CapabilitiesParams {
    APPIUM_URL("appiumUrl", "appium.url"),
    APPIUM_PLATFORM_NAME("platformName", "appium.platform.name"),
    APPIUM_PLATFORM_VERSION("appium:platformVersion", "appium.platform.version"),
    APPIUM_AUTOMATION_NAME("appium:deviceName", "appium.automation.name"),
    APPIUM_DEVICE_NAME("appium:deviceName", "appium.device.name"),
    APPIUM_APP( "appium:app", "appium.app"),
    APPIUM_APP_WAIT_INITIAL_ACTIVITY("appium:appWaitActivity", "appium.app.wait.initial.activity");

    public final String CAPABILITY;
    public final String CONFIG_PARAMETER;

    private CapabilitiesParams(String capability, String configParameter) {
        CAPABILITY = capability;
        CONFIG_PARAMETER = configParameter;
    }
}
