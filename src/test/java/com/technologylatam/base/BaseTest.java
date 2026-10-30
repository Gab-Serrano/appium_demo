package com.technologylatam.base;

import com.technologylatam.driver.DriverFactory;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {

    protected AndroidDriver driver;

    @BeforeMethod()
    public void setUp() throws Exception {
        driver = DriverFactory.getDriver();
    }

    @AfterMethod()
    public void tearDown() throws Exception {
    driver.quit();
    }
}
