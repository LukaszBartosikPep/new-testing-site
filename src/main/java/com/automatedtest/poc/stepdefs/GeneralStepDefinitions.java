package com.automatedtest.poc.stepdefs;

import com.automatedtest.poc.EnvironmentEnums;
import com.automatedtest.poc.model.pages.*;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

//TODO Create appropiate step definitions classes when implementing the test cases for SauceDemo
public class GeneralStepDefinitions {

    @Autowired
    GeneralPage generalPage;

    @Autowired
    LoginPage loginPage;
    @Autowired
    CartPage cartPage;
    @Autowired
    CheckoutPage checkoutPage;
    @Autowired
    GiveShipmentInfo giveInfo;
    @Autowired
    Finalization finalization;



    //TODO here you write your step definitions and use them in the Cucumber features files
    @Given("SauceDemo is opened")
    public void openSaucePage() {
        generalPage.openPage("https://www.saucedemo.com/");
    }

    @Then ("I login as standard user")
    public void loginStandardUser(){
    loginPage.logIn(EnvironmentEnums.StandardUser.getAddress(),EnvironmentEnums.Password.getAddress());
    String inventoryURL=generalPage.returnUrl();
    assertEquals("Login form issue",EnvironmentEnums.HomePageUrl.getAddress() , inventoryURL);



    }
    @Then ("I login as problem user")
    public void loginProblemUser(){
        loginPage.logIn(EnvironmentEnums.ProblemUserLogin.getAddress(),EnvironmentEnums.Password.getAddress());
        String inventoryURL=generalPage.returnUrl();
        assertEquals("Login form issue",EnvironmentEnums.HomePageUrl.getAddress() , inventoryURL);



    }
    @Then ("I login as performance glitch user")
    public void loginPerformanceGlitchUser(){
        loginPage.logIn(EnvironmentEnums.PerformanceGlitchUser.getAddress(),EnvironmentEnums.Password.getAddress());
        String inventoryURL=generalPage.returnUrl();
        assertEquals("Login form issue",EnvironmentEnums.HomePageUrl.getAddress() , inventoryURL);



    }
    @Then("I add product to cart")
    public void addProductToCart() {
        cartPage.clickAddProduct();
        cartPage.clickCartButton();
        String badgeIndex=cartPage.checkCartBadge();
        assertEquals("Product not in cart","1", badgeIndex);



    }
    @Then("I do checkout")
    public void checkout() {

        String itemInCart=checkoutPage.checkIfCorrectItemInCart();
        String correctItemName=EnvironmentEnums.Backpack.getAddress();
        assertEquals("Incorrect item in cart",correctItemName, itemInCart);
        checkoutPage.clickCheckout();
        String currentUrl= checkoutPage.returnUrl();
        assertEquals("Checkout is not proceeding",EnvironmentEnums.CheckoutStepOneUrl.getAddress(), currentUrl );

    }

    @Then("I fill info")
    public void fillInfo(){
        giveInfo.fillInformations(EnvironmentEnums.Name.getAddress(), EnvironmentEnums.LastName.getAddress(), EnvironmentEnums.Postal.getAddress());
        giveInfo.clickContinue();
        String checkoutUrlValid=giveInfo.checkoutUrlValid();
        assertEquals("Shipping data form invalid",EnvironmentEnums.CheckoutStepTwoUrl.getAddress(), checkoutUrlValid);


    }
    @Then("I return to home page")
    public void returnToHomePage(){

        finalization.finished();


    }
    @Then("I check if homepage valid")
    public void homePageReturnValid(){
        String inventoryURL=generalPage.returnUrl();
        assertEquals("Redirection not valid",EnvironmentEnums.HomePageUrl.getAddress(), inventoryURL);



    }
}