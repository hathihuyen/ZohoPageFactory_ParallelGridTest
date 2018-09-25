package com.hha.zoho.rough;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    public WebDriver driver;
    public static ThreadLocal<WebDriver> dr = new ThreadLocal<WebDriver>();

    public WebDriver getDriver(){
        return dr.get();
    }

    public void setWebDriver(WebDriver driver){
        dr.set(driver);
    }

    public void openBrowser(String browser) {
        if (browser.equals("chrome")) {
            System.out.println("Launching: " + browser);
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//src//test//resources//executables//chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browser.equals("firefox")){
            System.out.println("Launching: " + browser);
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "//src//test//resources//executables//geckodriver.exe");
            driver = new FirefoxDriver();
        }

        setWebDriver(driver);
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        getDriver().get("https://www.zoho.com/");
    }

    public void quit(){
        getDriver().quit();
    }
}
