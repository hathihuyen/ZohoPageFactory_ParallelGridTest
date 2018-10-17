package com.hha.zoho.testcases;

import com.hha.zoho.ExtentListeners.ExtentListeners;
import com.hha.zoho.Utilities.DriverFactory;
import com.hha.zoho.Utilities.DriverManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    //public static ThreadLocal<WebDriver> dr = new ThreadLocal<WebDriver>(); --> Parallel
    //public RemoteWebDriver driver; //change to WebDriver after create DriverFactory
    public WebDriver driver;
    private Properties config = new Properties();
    private FileInputStream fis;
    public Logger log = Logger.getLogger(BaseTest.class);

    private String defaultUserName;
    private String defaultPassword;

    public boolean grid = false; // parameterize for Jenkin build with grid

    public String getDefaultUserName() {
        return defaultUserName;
    }

    public void setDefaultUserName(String defaultUserName) {
        this.defaultUserName = defaultUserName;
    }

    public String getDefaultPassword() {
        return defaultPassword;
    }

    public void setDefaultPassword(String defaultPassword) {
        this.defaultPassword = defaultPassword;
    }

    @BeforeSuite
    public void setUpFramework() {
        configureLogging();
        DriverFactory.setGridPath("http://localhost:4444/wd/hub");
        DriverFactory.setConfigPropertyFile(System.getProperty("user.dir") + "//src//test//resources//properties//Config.properties");

        if (System.getProperty("os.name").contains("mac")){
            log.info("Running tests on Mac !!!");
            DriverFactory.setGeckoDriverExePath(System.getProperty("user.dir") + "//src//test//resources//executables//geckodriver");
            DriverFactory.setChromeDriverExePath(System.getProperty("user.dir") + "//src//test//resources//executables//chromedriver");
        } else {
            log.info("Running tests on " + System.getProperty("os.name") + " !!!");
            DriverFactory.setGeckoDriverExePath(System.getProperty("user.dir") + "//src//test//resources//executables//geckodriver.exe");
            DriverFactory.setChromeDriverExePath(System.getProperty("user.dir") + "//src//test//resources//executables//chromedriver.exe");
            DriverFactory.setIeDriverExePath(System.getProperty("user.dir") + "//src//test//resources//executables//IEDriverServer.exe");
        }

        try {
            fis = new FileInputStream(DriverFactory.getConfigPropertyFile());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            config.load(fis);
            log.info("Configuration file loaded !!!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is for ExtentReport
     * @param message that we want to add to the extent report
     */
    public void logInfo(String message){
        ExtentListeners.testReport.get().info(message);
    }

    public void configureLogging(){
        String log4jConfigFile = System.getProperty("user.dir") + "//src//test//resources//properties//log4j.properties";
        PropertyConfigurator.configure(log4jConfigFile);
    }
/*
    // Already move to DriverManager.java in src/main/java/com/hha/zoho/Utilities
    public static ThreadLocal<RemoteWebDriver> dr = new ThreadLocal<RemoteWebDriver>(); //Parallell + Grid

    public WebDriver getDriver(){
        return dr.get();
    }

    public void setWebDriver(RemoteWebDriver driver){
        dr.set(driver);
    }
*/
    public void openBrowser(String browser) {
        if ((System.getenv("ExecutionType") != null) && System.getenv("ExecutionType").equals("Grid"))
            grid = true;

        DriverFactory.setRemote(grid);
        System.out.println("Remote Execution type is: " + DriverFactory.isRemote());
        if (DriverFactory.isRemote()) {
            //These are for RemoteWebDriver
            DesiredCapabilities cap = null;
            if (browser.equals("chrome")) {
                cap = DesiredCapabilities.chrome();
                cap.setBrowserName("chrome");
                cap.setPlatform(Platform.ANY);
                log.info("Chrome browser lauched on Grid!!!");
            } else if (browser.equals("firefox")){
                cap = DesiredCapabilities.firefox();
                cap.setBrowserName("firefox");
                cap.setPlatform(Platform.ANY);
                log.info("FireFox browser lauched on Grid!!!");
            } else if (browser.equals("ie")){
                cap = DesiredCapabilities.internetExplorer();
                cap.setBrowserName("iexplore");
                cap.setPlatform(Platform.WIN10);
                log.info("IE browser lauched on Grid!!!");
            }

            try {
                driver = new RemoteWebDriver(new URL(DriverFactory.getGridPath()), cap);
                log.info("Starting the session on Grid !!!");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        } else {
            if (browser.equals("chrome")) {
                System.out.println("Launching: " + browser);
                System.setProperty("webdriver.chrome.driver", DriverFactory.getChromeDriverExePath());
                driver = new ChromeDriver();
                log.info("Chrome browser lauched !!!");
            } else if (browser.equals("firefox")) {
                System.out.println("Launching: " + browser);
                System.setProperty("webdriver.gecko.driver", DriverFactory.getGeckoDriverExePath());
                driver = new FirefoxDriver();
                log.info("FireFox browser lauched !!!");
            }
        }



/*      //before create DriverManager class
        setWebDriver(driver);
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        getDriver().get("https://www.zoho.com/");
*/
        DriverManager.setWebDriver(driver);
        DriverManager.getDriver().manage().window().maximize();
        DriverManager.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //DriverManager.getDriver().get("https://www.zoho.com/"); //no need this one after have BasePage class

        System.out.println("Default Username is: " + config.getProperty("defaultUserName"));
        System.out.println("Default Password is: " + config.getProperty("defaultPassword"));
        setDefaultUserName(config.getProperty("defaultUserName"));
        setDefaultPassword(config.getProperty("defaultPassword"));
    }

    public void quit(){
        DriverManager.getDriver().quit();
    }
}
