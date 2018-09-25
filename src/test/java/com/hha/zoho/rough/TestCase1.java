package com.hha.zoho.rough;

import org.apache.poi.ss.formula.functions.T;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestCase1 {

    public WebDriver driver;
    public static ThreadLocal<WebDriver> dr = new ThreadLocal<WebDriver>();

    public WebDriver getDriver(){
        return dr.get();
    }

    public void setWebDriver(WebDriver driver){
        dr.set(driver);
    }

    @Test(dataProvider = "getData")
    public void doLogin(String username, String password, String browser){

        if (browser.equals("chrome")) {
            System.out.println("Launching from TC1 : " + browser);
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//src//test//resources//executables//chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browser.equals("firefox")){
            System.out.println("Launching from TC1 : " + browser);
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "//src//test//resources//executables//geckodriver.exe");
            driver = new FirefoxDriver();
        }

        setWebDriver(driver);
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        getDriver().get("https://www.zoho.com/");

        getDriver().findElement(By.xpath("/html/body/div[1]/div[2]/div/a[3]")).click();
        getDriver().findElement(By.xpath("//*[@id='lid']")).sendKeys(username);
        getDriver().findElement(By.xpath("//*[@id='pwd']")).sendKeys(password);
        getDriver().findElement(By.xpath("//*[@id='signin_submit']")).click();

        getDriver().quit();
    }

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

}
