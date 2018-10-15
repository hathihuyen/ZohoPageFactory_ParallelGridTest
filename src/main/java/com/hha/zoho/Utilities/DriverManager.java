package com.hha.zoho.Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverManager {

    public static ThreadLocal<RemoteWebDriver> dr = new ThreadLocal<RemoteWebDriver>(); //Parallell + Grid

    public static WebDriver getDriver(){
        return dr.get();
    }

    public static void setWebDriver(RemoteWebDriver driver){
        dr.set(driver);
    }

}
