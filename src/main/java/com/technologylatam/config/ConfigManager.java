package com.technologylatam.config;

import com.technologylatam.exception.ConfigPropertiesException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

    private static final Properties properties = new Properties();
    private static final ConfigManager INSTANCE = new ConfigManager();

    private ConfigManager() {
        loadProperties();
    }

    private void loadProperties() {
        try (InputStream input = ConfigManager.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {

            if (input == null) {
                throw new ConfigPropertiesException(
                        "No se encontró el archivo config.properties."
                );
            }

            properties.load(input);

            if (properties.isEmpty()) {
                throw new ConfigPropertiesException(
                        "El archivo config.properties está vacío."
                );
            }

        } catch (IOException ex) {
            throw new ConfigPropertiesException(
                    "Error al leer el archivo config.properties.", ex
            );
        }
    }

    public static ConfigManager getInstance() {return INSTANCE;}

    public static String getAppiumUrl() {
        String appiumUrl = CapabilitiesParams.APPIUM_URL.CONFIG_PARAMETER;
        return System.getProperty(appiumUrl, properties.getProperty(appiumUrl, null));
    }

    public static String getAppiumPlatformName() {
        String appiumPlatformName = CapabilitiesParams.APPIUM_PLATFORM_NAME.CONFIG_PARAMETER;
        return System.getProperty(appiumPlatformName, properties.getProperty(appiumPlatformName, null));
    }

    public static String getAppiumPlatformVersion() {
        String appiumPlatformVersion = CapabilitiesParams.APPIUM_PLATFORM_VERSION.CONFIG_PARAMETER;
        return System.getProperty(appiumPlatformVersion, properties.getProperty(appiumPlatformVersion, null));
    }

    public static String getAppiumAutomationName() {
        String appiumAutomationName = CapabilitiesParams.APPIUM_AUTOMATION_NAME.CONFIG_PARAMETER;
        return System.getProperty(appiumAutomationName, properties.getProperty(appiumAutomationName, null));
    }

    public static String getAppiumDeviceName() {
        String appiumDeviceName = CapabilitiesParams.APPIUM_DEVICE_NAME.CONFIG_PARAMETER;
        return System.getProperty(appiumDeviceName, properties.getProperty(appiumDeviceName, null));
    }

    public static String getAppiumApp() {
        String appiumApp = CapabilitiesParams.APPIUM_APP.CONFIG_PARAMETER;
        return System.getProperty(appiumApp, properties.getProperty(appiumApp, null));
    }

    public static String getAppiumAppWaitInitialActivity() {
        String appiumAppWaitInitialActivity = CapabilitiesParams.APPIUM_APP_WAIT_INITIAL_ACTIVITY.CONFIG_PARAMETER;
        return System.getProperty(appiumAppWaitInitialActivity, properties.getProperty(appiumAppWaitInitialActivity));
    }

    public static int getExplicitWait(){
        String explicitWait = ConfigParams.EXPLICIT_WAIT.value;
        return Integer.parseInt(System.getProperty(explicitWait, properties.getProperty(explicitWait, null)));
    }

    public static String getDataFolder(){
        String dataFolder = ConfigParams.DATA_FOLDER.value;
        return System.getProperty(dataFolder, properties.getProperty(dataFolder, null));
    }

    public static String getProperty(String property){
        return System.getProperty(property, properties.getProperty(property, null));
    }
}
