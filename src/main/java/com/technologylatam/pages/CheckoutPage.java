package com.technologylatam.pages;

import com.technologylatam.model.User;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class CheckoutPage extends BasePage{

    private final By firstNameInput = AppiumBy.accessibilityId("test-First Name");
    private final By lastNameInput = AppiumBy.accessibilityId("test-Last Name");
    private final By zipCodeInput = AppiumBy.accessibilityId("test-Zip/Postal Code");
    private final By continueButton = AppiumBy.accessibilityId("test-CONTINUE");

    public CheckoutPage(AndroidDriver driver) {
        super(driver);
    }

    public void fillInformation(User user) {
        type(firstNameInput, user.getName());
        type(lastNameInput, user.getLastName());
        type(zipCodeInput, user.getZipCode());
    }

    public void clickContinue() {
        click(continueButton);
    }

}
