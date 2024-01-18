package com.automatedtest.poc.model.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;


@Component
public class CheckoutPage extends GeneralPage {




    private static final By checkoutButton =By.xpath("//*[text()='Checkout']");
    private static final By itemName=By.xpath("//div[@class='inventory_item_name']");





    public String checkIfCorrectItemInCart(){

        WebElement item=findElementBySelector(itemName);
        String itemText=item.getText();

        return itemText;

    }
    public void clickCheckout(){
        clickBySelector(checkoutButton);
    }

//    public String giveCheckoutUrl(){
//       String url= returnUrl();
//
//       return url;
//
//
//    }
}
