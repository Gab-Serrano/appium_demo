package com.technologylatam.driver;

import com.technologylatam.config.CapabilitiesParams;
import com.technologylatam.config.ConfigManager;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.remote.DesiredCapabilities;

public class CapabilitiesManager {

    public static UiAutomator2Options getCapabilities() {
        UiAutomator2Options options = new UiAutomator2Options();

        for (CapabilitiesParams cap : CapabilitiesParams.values()) {
            options.setCapability(cap.CAPABILITY, ConfigManager.getProperty(cap.CONFIG_PARAMETER));
        }

        return options;
    }
}
