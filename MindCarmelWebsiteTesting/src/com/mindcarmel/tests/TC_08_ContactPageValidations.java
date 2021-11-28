package com.mindcarmel.tests;

import org.testng.annotations.Test;

import com.mindcarmel.pageobject.ContactsPage;
import com.mindcarmel.pageobject.Homepage;

public class TC_08_ContactPageValidations extends BaseTest {
  @Test
  public void f() throws Exception {
	  extentTest=extentreport.createTest("TC_08_ContactPageValidations");
	  Homepage ohome = new Homepage(driver);
	  ohome.clickOnContactTab();
	  ContactsPage opage = new ContactsPage(driver);
	  opage.verifyTheContacts();
	  opage.verifyContactUsDetails();
  }
}
 