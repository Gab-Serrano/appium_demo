
package com.technologylatam.pages;

import com.technologylatam.model.Product;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.NoSuchElementException;

import java.util.*;

public class ProductsPage extends BasePage {

    private final By viewGroups = AppiumBy.xpath("//android.widget.ScrollView[@content-desc='test-PRODUCTS']//android.view.ViewGroup[@content-desc='test-Item']");
    private final By titleTextView = AppiumBy.androidUIAutomator("new UiSelector().text(\"PRODUCTS\")");
    private final By productNameTextView = AppiumBy.accessibilityId("test-Item title");
    private final By productPriceTextView = AppiumBy.accessibilityId("test-Price");
    private final By cartIcon = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(3)");
    private final By dropDown = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(5)");
    private final By highToLowOption = AppiumBy.androidUIAutomator("new UiSelector().text(\"Price (high to low)\")");
    private final By productList = AppiumBy.accessibilityId("test-PRODUCTS");


    public ProductsPage(AndroidDriver driver) {
        super(driver);
    }


    public boolean productsPageIsDisplayed() {
        return isDisplayed(titleTextView);
    }


    public Product getProductAtInstance(int productInstance) {
        WebElement productCard = getProductCard(productInstance);

        return mapProduct(productCard);
    }


    public void addProductToCart(int productInstance) {
        WebElement productCard = getProductCard(productInstance);

        By addToCartButton =
                AppiumBy.accessibilityId("test-ADD TO CART");

        productCard.findElement(addToCartButton).click();
    }


    public void openCartView() {
        click(cartIcon);
    }


    public void sortProductsByPriceHighToLow() {
        click(dropDown);
        click(highToLowOption);
    }


    public int getProductCount() {
        return getVisibleProductCards().size();
    }


    public List<Product> getAllProducts() {

        Map<String, Product> products = new LinkedHashMap<>();

        WebElement productListElement = wait.until(
                ExpectedConditions.presenceOfElementLocated(productList)
        );

        boolean canScrollMore;

        do {

            List<WebElement> productCards = getVisibleProductCards();

            for (WebElement productCard : productCards) {

                try {
                    Product product = mapProduct(productCard);

                    products.putIfAbsent(
                            product.getProductName(),
                            product
                    );

                } catch (NoSuchElementException e) {
                    System.out.println(
                            "Expected error. Product would be load in the next scroll."
                    );
                }
            }

            Object result = driver.executeScript(
                    "mobile: scrollGesture",
                    Map.of(
                            "elementId",
                            Objects.requireNonNull(
                                    ((RemoteWebElement) productListElement).getId()
                            ),
                            "direction", "down",
                            "percent", 0.75
                    )
            );

            if (!(result instanceof Boolean)) {
                throw new IllegalStateException(
                        "Resultado inesperado de scrollGesture: " + result
                );
            }

            canScrollMore = (Boolean) result;

        } while (canScrollMore);

        return new ArrayList<>(products.values());
    }


    private WebElement getProductCard(int productInstance) {

        String selector =
                "new UiSelector().description(\"test-Item\").instance(%d)";

        By productSelector = AppiumBy.androidUIAutomator(
                String.format(selector, productInstance)
        );

        return wait.until(
                ExpectedConditions.presenceOfElementLocated(productSelector)
        );
    }


    private List<WebElement> getVisibleProductCards() {

        return driver.findElements(viewGroups);

    }


    private Product mapProduct(WebElement productCard) {

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

}
