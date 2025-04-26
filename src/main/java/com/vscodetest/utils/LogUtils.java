package com.vscodetest.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Utility class for logging operations
 */
public class LogUtils {
    private static final Logger logger = LogManager.getLogger(LogUtils.class);

    /**
     * Logs an info message
     * @param message The message to log
     */
    public static void info(String message) {
        logger.info(message);
    }

    /**
     * Logs a debug message
     * @param message The message to log
     */
    public static void debug(String message) {
        logger.debug(message);
    }

    /**
     * Logs a warning message
     * @param message The message to log
     */
    public static void warn(String message) {
        logger.warn(message);
    }

    /**
     * Logs an error message
     * @param message The message to log
     */
    public static void error(String message) {
        logger.error(message);
    }

    /**
     * Logs an error message with exception details
     * @param message The message to log
     * @param throwable The exception to log
     */
    public static void error(String message, Throwable throwable) {
        logger.error(message, throwable);
    }

    /**
     * Logs the start of a test
     * @param testName The name of the test
     */
    public static void startTest(String testName) {
        logger.info("========== Starting Test: " + testName + " ==========");
    }

    /**
     * Logs the end of a test
     * @param testName The name of the test
     */
    public static void endTest(String testName) {
        logger.info("========== Ending Test: " + testName + " ==========");
    }
}