package com.technologylatam.tests;

import com.technologylatam.base.BaseTest;
import com.technologylatam.model.Product;
import com.technologylatam.model.User;
import com.technologylatam.pages.LoginPage;
import com.technologylatam.pages.ProductsPage;
import com.technologylatam.provider.DataProviders;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class ProductsTest extends BaseTest {

    @Test(
            dataProvider = "scenario01",
            dataProviderClass = DataProviders.class)
    public void shouldSortProductsByPriceHighToLow(User user) {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);

        loginPage.login(user);

        assertTrue(productsPage.productsPageIsDisplayed());

        productsPage.sortProductsByPriceHighToLow();

        List<Product> allProducts = productsPage.getAllProducts();

        Product highPriceProduct = allProducts.get(0);
        Product lowPriceProduct = allProducts.get(allProducts.size() - 1);

        assertTrue(highPriceProduct.getPrice() > lowPriceProduct.getPrice());

    }
}
