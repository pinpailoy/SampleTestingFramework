package com.one;

import com.one.framework.Browser;
import com.one.framework.WebDriverConfig;
import com.one.ui.domains.Product;
import com.one.ui.pages.LoginForm;
import com.one.ui.pages.ProductsContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import org.testng.Reporter;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ContextConfiguration(classes = {LoginForm.class, WebDriverConfig.class, Browser.class})
public class MainPageTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private LoginForm loginForm;

    @Inject
    private Browser browser;

    private ProductsContent productsContent;
    private Product product;



    @DataProvider(name = "defaultOrder")
    public Object[][] createDataTest() {
        return new Product[][]{
                { new Product(0,"Sauce Labs Backpack",
                        "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.",
                        "$29.99",
                        "ADD TO CARD") },
                { new Product(1, "Sauce Labs Bike Light",
                        "A red light isn't the desired state in testing but it sure helps when riding your bike at night. Water-resistant with 3 lighting modes, 1 AAA battery included.",
                        "$9.99",
                        "ADD TO CARD") },
                { new Product(2,"Sauce Labs Bolt T-Shirt",
                        "Get your testing superhero on with the Sauce Labs bolt T-shirt. From American Apparel, 100% ringspun combed cotton, heather gray with red bolt.",
                        "$15.99",
                        "ADD TO CARD") },
                {new Product(3, "Sauce Labs Fleece Jacket",
                        "It's not every day that you come across a midweight quarter-zip fleece jacket capable of handling everything from a relaxing day outdoors to a busy day at the office.",
                        "$49.99",
                        "ADD TO CARD")},
                {new Product(4, "Sauce Labs Onesie",
                        "Rib snap infant onesie for the junior automation engineer in development. Reinforced 3-snap bottom closure, two-needle hemmed sleeved and bottom won't unravel.",
                        "$7.99",
                        "ADD TO CARD")}};
    }


    @Test(dataProvider = "defaultOrder")
    public void verifyProducts_defaultOrder(Product p) {
        Reporter.log("Verify that the fifth product in the list - " + p.getName() + " -  is displayed correctly");
        product = productsContent.getProductFromPosition(p.getPosition());

        Reporter.log("Verify that the product " + p.getPosition() +" in the list - " + p.getName() + " -  is displayed correctly");

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(product.getName().matches(p.getName()), "The name " + product.getName() +" for product on position " + p.getPosition() +" is not the expected one");
        softAssert.assertTrue(product.getDescription().contains(p.getDescription()), "The description is not the expected one");
        softAssert.assertTrue(product.getPrice().contains(p.getPrice()), "The price is not the expected one");
        softAssert.assertAll();
    }


    //TODO - add tests for sorting
    @Test
    public void verifyProducts_sortByPrice() {
        SoftAssert softAssert = new SoftAssert();
        productsContent.sortBy("Price (low to high)");
        for (int i = 0; i <= productsContent.getAllProducts().size() - 1; i++) {
            if (i < productsContent.getAllProducts().size() - 1) {
                softAssert.assertTrue(Double.parseDouble(productsContent.getProductFromPosition(i).getPrice().replace("$", "")) <= Double.parseDouble(productsContent.getProductFromPosition(i + 1).getPrice().replace("$", "")), "The price is not sorting");
            }
        }
        softAssert.assertAll();
    }



    @BeforeClass(alwaysRun = true)
    @Parameters({"username", "password"})
    public void beforeTestMethod(String username, String password) {
        productsContent = new ProductsContent(browser);
        loginForm.loginAs(username, password);
    }
}
