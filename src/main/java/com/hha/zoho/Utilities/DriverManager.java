package com.hha.zoho.Utilities;

import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverManager {

    //public static ThreadLocal<RemoteWebDriver> dr = new ThreadLocal<RemoteWebDriver>(); //Parallell + Grid
    public static ThreadLocal<WebDriver> dr = new ThreadLocal<WebDriver>();

    public static WebDriver getDriver(){
        return dr.get();
    }

    public static void setWebDriver(WebDriver driver){ //before is RemoteWebDriver
        dr.set(driver);
    }

}
