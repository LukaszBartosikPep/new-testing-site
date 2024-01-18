package com.automatedtest.poc.model.pages;

import org.openqa.selenium.By;
import org.springframework.stereotype.Component;


@Component
public class Finalization extends GeneralPage {
    private static final By finishTransactionButton =By.id("finish");
    private static final By goHomeButton =By.id("back-to-products");





    public void finished(){

        clickBySelector(finishTransactionButton);
        clickBySelector(goHomeButton);
    }
}
