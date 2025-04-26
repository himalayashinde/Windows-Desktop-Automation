package com.vscodetest.base;

import com.vscodetest.config.Configuration;
import com.vscodetest.utils.LogUtils;
import io.appium.java_client.windows.WindowsDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.net.URL;
import java.time.Duration;

public class BaseTest {
    protected static WindowsDriver driver;
    protected static final Logger logger = LogManager.getLogger(BaseTest.class);
    protected Configuration config;
    protected String testName;

    @BeforeClass
    public void setUpClass() {
        // Initialize configuration at class level
        config = new Configuration();
        logger.info("Configuration initialized at class level");
    }

    @BeforeMethod
    public void setUp() {
        try {
            // Get the name of the test method being executed
            testName = this.getClass().getSimpleName();
            LogUtils.startTest(testName);
            
            // Ensure configuration is initialized
            if (config == null) {
                config = new Configuration();
                logger.info("Configuration initialized in setUp method");
            }
            
            // Try with a simpler app first - Calculator
            logger.info("Trying to connect to Calculator to verify WinAppDriver...");
            
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("app", "Microsoft.WindowsCalculator_8wekyb3d8bbwe!App");
            capabilities.setCapability("platformName", "Windows");
            
            logger.info("Connecting to WinAppDriver at: " + config.getWinAppDriverUrl());
            driver = new WindowsDriver(new URL(config.getWinAppDriverUrl()), capabilities);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            
            logger.info("Connected to Calculator successfully");
            
            // Close Calculator
            driver.quit();
            logger.info("Closed Calculator successfully");
            
            // Now try to connect to VS Code
            String vsCodePath = config.getVSCodePath();
            logger.info("Please launch VS Code manually from: " + vsCodePath);
            logger.info("Waiting 10 seconds for VS Code to start...");
            
            // Use Runtime to execute the VS Code application
            Process process = Runtime.getRuntime().exec(vsCodePath);
            Thread.sleep(10000); // Wait for VS Code to launch
            
            // Set up capabilities for the Root session
            capabilities = new DesiredCapabilities();
            capabilities.setCapability("app", "Root");
            capabilities.setCapability("platformName", "Windows");
            
            // Connect to WinAppDriver
            logger.info("Connecting to WinAppDriver at: " + config.getWinAppDriverUrl());
            driver = new WindowsDriver(new URL(config.getWinAppDriverUrl()), capabilities);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            
            logger.info("Connected to Windows desktop successfully");
            
            // Now we can find and interact with VS Code window using driver.findElement()
            
        } catch (Exception e) {
            logger.error("Failed to initialize the driver: " + e.getMessage(), e);
            throw new RuntimeException("Failed to initialize the driver", e);
        }
    }

    @AfterMethod
    public void tearDown() {
        try {
            if (driver != null) {
                driver.quit();
                logger.info("Driver closed successfully");
            }
        } catch (Exception e) {
            logger.error("Error during teardown: " + e.getMessage(), e);
        } finally {
            LogUtils.endTest(testName);
        }
    }
}
