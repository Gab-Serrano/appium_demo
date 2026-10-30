package com.technologylatam.provider;

import com.technologylatam.config.ConfigManager;
import com.technologylatam.config.ConfigParams;
import com.technologylatam.model.User;
import com.technologylatam.utils.JsonReader;
import org.testng.annotations.DataProvider;

public final class DataProviders {

    private static final String SCENARIO_01_DATA = ConfigManager.getProperty(ConfigParams.SCENARIO_01.value);
    private static final String SCENARIO_02_DATA = ConfigManager.getProperty(ConfigParams.SCENARIO_02.value);

    @DataProvider(name = "scenario01")
    public static Object[][] scenario01(){
        User[] data = JsonReader.read(SCENARIO_01_DATA, User[].class);

        Object[][] result = new Object[data.length][1];

        for (int i = 0; i < data.length; i++) {
            result[i][0] = data[i];
        }
        return result;
    }

    @DataProvider(name = "scenario02")
    public static Object[][] scenario02(){
        User[] data = JsonReader.read(SCENARIO_02_DATA, User[].class);

        Object[][] result = new Object[data.length][1];

        for (int i = 0; i < data.length; i++) {
            result[i][0] = data[i];
        }
        return result;
    }


}
