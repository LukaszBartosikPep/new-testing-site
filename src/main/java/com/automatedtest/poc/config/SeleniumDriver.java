package com.automatedtest.poc.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.stereotype.Component;
import java.util.HashMap;

public class SeleniumDriver {
    static ThreadLocal<WebDriver> threadLocal = new ThreadLocal<>();
    static boolean driverOpen = false;

    public static WebDriver getDriver() {
        if (threadLocal.get() == null) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = customizeCapabilities(new DesiredCapabilities());
            options.addArguments("start-maximized");
            options.addArguments("--disable-gpu");
            options.addArguments("enable-automation");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-browser-side-navigation");
            HashMap<String, Object> chromeDriverPreference = new HashMap<>();
            chromeDriverPreference.put("download.default_directory", setDownloadFilePath());
            options.setExperimentalOption("prefs", chromeDriverPreference);
            WebDriver driver = new ChromeDriver(options);
            System.out.println(driver);
            threadLocal.set(driver);
            driverOpen = true;
            return driver;
        } else
            return threadLocal.get();
    }

    public static void closeDriver() {
        if (driverOpen) {
            getDriver().quit();
            threadLocal.remove();
            driverOpen = false;
        }
    }

    private static ChromeOptions customizeCapabilities(DesiredCapabilities cap) {
        cap.setJavascriptEnabled(true);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.merge(cap);
        return chromeOptions;
    }

    private static String setDownloadFilePath(){
        return System.getProperty("user.home") + "\\Downloads";
    }

    public static boolean isPresent() {
        return threadLocal.get() != null;
    }
}
