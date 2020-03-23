package XEROAutomationTestSuite.XEROAutomationTestSuite;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

public class XEROTestCases extends XEROReusableMethods {
	
	@BeforeSuite
	public void InitializeCls() {
		System.out.println("Initiazlize");
		createReport();
	}
//	@BeforeClass
//	public void InitializeCls() {
//		System.out.println("Initiazlize");
//		createReport();
//	}
	
	@Parameters({ "browserName" })
	@BeforeTest
	public void InitializeTest(String browserName) {
		
		System.out.println("Initialize Driver" + " " +  browserName);
		//logger = report.startTest("InitializeTest");
		//logger.log(LogStatus.INFO, "BEGIN: InitializeTest");
		InitializeDriver(browserName);
		//logger.log(LogStatus.INFO, "END: InitializeTest");
	}
	
	@Parameters({ "browserName" })
	@Test (priority=1)
	public void TC01_NavigateToXERO(String browserName) throws InterruptedException, IOException {
		
		logger = report.startTest("TC01_NavigateToXERO" + browserName);
		OpenUrl("https://www.xero.com/us/");
		logger.log(LogStatus.INFO, " URL opened successfully");
		Thread.sleep(3000);
		
		WebElement loginBtn = driver.findElement(By.linkText("Login"));
		Click(loginBtn, "Click login button to enter the details");
		Thread.sleep(3000);
		// enter user name
		WebElement userName = driver.findElement(By.name("userName"));
		EnterText(userName, "khursheed.ma@gmail.com", "Entered user name");
		// enter password
		WebElement pwd = driver.findElement(By.id("password"));
		EnterText(pwd, "Test123456!", "Entered password");
		// Click on login
		WebElement submitBtn = driver.findElement(By.id("submitButton"));
		Click(submitBtn, "Click login submit button");
		
		String expectedTitle = "Xero | Dashboard | IT";
		String actualTitle = driver.getTitle();
		
		System.out.println("actual title : " + actualTitle);
		
		if (actualTitle.equalsIgnoreCase(expectedTitle)) {
			logger.log(LogStatus.PASS, "User is on home page" + logger.addScreenCapture(takeScreenshot()));
		} else {
			logger.log(LogStatus.FAIL, "Home page is not launched successfully" + logger.addScreenCapture(takeScreenshot()));
		}	
	}
	
	@Parameters({ "browserName" })
	@Test (priority=2)
	public void TC02_IncorrectPassword(String browserName) throws InterruptedException, IOException {
		
		logger = report.startTest("TC02_IncorrectPassword " + browserName);
		OpenUrl("https://www.xero.com/us/");
		logger.log(LogStatus.INFO, " URL opened successfully");
		Thread.sleep(3000);
		
		WebElement loginBtn = driver.findElement(By.linkText("Login"));
		Click(loginBtn, "Click login button to enter the details");
		
		 // Waiting 30 seconds for an element to be present on the page, checking for its presence once every 5 seconds.	
// 		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)							
//					.withTimeout(90, TimeUnit.SECONDS) 			
//					.pollingEvery(5, TimeUnit.SECONDS) 			
//					.ignoring(NoSuchElementException.class);
//			WebElement userName = wait.until(new Function<WebDriver, WebElement>(){
//				public WebElement apply(WebDriver driver) {   
//					       return driver.findElement(By.name("userName"));    
//				}
//			});
		Thread.sleep(4000);
		// enter user name
		WebElement userName = driver.findElement(By.name("userName"));
		EnterText(userName, "xxxxxxx@gmail.com", "Entered user name");
		// enter password
		WebElement pwd = driver.findElement(By.id("password"));
		EnterText(pwd, "Test12345!!!", "Entered password");
		// Click on login
		WebElement submitBtn = driver.findElement(By.id("submitButton"));
		Click(submitBtn, "Click login submit button");
		
		Thread.sleep(3000);
		
		String errorMessageActual = driver.findElement(By.xpath("//p[contains(text(),'Your email or password is incorrect')]")).getText();
		System.out.println("errorMessageActual : " + errorMessageActual);
		
		String errorMessageExpected = "Your email or password is incorrect";

		if (errorMessageActual.equals(errorMessageExpected)) {
			logger.log(LogStatus.PASS, "Error message has been been displayed" + logger.addScreenCapture(takeScreenshot()));
		} else {
			logger.log(LogStatus.FAIL,"Error message has not been displayed" + logger.addScreenCapture(takeScreenshot()));
		}	
	}
	
	@Parameters({ "browserName" })
	@Test (priority=3)
	public void TC03_IncorrectEmail(String browserName) throws InterruptedException, IOException {
		
		logger = report.startTest("TC03_IncorrectEmail " + browserName);
		OpenUrl("https://www.xero.com/us/");
		logger.log(LogStatus.INFO, " URL opened successfully");
		Thread.sleep(2000);
		
		WebElement loginBtn = driver.findElement(By.linkText("Login"));
		Click(loginBtn, "Click login button to enter the details");
		Thread.sleep(4000);
		// enter user name
		WebElement userName = driver.findElement(By.name("userName"));
		EnterText(userName, "khursheed.maaaaa@gmail.com", "Entered user name");
		// enter password
		WebElement pwd = driver.findElement(By.id("password"));
		EnterText(pwd, "Test12345!", "Entered password");
		// Click on login
		WebElement submitBtn = driver.findElement(By.id("submitButton"));
		Click(submitBtn, "Click login submit button");
		
		Thread.sleep(3000);
		
		String errorMessageActual = driver.findElement(By.xpath("//p[contains(text(),'Your email or password is incorrect')]")).getText();
		System.out.println("errorMessageActual : " + errorMessageActual);
		
		String errorMessageExpected = "Your email or password is incorrect";

		if (errorMessageActual.equals(errorMessageExpected)) {
			logger.log(LogStatus.PASS, "Error message has been been displayed" + logger.addScreenCapture(takeScreenshot()));
		} else {
			logger.log(LogStatus.FAIL,"Error message has not been displayed" + logger.addScreenCapture(takeScreenshot()));
		}	
	}
	
	@Parameters({ "browserName" })
	@Test (priority=4)
	public void TC04_ForgotPassword(String browserName) throws InterruptedException, IOException {
		
		logger = report.startTest("TC04_ForgotPassword " + browserName);
		OpenUrl("https://www.xero.com/us/");
		logger.log(LogStatus.INFO, " URL opened successfully");
		Thread.sleep(2000);
		
		WebElement loginBtn = driver.findElement(By.linkText("Login"));
		Click(loginBtn, "Click login button to enter the details");
		Thread.sleep(4000);
		WebElement forgotPassword = driver.findElement(By.linkText("Forgot your password?"));
		Click(forgotPassword, "Click on forgot password link");
		
		// enter user name
		WebElement userName = driver.findElement(By.name("UserName"));
		EnterText(userName, "xxx@abc.com", "Entered user name");
		
		WebElement sendLinkPwdReset = driver.findElement(By.linkText("Send link"));
		Click(sendLinkPwdReset, "Click on send link for password reset");
		
		String pwdResetMessageActual = driver.findElement(By.xpath("//h2[contains(text(),'Please check your email')]")).getText();
		System.out.println("errorMessageActual : " + pwdResetMessageActual);
		
		String pwdResetMessageExpected = "Please check your email";

		if (pwdResetMessageActual.equals(pwdResetMessageExpected)) {
			logger.log(LogStatus.INFO, "Password reset message has been been displayed" + logger.addScreenCapture(takeScreenshot()));
		} else {
			logger.log(LogStatus.INFO,"Password reset message has not been displayed" + logger.addScreenCapture(takeScreenshot()));
		}	
		
		String resetEmailSentToActual = driver.findElement(By.xpath("//strong[contains(text(),'xxx@abc.com')]")).getText();
		System.out.println("resetEmailSentToActual : " + resetEmailSentToActual);
		
		String resetEmailSentToExpected = "xxx@abc.com";

		if (resetEmailSentToActual.equals(resetEmailSentToExpected)) {
			logger.log(LogStatus.PASS, "A link to reset your password has been sent to correct email address"  + logger.addScreenCapture(takeScreenshot()));
		} else {
			logger.log(LogStatus.FAIL,"A link to reset your password has been sent to incorrect email address" + logger.addScreenCapture(takeScreenshot()));
		}	
	}
	
	@Parameters ({"browserName"})
	@Test (priority=5)
	public void TC05_SignUpToXDC(String browserName) throws InterruptedException, IOException {
		
		logger = report.startTest("TC05_SignUpToXDC " + browserName);
		OpenUrl("https://www.xero.com/us/");
		logger.log(LogStatus.INFO, " URL opened successfully");
		Thread.sleep(2000);
		
		WebElement freeTrialLink = driver.findElement(By.linkText("Free trial"));
		if (driver.findElement(By.linkText("Free trial")).isEnabled()){
			Click(freeTrialLink, "Click free trial link");
		} else {
			logger.log(LogStatus.INFO, " Link not found");
		}
		
		// enter first name
		WebElement firstName = driver.findElement(By.name("FirstName"));
		EnterText(firstName, "xxxx", "Entered first name");
		
		// enter last name
		WebElement lastName = driver.findElement(By.name("LastName"));
		EnterText(lastName, "yyyy", "Entered last name");
		// enter email address
		WebElement emailAddr = driver.findElement(By.name("EmailAddress"));
		EnterText(emailAddr, "zzzz@abc.com", "Entered last name");
		// enter phone number
		WebElement phoneNumber = driver.findElement(By.name("PhoneNumber"));
		EnterText(phoneNumber, "10000000000", "Entered last name");
		
		// select location code
		Select locCode = new Select(driver.findElement(By.name("LocationCode")));

		// list all the options for the selected drop down and select one option of your choice
		List<WebElement> listLocCodes = locCode.getOptions();
		
		for (WebElement locCodeEle : listLocCodes) {
			if (locCodeEle.getText().equals("US")) {
				locCode.selectByVisibleText(locCodeEle.getText());
				break;
			}
		}
		
//	   new WebDriverWait(driver, 30).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[starts-with(@src, 'https://www.google.com/recaptcha')]")));
//	   new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.recaptcha-checkbox-border"))).click();

// 	   fluent wait begins
//	   Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)							
//				.withTimeout(90, TimeUnit.SECONDS) 			
//		 		.pollingEvery(5, TimeUnit.SECONDS) 			
//				.ignoring(NoSuchElementException.class);
//		WebElement clickseleniumlink = wait.until(new Function<WebDriver, WebElement>(){
//		
//			public WebElement apply(WebDriver driver ) {
//				//driver.switchTo().frame("a-tklph4lj0zw5");
//				driver.switchTo().frame(By.xpath("//iframe[starts-with(@src, 'https://www.google.com/recaptcha')]"));
//				return driver.findElement(By.cssSelector("div.recaptcha-checkbox-checkmark"));
//			}
//		});
//		clickseleniumlink.click();
//     fluent wait ends
	   
//		driver.switchTo().defaultContent();
//		
//		WebElement termsAccept = driver.findElement(By.name("TermsAccepted"));
//		Click(termsAccept, "Click on terms accepted");
//		
//		WebElement getStartedBtn = driver.findElement(By.xpath("///button[@class='btn btn-primary']"));
//		Click(getStartedBtn, "Click on get started button");
		logger.log(LogStatus.INFO, " yet to complete");
	}
	
	@Parameters ({"browserName"})
	@Test (priority=6)
	public void TC06_SignUpToXDC(String browserName) throws InterruptedException, IOException {
		
		logger = report.startTest("TC06_SignUpToXDC " + browserName);
		OpenUrl("https://www.xero.com/us/");
		logger.log(LogStatus.INFO, " URL opened successfully");
		Thread.sleep(2000);
		
		WebElement freeTrialLink = driver.findElement(By.linkText("Free trial"));
		if (driver.findElement(By.linkText("Free trial")).isEnabled()){
			Click(freeTrialLink, "Click free trial link");
		} else {
			logger.log(LogStatus.INFO, " Link not found");
		}
		
		// enter first name
		WebElement firstName = driver.findElement(By.name("FirstName"));
		EnterText(firstName, "xxxx", "Entered first name");
		
		// enter last name
		WebElement lastName = driver.findElement(By.name("LastName"));
		EnterText(lastName, "yyyy", "Entered last name");
		// enter email address
		WebElement emailAddr = driver.findElement(By.name("EmailAddress"));
		EnterText(emailAddr, "zzzz@abc.com", "Entered last name");
		// enter phone number
		WebElement phoneNumber = driver.findElement(By.name("PhoneNumber"));
		EnterText(phoneNumber, "10000000000", "Entered last name");
		
		// select location code
		Select locCode = new Select(driver.findElement(By.name("LocationCode")));

		// list all the options for the selected drop down and select one option of your choice
		List<WebElement> listLocCodes = locCode.getOptions();
		
		for (WebElement locCodeEle : listLocCodes) {
			if (locCodeEle.getText().equals("US")) {
				locCode.selectByVisibleText(locCodeEle.getText());
				break;
			}
		}
	  
//		WebElement termsAccept = driver.findElement(By.name("TermsAccepted"));
//		Click(termsAccept, "Click on terms accepted");
//		
//		WebElement getStartedBtn = driver.findElement(By.xpath("///button[@class='btn btn-primary']"));
//		Click(getStartedBtn, "Click on get started button");
		logger.log(LogStatus.INFO, " yet to complete");
	}
	
	@Parameters ({"browserName"})
	@Test (priority=7)
	public void TC07_SignUpToXDC(String browserName) throws InterruptedException, IOException {
		
		logger = report.startTest("TC07_SignUpToXDC " + browserName);
		OpenUrl("https://www.xero.com/us/");
		logger.log(LogStatus.INFO, " URL opened successfully");
		Thread.sleep(2000);
		
		WebElement freeTrialLink = driver.findElement(By.linkText("Free trial"));
		if (driver.findElement(By.linkText("Free trial")).isEnabled()){
			Click(freeTrialLink, "Click free trial link");
		} else {
			logger.log(LogStatus.INFO, " Link not found");
		}
		Thread.sleep(2000);
		WebElement termsLink = driver.findElement(By.linkText("terms"));
		if (driver.findElement(By.linkText("terms")).isEnabled()){
			Click(termsLink, "Click terms link");
		} else {
			logger.log(LogStatus.INFO, " terms link not found");
		}
	  
		String parentWindowHandler = driver.getWindowHandle(); // Store main window handler
		
		SwitchToNewWindow("terms link URL");
		
		driver.switchTo().window(parentWindowHandler);  // switch back to main window
		
		Thread.sleep(2000);
		
		WebElement privacyLink = driver.findElement(By.linkText("privacy"));
		if (driver.findElement(By.linkText("privacy")).isEnabled()){
			Click(privacyLink, "Click privacy link");
		} else {
			logger.log(LogStatus.INFO, " privacy link not found");
		}
	  
		parentWindowHandler = driver.getWindowHandle(); // Store main window handler
		SwitchToNewWindow("privacy link URL");
		driver.switchTo().window(parentWindowHandler);  // switch back to parent window
	}
	
	@Parameters ({"browserName"})
	@Test (priority=8)
	public void TC08_SignUpToXDC(String browserName) throws InterruptedException, IOException {
		
		logger = report.startTest("TC08_SignUpToXDC " + browserName);
		OpenUrl("https://www.xero.com/us/");
		logger.log(LogStatus.INFO, " URL opened successfully");
		Thread.sleep(2000);
		
		WebElement freeTrialLink = driver.findElement(By.linkText("Free trial"));
		if (driver.findElement(By.linkText("Free trial")).isEnabled()){
			Click(freeTrialLink, "Click free trial link");
		} else {
			logger.log(LogStatus.INFO, " link not found");
		}
		Thread.sleep(2000);
		WebElement offerDetailsLink = driver.findElement(By.linkText("offer details"));
		if (driver.findElement(By.linkText("offer details")).isEnabled()){
			Click(offerDetailsLink, "Click offer details link");
		} else {
			logger.log(LogStatus.INFO, " offer details link not found");
		}
	  
		String parentWindowHandler = driver.getWindowHandle(); // Store main window handler
		
		SwitchToNewWindow("offer details link URL");
		
		driver.switchTo().window(parentWindowHandler);  // switch back to main window
	}
	
	@Parameters ({"browserName"})
	@Test (priority=9)
	public void TC09_SignUpToXDC(String browserName) throws InterruptedException, IOException {
		
		logger = report.startTest("TC09_SignUpToXDC " + browserName);
		OpenUrl("https://www.xero.com/us/");
		logger.log(LogStatus.INFO, " URL opened successfully");
		Thread.sleep(2000);
		
		WebElement freeTrialLink = driver.findElement(By.linkText("Free trial"));
		if (driver.findElement(By.linkText("Free trial")).isEnabled()){
			Click(freeTrialLink, "Click free trial link");
		} else {
			logger.log(LogStatus.INFO, " link not found");
		}
		Thread.sleep(2000);
		WebElement acctOrBookkpLink = driver.findElement(By.linkText("accountant or bookkeeper"));
		if (driver.findElement(By.linkText("accountant or bookkeeper")).isEnabled()){
			Click(acctOrBookkpLink, "Click accountant or bookkeeper link");
		} else {
			logger.log(LogStatus.INFO, " accountant or bookkeeper link not found");
		}
	  
		if (driver.getCurrentUrl() != null) {
			logger.log(LogStatus.PASS, driver.getCurrentUrl() + " " +  " accountant or bookkeeper page is correctly displayed");
		} else {
			logger.log(LogStatus.FAIL, driver.getCurrentUrl() + " " + " accountant or bookkeeper page is incorrectly displayed");
		}
	}
	
	
	@Parameters({ "browserName" })
	@Test (priority=10)
	public void TC10_TestAllTabsPage(String browserName) throws InterruptedException, IOException {
		
		logger = report.startTest("TC10_TestAllTabsPage " + browserName);
		OpenUrl("https://www.xero.com/us/");
		logger.log(LogStatus.INFO, " URL opened successfully");
		Thread.sleep(3000);
		
		WebElement loginBtn = driver.findElement(By.linkText("Login"));
		Click(loginBtn, "Click login button to enter the details");
		Thread.sleep(3000);
		// enter user name
		WebElement userName = driver.findElement(By.name("userName"));
		EnterText(userName, "khursheed.ma@gmail.com", "Entered user name");
		// enter password
		WebElement pwd = driver.findElement(By.id("password"));
		EnterText(pwd, "Test123456!", "Entered password");
		// Click on login
		WebElement submitBtn = driver.findElement(By.id("submitButton"));
		Click(submitBtn, "Click login submit button");
		
		String expectedTitle = "Xero | Dashboard | IT";
		String actualTitle = driver.getTitle();
		
		System.out.println("actual title : " + actualTitle);
		
		if (actualTitle.equalsIgnoreCase(expectedTitle)) {
			logger.log(LogStatus.PASS, "User is on home page" + logger.addScreenCapture(takeScreenshot()));
		} else {
			logger.log(LogStatus.FAIL, "Home page is not launched successfully" + logger.addScreenCapture(takeScreenshot()));
		}
		
		// Click on dashboard menu
		WebElement clickDashboardMenu = driver.findElement(By.name("navigation-menu/dashboard"));
		Click(clickDashboardMenu, "Click on dashboard menu");
		String Object = "Dashboard";
		verifyMenuItemPage(Object);
		
		// Click on accounting menu
		WebElement clickAccountingMenu = driver.findElement(By.name("navigation-menu/accounting"));
		Click(clickAccountingMenu, "Click on accounting menu");
		Object = "Accounting";
		verifyMenuItemPage(Object);
		
		// Click on reports menu
		WebElement clickReportsMenu = driver.findElement(By.xpath("//a[contains(text(),'Reports')]"));
		Click(clickReportsMenu, "Click on reports menu");
		Object = "Reports";
		verifyMenuItemPage(Object);
		
		// Click on contacts menu
		WebElement clickContactsMenu = driver.findElement(By.name("navigation-menu/contacts"));
		Click(clickContactsMenu, "Click on contacts menu");
		Object = "Contacts";
		verifyMenuItemPage(Object);
		
		// Click on IT menu
		WebElement clickITMenu = driver.findElement(By.xpath("//div[@class='xrh-appbutton--body']"));
		Click(clickITMenu, "Click on IT menu");
		Object = "IT";
		verifyMenuItemPage(Object);
		
		// Click on settings menu
		WebElement clickSettingsMenu = driver.findElement(By.xpath("//a[contains(text(),'Settings')]"));
		Click(clickSettingsMenu, "Click on settings menu");
		Object = "Settings";
		verifyMenuItemPage(Object);
		
		// Click on + menu
		WebElement clickPlusMenu = driver.findElement(By.xpath("//li[1]//button[1]//div[1]"));
		Click(clickPlusMenu, "Click on + menu");
		Object = "+";
		verifyMenuItemPage(Object);
		
		// Click on IT menu
		clickITMenu = driver.findElement(By.xpath("//div[@class='xrh-appbutton--body']"));
		Click(clickITMenu, "Click on IT menu");
		Object = "IT";
		verifyMenuItemPage(Object);
		
		// Click on settings menu
		WebElement clickFilesMenu = driver.findElement(By.xpath("//a[contains(text(),'Files')]"));
		Click(clickFilesMenu, "Click on files menu");
		Object = "Files";
		verifyMenuItemPage(Object);
		
		// Click on notification menu
		WebElement clickNotificationMenu = driver.findElement(By.xpath("//li[3]//button[1]//div[1]//*[local-name()='svg']"));
		Click(clickNotificationMenu, "Click on notification menu");
		Object = "Notification";
		verifyMenuItemPage(Object);
		
		// Click on search menu
		WebElement clickSearchMenu = driver.findElement(By.xpath("//li[2]//button[1]//div[1]//*[local-name()='svg']"));
		Click(clickSearchMenu, "Click on search menu");
		Object = "Search";
		verifyMenuItemPage(Object);
		
		// Click on help menu
		WebElement clickHelpMenu = driver.findElement(By.xpath("//li[4]//button[1]//div[1]//*[local-name()='svg']"));
		Click(clickHelpMenu, "Click on help menu");
		Object = "Help";
		verifyMenuItemPage(Object);
	}
	
	@Parameters({ "browserName" })
	@Test (priority=11)
	public void TC11_LogoutFunctionality(String browserName) throws InterruptedException, IOException {
		
		logger = report.startTest("TC11_LogoutFunctionality " + browserName);
		OpenUrl("https://www.xero.com/us/");
		logger.log(LogStatus.INFO, " URL opened successfully");
		Thread.sleep(3000);
		
		WebElement loginBtn = driver.findElement(By.linkText("Login"));
		Click(loginBtn, "Click login button to enter the details");
		Thread.sleep(3000);
		// enter user name
		WebElement userName = driver.findElement(By.name("userName"));
		EnterText(userName, "khursheed.ma@gmail.com", "Entered user name");
		// enter password
		WebElement pwd = driver.findElement(By.id("password"));
		EnterText(pwd, "Test123456!", "Entered password");
		// Click on login
		WebElement submitBtn = driver.findElement(By.id("submitButton"));
		Click(submitBtn, "Click login submit button");
		
		String expectedTitle = "Xero | Dashboard | IT";
		String actualTitle = driver.getTitle();
		
		System.out.println("actual title : " + actualTitle);
		
		if (actualTitle.equalsIgnoreCase(expectedTitle)) {
			logger.log(LogStatus.PASS, "User is on home page" + logger.addScreenCapture(takeScreenshot()));
		} else {
			logger.log(LogStatus.FAIL, "Home page is not launched successfully" + logger.addScreenCapture(takeScreenshot()));
		}	
		
		// Click on user info menu
		WebElement userInfoBtn = driver.findElement(By.xpath("//abbr[@class='xrh-avatar xrh-avatar-color-3']"));
		Click(userInfoBtn, "Click on user info button");
		
		// Click on logout button
		WebElement logoutBtn = driver.findElement(By.linkText("Log out"));
		Click(logoutBtn, "Click logout button");
		
		// validate user name displayed
		userName = driver.findElement(By.name("userName"));
		
		System.out.println("user name : " + userName.getText());
		
		if (userName.getText() != null) {
			logger.log(LogStatus.PASS, userName.getText() + logger.addScreenCapture(takeScreenshot()));
		} else {
			logger.log(LogStatus.FAIL, userName.getText() + logger.addScreenCapture(takeScreenshot()));
		}	
	}
	
	@Parameters({ "browserName" })
	@Test (priority=12)
	public void TC12_UploadProfileImage(String browserName) throws InterruptedException, IOException {
		
		logger = report.startTest("TC12_UploadProfileImage " + browserName);
		OpenUrl("https://www.xero.com/us/");
		logger.log(LogStatus.INFO, " URL opened successfully");
		Thread.sleep(3000);
		
		WebElement loginBtn = driver.findElement(By.linkText("Login"));
		Click(loginBtn, "Click login button to enter the details");
		Thread.sleep(3000);
		// enter user name
		WebElement userName = driver.findElement(By.name("userName"));
		EnterText(userName, "khursheed.ma@gmail.com", "Entered user name");
		// enter password
		WebElement pwd = driver.findElement(By.id("password"));
		EnterText(pwd, "Test123456!", "Entered password");
		// Click on login
		WebElement submitBtn = driver.findElement(By.id("submitButton"));
		Click(submitBtn, "Click login submit button");
		
		String expectedTitle = "Xero | Dashboard | IT";
		String actualTitle = driver.getTitle();
		
		System.out.println("actual title : " + actualTitle);
		
		if (actualTitle.equalsIgnoreCase(expectedTitle)) {
			logger.log(LogStatus.PASS, "User is on home page" + logger.addScreenCapture(takeScreenshot()));
		} else {
			logger.log(LogStatus.FAIL, "Home page is not launched successfully" + logger.addScreenCapture(takeScreenshot()));
		}	
		
		// Click on user info menu
		WebElement userInfoBtn = driver.findElement(By.xpath("//abbr[@class='xrh-avatar xrh-avatar-color-3']"));
		Click(userInfoBtn, "Click on user info button");
		
		// Click on user profile
		WebElement userProfile = driver.findElement(By.xpath("//span[@class='xrh-verticalmenuitem--subheading']"));
		Click(userProfile, "Click on user profile");
		Thread.sleep(2000);
		
		// upload image 
		WebElement AddPhotolink = driver.findElement(By.id("button-1041-btnInnerEl"));
		Click(AddPhotolink, "AddPhotolink");
		System.out.println("Test : upload image");
		
//		SwitchFrame("sandbox-2");
//		
//		String parentWindowHandler = driver.getWindowHandle();
//		String subWindowHandler = null;
//		Set<String> handles = driver.getWindowHandles(); // get all window handles
//		Iterator<String> iterator = handles.iterator();
//		while (iterator.hasNext()){
//		    subWindowHandler = iterator.next();
//		}
//		driver.switchTo().window(subWindowHandler); // switch to new tab/window window
//	
//		// Now you are in the new window/tab, perform necessary actions here
//		if (driver.getCurrentUrl() != null) {
//			logger.log(LogStatus.PASS, driver.getCurrentUrl() +  " is correctly displayed");
//		} else {
//			logger.log(LogStatus.FAIL, driver.getCurrentUrl() + " is incorrectly displayed");
//		}
		
		for (String handle : driver.getWindowHandles()) {	 
		    driver.switchTo().window(handle);
		}
		
		if (driver.getCurrentUrl() != null) {
			logger.log(LogStatus.PASS, driver.getCurrentUrl());
		} else {
			logger.log(LogStatus.FAIL, driver.getCurrentUrl());
		}
		
		Thread.sleep(2000);
	
		WebElement choosefileoption = driver.findElement(By.xpath("//input[starts-with(@id, 'filefield-') and contains(@class, 'x-form-file-input')]"));
		
		//button[starts-with(@id, 'save') and contains(@class,'publish')]
		//*[@id="filefield-1174-button"]
		EnterText(choosefileoption, "/Users/khursheed/Downloads/BISMILLAH.JPG", "choosefileoption");
//		WebElement save = driver.findElement(By.id("button-1164-btnEl"));
//		Click(save, "save");
		//driver.switchTo().window(parentWindowHandler);
		
	}
	

	@Parameters({ "browserName" })
	@Test (priority=13)
	public void TC13_AddAnotherOrgTrailVer_1(String browserName) throws InterruptedException, IOException {
		
		logger = report.startTest("TC13_AddAnotherOrgTrailVer_1 " + browserName);
		OpenUrl("https://www.xero.com/us/");
		logger.log(LogStatus.INFO, " URL opened successfully");
		Thread.sleep(3000);
		
		WebElement loginBtn = driver.findElement(By.linkText("Login"));
		Click(loginBtn, "Click login button to enter the details");
		Thread.sleep(3000);
		// enter user name
		WebElement userName = driver.findElement(By.name("userName"));
		EnterText(userName, "khursheed.ma@gmail.com", "Entered user name");
		// enter password
		WebElement pwd = driver.findElement(By.id("password"));
		EnterText(pwd, "Test123456!", "Entered password");
		// Click on login
		WebElement submitBtn = driver.findElement(By.id("submitButton"));
		Click(submitBtn, "Click login submit button");
		
//		String expectedTitle = "Xero | Dashboard | IT";
//		String actualTitle = driver.getTitle();
//		
//		System.out.println("actual title : " + actualTitle);
//		
//		if (actualTitle.equalsIgnoreCase(expectedTitle)) {
//			logger.log(LogStatus.PASS, "User is on home page" + logger.addScreenCapture(takeScreenshot()));
//		} else {
//			logger.log(LogStatus.FAIL, "Home page is not launched successfully" + logger.addScreenCapture(takeScreenshot()));
//		}
		
		if (driver.getCurrentUrl() != null) {
			logger.log(LogStatus.PASS, driver.getCurrentUrl() + " is correctly displayed");
		} else {
			logger.log(LogStatus.FAIL, driver.getCurrentUrl() + " is incorrectly displayed");
		}
		
		// Click on IT menu
		WebElement clickITMenu = driver.findElement(By.xpath("//div[@class='xrh-appbutton--body']"));
		Click(clickITMenu, "Click on IT menu");
		
		// Click on My Xero menu
		WebElement clickMyXeroMenu = driver.findElement(By.xpath("//a[contains(text(),'My Xero')]"));
		Click(clickMyXeroMenu, "Click on My Xero menu");

		String parentWindowHandler = driver.getWindowHandle(); // Store main window handler
		String subWindowHandler = null;
		Set<String> handles = driver.getWindowHandles(); // get all window handles
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()){
		    subWindowHandler = iterator.next();
		}
		
		driver.switchTo().window(subWindowHandler); // switch to new tab/window window
		
		// Now you are in the new window/tab, perform necessary actions here
		if (driver.getCurrentUrl() != null) {
			logger.log(LogStatus.PASS, driver.getCurrentUrl() + " is correctly displayed");
		} else {
			logger.log(LogStatus.FAIL, driver.getCurrentUrl() + " is incorrectly displayed");
		}
		Thread.sleep(2000);
		WebElement addOrg = driver.findElement(By.partialLinkText("Add an organization"));
		if (driver.findElement(By.partialLinkText("Add an organization")).isEnabled()){
			Click(addOrg, "Click on add an orgaganization");
		} else {
			logger.log(LogStatus.INFO, " add an organization link not found");
		}
	  
		WebElement orgName = driver.findElement(By.cssSelector("input[data-automationid='organisation-name--input']"));
		EnterText(orgName, "xxxx", "entered organizaion name");
		
		WebElement industryName = driver.findElement(By.cssSelector("input[data-automationid='industry-autocompleter--input']"));
		EnterText(industryName, "IT", "entered industry name");
				
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//ul[@role='group']//li")));
		
		List<WebElement> list = driver.findElements(By.xpath("//ul[@role='group']//li"));
		
		System.out.println("Auto Suggest List : " + list.size());
		
		for(int i = 0; i < list.size(); i++)
		{
			//System.out.println(list.get(i).getText());
			if(list.get(i).getText().equals("IT Software Development"))
			{
				list.get(i).click();
				break;
			}
		}
		
		WebElement empCheckBox = driver.findElement(By.xpath("//div[@class='xui-styledcheckboxradio-group']//div[1]//label[1]//div[1]"));
		Click(empCheckBox,  "yes, employees selected");
		
		WebElement startTrialBtn = driver.findElement(By.xpath("//div[@class='xui-actions xui-actions-layout']//preceding-sibling::button"));
		Click(startTrialBtn,  "Click start trial");
		
		// Now you are in the new window/tab, perform necessary actions here
		if (driver.getCurrentUrl() != null) {
			logger.log(LogStatus.PASS, driver.getCurrentUrl() + " is correctly displayed");
		} else {
			logger.log(LogStatus.FAIL, driver.getCurrentUrl() + " is incorrectly displayed");
		}
		closeBrowser();
		driver.switchTo().window(parentWindowHandler); // switch to main window window
	}
	
	
	@Parameters({ "browserName" })
	@Test (priority=14)
	public void TC14_AddAnotherOrgTrailVer_2(String browserName) throws InterruptedException, IOException {
		
		logger = report.startTest("TC14_AddAnotherOrgTrailVer_2 " + browserName);
		OpenUrl("https://www.xero.com/us/");
		logger.log(LogStatus.INFO, " URL opened successfully");
		Thread.sleep(3000);
		
		WebElement loginBtn = driver.findElement(By.linkText("Login"));
		Click(loginBtn, "Click login button to enter the details");
		Thread.sleep(4000);
		// enter user name
		WebElement userName = driver.findElement(By.name("userName"));
		EnterText(userName, "khursheed.ma@gmail.com", "Entered user name");
		// enter password
		WebElement pwd = driver.findElement(By.id("password"));
		EnterText(pwd, "Test123456!", "Entered password");
		// Click on login
		WebElement submitBtn = driver.findElement(By.id("submitButton"));
		Click(submitBtn, "Click login submit button");
		
		if (driver.getCurrentUrl() != null) {
			logger.log(LogStatus.PASS, driver.getCurrentUrl() + " is correctly displayed");
		} else {
			logger.log(LogStatus.FAIL, driver.getCurrentUrl() + " is incorrectly displayed");
		}
		
		// Click on IT menu
		WebElement clickITMenu = driver.findElement(By.xpath("//div[@class='xrh-appbutton--body']"));
		Click(clickITMenu, "Click on IT menu");
		
		// Click on My Xero menu
		WebElement clickMyXeroMenu = driver.findElement(By.xpath("//a[contains(text(),'My Xero')]"));
		Click(clickMyXeroMenu, "Click on My Xero menu");

		String parentWindowHandler = driver.getWindowHandle(); // Store main window handler
		String subWindowHandler = null;
		Set<String> handles = driver.getWindowHandles(); // get all window handles
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()){
		    subWindowHandler = iterator.next();
		}
		driver.switchTo().window(subWindowHandler); // switch to new tab/window window
		
		// Now you are in the new window/tab, perform necessary actions here
		if (driver.getCurrentUrl() != null) {
			logger.log(LogStatus.PASS, driver.getCurrentUrl() + " is correctly displayed");
		} else {
			logger.log(LogStatus.FAIL, driver.getCurrentUrl() + " is incorrectly displayed");
		}
		Thread.sleep(2000);
		WebElement addOrg = driver.findElement(By.partialLinkText("Add an organization"));
		if (driver.findElement(By.partialLinkText("Add an organization")).isEnabled()){
			Click(addOrg, "Click on add an orgaganization");
		} else {
			logger.log(LogStatus.INFO, " add an organization link not found");
		}
	  
		WebElement orgName = driver.findElement(By.cssSelector("input[data-automationid='organisation-name--input']"));
		EnterText(orgName, "yyyy", "entered organizaion name");
		
		WebElement industryName = driver.findElement(By.cssSelector("input[data-automationid='industry-autocompleter--input']"));
		EnterText(industryName, "IT", "entered industry name");
	
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//ul[@role='group']//li")));
		
		List<WebElement> list = driver.findElements(By.xpath("//ul[@role='group']//li"));
		
		// System.out.println("Auto Suggest List : " + list.size());
		
		for(int i = 0; i< list.size(); i++)
		{
			// System.out.println(list.get(i).getText());
			if(list.get(i).getText().equals("IT Software Development"))
			{
				list.get(i).click();
				break;
			}
		}
		
		WebElement empCheckBox = driver.findElement(By.xpath("//div[@class='xui-styledcheckboxradio-group']//div[1]//label[1]//div[1]"));
		Click(empCheckBox,  "yes, employees selected");
		
		WebElement buyNowBtn = driver.findElement(By.xpath("//div[@class='xui-actions xui-actions-layout']//following-sibling::button"));
		Click(buyNowBtn,  "Click buy now");
		
		// Now you are in the new window/tab, perform necessary actions here
		if (driver.getCurrentUrl() != null) {
			logger.log(LogStatus.PASS, driver.getCurrentUrl() + " is correctly displayed");
		} else {
			logger.log(LogStatus.FAIL, driver.getCurrentUrl() + " is incorrectly displayed");
		}
		//closeBrowser();
		driver.switchTo().window(parentWindowHandler); // switch to main window window
	}
	
	@AfterTest
	public void CloseBrowserTest() {
		System.out.println("Closing the browser");
		closeBrowser();	
	}
	
//	@AfterClass
//	public void AfterClassMethod() {
//		closeReport();
//	}
	
	@AfterSuite
	public void AfterClassMethod() {
		closeReport();
	}
}
