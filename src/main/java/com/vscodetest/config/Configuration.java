package com.vscodetest.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {
    private Properties properties;
    private static final Logger logger = LogManager.getLogger(Configuration.class);

    public Configuration() {
        properties = new Properties();
        try {
            // Try multiple locations for the config file
            InputStream inputStream = null;
            
            // Try to load from classpath
            inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");
            
            // If not found in classpath, try to load from file system
            if (inputStream == null) {
                try {
                    inputStream = new FileInputStream("src/main/resources/config.properties");
                } catch (IOException e) {
                    logger.warn("Could not find config.properties in src/main/resources: " + e.getMessage());
                }
            }
            
            // Try another location if still not found
            if (inputStream == null) {
                try {
                    inputStream = new FileInputStream("config.properties");
                } catch (IOException e) {
                    logger.warn("Could not find config.properties in project root: " + e.getMessage());
                }
            }
            
            // If still not found, throw exception
            if (inputStream == null) {
                throw new IOException("Could not find config.properties file in any location");
            }
            
            properties.load(inputStream);
            logger.info("Configuration loaded successfully");
            
            // Log all properties for debugging
            logger.debug("Loaded properties:");
            for (String key : properties.stringPropertyNames()) {
                logger.debug(key + " = " + properties.getProperty(key));
            }
            
        } catch (IOException e) {
            logger.error("Failed to load configuration: " + e.getMessage(), e);
            throw new RuntimeException("Failed to load configuration", e);
        }
    }

    public String getVSCodePath() {
        String path = properties.getProperty("vscode.path");
        if (path == null || path.isEmpty()) {
            logger.error("VS Code path is not defined in config.properties");
            throw new RuntimeException("VS Code path is not defined in config.properties");
        }
        logger.debug("VS Code path from config: " + path);
        return path;
    }

    public String getWinAppDriverUrl() {
        String url = properties.getProperty("winappdriver.url");
        if (url == null || url.isEmpty()) {
            logger.warn("WinAppDriver URL is not defined in config.properties, using default");
            return "http://127.0.0.1:4723";
        }
        return url;
    }

    public int getDefaultTimeout() {
        try {
            return Integer.parseInt(properties.getProperty("default.timeout", "10"));
        } catch (NumberFormatException e) {
            logger.warn("Invalid default timeout in config.properties, using default value of 10");
            return 10;
        }
    }
    
    public String getExtensionName() {
        String name = properties.getProperty("extension.name");
        if (name == null || name.isEmpty()) {
            logger.error("Extension name is not defined in config.properties");
            throw new RuntimeException("Extension name is not defined in config.properties");
        }
        return name;
    }

    public String getExtensionId() {
        String id = properties.getProperty("extension.id");
        if (id == null || id.isEmpty()) {
            logger.error("Extension ID is not defined in config.properties");
            throw new RuntimeException("Extension ID is not defined in config.properties");
        }
        return id;
    }
}