package com.vscodetest.pages;

import com.vscodetest.base.BasePage;
import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

public class ExtensionMarketplacePage extends BasePage {
    
    // Locators
    private final By searchBox = By.name("Search Extensions in Marketplace");
    private final By installButton = By.name("Install");
    private final By extensionSettingsButton = By.name("Extension Settings");
    private final By uninstallButton = By.name("Uninstall");
    private final By enableButton = By.name("Enable");
    private final By disableButton = By.name("Disable");
    
    public ExtensionMarketplacePage(WindowsDriver driver) {
        super(driver);
    }
    
    public ExtensionMarketplacePage searchExtension(String extensionName) {
        logger.info("Searching for extension: " + extensionName);
        click(searchBox);
        sendKeys(searchBox, extensionName);
        new Actions(driver).sendKeys(Keys.ENTER).perform();
        return this;
    }
    
    public ExtensionMarketplacePage selectExtension(String extensionName) {
        logger.info("Selecting extension: " + extensionName);
        By extensionLocator = By.name(extensionName);
        waitUtils.waitForElementPresent(extensionLocator);
        click(extensionLocator);
        return this;
    }
    
    public ExtensionMarketplacePage installExtension() {
        logger.info("Installing extension");
        if (isElementDisplayed(installButton)) {
            click(installButton);
            // Wait for installation to complete
            waitUtils.waitForElementPresent(uninstallButton, 30);
        } else {
            logger.info("Extension is already installed");
        }
        return this;
    }
    
    public boolean isExtensionInstalled(String extensionName) {
        logger.info("Checking if extension is installed: " + extensionName);
        selectExtension(extensionName);
        return isElementDisplayed(uninstallButton);
    }
    
    public ExtensionMarketplacePage uninstallExtension() {
        logger.info("Uninstalling extension");
        if (isElementDisplayed(uninstallButton)) {
            click(uninstallButton);
            // Wait for uninstallation to complete
            waitUtils.waitForElementPresent(installButton, 30);
        } else {
            logger.info("Extension is not installed");
        }
        return this;
    }
    
    public ExtensionMarketplacePage enableExtension() {
        logger.info("Enabling extension");
        if (isElementDisplayed(enableButton)) {
            click(enableButton);
            // Wait for enable to complete
            waitUtils.waitForElementPresent(disableButton, 10);
        } else {
            logger.info("Extension is already enabled");
        }
        return this;
    }
    
    public ExtensionMarketplacePage disableExtension() {
        logger.info("Disabling extension");
        if (isElementDisplayed(disableButton)) {
            click(disableButton);
            // Wait for disable to complete
            waitUtils.waitForElementPresent(enableButton, 10);
        } else {
            logger.info("Extension is already disabled");
        }
        return this;
    }
    
    public ExtensionSettingsPage openExtensionSettings() {
        logger.info("Opening extension settings");
        click(extensionSettingsButton);
        return new ExtensionSettingsPage(driver);
    }
}