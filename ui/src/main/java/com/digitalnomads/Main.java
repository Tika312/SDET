package com.digitalnomads;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Main {
    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();

        // Initialize ChromeDriver
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized"); // Start Chrome in maximized mode
        WebDriver driver = new ChromeDriver(options);

        // Navigate to the web page
        driver.get("https://example.com");

        // Maximize the window to ensure it's fully opened
        driver.manage().window().maximize();

        // Use JavaScript to get the full page size
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        Long pageWidth = (Long) jsExecutor.executeScript("return window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth;");
        Long pageHeight = (Long) jsExecutor.executeScript("return window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight;");

        System.out.println("Page Width: " + pageWidth + " pixels");
        System.out.println("Page Height: " + pageHeight + " pixels");

        // Close the browser
        driver.quit();
    }
}