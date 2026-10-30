package com.technologylatam;

import com.technologylatam.config.ConfigManager;
import com.technologylatam.driver.DriverFactory;
import com.technologylatam.model.User;
import com.technologylatam.pages.CartPage;
import com.technologylatam.pages.LoginPage;
import com.technologylatam.pages.ProductsPage;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        AndroidDriver driver = DriverFactory.getDriver();

        try {

            LoginPage loginPage = new LoginPage(driver);

            loginPage.login(new User("standard_user", "secret_sauce"));

            ProductsPage productsPage = new ProductsPage(driver);

            productsPage.addProductToCart(0);

            productsPage.openCartView();

            CartPage cartPage = new CartPage(driver);
            System.out.println(cartPage.getUniqueProduct());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }

    }
}
