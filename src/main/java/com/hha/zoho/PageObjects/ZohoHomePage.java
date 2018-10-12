package com.hha.zoho.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ZohoHomePage {

    public WebDriver driver;

    public ZohoHomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "/html/body/div[1]/div[3]/div/a[4]")
    public WebElement logIn;

    public ZohoLoginPage gotoLoginPage(){
        logIn.click();
        return new ZohoLoginPage(driver);
    }
}
