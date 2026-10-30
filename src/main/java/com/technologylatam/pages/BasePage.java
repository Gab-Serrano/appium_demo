package com.technologylatam.pages;

import com.technologylatam.config.ConfigManager;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public abstract class BasePage {

    protected final AndroidDriver driver;
    protected final WebDriverWait wait;

    protected BasePage(AndroidDriver driver) {
        this.driver = driver;

        Duration timeout = Duration.ofSeconds(ConfigManager.getExplicitWait());

        this.wait = new WebDriverWait(driver, timeout);
    }

    protected void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected void type(By locator, String text) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).sendKeys(text);
    }

    protected String getText(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator)).getText();
    }

    protected boolean isDisplayed(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator)).isDisplayed();
    }

    protected void waitForElementToBeVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void waitForElementToBeClickable(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void clear(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).clear();
    }

    protected boolean isEnabled(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator)).isEnabled();
    }

    protected WebElement findElement(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected List<WebElement> findAllElements(By locator) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    protected WebElement getFirstChildElement(By locator) {
        WebElement parent = wait.until(
                ExpectedConditions.elementToBeClickable(locator)
        );

        List<WebElement> children = parent.findElements(
                By.xpath(".//android.widget.TextView[@content-desc='test-Item title']")
        );

        if (children.isEmpty()) {
            throw new NoSuchElementException(
                    "No se encontraron elementos hijos con content-desc='test-Item title'"
            );
        }

        return children.get(0);
    }

    protected boolean areChildrenVisibles(By parent, By child) {
        return !wait.until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(parent,child)).isEmpty();
    }
}
