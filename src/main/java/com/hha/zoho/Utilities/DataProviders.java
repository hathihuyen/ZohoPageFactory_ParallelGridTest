package com.hha.zoho.Utilities;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="masterDP",parallel=true)
	public static Object[][] getDataSuite1(Method m) {
		ExcelReader excel = new ExcelReader(Constants.SUITE1_XL_PATH);
		String testcase = m.getName();
		System.out.println(testcase);
		return DataUtil.getData(testcase, excel);
	}
}
