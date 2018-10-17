package com.hha.zoho.testcases;

import com.hha.zoho.PageObjects.ZohoHomePage;
import com.hha.zoho.PageObjects.ZohoLoginPage;
import com.hha.zoho.Utilities.*;
import org.apache.poi.ss.formula.functions.T;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

//public class TestCase1 extends BaseTest {
public class LoginTest extends BaseTest {

    //@Test(dataProvider = "getData")
    //public void doLogin(String username, String password, String browser){
    @Test(dataProviderClass = DataProviders.class, dataProvider = "masterDP")
    public void loginTest(Hashtable<String, String> data){
        ExcelReader excel = new ExcelReader(Constants.SUITE1_XL_PATH);
        DataUtil.checkExecution("master", "LoginTest", data.get("Runmode"), excel);
        log.info("Inside Login Test");
        openBrowser(data.get("browser"));

        logInfo("Launched Browser : " + data.get("browser")); //add this information to extent report

        // ZohoHomePage homePage = new ZohoHomePage(DriverManager.getDriver()); //Before having BasePage.java
        ZohoHomePage homePage = new ZohoHomePage().open("https://www.zoho.com/");
        ZohoLoginPage loginPage = homePage.gotoLoginPage();
        loginPage.doLoginAsInvalidUser(data.get("username"), data.get("password"));

        logInfo("Username entered as: " + data.get("username") + " and Password entered as: " + data.get("password")); //add to extent report
        quit();
    }
/*
    @AfterMethod
    public void tearDown(){
        logInfo("LoginTest Completed !!!"); //add to extent report
        quit();
    }
*/
}