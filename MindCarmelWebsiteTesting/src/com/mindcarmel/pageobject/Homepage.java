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
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.asserts.SoftAssert;
import com.aventstack.extentreports.Status;
import com.mindcarmel.tests.BaseTest;
import com.utils.MindCarmelExcelUtils;


public class Homepage extends BaseTest  {
	

	By HomeTab = By.xpath("//li/a[text()='Home']");
	By AboutTab = By.xpath("//a[text()='About']");
	By InstitutesTab = By.xpath("//a[text()='Institutes']");
	By BlogsTab = By.xpath("//a[text()='Blogs']");
	By ContactTab = By.xpath("//a[text()='Contact']");

	public Homepage(WebDriver driver) throws Exception {
		BaseTest.driver = driver;
		BaseTest.wait = new WebDriverWait(driver, 45);
		BaseTest.fwait = new FluentWait<WebDriver>(driver).pollingEvery(Duration.ofSeconds(3))
				.ignoring(NoSuchElementException.class).ignoring(ElementClickInterceptedException.class)
				.withTimeout(Duration.ofSeconds(30));
		BaseTest.overify = new SoftAssert();
		BaseTest.mcutils = new MindCarmelExcelUtils(System.getProperty("user.dir")+ "\\testdata\\Sheet1.xlsx", "MindCarmelTesting");
		 
 
	} 
	public void verifyTheHomeTabs() {
		try {
			ArrayList<WebElement> header = new ArrayList<>(
					driver.findElements(By.xpath("//div[@id='header_menu']/following-sibling::ul/li/a")));
			captureScreenshot();
			for (i = 1; i < header.size(); i++)
				if (header.get(i).isDisplayed()) {
					header.get(i).click();
					header = new ArrayList<>(
							driver.findElements(By.xpath("//div[@id='header_menu']/following-sibling::ul/li/a")));
				}
			driver.findElement(By.xpath("//div[@id='header_menu']/following-sibling::ul/li/a[1]")).click();
			
			extentTest.pass("Home tabs have been verified successfully");
		} catch (Exception e) {
			extentTest.fail("Could not verify the home tabs");
			e.printStackTrace();
		}
	}

	public void verifyTheModulesTabs() {
		try {
			ArrayList<WebElement> modules = new ArrayList<>(driver.findElements(
					By.xpath("//div[contains(@class,'_quick_links_section')]//a/span[@class='loc_open']")));
			
			for (i = 0; i < modules.size(); i++)
				if (modules.get(i).isDisplayed()) {
					modules.get(i).click();
					driver.navigate().back();
					modules = new ArrayList<>(driver.findElements(
							By.xpath("//div[contains(@class,'_quick_links_section')]//a/span[@class='loc_open']")));
				}
	
			extentTest.pass("All the module tabs are working successfully");
		} catch (Exception e) {
			extentTest.fail("Could not verify the module tabs successfully");
			e.printStackTrace();
		}
	}

	public void verifyTheLogo() throws Exception {
		try {
			

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,-250)");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a/img[@class='logo_normal']")))
					.click();
			Screen oscreen = new Screen();
			Pattern ologo = new Pattern(System.getProperty("user.dir")+"\\snips\\photo.png");
			oscreen.click(ologo);
			extentTest.pass("Logo was clicked successfully");
			
		} catch (Exception e) {
			extentTest.fail("Could not click on the MindCarmel Logo");
			e.printStackTrace();
		}
	}

	public void verifyTheTexts() {
		try {
			String text1 = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='hero-text']/h1")))
					.getText();
			overify.assertEquals(driver.findElement(By.xpath("//div[@class='hero-text']/h1")).getText(), mcutils.getCellData(3, "Verify Texts").trim());
			extentTest.generateLog(Status.INFO, text1);

		} catch (Exception e) {
			extentTest.fail("Could not verify the text");
			e.printStackTrace();
		}
	}

	public void clickOnPsychologistModule() {
		try {
			driver.findElement(
					By.xpath("(//div[contains(@class,'_quick_links_section')]//a/span[@class='loc_open'])[1]")).click();
			extentTest.pass("Successfully clicked on the psychologist Module");
			Psychologistspage opage = new Psychologistspage(driver);
			opage.clickOnPsychologistBookBtn();
			extentTest.pass("Successfully clicked on the Psychologists Module");
		} catch (Exception e) {
			extentTest.fail("Could not click on the Psychologist Module");
			e.printStackTrace();
		}

	}

	public void clickOnRegisterProgramsModule() {
		try {

			driver.findElement(
					By.xpath("(//div[contains(@class,'_quick_links_section')]//a/span[@class='loc_open'])[2]")).click();
			extentTest.pass("Successfully clicked on the Register Programs Module");
			RegisterProgramsPage oprograms = new RegisterProgramsPage(driver);
			oprograms.clickOnPrograms();
			extentTest.pass("Successfully clicked on the Register Programs Module");
		} catch (Exception e) {
			extentTest.fail("Could not click on the Register Programs Module");
			e.printStackTrace();
		}
	}

	public void clickOnRegisterCourses() {
		try {
			driver.findElement(By.xpath("(//div[contains(@class,'_quick_links_section')]//a/span[@class='loc_open'])[3]"))
			.click();
			
		RegisterCoursesPage ocourses = new RegisterCoursesPage(driver);
		ocourses.clickOnCourses();
		extentTest.pass("Successfully clicked on the Register Courses Module");	
		}catch (Exception e) {
			extentTest.fail("Failed to click on the Register Courses Module");
		}
		
	}

	public void clickOnAbouttab() {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(AboutTab)).click();
			extentTest.pass("Successfully clicked on the About Tab");
		}catch (Exception e) {
			extentTest.fail("Failed to click on the About Tab");
			e.printStackTrace();
		}
	}

	public void clickOnInstitutesTab() {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(InstitutesTab)).click();
			extentTest.pass("Successfully clicked on the Institutes Tab");
		}catch (Exception e) {
			extentTest.fail("Failed to click on the Institutes Tab");
			e.printStackTrace();
		}
		
	}

	public void clickOnBlogsTab() {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(BlogsTab)).click();
			extentTest.pass("Successfully clicked on the Blogs Tab");	
		}catch (Exception e) {
			extentTest.fail("Failed to click on the Blogs Tab");
			e.printStackTrace();
		}
		
	}

	public void clickOnContactTab() {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(ContactTab)).click();
			extentTest.pass("Successfully clicked on the Contact Tab");
		}catch (Exception e) {
			extentTest.fail("Failed to click on the Contact Tab");
			e.printStackTrace();
		}
		
	}
}
