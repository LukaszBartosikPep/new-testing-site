package com.automatedtest.poc.model.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

@Component
public class CartPage extends GeneralPage{



    private static final By addToCartButton = By.id("add-to-cart-sauce-labs-backpack");

    private static final By cartIcon =By.className("shopping_cart_link");

    private static final By cartBadgeIndex =By.xpath("//div[@id='shopping_cart_container']/a/span");

    public void clickAddProduct() {
        clickBySelector(addToCartButton);


    }
    public void clickCartButton(){
        clickBySelector(cartIcon);
    }
    public String checkCartBadge(){
       WebElement badge= findElementBySelector(cartBadgeIndex);
       String badgeIndex=badge.getText();
       return badgeIndex;

    }


}
