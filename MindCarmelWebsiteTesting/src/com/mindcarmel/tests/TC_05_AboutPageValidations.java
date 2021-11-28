 package com.mindcarmel.tests;

import org.testng.annotations.Test;

import com.mindcarmel.pageobject.AboutPageContentInfo;
import com.mindcarmel.pageobject.Homepage;

public class TC_05_AboutPageValidations extends BaseTest {
  @Test
  public void f() throws Exception {
	  extentTest=extentreport.createTest("TC_05_AboutPageValidations");
	  Homepage ohome = new Homepage(driver);
	  ohome.clickOnAbouttab();
	  AboutPageContentInfo oabout = new AboutPageContentInfo(driver);
	  oabout.verifyTheContents();
  }
}
