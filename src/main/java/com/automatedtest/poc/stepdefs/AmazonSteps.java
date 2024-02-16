package com.automatedtest.poc.stepdefs;

import com.automatedtest.poc.EnvironmentEnums;
import com.automatedtest.poc.model.pages.*;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import static org.junit.Assert.assertEquals;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashMap;

//TODO Create appropiate step definitions classes when implementing the test cases for SauceDemo
public class GeneralStepDefinitions {

    @Autowired
    GeneralPage generalPage;
//
    @Autowired
    BasicPage basicPage;
    @Autowired
    Product product;
    @Autowired
    Reviews reviews;
    @Autowired
    Table table;
//    @Autowired
//    Finalization finalization;



    //TODO here you write your step definitions and use them in the Cucumber features files
    @Given("Amazon is opened")
    public void openSaucePage() {
        generalPage.openPage("https://www.amazon.pl/");
        basicPage.clickAccept();
        product.searchForProduct();





    }
    @When("I check reviews number")
    public void checkNumberOfReviews(){
//        reviews.openReviewsLink();
//        String reviewsCount=reviews.giveReviewsNumber();
//        assertEquals("1 686", reviewsCount);


    }
    @When("I check if table valid")
    public void tableValid(){
//       LinkedHashMap<String, String> testHashMap= table.HashTable();
//       assertEquals(EnvironmentEnums.Baterie.getAddress(), testHashMap.get("Baterie:"));
//       assertEquals(EnvironmentEnums.Jezyk.getAddress(), testHashMap.get("Język:"));
//       assertEquals(EnvironmentEnums.LiczbaSztuk.getAddress(),testHashMap.get("Liczba sztuk"));





    }

    @When("I check percentage of stars")
    public void starsPercente() {

        boolean comparision=reviews.comparePercentAndNumberOfStars();

        assertEquals(comparision,true);



    }

    @When("I check how many comments")
    public void howManyComments() throws InterruptedException{

        reviews.howManyReviewsWithComment();



    }
}