package com.hha.zoho.rough;

import com.hha.zoho.PageObjects.ZohoHomePage;
import com.hha.zoho.PageObjects.ZohoLoginPage;
import com.hha.zoho.Utilities.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

public class TestCase2 extends BaseTest {

    //@Test(dataProvider = "getData")
    @Test(dataProviderClass = DataProviders.class, dataProvider = "masterDP")
    public void doLogin(Hashtable<String, String> data){
        ExcelReader excel = new ExcelReader(Constants.SUITE1_XL_PATH);
        DataUtil.checkExecution("master", "LoginTest", data.get("Runmode"), excel);

        openBrowser(data.get("browser"));
        // ZohoHomePage homePage = new ZohoHomePage(DriverManager.getDriver()); //Before having BasePage.java
        ZohoHomePage homePage = new ZohoHomePage().open("https://www.zoho.com/");
        ZohoLoginPage loginPage = homePage.gotoLoginPage();
        loginPage.doLogin(data.get("username"), data.get("password"));

        quit();
    }
/*
    @DataProvider(parallel = true)
    public Object[][] getData(){
        Object[][] data = new Object[1][3];
        data[0][0] = "corporate@way2automation.com";
        data[0][1] = "Selenium@1234";
        data[0][2] = "chrome";

        return data;
    }
*/
}
