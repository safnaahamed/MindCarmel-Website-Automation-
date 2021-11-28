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

public class Psychologistspage extends BaseTest {

	By psychnametab = By.xpath("//p[@class='psychologist-name']");
	By bookBtn = By.xpath("//a[text()='Book Now']");
	By onlineBtn = By.xpath("//label[text()='Online']");
	By activedate = By.xpath("//td[@data-event='click']/a");
	By timeslot = By.xpath("//label[@for='time_0']");
	By HomeTab = By.xpath("//li/a[text()='Home']");
	By BookSlotText = By.xpath("//h3[text()='Book a Slot']");
	By psychologisttab = By.xpath("//li/a[text()='Psychologists']");

	public Psychologistspage(WebDriver driver) throws Exception {
		BaseTest.driver = driver;
		BaseTest.wait = new WebDriverWait(driver, 45);
		BaseTest.fwait = new FluentWait<WebDriver>(driver).pollingEvery(Duration.ofSeconds(3))
				.ignoring(NoSuchElementException.class).ignoring(ElementClickInterceptedException.class)
				.withTimeout(Duration.ofSeconds(30));
		BaseTest.overify = new SoftAssert();
		BaseTest.mcutils = new MindCarmelExcelUtils(System.getProperty("user.dir") + "\\testdata\\Sheet1.xlsx",
				"MindCarmelTesting");

	}

	public void clickOnPsychologistBookBtn() {
		try {
			Assert.assertEquals(driver.findElement(By.xpath("//div[contains(@class,'main_title')]/p")).getText().trim(),
					mcutils.getCellData(2, "Verify Texts"));
			extentTest.generateLog(Status.INFO,
					"Our Psychologists paragraph verified as:: " + mcutils.getCellData(2, "Verify Texts"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,500)");
			ArrayList<WebElement> psychologist = new ArrayList<>(fwait.until(
					ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='post_info']/p/a"))));
			
			for (j = 0; j < psychologist.size(); j++) {
				String psychologistname = mcutils.getCellData(j+1, "Psychologist Name");
				String text = psychologist.get(j).getText();
				System.out.println(text);
				overify.assertEquals(driver.findElement(By.xpath("(//p[@class='psychologist-name'])[" + (j+1) + "]"))
						.getText().trim(), psychologistname);
				extentTest.generateLog(Status.INFO, "The name of the psychologist is " + text);
				if (psychologist.get(j).isDisplayed()) {
					ArrayList<WebElement> bookBtn = new ArrayList<>(wait.until(
							ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("(//a[text()='Book Now'])"))));
					try {
						if (bookBtn.get(j).isDisplayed()) {
							bookBtn.get(j).click();
							try {
								if (driver.findElement(BookSlotText).isDisplayed()) {
								String textaboutdoctor = mcutils.getCellData(j + 4, "Verify Texts");
								overify.assertEquals(
										driver.findElement(By.xpath("(//div[@class='row']//p)[1]")).getText(),
										textaboutdoctor.trim());
								SelectPsychologistsPage oselect = new SelectPsychologistsPage(driver);
								oselect.clickOnBookSlot();
								PaymentPage opay = new PaymentPage(driver);								
								opay.personalInfo();
								opay.clickOnPayment();
								fwait.until(ExpectedConditions.visibilityOfElementLocated(psychologisttab)).click();
								}	
								extentTest.pass("Selected the Psychologist and payment completed successfully");
								} catch (Exception e) {
									wait.until(ExpectedConditions
									.visibilityOfElementLocated(By.xpath("//h2[text()='Our Psychologists']")))
									.isDisplayed();
									wait.until(ExpectedConditions.visibilityOfElementLocated(HomeTab)).click();
									break;
							}
					}
					js.executeScript("window.scrollBy(0,200)");
					}catch (Exception e) {

					}
				psychologist = new ArrayList<>(fwait.until(ExpectedConditions
						.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='post_info']/p/a"))));
					}
				}

			}catch (Exception e) {
				extentTest.fail("Failed to select and verify the Psychologist");
				e.printStackTrace();
			}
		
	}
}