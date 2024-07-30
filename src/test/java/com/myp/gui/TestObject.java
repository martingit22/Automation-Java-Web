package com.myp.gui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class TestObject {
    // Directory paths for storing test resources
    protected static final String TEST_RESOURCES_DIR = "src/test/resources/";
    protected static final String DOWNLOAD_DIR = TEST_RESOURCES_DIR.concat("download/");
    protected static final String SCREENSHOTS_DIR = TEST_RESOURCES_DIR.concat("screenshots/");
    protected static final String REPORTS_DIR = TEST_RESOURCES_DIR.concat("reports/");
    protected static final String UPLOAD_DIR = TEST_RESOURCES_DIR.concat("upload/");

    private WebDriver webDriver;

    public WebDriver getWebDriver() {
        return webDriver;
    }

    @BeforeSuite
    protected final void setupTestSuite() throws IOException {
        // Clean up directories before running the test suite
        cleanDirectory(REPORTS_DIR);
        cleanDirectory(SCREENSHOTS_DIR);

        // Setup WebDriverManager for Chrome
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    protected final void setUpTest() {
        // Initialize ChromeDriver with custom options
        this.webDriver = new ChromeDriver(configChromeOptions());
        this.webDriver.manage().window().maximize();
        this.webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
    }

    @AfterMethod
    protected final void tearDownTest(ITestResult testResult) throws IOException {
        // Take screenshot if the test fails
        takeScreenshot(testResult);
        quitDriver();
    }

    @AfterSuite
    public void cleanFiles() throws IOException {
        // Clean up download directory after the test suite
        cleanDirectory(DOWNLOAD_DIR);
    }

    private void quitDriver() {
        if (webDriver != null) {
            System.out.println("The driver is about to be closed.");
            webDriver.quit();
        }
    }

    private void takeScreenshot(ITestResult testResult) {
        if (ITestResult.FAILURE == testResult.getStatus()) {
            try {
                TakesScreenshot takesScreenshot = (TakesScreenshot) webDriver;
                File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
                String testName = testResult.getName();
                File destinationFile = new File(SCREENSHOTS_DIR.concat(testName).concat(".jpg"));
                FileUtils.copyFile(screenshot, destinationFile);
                System.out.println("Screenshot saved at: " + destinationFile.getPath());
            } catch (IOException e) {
                System.out.println("Unable to create a screenshot file: " + e.getMessage());
            }
        }
    }

    private ChromeOptions configChromeOptions() {
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory",
                System.getProperty("user.dir").concat("\\").concat(DOWNLOAD_DIR));

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("prefs", prefs);
        chromeOptions.addArguments("disable-popup-blocking");

        return chromeOptions;
    }

    private void cleanDirectory(String directoryPath) throws IOException {
        File directory = new File(directoryPath);

        // Create directory if it does not exist
        if (!directory.exists()) {
            FileUtils.forceMkdir(directory);
            System.out.println("Created folder with path: " + directoryPath);
        }

        // Clean directory
        FileUtils.cleanDirectory(directory);
        String[] fileList = directory.list();
        if (fileList != null && fileList.length == 0) {
            System.out.printf("All files are deleted in Directory: %s%n", directoryPath);
        } else {
            System.out.printf("Unable to delete the files in Directory: %s%n", directoryPath);
        }
    }
}
