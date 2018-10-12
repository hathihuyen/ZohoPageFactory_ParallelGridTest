package com.hha.zoho.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ZohoLoginPage {
    public WebDriver driver;

    public ZohoLoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id='lid']")
    public WebElement user;

    @FindBy(xpath = "//*[@id='pwd']")
    public WebElement pass;

    @FindBy(xpath = "//*[@id='signin_submit']")
    public WebElement signIn;

    public void doLogin(String username, String password) {
        user.sendKeys(username);
        pass.sendKeys(password);
        signIn.click();
    }
}
