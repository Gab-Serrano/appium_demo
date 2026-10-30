package com.technologylatam.pages;

import com.technologylatam.model.Product;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CartPage extends BasePage {

    String PRODUCT_DESCRIPTION_SELECTOR = "new UiSelector().description(\"test-Description\")";
    String PRODUCT_PRICE_SELECTOR = "new UiSelector().description(\"test-Price\")";

    public CartPage(AndroidDriver driver) {
        super(driver);
    }

    public Product getUniqueProduct () {

        WebElement productDescriptionBox =
                findElement(AppiumBy.androidUIAutomator(PRODUCT_DESCRIPTION_SELECTOR));

        String productName = productDescriptionBox.findElements(AppiumBy.xpath("/*/*"))
                .get(0)
                .getText();

        WebElement productPriceBox = findElement(AppiumBy.androidUIAutomator(PRODUCT_PRICE_SELECTOR));

        double productPrice = Double.parseDouble(
                productPriceBox.findElements(AppiumBy.xpath("/*/*"))
                        .get(0)
                        .getText()
                        .replace("$", "")
                        .trim()
        );

        return new Product(productName, productPrice);
    }

    public Product getProductAtInstance(int productInstance) {
        PRODUCT_DESCRIPTION_SELECTOR = PRODUCT_DESCRIPTION_SELECTOR + ".instance(%d)";
        PRODUCT_PRICE_SELECTOR = PRODUCT_PRICE_SELECTOR + ".instance(%d)";

        WebElement productDescriptionBox =
                findElement(AppiumBy.androidUIAutomator(String.format(PRODUCT_DESCRIPTION_SELECTOR,
                        productInstance)));

        String productName = productDescriptionBox.findElements(AppiumBy.xpath("/*/*"))
                .get(0)
                .getText();

        WebElement productPriceBox = findElement(AppiumBy.androidUIAutomator(String.format(PRODUCT_PRICE_SELECTOR,
                productInstance)));

        double productPrice = Double.parseDouble(
                productPriceBox.findElements(AppiumBy.xpath("/*/*"))
                        .get(0)
                        .getText()
                        .replace("$", "")
                        .trim()
        );

        return new Product(productName, productPrice);
    }


}
