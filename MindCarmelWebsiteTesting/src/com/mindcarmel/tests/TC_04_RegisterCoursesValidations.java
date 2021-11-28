package com.mindcarmel.tests;

import org.testng.annotations.Test;

import com.mindcarmel.pageobject.Homepage;

public class TC_04_RegisterCoursesValidations extends BaseTest {
  @Test
  public void f() throws Exception {
	  extentTest=extentreport.createTest("TC_04_RegisterCoursesValidations");
	  Homepage ohome = new Homepage(driver);
	  ohome.clickOnRegisterCourses();
  }
}
 