package com.vscodetest.utils;

import com.vscodetest.config.Configuration;
import io.appium.java_client.windows.WindowsDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class WaitUtils {
    private WindowsDriver driver;
    private static final Logger logger = LogManager.getLogger(WaitUtils.class);
    private int defaultTimeoutInSeconds;

    public WaitUtils(WindowsDriver driver) {
        this.driver = driver;
        try {
            Configuration config = new Configuration();
            this.defaultTimeoutInSeconds = config.getDefaultTimeout();
        } catch (Exception e) {
            this.defaultTimeoutInSeconds = 10; // Default fallback
            logger.warn("Using default timeout of 10 seconds as configuration could not be loaded");
        }
    }

    /**
     * Creates a FluentWait instance with default settings
     * @return Wait<WindowsDriver> instance
     */
    private Wait<WindowsDriver> createFluentWait() {
        return createFluentWait(defaultTimeoutInSeconds);
    }

    /**
     * Creates a FluentWait instance with custom timeout
     * @param timeoutInSeconds Custom timeout in seconds
     * @return Wait<WindowsDriver> instance
     */
    private Wait<WindowsDriver> createFluentWait(int timeoutInSeconds) {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutInSeconds))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);
    }

    /**
     * Waits for an element to be present in the DOM
     * @param locator The locator of the element
     * @return The WebElement once it is located
     */
    public WebElement waitForElementPresent(By locator) {
        logger.debug("Waiting for element to be present: " + locator);
        return createFluentWait().until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * Waits for an element to be present with custom timeout
     * @param locator The locator of the element
     * @param timeoutInSeconds Custom timeout in seconds
     * @return The WebElement once it is located
     */
    public WebElement waitForElementPresent(By locator, int timeoutInSeconds) {
        logger.debug("Waiting for element to be present with timeout " + timeoutInSeconds + "s: " + locator);
        return createFluentWait(timeoutInSeconds).until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * Waits for an element to be clickable
     * @param locator The locator of the element
     * @return The WebElement once it is clickable
     */
    public WebElement waitForElementClickable(By locator) {
        logger.debug("Waiting for element to be clickable: " + locator);
        return createFluentWait().until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Waits for an element to be visible
     * @param locator The locator of the element
     * @return The WebElement once it is visible
     */
    public WebElement waitForElementVisible(By locator) {
        logger.debug("Waiting for element to be visible: " + locator);
        return createFluentWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Waits for an element to disappear
     * @param locator The locator of the element
     * @return true if the element is no longer visible
     */
    public boolean waitForElementInvisible(By locator) {
        logger.debug("Waiting for element to be invisible: " + locator);
        return createFluentWait().until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    /**
     * Waits for a specific text to be present in an element
     * @param locator The locator of the element
     * @param text The text to wait for
     * @return true if the text is present in the element
     */
    public boolean waitForTextPresent(By locator, String text) {
        logger.debug("Waiting for text '" + text + "' to be present in element: " + locator);
        return createFluentWait().until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    /**
     * Pauses execution for the specified amount of time
     * @param milliseconds Time to sleep in milliseconds
     */
    public void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.warn("Sleep interrupted", e);
        }
    }
}
