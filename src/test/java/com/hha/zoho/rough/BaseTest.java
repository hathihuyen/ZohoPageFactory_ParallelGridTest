package com.hha.zoho.rough;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    //public WebDriver driver;
    //public static ThreadLocal<WebDriver> dr = new ThreadLocal<WebDriver>(); --> Parallel
    public RemoteWebDriver driver;
    public static ThreadLocal<RemoteWebDriver> dr = new ThreadLocal<RemoteWebDriver>(); //Parallell + Grid

    public WebDriver getDriver(){
        return dr.get();
    }

    public void setWebDriver(RemoteWebDriver driver){
        dr.set(driver);
    }

    public void openBrowser(String browser) {

        /*
        if (browser.equals("chrome")) {
            System.out.println("Launching: " + browser);
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//src//test//resources//executables//chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browser.equals("firefox")){
            System.out.println("Launching: " + browser);
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "//src//test//resources//executables//geckodriver.exe");
            driver = new FirefoxDriver();
        }*/ //these are for WebDriver

        //These are for RemoteWebDriver
        DesiredCapabilities cap = null;
        if (browser.equals("chrome")) {
            cap = DesiredCapabilities.chrome();
            cap.setBrowserName("chrome");
            cap.setPlatform(Platform.ANY);
        } else if (browser.equals("firefox")){
            cap = DesiredCapabilities.firefox();
            cap.setBrowserName("firefox");
            cap.setPlatform(Platform.ANY);
        } else if (browser.equals("ie")){
            cap = DesiredCapabilities.internetExplorer();
            cap.setBrowserName("iexplore");
            cap.setPlatform(Platform.WIN10);
        }

        try {
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
        } catch (MalformedURLException e) {
            e.printStackTrace();
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
