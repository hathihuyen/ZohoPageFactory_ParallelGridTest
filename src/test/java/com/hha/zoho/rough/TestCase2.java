package com.hha.zoho.rough;

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

        getDriver().findElement(By.xpath("/html/body/div[1]/div[2]/div/a[3]")).click();
        getDriver().findElement(By.xpath("//*[@id='lid']")).sendKeys(username);
        getDriver().findElement(By.xpath("//*[@id='pwd']")).sendKeys(password);
        getDriver().findElement(By.xpath("//*[@id='signin_submit']")).click();

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
