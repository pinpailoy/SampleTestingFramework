package com.one.ui.pages;

import com.one.framework.Browser;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.TimeoutException;

import static com.one.locators.ClassName.PRODUCT_LABEL;

import static com.one.locators.XPathSelector.*;

public class Header {

    private Browser browser;

    public Header(Browser browser) {
        this.browser = browser;
    }

    public void  openMenu() {
        browser.click(OPEN_MENU_BUTTON);
    }

}
