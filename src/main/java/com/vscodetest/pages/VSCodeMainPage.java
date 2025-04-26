package com.vscodetest.pages;

import com.vscodetest.base.BasePage;
import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

public class VSCodeMainPage extends BasePage {
    
    // Locators
    private final By activityBarExtensionsButton = By.name("Extensions");
    private final By commandPaletteInput = By.className("quick-input-input");
    private final By statusBar = By.className("statusbar-item");
    private final By editorArea = By.className("editor-instance");
    
    public VSCodeMainPage(WindowsDriver driver) {
        super(driver);
    }
    
    public ExtensionMarketplacePage openExtensionsMarketplace() {
        logger.info("Opening Extensions Marketplace");
        click(activityBarExtensionsButton);
        return new ExtensionMarketplacePage(driver);
    }
    
    public void openCommandPalette() {
        logger.info("Opening Command Palette");
        // Press Ctrl+Shift+P to open command palette
        Actions actions = new Actions(driver);
        actions.keyDown(Keys.CONTROL).keyDown(Keys.SHIFT).sendKeys("p").keyUp(Keys.SHIFT).keyUp(Keys.CONTROL).perform();
        waitUtils.waitForElementPresent(commandPaletteInput);
    }
    
    public void executeCommand(String command) {
        logger.info("Executing command: " + command);
        openCommandPalette();
        sendKeys(commandPaletteInput, command);
        new Actions(driver).sendKeys(Keys.ENTER).perform();
    }
    
    public boolean isExtensionActivated(String extensionName) {
        logger.info("Checking if extension is activated: " + extensionName);
        // This is a simplified check - you may need to adjust based on your extension
        executeCommand("Developer: Show Running Extensions");
        // Wait for the running extensions view to appear and check if your extension is listed
        return driver.findElements(By.name(extensionName)).size() > 0;
    }
    
    public void createNewFile() {
        logger.info("Creating a new file");
        Actions actions = new Actions(driver);
        actions.keyDown(Keys.CONTROL).sendKeys("n").keyUp(Keys.CONTROL).perform();
    }
    
    public void saveFile(String filePath) {
        logger.info("Saving file to: " + filePath);
        Actions actions = new Actions(driver);
        actions.keyDown(Keys.CONTROL).sendKeys("s").keyUp(Keys.CONTROL).perform();
        
        // Wait for save dialog and enter path
        By saveInputLocator = By.className("input");
        waitUtils.waitForElementPresent(saveInputLocator);
        sendKeys(saveInputLocator, filePath);
        actions.sendKeys(Keys.ENTER).perform();
    }
    
    public void typeInEditor(String text) {
        logger.info("Typing in editor: " + text);
        click(editorArea);
        new Actions(driver).sendKeys(text).perform();
    }
}
