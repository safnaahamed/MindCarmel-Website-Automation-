package com.mindcarmel.tests;

import org.testng.annotations.Test;

import com.mindcarmel.pageobject.BlogsPage;
import com.mindcarmel.pageobject.Homepage;

public class TC_07_BlogsPageValidations extends BaseTest{
  @Test
  public void f() throws Exception {
	  extentTest=extentreport.createTest("TC_07_BlogsPageValidations");
	  Homepage ohome = new Homepage(driver);
	  ohome.clickOnBlogsTab();
	  BlogsPage oblog = new BlogsPage(driver);
	  oblog.verifyTheBlogPage();
	  oblog.verifyThePosts();
  }
}
 