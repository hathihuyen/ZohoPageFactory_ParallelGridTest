package com.hha.zoho.testcases;

import com.hha.zoho.PageObjects.ZohoAppPage;
import com.hha.zoho.PageObjects.ZohoHomePage;
import com.hha.zoho.PageObjects.ZohoLoginPage;
import com.hha.zoho.Utilities.Constants;
import com.hha.zoho.Utilities.DataProviders;
import com.hha.zoho.Utilities.DataUtil;
import com.hha.zoho.Utilities.ExcelReader;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class ValidateCRMTest extends BaseTest {

    @Test(dataProviderClass = DataProviders.class, dataProvider = "masterDP")
    public void validateCRMTest(Hashtable<String, String> data){
        ExcelReader excel = new ExcelReader(Constants.SUITE1_XL_PATH);
        DataUtil.checkExecution("master", "ValidateCRMTest", data.get("Runmode"), excel);
        log.info("Inside Login Test");
        openBrowser(data.get("browser"));
        logInfo("Launched Browser : " + data.get("browser")); //add this information to extent report

        ZohoHomePage homePage = new ZohoHomePage().open("https://www.zoho.com/");
        ZohoLoginPage loginPage = homePage.gotoLoginPage();
        ZohoAppPage appPage = loginPage.doLoginAsValidUser(getDefaultUserName(), getDefaultPassword());
        logInfo("Username entered as: " + getDefaultUserName() + " and Password entered as: " + getDefaultPassword()); //add to extent report
        appPage.gotoCRM();
    }

    @AfterMethod
    public void tearDown(){
        logInfo("LoginTest Completed !!!"); //add to extent report
        quit();
    }
}
