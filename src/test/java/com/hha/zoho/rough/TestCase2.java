package com.hha.zoho.rough;

import com.hha.zoho.PageObjects.ZohoHomePage;
import com.hha.zoho.PageObjects.ZohoLoginPage;
import com.hha.zoho.Utilities.*;
import com.hha.zoho.testcases.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class TestCase2 extends BaseTest {

    @Test(dataProviderClass = DataProviders.class, dataProvider = "masterDP")
    public void doLogin(Hashtable<String, String> data){
        ExcelReader excel = new ExcelReader(Constants.SUITE1_XL_PATH);
        DataUtil.checkExecution("master", "LoginTest", data.get("Runmode"), excel);

        openBrowser(data.get("browser"));
        ZohoHomePage homePage = new ZohoHomePage().open("https://www.zoho.com/");
        ZohoLoginPage loginPage = homePage.gotoLoginPage();
        loginPage.doLoginAsInvalidUser(data.get("username"), data.get("password"));

        quit();
    }
}
