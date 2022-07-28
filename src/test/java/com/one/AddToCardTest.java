package com.one;

import com.one.framework.Browser;
import com.one.framework.WebDriverConfig;
import com.one.ui.domains.Product;
import com.one.ui.pages.LoginForm;
import com.one.ui.pages.ProductsContent;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import javax.inject.Inject;

import static com.one.locators.ClassName.SORT;
import static com.one.locators.LinkText.ADDTOCART;

@ContextConfiguration(classes = {LoginForm.class, WebDriverConfig.class, Browser.class})
public class AddToCardTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private LoginForm loginForm;

    @Inject
    private Browser browser;

    private ProductsContent productsContent;
    private Product product;

    @Test
    public void verifyAddFirstProductToCart() {
        browser.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        //After click ADD TO CART on First Product-> we will check Shopping Cart
        productsContent.clickShoppingCart();
        //on Your cart page https://www.saucedemo.com/cart.html
        //check product name in the cart
        Assert.assertEquals(product.getName(), "Sauce Labs Backpack");

    }

    @Test
    public void verifyAddSecondProductToCart() {
        browser.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        //After click ADD TO CART on Second Product-> we will check Shopping Cart
        productsContent.clickShoppingCart();
        //on Your cart page https://www.saucedemo.com/cart.html
        //check product name in the cart
        Assert.assertEquals(product.getName(), "Sauce Labs Bike Light");

    }

    @Test
    @Parameters({"firstname", "lastname", "postalcode"})
    public void verifyCheckOutPageForSingleProduct(String firstname, String lastname, String postalcode) {
        product = productsContent.getProductFromPosition(0);
        browser.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        //After click ADD TO CART on First Product-> click Shopping Cart ICON
        productsContent.clickShoppingCart();
        //click  CHECK OUT button
        productsContent.clickCheckOut();
        //We are on this page https://www.saucedemo.com/checkout-step-one.html and fill out the form
        productsContent.insertDataForCheckout(firstname, lastname, postalcode);
        //click Continue
        productsContent.clickContinue();
        //We're on https://www.saucedemo.com/checkout-step-two.html
        //Then we check inventory_item_name
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(browser.findElement(By.cssSelector("div.inventory_item_name")), "Sauce Labs Backpack");
        softAssert.assertEquals(product.getPrice(), "$29.99");
        //check Shipping Information:
        softAssert.assertEquals(browser.findElement(By.cssSelector("div.summary_value_label")).getText(), "FREE PONY EXPRESS DELIVERY!");
        //check Item total
        softAssert.assertEquals(browser.findElement(By.cssSelector("div.summary_subtotal_label")).getText(), product.getPrice());
        //check Total price
        Double tax = Double.parseDouble(browser.findElement(By.cssSelector("div.summary_tax_label")).getText().replace("Tax: $", ""));
        softAssert.assertEquals(Double.parseDouble(browser.findElement(By.cssSelector("div.summary_total_label")).getText().replace("Total: $", "")), Double.parseDouble(product.getPrice().replace("$", "")) + tax);
        //click Finish
        productsContent.clickFinish();
        //go to https://www.saucedemo.com/checkout-complete.html
        //check succesfully Order
        softAssert.assertEquals(browser.findElement(By.cssSelector("h2.complete-header")).getText(), "THANK YOU FOR YOUR ORDER");
        softAssert.assertAll();


    }



    @BeforeClass(alwaysRun = true)
    @Parameters({"username", "password"})
    public void beforeTestMethod(String username, String password) {
        productsContent = new ProductsContent(browser);
        loginForm.loginAs(username, password);
    }
}
