package com.one.locators;

import org.openqa.selenium.By;

import java.util.function.Supplier;

import static org.openqa.selenium.By.className;

public enum ClassName implements Supplier<By> {

    PRODUCT_LABEL("title"),
    LOGO("login_logo"),
    SORT("product_sort_container");

    //INVENTORY_LIST("inventory_list"),
    //INVENTORY_ITEM("inventory_item");



    private final By by;

    ClassName(String id) {
        this.by = className(id);
    }

    @Override
    public By get() {
        return by;
    }

    @Override
    public String toString() {
        return by.toString();
    }
}