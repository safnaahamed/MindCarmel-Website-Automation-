 package com.mindcarmel.tests;

import org.testng.annotations.Test;

import com.mindcarmel.pageobject.Homepage;

public class TC_02_PsychologistBookingValidations extends BaseTest{
  @Test
  public void f() throws Exception {
	  extentTest=extentreport.createTest("TC_02_PsychologistBookingValidations");
	  Homepage ohome = new Homepage(driver);
	  ohome.clickOnPsychologistModule();
	  
  }
}
