package com.automatedtest.poc.model.pages;

import com.automatedtest.core.utils.repeatable.Repeatable;
import com.automatedtest.poc.config.SeleniumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

import static com.automatedtest.core.logger.BFLogger.logDebug;

//TODO Here you write methods that do general operations on the application
//TODO These methods are directly or indirectly used in STEP DEFINITIONS
//TODO Create appropriate page models when implementing test cases for SauceDemo
@Component
public class GeneralPage {

    private final int repeatCount = 15;
    private final int waitTime = 2000;

    @Autowired
    private Repeatable repeatable;


    public WebDriver getDriver() {
        return SeleniumDriver.getDriver();
    }

    public void openPage(String page) {
        getDriver().manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        getDriver().get(page);
        getDriver().manage().window().maximize();
    }

    public String returnUrl(){
        String currentPageUrl= getDriver().getCurrentUrl();

        return currentPageUrl;


    }

    public WebElement findElementBySelector(By selector) {
        logDebug("Looking for element by selector " + selector.toString());
        repeatable.repeat(() -> {
            getDriver().findElement(selector);
        }, repeatCount, waitTime);
        return getDriver().findElement(selector);
    }

    public void clickBySelector(By selector) {
        logDebug("Click element by selector " + selector.toString());
        repeatable.repeat(() -> {
            getDriver().findElement(selector).click();
        }, repeatCount, waitTime);
    }

    public void setTextBySelector(String text, By selector) {
        logDebug("Set Text by selector " + selector.toString());
        repeatable.repeat(() -> {
            getDriver().findElement(selector).sendKeys(text + Keys.ENTER);
        }, repeatCount, waitTime);
    }
    public void setTextBySelectorNoEnter(String text, By selector) {
        logDebug("Set Text by selector " + selector.toString());
        repeatable.repeat(() -> {
            getDriver().findElement(selector).sendKeys(text);
        }, repeatCount, waitTime);
    }

}
