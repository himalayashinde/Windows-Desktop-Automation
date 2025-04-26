package com.vscodetest.base;

import com.vscodetest.utils.ElementUtils;
import com.vscodetest.utils.WaitUtils;
import io.appium.java_client.windows.WindowsDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BasePage {
    protected WindowsDriver driver;
    protected WaitUtils waitUtils;
    protected ElementUtils elementUtils;
    protected static final Logger logger = LogManager.getLogger(BasePage.class);

    public BasePage(WindowsDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
        this.elementUtils = new ElementUtils(driver);
    }

    protected WebElement getElement(By locator) {
        return waitUtils.waitForElementPresent(locator);
    }

    protected void click(By locator) {
        getElement(locator).click();
        logger.debug("Clicked on element: " + locator);
    }

    protected void sendKeys(By locator, String text) {
        getElement(locator).sendKeys(text);
        logger.debug("Entered text '" + text + "' in element: " + locator);
    }

    protected String getText(By locator) {
        String text = getElement(locator).getText();
        logger.debug("Retrieved text '" + text + "' from element: " + locator);
        return text;
    }

    protected boolean isElementDisplayed(By locator) {
        return elementUtils.isElementDisplayed(locator);
    }
}
