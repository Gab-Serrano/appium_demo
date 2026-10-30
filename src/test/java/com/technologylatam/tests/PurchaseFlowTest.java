package com.technologylatam.tests;

import com.technologylatam.base.BaseTest;
import com.technologylatam.model.Product;
import com.technologylatam.model.User;
import com.technologylatam.pages.*;
import com.technologylatam.provider.DataProviders;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PurchaseFlowTest extends BaseTest {

    @Test(
            dataProvider = "scenario04",
            dataProviderClass = DataProviders.class
    )
    public void purchaseFlowE2ETest(User user) throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(driver);
        CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage(driver);

        loginPage.login(user);

        Product productToBuy = productsPage.getProductAtInstance(0);
        productsPage.addProductToCart(0);
        productsPage.openCartView();

        Product productInCart = cartPage.getUniqueProduct();

        assertEquals(productToBuy.getProductName(), productInCart.getProductName());
        assertEquals(productToBuy.getPrice(), productInCart.getPrice());

        cartPage.clickContinueButton();

        checkoutPage.fillInformation(user);
        checkoutPage.clickContinue();

        double totalPrice = checkoutOverviewPage.getTotalPriceUniqueItem();

        assertFalse(checkoutOverviewPage.getTotalPriceUniqueItemAsText().isEmpty());
        assertEquals(productToBuy.getProductName(), checkoutOverviewPage.getNamenUniqueItem());
        assertEquals(productToBuy.getPrice(), checkoutOverviewPage.getPriceUniqueItem());
        assertTrue(totalPrice > 0);
        assertEquals(productToBuy.getPrice(), totalPrice);

        checkoutOverviewPage.scrollToButton();
        checkoutOverviewPage.clickFinishButton();

        assertTrue(checkoutCompletePage.isCheckoutComplete());

        assertEquals(checkoutCompletePage.getResultText(), "THANK YOU FOR YOU ORDER");

    }
}
