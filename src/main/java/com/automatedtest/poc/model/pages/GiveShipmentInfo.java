package com.automatedtest.poc.model.pages;


import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

@Component
public class GiveShipmentInfo extends GeneralPage{

    private static final By nameField =By.id("first-name");
    private static final By lastNameField =By.id("last-name");
    private static final By postalCodeField =By.id("postal-code");
    private static final By continueCheckoutButton =By.id("continue");






    public void fillInformations(String Fname, String Flastname, String Fpostal){

        setTextBySelectorNoEnter(Fname, nameField);
        setTextBySelectorNoEnter(Flastname, lastNameField);
        setTextBySelectorNoEnter(Fpostal, postalCodeField);
    }
    public void clickContinue(){
        clickBySelector(continueCheckoutButton);
    }

    public String checkoutUrlValid(){
      String checkoutUrl= returnUrl();

      return checkoutUrl;
    }



}
