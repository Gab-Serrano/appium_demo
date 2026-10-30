package com.technologylatam.tests;

import com.technologylatam.base.BaseTest;
import com.technologylatam.model.Product;
import com.technologylatam.model.User;
import com.technologylatam.pages.CartPage;
import com.technologylatam.pages.LoginPage;
import com.technologylatam.pages.ProductsPage;
import com.technologylatam.provider.DataProviders;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {

    @Test(
            dataProvider = "scenario01",
            dataProviderClass = DataProviders.class
    )
    public void shouldLoginAndAddFirstProduct(User user) throws Exception {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        CartPage cartPage = new CartPage(driver);

        loginPage.login(user);

        assertTrue(productsPage.productsPageIsDisplayed());

        Product productToBuy = productsPage.getProductAtInstance(0);
        productsPage.addProductToCart(0);
        productsPage.openCartView();

        Product productInCart = cartPage.getProductAtInstance(0);

        assertEquals(productToBuy.getProductName(), productInCart.getProductName());
    }

    @Test(
            dataProvider = "scenario02",
            dataProviderClass = DataProviders.class)
    public void shouldntLogInvalidUser(User user){
        LoginPage login = new LoginPage(driver);
        login.login(user);

        assertTrue(login.hasLoginFailed());
        assertEquals(login.getErrorMessage(), "Sorry, this user has been locked out.");
    }
}
