package com.technologylatam.pages;

import com.technologylatam.App;
import com.technologylatam.model.User;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {

    private final By usernameInput = AppiumBy.accessibilityId("test-Username");
    private final By passwordInput = AppiumBy.accessibilityId("test-Password");
    private final By loginButton = AppiumBy.accessibilityId("test-LOGIN");
    private final By errorMessage = AppiumBy.accessibilityId("test-Error message");

    public LoginPage(AndroidDriver driver) {
        super(driver);
    }

    public void login(User user) {
        type(usernameInput, user.getUsername());
        type(passwordInput, user.getPassword());

        click(loginButton);
    }

    public boolean hasLoginFailed() {
        return isDisplayed(errorMessage);
    }

    public String getErrorMessage() {
        WebElement errorMessageContainer = findElement(errorMessage);
        WebElement child = errorMessageContainer.findElement(AppiumBy.xpath("/*/*"));
        return child.getText();
    }
}
