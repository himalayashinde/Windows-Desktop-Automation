package com.vscodetest.utils;

import io.appium.java_client.windows.WindowsDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

public class ElementUtils {
    private WindowsDriver driver;
    private static final Logger logger = LogManager.getLogger(ElementUtils.class);

    public ElementUtils(WindowsDriver driver) {
        this.driver = driver;
    }

    /**
     * Checks if an element is displayed on the page
     * @param locator The locator of the element
     * @return true if the element is displayed, false otherwise
     */
    public boolean isElementDisplayed(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            logger.debug("Element not displayed: " + locator);
            return false;
        }
    }

    /**
     * Checks if an element is enabled
     * @param locator The locator of the element
     * @return true if the element is enabled, false otherwise
     */
    public boolean isElementEnabled(By locator) {
        try {
            return driver.findElement(locator).isEnabled();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            logger.debug("Element not enabled: " + locator);
            return false;
        }
    }

    /**
     * Gets the attribute value of an element
     * @param locator The locator of the element
     * @param attributeName The name of the attribute
     * @return The attribute value
     */
    public String getElementAttribute(By locator, String attributeName) {
        try {
            return driver.findElement(locator).getAttribute(attributeName);
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            logger.error("Failed to get attribute '" + attributeName + "' from element: " + locator);
            return null;
        }
    }

    /**
     * Checks if an element exists on the page
     * @param locator The locator of the element
     * @return true if the element exists, false otherwise
     */
    public boolean isElementExists(By locator) {
        try {
            return driver.findElements(locator).size() > 0;
        } catch (Exception e) {
            logger.debug("Element does not exist: " + locator);
            return false;
        }
    }

    /**
     * Gets all elements matching the locator
     * @param locator The locator to find elements
     * @return List of WebElements
     */
    public java.util.List<WebElement> getElements(By locator) {
        return driver.findElements(locator);
    }
}
