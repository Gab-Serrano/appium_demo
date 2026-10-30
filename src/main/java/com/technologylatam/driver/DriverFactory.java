package com.technologylatam.driver;

import com.technologylatam.config.ConfigManager;
import io.appium.java_client.android.AndroidDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class DriverFactory {

    public static AndroidDriver getDriver() {

        URL url = getUrl(ConfigManager.getAppiumUrl());

        return new AndroidDriver(url, CapabilitiesManager.getCapabilities());
    }

    private static URL getUrl(String url) {
        URL uri = null;

        try{
            uri = new URI("http://127.0.0.1:4723").toURL();
        } catch (MalformedURLException | URISyntaxException e) {
            throw new RuntimeException(e);
        }

        return uri;
    }
}
