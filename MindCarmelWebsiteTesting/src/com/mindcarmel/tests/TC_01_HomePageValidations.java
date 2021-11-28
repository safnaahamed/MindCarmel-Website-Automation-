package com.mindcarmel.tests;

import org.testng.annotations.Test;

import com.mindcarmel.pageobject.Homepage;

public class TC_01_HomePageValidations extends BaseTest {
	 
  @Test
  public void f() throws Exception {
	  extentTest=extentreport.createTest("TC_01_HomePageValidations");
	  Homepage ohome = new Homepage(driver);
	  ohome.verifyTheHomeTabs();
	  ohome.verifyTheModulesTabs();
	  ohome.verifyTheLogo();
	  ohome.verifyTheTexts();
	  
  }
  

}
 