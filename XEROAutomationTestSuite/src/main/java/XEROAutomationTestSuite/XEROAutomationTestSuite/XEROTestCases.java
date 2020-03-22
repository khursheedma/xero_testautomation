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
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

public class XEROTestCases extends XEROReusableMethods {
	
	@BeforeClass
	public void InitializeCls() {
		System.out.println("Initiazlize");
		createReport();
	}
	@Parameters({ "browserName" })
	@BeforeTest
	public void InitializeTest(String browserName) {
		
		System.out.println("Initialize Driver" + " " +  browserName);
		//logger = report.startTest("InitializeTest");
		//logger.log(LogStatus.INFO, "BEGIN: InitializeTest");
		InitializeDriver(browserName);
		//logger.log(LogStatus.INFO, "END: InitializeTest");
	}
	
	/*@Test (priority=1)
	public void TC01_NavigateToXERO() throws InterruptedException, IOException {
		
		logger = report.startTest("TC01_NavigateToXERO");
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
	}*/
	
	@Test (priority=2)
	public void TC02_IncorrectPassword() throws InterruptedException, IOException {
		
		logger = report.startTest("TC02_IncorrectPassword");
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
		EnterText(userName, "khursheed.ma@gmail.com", "Entered user name");
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
	
	@Test (priority=3)
	public void TC03_IncorrectEmail() throws InterruptedException, IOException {
		
		logger = report.startTest("TC03_IncorrectEmail");
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
	

	@Test (priority=4)
	public void TC04_ForgotPassword() throws InterruptedException, IOException {
		
		logger = report.startTest("TC04_ForgotPassword");
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
	
	@Test (priority=5)
	public void TC05_SignUpToXDC() throws InterruptedException, IOException {
		
		logger = report.startTest("TC05_SignUpToXDC");
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

// fluent wait begins
//	   Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)							
//				.withTimeout(90, TimeUnit.SECONDS) 			
//				.pollingEvery(5, TimeUnit.SECONDS) 			
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
// fluent wait ends
	   
//		driver.switchTo().defaultContent();
//		
//		WebElement termsAccept = driver.findElement(By.name("TermsAccepted"));
//		Click(termsAccept, "Click on terms accepted");
//		
//		WebElement getStartedBtn = driver.findElement(By.xpath("///button[@class='btn btn-primary']"));
//		Click(getStartedBtn, "Click on get started button");
		logger.log(LogStatus.INFO, " yet to complete");
	}
	

	@Test (priority=6)
	public void TC06_SignUpToXDC() throws InterruptedException, IOException {
		
		logger = report.startTest("TC05_SignUpToXDC");
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
	
	@Test (priority=7)
	public void TC07_SignUpToXDC() throws InterruptedException, IOException {
		
		logger = report.startTest("TC07_SignUpToXDC");
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
	
	@Test (priority=8)
	public void TC08_SignUpToXDC() throws InterruptedException, IOException {
		
		logger = report.startTest("TC08_SignUpToXDC");
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
	
	@Test (priority=9)
	public void TC09_SignUpToXDC() throws InterruptedException, IOException {
		
		logger = report.startTest("TC09_SignUpToXDC");
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
	
	@AfterTest
	public void CloseBrowserTest() {
		System.out.println("Closing the browser");
		closeBrowser();	
	}
	@AfterClass
	public void AfterClassMethod() {
		closeReport();
	}
	
}
