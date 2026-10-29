package com.technologylatam.config;

import com.technologylatam.exception.ConfigPropertiesException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

    private static ConfigManager INSTANCE = new ConfigManager();
    private static final Properties properties = new Properties();

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

    public static int getExplicitWait(){
        String explicitWait = ConfigParams.EXPLICIT_WAIT.value;
        return Integer.parseInt(System.getProperty(explicitWait, properties.getProperty(explicitWait)));
    }

    public static String getProperty(String property){
        return System.getProperty(property, properties.getProperty(property, null));
    }
}
