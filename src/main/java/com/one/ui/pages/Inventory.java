package com.one.ui.pages;

import com.one.framework.Browser;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.TimeoutException;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static com.one.locators.ClassName.PRODUCT_LABEL;

public class Inventory {


        private Browser browser;

        public Inventory(Browser browser) {
            this.browser = browser;
        }

        public boolean isProductLabelPresent() {
            return browser.elementIsDisplayed(PRODUCT_LABEL);
        }

        public String getProductLabel() {
            return browser.getText(PRODUCT_LABEL);
        }





}
