package com.hha.zoho.PageObjects;

import com.hha.zoho.Utilities.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage<T> {
    protected WebDriver driver;
    protected abstract ExpectedCondition getPageLoadCondition();

    public BasePage() {
        this.driver = DriverManager.getDriver();
    }

    private void waitForPageToLoad(ExpectedCondition pageLoadCondition) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(pageLoadCondition);
    }

    public T openPage(Class<T> clazz){
        T page = null;
        driver = DriverManager.getDriver();
        AjaxElementLocatorFactory ajEleLocFac = new AjaxElementLocatorFactory(driver, 10);

        /*
         * 1st: Init Page Factory elements
         * 2nd: generate Page Load Condition
         */
        page = PageFactory.initElements(driver, clazz);
        PageFactory.initElements(ajEleLocFac, page);
        ExpectedCondition pageLoadCondition = ((BasePage)page).getPageLoadCondition();
        waitForPageToLoad(pageLoadCondition);
        return page;
    }
}
