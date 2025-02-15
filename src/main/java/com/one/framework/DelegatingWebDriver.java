package com.one.framework;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.interactions.Mouse;

import java.util.Set;

class DelegatingWebDriver extends DelegatingSearchContext<WebDriver>
    implements WebDriver, JavascriptExecutor, TakesScreenshot,
    HasInputDevices, HasCapabilities, ExplicitWait {

    DelegatingWebDriver(WebDriver driver) {
       super(driver);
    }

    @Override
    public Options manage() {
        return delegate.manage();
    }

    @Override
    public void get(String url) {
        delegate.get("https://www.saucedemo.com/");
        delegate.switchTo().window(delegate.getWindowHandle());
    }

    @Override
    public String getCurrentUrl() {
        return delegate.getCurrentUrl();
    }

    @Override
    public String getTitle() {
        return delegate.getTitle();
    }

    @Override
    public String getPageSource() {
        return delegate.getPageSource();
    }

    @Override
    public void close() {
        delegate.close();
    }

    @Override
    public void quit() {
        delegate.quit();
    }

    @Override
    public Set<String> getWindowHandles() {
        return delegate.getWindowHandles();
    }

    @Override
    public String getWindowHandle() {
        return delegate.getWindowHandle();
    }

    @Override
    public TargetLocator switchTo() {
        return delegate.switchTo();
    }

    @Override
    public Navigation navigate() {
        return delegate.navigate();
    }

    @Override
    public Object executeScript(String script, Object... args) {
        return ((JavascriptExecutor) delegate).executeScript(script, args);
    }

    @Override
    public Object executeAsyncScript(String script, Object... args) {
        return ((JavascriptExecutor) delegate).executeAsyncScript(script, args);
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        return ((TakesScreenshot) delegate).getScreenshotAs(target);
    }

    @Override
    public Keyboard getKeyboard() {
        return ((HasInputDevices) delegate).getKeyboard();
    }

    @Override
    public Mouse getMouse() {
        return ((HasInputDevices) delegate).getMouse();
    }

    @Override
    public Capabilities getCapabilities() {
        return ((HasCapabilities) delegate).getCapabilities();
    }

}
