package com.utilities;

import com.microsoft.playwright.*;
import org.testng.annotations.*;

import java.io.File;
import java.nio.file.Paths;

public class TestBase {
    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected static Page page;

    public String baseUrl;
    public String userName;
    public String password;

    @BeforeMethod
    @Parameters("browser")
    public void setUp(String browserName) {
        try {
            // Load config values
            baseUrl = ReadConfig.readPropertyFileData("baseUrl", "config");
            userName = ReadConfig.readPropertyFileData("userName", "config");
            password = ReadConfig.readPropertyFileData("password", "config");

            System.out.println("Base URL: " + baseUrl);
            System.out.println("Username: " + userName);
            System.out.println("Password: " + password);

            // Initialize Playwright and browser
            playwright = Playwright.create();

            switch (browserName.toLowerCase()) {
                case "chromium":
                    browser = playwright.chromium().launch(
                        new BrowserType.LaunchOptions().setChannel("chromium").setHeadless(false));
                    break;
                case "firefox":
                    browser = playwright.firefox().launch(
                        new BrowserType.LaunchOptions().setHeadless(false));
                    break;
                case "webkit":
                    browser = playwright.webkit().launch(
                        new BrowserType.LaunchOptions().setHeadless(false));
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browserName);
            }

            context = browser.newContext();
            page = context.newPage();

            // Navigate to base URL
            if (baseUrl != null && !baseUrl.isEmpty()) {
                page.navigate(baseUrl);
            } else {
                throw new RuntimeException("Base URL is missing or empty.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize Playwright setup", e);
        }
    }

    public static String captureScreenshot(String testName) {
        String path = "./target/screenshots/" + testName + ".png";
        new File("./target/screenshots").mkdirs(); // Ensure folder exists
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)));
        return path;
    }

    @AfterMethod
    public void tearDown() {
        if (page != null) page.close();
        if (context != null) context.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }

    public Page getPage() {
        return page;
    }
}