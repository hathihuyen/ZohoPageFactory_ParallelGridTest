package com.hha.zoho.rough;

import com.hha.zoho.PageObjects.ZohoHomePage;
import com.hha.zoho.PageObjects.ZohoLoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestCase2 extends BaseTest {

    @Test(dataProvider = "getData")
    public void doLogin(String username, String password, String browser){
        openBrowser(browser);

        ZohoHomePage homePage = new ZohoHomePage(getDriver());
        ZohoLoginPage loginPage = homePage.gotoLoginPage();
        loginPage.doLogin(username, password);

        quit();
    }

    @DataProvider(parallel = true)
    public Object[][] getData(){
        Object[][] data = new Object[1][3];
        data[0][0] = "corporate@way2automation.com";
        data[0][1] = "Selenium@1234";
        data[0][2] = "chrome";

        return data;
    }

}
