package com.automatedtest.poc.model.pages;

import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

@Component
public class LoginPage extends GeneralPage{

    private static final By loginUserField=By.id("user-name");
    private static final By passwordUserField =By.id("password");


    public void logIn(String standardLogin, String standardPassword){
        setTextBySelectorNoEnter(standardLogin, loginUserField );
        setTextBySelector(standardPassword, passwordUserField);

    }
}
