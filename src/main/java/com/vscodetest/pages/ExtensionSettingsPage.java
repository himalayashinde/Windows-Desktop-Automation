package com.vscodetest.pages;

import com.vscodetest.base.BasePage;
import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class ExtensionSettingsPage extends BasePage {
    
    // Locators
    private final By settingsSearchBox = By.name("Search settings");
    private final By settingItems = By.className("setting-item");
    private final By settingItemTitle = By.className("setting-item-title");
    private final By checkboxSetting = By.className("setting-value-checkbox");
    private final By textInputSetting = By.className("setting-item-control");
    
    public ExtensionSettingsPage(WindowsDriver driver) {
        super(driver);
    }
    
    public ExtensionSettingsPage searchSetting(String settingName) {
        logger.info("Searching for setting: " + settingName);
        click(settingsSearchBox);
        sendKeys(settingsSearchBox, settingName);
        return this;
    }
    
    public ExtensionSettingsPage toggleCheckboxSetting(String settingName) {
        logger.info("Toggling checkbox setting: " + settingName);
        WebElement setting = findSettingByName(settingName);
        if (setting != null) {
            WebElement checkbox = setting.findElement(checkboxSetting);
            checkbox.click();
        } else {
            logger.warn("Setting not found: " + settingName);
        }
        return this;
    }
    
    public ExtensionSettingsPage enterTextSetting(String settingName, String value) {
        logger.info("Entering text for setting: " + settingName + " with value: " + value);
        WebElement setting = findSettingByName(settingName);
        if (setting != null) {
            WebElement textInput = setting.findElement(textInputSetting);
            textInput.click();
            
            // Clear existing text
            Actions actions = new Actions(driver);
            actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();
            actions.sendKeys(Keys.DELETE).perform();
            
            // Enter new text
            textInput.sendKeys(value);
        } else {
            logger.warn("Setting not found: " + settingName);
        }
        return this;
    }
    
    public boolean isSettingPresent(String settingName) {
        logger.info("Checking if setting is present: " + settingName);
        return findSettingByName(settingName) != null;
    }
    
    public String getSettingValue(String settingName) {
        logger.info("Getting value for setting: " + settingName);
        WebElement setting = findSettingByName(settingName);
        if (setting != null) {
            WebElement valueElement = setting.findElement(By.className("setting-item-value"));
            return valueElement.getText();
        }
        logger.warn("Setting not found: " + settingName);
        return null;
    }
    
    private WebElement findSettingByName(String settingName) {
        List<WebElement> settings = driver.findElements(settingItems);
        for (WebElement setting : settings) {
            try {
                WebElement titleElement = setting.findElement(settingItemTitle);
                if (titleElement.getText().contains(settingName)) {
                    return setting;
                }
            } catch (Exception e) {
                // Continue to next element if this one doesn't have a title
            }
        }
        return null;
    }
}
