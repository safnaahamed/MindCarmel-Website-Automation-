package com.mindcarmel.tests;

import org.testng.annotations.Test;

import com.mindcarmel.pageobject.Homepage;
import com.mindcarmel.pageobject.InstitutesPage;

public class TC_06_InstitutesPageValidations extends BaseTest{
  @Test
  public void f() throws Exception {
	  extentTest=extentreport.createTest("TC_06_InstitutesPageValidations");
	  Homepage ohome = new Homepage(driver);
	  ohome.clickOnInstitutesTab();
	  InstitutesPage oinstitute = new InstitutesPage(driver);
	  oinstitute.clickOnInstitutes();
  }
}
 