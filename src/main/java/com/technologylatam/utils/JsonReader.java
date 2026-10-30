package com.technologylatam.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.technologylatam.config.ConfigManager;
import com.technologylatam.config.ConfigParams;

import java.io.IOException;
import java.io.InputStream;

public final class JsonReader {

    public static final ObjectMapper MAPPER = new ObjectMapper();
    public static final String DATA_FOLDER = ConfigManager.getDataFolder();

    private JsonReader(){}

    public static <T> T read(String dataFileName, Class<T> clazz){

        try(InputStream inputStream = JsonReader.class.getClassLoader()
                .getResourceAsStream(DATA_FOLDER + dataFileName)){

            return MAPPER.readValue(inputStream, clazz);

        }catch (IOException ex){
            throw new RuntimeException(ex.getMessage(), ex.getCause());
        }
    }
}
