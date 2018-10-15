package com.hha.zoho.PageObjects;

import com.hha.zoho.Utilities.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ZohoHomePage extends BasePage {
/*  //Before BasePage class
    public WebDriver driver;

    public ZohoHomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
*/
    @FindBy(xpath = "/html/body/div[1]/div[3]/div/a[4]")
    public WebElement logIn;

    public ZohoHomePage open(String url){
        DriverManager.getDriver().navigate().to(url);
        return (ZohoHomePage) openPage(ZohoHomePage.class);
    }

    public ZohoLoginPage gotoLoginPage(){
        click(logIn, "Login Link");
        //return new ZohoLoginPage(driver); //before having BasePage
        return (ZohoLoginPage) openPage(ZohoLoginPage.class);
    }

    @Override
    protected ExpectedCondition getPageLoadCondition() {
        return ExpectedConditions.visibilityOf(logIn);
    }
}
