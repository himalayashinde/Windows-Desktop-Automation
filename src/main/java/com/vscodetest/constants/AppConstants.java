package com.vscodetest.constants;

/**
 * Constants used throughout the application
 */
public class AppConstants {
    // Timeouts
    public static final int DEFAULT_TIMEOUT = 10;
    public static final int LONG_TIMEOUT = 30;
    public static final int SHORT_TIMEOUT = 5;
    
    // VS Code UI constants
    public static final String ACTIVITY_BAR_CLASS = "ActivityBar";
    public static final String EDITOR_CLASS = "EditorComponent";
    public static final String EXTENSIONS_VIEW_CLASS = "ExtensionsViewlet";
    
    // VS Code keyboard shortcuts
    public static final String COMMAND_PALETTE_SHORTCUT = "ctrl+shift+p";
    public static final String NEW_FILE_SHORTCUT = "ctrl+n";
    public static final String SAVE_FILE_SHORTCUT = "ctrl+s";
    
    // Extension-related constants
    public static final String EXTENSION_INSTALL_BUTTON_NAME = "Install";
    public static final String EXTENSION_SETTINGS_BUTTON_NAME = "Extension Settings";
    
    // Test data
    public static final String TEST_FILE_EXTENSION = ".txt";
    public static final String TEST_FILE_CONTENT = "This is a test file created by automation.";
}
