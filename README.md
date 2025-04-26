# Windows-Desktop-Automation

## Overview
This project automates desktop applications on Windows using tools like Appium and Selenium. It includes end-to-end tests for verifying the functionality of Visual Studio Code extensions.

## Project Structure
- **`src/main`**: Contains the main application code.
- **`src/test`**: Contains test cases for automation.
- **`test-output`**: Stores test reports and logs.

## Features
- Automated testing of Visual Studio Code extensions.
- Generates detailed TestNG reports in HTML format.
- Supports configuration through `testng.xml`.

## Prerequisites
- Java Development Kit (JDK) 8 or higher.
- Maven for dependency management.
- Appium for desktop automation.
- Visual Studio Code installed on the system.

## How to Run
1. Clone the repository:
   ```sh
   git clone <repository-url>

2. Navigate to the project directory:
    cd Windows-Desktop-Automation

3. Build the project using Maven:
    mvn clean install

4. Run the tests using TestNG:
    mvn test

Test Reports
TestNG reports are generated in the test-output directory.
Open test-output/index.html in a browser to view the detailed test results.
Troubleshooting
Ensure Appium is running and configured correctly.
Verify that the testng.xml file is correctly set up in src/test/resources.
License
This project is licensed under the MIT License. See the LICENSE file for details.


Feel free to customize this content based on your project's specific details.
