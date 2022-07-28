package com.one.ui.pages;

import com.one.framework.Browser;

import static com.one.locators.Id.LOGOUT;
import static com.one.locators.XPathSelector.CLOSE_MENU_BUTTON;

public class Menu {

    private Browser browser;

    public Menu(Browser browser) {
        this.browser = browser;
    }

    public boolean isLogoutVisible() {
        return browser.elementIsDisplayed(LOGOUT);
    }

    public void  closeMenu() {
        browser.click(CLOSE_MENU_BUTTON);
    }

    public void  clickLogout() {
        browser.click(LOGOUT);
    }

}
