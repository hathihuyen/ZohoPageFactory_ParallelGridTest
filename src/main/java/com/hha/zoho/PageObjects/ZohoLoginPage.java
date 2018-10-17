package com.hha.zoho.PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ZohoLoginPage extends BasePage {

    @FindBy(xpath = "//*[@id='lid']")
    public WebElement user;

    @FindBy(xpath = "//*[@id='pwd']")
    public WebElement pass;

    @FindBy(xpath = "//*[@id='signin_submit']")
    public WebElement signIn;

    public ZohoLoginPage doLoginAsInvalidUser(String username, String password) {
        type(user, username, "User Name textbox");
        type(pass, password, "Password textbox");
        click(signIn, "Signin button");
        return this;
    }

    public ZohoAppPage doLoginAsValidUser(String username, String password) {
        type(user, username, "User Name textbox");
        type(pass, password, "Password textbox");
        click(signIn, "Signin button");
        return (ZohoAppPage) openPage(ZohoAppPage.class);
    }

    @Override
    protected ExpectedCondition getPageLoadCondition() {
        return ExpectedConditions.visibilityOf(signIn);
    }
}
