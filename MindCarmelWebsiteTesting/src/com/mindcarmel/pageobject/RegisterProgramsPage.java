package com.mindcarmel.pageobject;

import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import com.aventstack.extentreports.Status;
import com.mindcarmel.tests.BaseTest;
import com.utils.MindCarmelExcelUtils;

public class RegisterProgramsPage extends BaseTest {
	By HomeTab = By.xpath("//li/a[text()='Home']");
	
	
	public RegisterProgramsPage(WebDriver driver) throws Exception {
		BaseTest.driver = driver;
		BaseTest.wait = new WebDriverWait(driver, 45);
		BaseTest.fwait = new FluentWait<WebDriver>(driver).pollingEvery(Duration.ofSeconds(3))
				.ignoring(NoSuchElementException.class).ignoring(ElementClickInterceptedException.class)
				.withTimeout(Duration.ofSeconds(30));
		BaseTest.overify = new SoftAssert();
		BaseTest.mcutils = new MindCarmelExcelUtils(System.getProperty("user.dir")+ "\\testdata\\Sheet1.xlsx", "MindCarmelTesting");
 
	}
	 
	public void clickOnPrograms() {
		try {	
			extentTest.addScreenCaptureFromPath(screenshotpath);
			Assert.assertEquals(driver.findElement(By.xpath("//div[contains(@class,'main_title')]/p")).getText(), mcutils.getCellData(1, "Verify Texts").trim());
			extentTest.generateLog(Status.INFO, "Our Programs paragraph detail is:: "+mcutils.getCellData(1, "Verify Texts"));
			
			ArrayList<WebElement>Programs = new ArrayList<>(driver.findElements(By.xpath("//div[@id='program-paginate']//h2")));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,200)");
			
		
			for(i=0; i<Programs.size();i++) {
				 
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='View More'])["+(i+1)+"]"))).click();
				js.executeScript("window.scrollBy(0,200)");
				
				
				String programcontent=fwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='title']//p/following-sibling::p"))).getText();
				extentTest.generateLog(Status.INFO, "The content of the program to register is:: "+programcontent);
				System.out.println(programcontent);
				String eventInfoDate= fwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@class='icon_calendar']/parent::p"))).getText();
				System.out.println(eventInfoDate);
				overify.assertEquals(driver.findElement(By.xpath("(//h5/parent::div//p)[1]")).getText(), mcutils.getCellData(i+1, "Event Info Date").trim());
				extentTest.generateLog(Status.INFO, "The date of the registered program is:: "+eventInfoDate);
				String eventInfoTime= fwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@class='icon_clock_alt']/parent::p"))).getText();
				System.out.println(eventInfoTime);
				overify.assertEquals(driver.findElement(By.xpath("(//h5/parent::div//p)[2]")).getText(), mcutils.getCellData(i+1, "Event Info Date").trim());
				extentTest.generateLog(Status.INFO, "The time to of the event is:: "+eventInfoTime);
				
				ArrayList<WebElement>contactInfo = new ArrayList<>(driver.findElements(By.xpath("((//div[@class='row'])[3]//p)")));
			
				for(j=0;j<contactInfo.size(); j++) {
					String text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("((//div[@class='row'])[3]//p)["+(j+1)+"]"))).getText();
					System.out.println(text);
					extentTest.generateLog(Status.INFO, "The contact information is:: "+text);
					overify.assertEquals(driver.findElement(By.xpath("((//div[@class='row'])[3]//p)["+(j+1)+"]")).getText().trim(), mcutils.getCellData(j+1, "Event Address"));	
				
				}
				driver.navigate().back();			
			}
			wait.until(ExpectedConditions.visibilityOfElementLocated(HomeTab)).click();
			extentTest.pass("Successfully validated all the programs but the program may fail due to the assertion failure");
		}catch (Exception e) {
			extentTest.fail("Failed to provide the necessary information of the programs to be registered");
			e.printStackTrace();
		}
		
	}
	
}
