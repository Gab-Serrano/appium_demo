package com.technologylatam.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import java.util.Map;
import java.util.Objects;

public class CheckoutOverviewPage extends CheckoutPage {

    private final By overviewContainer = AppiumBy.accessibilityId("test-CHECKOUT: OVERVIEW");
    private final By productName = AppiumBy.xpath("(//android.view.ViewGroup[@content-desc=\"test-Description\"]//android.widget.TextView)[1]");
    private final By productPrice = AppiumBy.xpath("(//android.view.ViewGroup[@content-desc=\"test-Price\"]//android.widget.TextView)[1]");
    private final By itemTotal = AppiumBy.xpath("//android.widget.TextView[contains(@text, 'Item total')]");
    private final By finishButton = AppiumBy.accessibilityId("test-FINISH");

    public CheckoutOverviewPage(AndroidDriver driver) {
        super(driver);
    }

    public String getNamenUniqueItem() {
        return getText(productName);
    }

    public double getPriceUniqueItem() {
        return Double.parseDouble(getText(productPrice).replace("$", "").trim());
    }

    public String getTotalPriceUniqueItemAsText() {
        return getText(itemTotal);
    }

    public double getTotalPriceUniqueItem() {
        return Double.parseDouble(getText(itemTotal).replace("Item total: $", "").trim());
    }

    public void scrollToButton() {
        WebElement element = driver.findElement(overviewContainer);

        Object result = driver.executeScript(
                "mobile: scrollGesture",
                Map.of(
                        "elementId",
                        Objects.requireNonNull(
                                ((RemoteWebElement) element).getId()
                        ),
                        "direction", "down",
                        "percent", 0.75
                )
        );
    }

    public void clickFinishButton() {
        click(finishButton);
    }


}
