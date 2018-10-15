package com.hha.zoho.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ZohoLoginPage extends BasePage {
/*  //before having BasePage
    public WebDriver driver;

    public ZohoLoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
*/
    @FindBy(xpath = "//*[@id='lid']")
    public WebElement user;

    @FindBy(xpath = "//*[@id='pwd']")
    public WebElement pass;

    @FindBy(xpath = "//*[@id='signin_submit']")
    public WebElement signIn;

    public ZohoLoginPage doLogin(String username, String password) {
        type(user, username, "User Name textbox");
        type(pass, password, "Password textbox");
        click(signIn, "Signin button");
        return this;
    }

    @Override
    protected ExpectedCondition getPageLoadCondition() {
        return ExpectedConditions.visibilityOf(signIn);
    }
}
