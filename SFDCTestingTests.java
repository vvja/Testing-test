package com.nari.selenium.unit.test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.AssertJUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.nari.selenium.unit.test.ReusableMethods1;

import io.github.bonigarcia.wdm.WebDriverManager;

//@BeforeClass

public class SFDCTestingTests extends ReusableMethods1 {

	private static final String VALID_SFDC_PASSWORD = "Steps100@";
	private static final String VALID_SFDC_USERNAME = "vvja@gmail.com";

	

	@BeforeClass
	public void setUp() throws Exception {

		WebDriverManager.chromedriver().setup();
		ReusableMethods1.InitializeDriver();
	}

	

	@AfterClass
	public void tearDown() throws Exception {
	}

	private void doLogin(String userName, String userPassword) throws InterruptedException {

		ReusableMethods1.driver.get("https://login.salesforce.com/");

		WebElement username = ReusableMethods1.driver.findElement(By.id("username"));
		username.sendKeys(userName);

		WebElement password = ReusableMethods1.driver.findElement(By.id("password"));
		password.sendKeys(userPassword);

		Thread.sleep(2000);

		WebElement button = ReusableMethods1.driver.findElement(By.id("Login"));
		button.click();
	}

	@Test
	public void testTC2ValidLoginToSalesForce() throws InterruptedException {

		doValidLogin();

		Assert.assertEquals("Failed", ReusableMethods1.driver.getTitle(),
				"Home Page ~ Salesforce - Developer Edition");

	}

	/**
	 * Launch and Log into Sales Force with valid user credentials
	 * 
	 * @throws InterruptedException
	 */
	private void doValidLogin() throws InterruptedException {
		doLogin(VALID_SFDC_USERNAME, VALID_SFDC_PASSWORD);
	}

	private void checkIfLogoutLinkIsPartOfMenuItems() {
		WebElement logOut = ReusableMethods1.driver.findElement(By.xpath("//*[@id=\"userNav-menuItems\"]/a[5]"));

		Assert.assertNotNull(logOut, "Failed");
		logOut.click();

	}

	private void checkForUserMenuDropdown() throws InterruptedException {

		doValidLogin();

		WebElement userNavButton = ReusableMethods1.driver.findElement(By.xpath("//*[@id=\"userNavButton\"]"));

		Assert.assertNotNull(userNavButton, "Failed");

		userNavButton.click();
	}

	@Test
	public void testTC5SelectUserMenu() throws InterruptedException {

		checkForUserMenuDropdown();

		Thread.sleep(1000);

		checkIfLogoutLinkIsPartOfMenuItems();

	}

	@Test
	public void testTC6SelectMyProfileFromUserMenu() throws InterruptedException {
		checkForUserMenuDropdown();

		WebElement myProfile = ReusableMethods1.driver.findElement(By.xpath("//*[@id=\"userNav-menuItems\"]/a[1]"));
		Assert.assertNotNull("Failed");
		myProfile.click();

		Thread.sleep(2000);

		// Edit Profile link
		WebElement editlink = ReusableMethods1.driver
				.findElement(By.xpath("//*[@id=\"chatterTab\"]/div[2]/div[2]/div[1]/h3/div/div/a"));
		ReusableMethods1.Click(editlink, "Editlink");

		ReusableMethods1.SwitchFrame("contactInfoContentId");
		ReusableMethods1.driver.findElement(By.id("aboutTab")).click();

		WebElement lastname = ReusableMethods1.driver.findElement(By.id("lastName"));
		lastname.clear();

		ReusableMethods1.EnterText(lastname, "Bodepu", "lastname");
		System.out.println("LastName is Entered");
		// WebElement
		// bodyElement=ReusableMethods.driver.findElement(By.xpath("//div[contains(@class,'zen-body')]"));
		WebElement bodyElement = ReusableMethods1.driver.findElement(By.xpath("//input[@value='Save All']"));
		ReusableMethods1.Click(bodyElement, "bodyElement");

		// Edit Post link
		Thread.sleep(4000);
		WebElement postlink = ReusableMethods1.driver.findElement(By.id("publisherAttachTextPost"));
		ReusableMethods1.Click(postlink, "postlink");

		WebElement postIframe = ReusableMethods1.driver
				.findElement(By.xpath("//iframe[contains(@title,'Rich Text Editor, publisherRichTextEditor')]"));
		ReusableMethods1.SwitchFrame(postIframe);

		// ReusableMethods.driver.switchTo().frame(postIframe);
		WebElement postBody = ReusableMethods1.driver.findElement(By.xpath("//html[1]/body[1]"));
		ReusableMethods1.Click(postBody, "postBody");
		ReusableMethods1.EnterText(postBody, "Testing Post Text Message", "PostText");

		ReusableMethods1.SwitchFrame();
		WebElement ShareButton = ReusableMethods1.driver.findElement(By.xpath("//input[@id='publishersharebutton']"));
		ReusableMethods1.Click(ShareButton, "ShareButton");

		Thread.sleep(3000);
		WebElement FileLink = ReusableMethods1.driver
				.findElement(By.xpath("//span[contains(@class,'publisherattachtext')][contains(text(),'File')]"));
		ReusableMethods1.Click(FileLink, "FileLink");
		WebElement UploadFile = ReusableMethods1.driver.findElement(By.xpath("//a[@id='chatterUploadFileAction']"));
		ReusableMethods1.Click(UploadFile, "UploadFile");

		Thread.sleep(3000);
		WebElement choosefile = ReusableMethods1.driver.findElement(By.xpath("//input[@id='chatterFile']"));

		ReusableMethods1.EnterText(choosefile, "C:\\selenium\\test\\test.txt", "choosefile");

		WebElement Share = ReusableMethods1.driver.findElement(By.id("publishersharebutton"));
		ReusableMethods1.Click(Share, "ShareButton");

		Thread.sleep(10000);

		// Uploading photo
		Thread.sleep(3000);

		WebElement moderator = ReusableMethods1.driver.findElement(By.id("displayBadge"));
		// clickObj(AddPhoto, "AddPhoto");
		ReusableMethods1.MouseOver(ReusableMethods1.driver, moderator);

		WebElement AddPhotolink = ReusableMethods1.driver.findElement(By.xpath("//a[@id='uploadLink']"));
		ReusableMethods1.Click(AddPhotolink, "AddPhotolink");
		ReusableMethods1.SwitchFrame("uploadPhotoContentId");
		Thread.sleep(3000);

		WebElement choosefileoption = ReusableMethods1.driver
				.findElement(By.id("j_id0:uploadFileForm:uploadInputFile"));
		ReusableMethods1.EnterText(choosefileoption, "C:\\selenium\\test\\ProfilePic.JPG", "choosefileoption");

		WebElement save = ReusableMethods1.driver.findElement(By.id("j_id0:uploadFileForm:uploadBtn"));
		ReusableMethods1.Click(save, "save");
		System.out.println("TC06_MyProfile completed successfully");
		Thread.sleep(3000);

		WebElement save2 = ReusableMethods1.driver.findElement(By.xpath("//input[@id='j_id0:j_id7:save']"));
		ReusableMethods1.Click(save2, "Save Crop");

		Thread.sleep(5000);
		ReusableMethods1.driver.close();

	}

}
