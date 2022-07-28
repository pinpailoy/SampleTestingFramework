package com.one.ui.pages;

import com.one.framework.Browser;
import com.one.ui.domains.Product;
//import com.sun.org.slf4j.internal.Logger;
//import com.sun.org.slf4j.internal.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static com.one.locators.ClassName.SORT;
import static com.one.locators.Id.*;
import static com.one.locators.Id.PASSWORD;
import static com.one.locators.LinkText.ADDTOCART;
import static com.one.locators.XPathSelector.*;

public class ProductsContent {

    //private static final Logger logger = LoggerFactory.getLogger(ProductsContent.class);

    private Browser browser;

    public ProductsContent(Browser browser) {
        this.browser = browser;
    }

/*    public ProductsTable<Product> getProductsList() {
        return new ProductsTable<>(browser.await(INVENTORY_CONTAINER),
                cells -> new Product(cells.get(0).getText(),
                       // cells.get(1).getText(),
                       // cells.get(2).getText(),
                        cells.get(1).getText())
        );
    }*/

    public Product getProductFromPosition(int row) {
        return getProductsList().get(row);
    }

    private List<Product> getProductsList() {
        List<Product> rows = new ArrayList<>();
        browser.await(INVENTORY_LIST).findElements(INVENTORY_ITEM).forEach(content -> {
                    String text = content.toString();
                    // split by new line
                    List<String> info = new ArrayList<>();
                    String[] lines = text.split("\\r?\\n");
                    for (String line : lines) {
                        info.add(line);
                    }
                    try {
                        Product newProduct = new Product(info.get(0),
                                info.get(1),
                                info.get(2),
                                info.get(3));
                        rows.add(newProduct);

                    } catch (Exception e) {
                        //logger.error("Product structure is not the expected one");
                    }
                });
        return rows;
    }


    public void sortBy(String criteria){
        browser.selectByVisibleText(SORT, criteria);
    }

    public List<Product> getAllProducts(){
        return getProductsList();
    }
    public void clickShoppingCart(){
        browser.click(CART);
    }

    public void clickCheckOut(){
        browser.click(CHECKOUT);
    }
    public void insertDataForCheckout(String firstname,String lastname,String postalcode){
        browser.await(FIRSTNAME).sendKeys(firstname);
        browser.await(LASTNAME).sendKeys(lastname);
        browser.await(POSTALCODE).sendKeys(postalcode);
    }
    public void clickContinue(){
        browser.click(CONTINUE);
    }

    public void clickFinish(){
        browser.click(FINISH);
    }
}
