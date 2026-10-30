package com.technologylatam.pages;

import com.technologylatam.model.Product;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductsPage extends BasePage{

    private final By titleTextView = AppiumBy.androidUIAutomator("new UiSelector().text(\"PRODUCTS\")");
    private final By productNameTextView = AppiumBy.accessibilityId("test-Item title");
    private final By productPriceTextView = AppiumBy.accessibilityId("test-Price");
    private final By cartIcon=  AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\")" +
            ".instance(3)");

    public ProductsPage(AndroidDriver driver) {
        super(driver);
    }

    public boolean productsPageIsDisplayed() {
        return isDisplayed(titleTextView);
    }

    public Product getProductAtInstance(int productInstance) {
        String PRODUCT_SELECTOR =
                "new UiSelector().description(\"test-Item\").instance(%d)";

        By productContainer = AppiumBy.androidUIAutomator(
                String.format(PRODUCT_SELECTOR, productInstance)
        );

        WebElement productCard = wait.until(
                ExpectedConditions.presenceOfElementLocated(productContainer)
        );

        String productName = productCard
                .findElement(productNameTextView)
                .getText();

        String priceText = productCard
                .findElement(productPriceTextView)
                .getText()
                .replace("$", "")
                .trim();

        double productPrice = Double.parseDouble(priceText);

        return new Product(productName, productPrice);
    }

    public void addProductToCart(int productInstance) {
        WebElement productCard = getProductCard(productInstance);

        By addToCartButton = AppiumBy.accessibilityId("test-ADD TO CART");

        productCard.findElement(addToCartButton).click();
    }

    public void openCartView(){
        click(cartIcon);
    }

    private WebElement getProductCard(int productInstance) {
        String selector =
                "new UiSelector().description(\"test-Item\").instance(%d)";

        By productContainer = AppiumBy.androidUIAutomator(
                String.format(selector, productInstance)
        );

        return wait.until(
                ExpectedConditions.presenceOfElementLocated(productContainer)
        );
    }
}
