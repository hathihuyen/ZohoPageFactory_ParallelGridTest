package com.hha.zoho.rough;

import com.hha.zoho.PageObjects.ZohoHomePage;
import com.hha.zoho.PageObjects.ZohoLoginPage;
import com.hha.zoho.Utilities.*;
import org.apache.poi.ss.formula.functions.T;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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

        openBrowser(data.get("browser"));
        ZohoHomePage homePage = new ZohoHomePage(DriverManager.getDriver());
        ZohoLoginPage loginPage = homePage.gotoLoginPage();
        loginPage.doLogin(data.get("username"), data.get("password"));

        //getDriver().findElement(By.xpath("/html/body/div[1]/div[2]/div/a[3]")).click();
        //getDriver().findElement(By.xpath("//*[@id='lid']")).sendKeys(username);
        //getDriver().findElement(By.xpath("//*[@id='pwd']")).sendKeys(password);
        //getDriver().findElement(By.xpath("//*[@id='signin_submit']")).click();

        quit();
    }
/*
    @DataProvider(parallel = true)
    public Object[][] getData(){
        Object[][] data = new Object[2][3];
        data[0][0] = "trainer@way2automation.com";
        data[0][1] = "Selenium@1234";
        data[0][2] = "chrome";

        data[1][0] = "java@way2automation.com";
        data[1][1] = "Selenium@1234";
        data[1][2] = "firefox";

        return data;
    }
*/
}
