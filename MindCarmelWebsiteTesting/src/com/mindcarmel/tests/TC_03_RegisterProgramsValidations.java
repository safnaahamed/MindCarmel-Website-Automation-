package com.mindcarmel.tests;

import org.testng.annotations.Test;

import com.mindcarmel.pageobject.Homepage;

public class TC_03_RegisterProgramsValidations extends BaseTest{
  @Test
  public void f() throws Exception {
	  extentTest=extentreport.createTest("TC_03_RegisterProgramsValidations");
	  Homepage ohome = new Homepage(driver);
	  ohome.clickOnRegisterProgramsModule();
  }
}
 