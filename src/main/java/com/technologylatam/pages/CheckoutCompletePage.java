package com.technologylatam.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class CheckoutCompletePage extends CheckoutPage {

    private final By resultText = AppiumBy.androidUIAutomator("new UiSelector().text(\"THANK YOU FOR YOU ORDER\")");

    public CheckoutCompletePage(AndroidDriver driver) {
        super(driver);
    }

    public boolean isCheckoutComplete() {
        return isDisplayed(resultText);
    }

    public String getResultText() {
        return getText(resultText);
    }
}
