package com.vscodetest.e2e;

import com.vscodetest.base.BaseTest;
import com.vscodetest.pages.ExtensionMarketplacePage;
import com.vscodetest.pages.VSCodeMainPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SimpleExtensionTest extends BaseTest {

    @BeforeMethod
    @Override
    public void setUp() {
        super.setUp(); // Call the parent's setUp method
    }

    @Test(description = "Install and verify VS Code extension")
    public void testExtensionInstallation() {
        // Make sure config is initialized
        if (config == null) {
            throw new RuntimeException("Configuration is not initialized. Check BaseTest.setUp() method.");
        }
        
        // Get extension name from configuration
        String extensionName = config.getExtensionName();
        
        logger.info("Starting test to install and verify extension: " + extensionName);
        
        // Initialize main page
        VSCodeMainPage mainPage = new VSCodeMainPage(driver);
        
        // Open extensions marketplace
        ExtensionMarketplacePage marketplacePage = mainPage.openExtensionsMarketplace();
        
        // Search for the extension
        marketplacePage.searchExtension(extensionName);
        
        // Select and install the extension
        marketplacePage.selectExtension(extensionName)
                      .installExtension();
        
        // Verify extension is installed
        Assert.assertTrue(marketplacePage.isExtensionInstalled(extensionName), 
                         "Extension was not installed successfully");
        
        // Go back to main page
        mainPage = new VSCodeMainPage(driver);
        
        // Create a new file to test the extension
        mainPage.createNewFile();
        
        // Type some content
        mainPage.typeInEditor("Testing the extension functionality");
        
        // Verify extension is activated
        Assert.assertTrue(mainPage.isExtensionActivated(extensionName),
                         "Extension was not activated");
        
        logger.info("Extension installation and verification test completed successfully");
    }
}
