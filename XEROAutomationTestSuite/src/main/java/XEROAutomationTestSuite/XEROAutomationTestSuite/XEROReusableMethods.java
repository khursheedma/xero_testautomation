package XEROAutomationTestSuite.XEROAutomationTestSuite;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
//import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class XEROReusableMethods {

	static WebDriver driver;
	static ExtentTest logger;
	static ExtentReports report;
	
	public void InitializeDriver(String Browser) {
		
		System.out.println("The WebDriver Init Method " + Browser);
		String userDir = System.getProperty("user.dir");
		String OS = OSDetector();
		System.out.println("OS : " + OS);
		
		if (Browser.equalsIgnoreCase("chrome") && (OS.equals("Mac") || OS.equals("Windows"))) {
			System.out.println("BEGIN : The Chrome Driver on Mac OS or Windows OS ");
			//logger.log(LogStatus.INFO, "The Chrome Driver on Mac OS or Windows OS");
			WebDriverManager.chromedriver().setup();
//			ChromeOptions options = new ChromeOptions();
//			options.addArguments("--start-maximized");
//			options.addArguments("disable-infobars");
//			options.addArguments("--disable-extensions");
//			driver = new ChromeDriver(options);	        
			driver = new ChromeDriver();	
		}
		
		if (Browser.equalsIgnoreCase("FireFox") && (OS.equals("Mac") || OS.equals("Windows"))) {
			//logger.log(LogStatus.INFO, "The Firefox Driver on Mac OS or Windows OS");
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		
		if (Browser.equals("safari") || (Browser.equals("Safari")) && (OS.equals("Mac"))) {
			//logger.log(LogStatus.INFO, "The Safari Driver on Mac OS");
			driver = new SafariDriver(); 
		}
	}
	
	public String OSDetector() {
		
		String os = System.getProperty("os.name").toLowerCase();
		
		if (os.contains("win")) {
			return "windows";
		} else if (os.contains("nux") || os.contains("nix")) {
			return "Linux";
		} else if (os.contains("mac")) {
			return "Mac";
		} else if (os.contains("sunos")) {
			return "Solaris";
		} else {
			return "Other";
		}
	}
	
	public Boolean navigateTo(final String urlString) {
		
		Boolean mainPageFound = false;
		
		try {
			System.out.println("The Navigate URL " + urlString);
			String navigateUrl;
			
			navigateUrl = urlString;
			
			driver.get(navigateUrl);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			String currentUrl = getCurrentUrl();
			
			if (currentUrl != null) {
				mainPageFound = true;
				//driver.manage().window().maximize();
				driver.manage().window().fullscreen();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return mainPageFound;
	}

	
	public String getCurrentUrl() {
		
		String currentUrl = driver.getCurrentUrl();
		return currentUrl;
		
	}
	
	public static void OpenUrl(String url) {
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		//driver.manage().window().maximize();
	}
	
	public static void closeBrowser() {
		driver.close();
	}
	
	public static void closeReport() {
		report.flush();
	}
	
	public void quitDriver() {
		driver.quit();
	}

	
	/*
	 * Name of the Method 	: CreateReport
	 * Brief Description 	: This will create a empty Extent Report
	 * Arguments			: No arguments
	 * Created By			: Khursheed
	 * Created Date 		: 14/02/2020
	 * Last Modified		: 14/02/2020
	 * 
	 */
	
	public static void createReport() {
		String fileName = new SimpleDateFormat("'SFDCReport_'YYYYMMddHHmm'.html'").format(new Date());
		String path = "/Users/khursheed/test_reports/" + fileName;
		System.out.println(path);
		report =  new ExtentReports(path);
	}
	
	public static String takeScreenshot() throws IOException {
		TakesScreenshot srcShot = ((TakesScreenshot) driver);
		
		File srcFile = srcShot.getScreenshotAs(OutputType.FILE);
		String imagePath = "/Users/khursheed/test_reports/" + new SimpleDateFormat("'Image_'YYYYMMddHHmmss'.png'").format(new Date());
		File destFile = new File(imagePath);
		FileUtils.copyFile(srcFile, destFile);
		return imagePath;
	}
	
	/*
	 * Name of the Method 	: EnterText
	 * Brief Description 	: Entering test value for text box
	 * Arguments			: element -> object
	 * 						  text    -> to be entered
	 * 						  objName -> object name
	 * Created By			: Khursheed
	 * Created Date 		: 14/02/2020
	 * Last Modified		: 14/02/2020
	 * 
	 */
	
	public static void EnterText(WebElement element, String text, String objName) {
		
		logger.log(LogStatus.INFO, "EnterText");
		
		if (element == null || !element.isDisplayed()) {
			logger.log(LogStatus.ERROR, objName + "User text box is not found");
		} else {
			logger.log(LogStatus.INFO, objName + "User text box is found");
			element.sendKeys(text);
		}
	}
	
	public static void sendKeys(WebElement element, String name, String objName) {
		
		logger.log(LogStatus.INFO, "BEGIN: sendKeys");
		WebDriverWait wait = new WebDriverWait(driver, 10);
		if (element == null || !element.isDisplayed()) {
			logger.log(LogStatus.ERROR, objName + "User text box is not found");
		} else {
			logger.log(LogStatus.INFO, objName + "User text box is found");
			//WebElement clickableElement = wait.until(ExpectedConditions.elementToBeClickable(element));
			//clickableElement.sendKeys(name);
		}
		logger.log(LogStatus.INFO, "END: sendKeys");
	}
	
	public WebElement findElement(By element) {
		
		logger.log(LogStatus.INFO, "findElement");
		
		if (driver.findElement(element).isDisplayed()) {
			logger.log(LogStatus.INFO, "The element exists");
			return driver.findElement(element);
		}
		else {
			logger.log(LogStatus.INFO, "The element does not exist");
			return null;
		}
		
	}
	
	public List<WebElement> findElements(By element) {
		logger.log(LogStatus.INFO, "findElements");
		
		if (!driver.findElements(element).isEmpty()) {
			logger.log(LogStatus.INFO, "List <WebElements> not empty");
			return driver.findElements(element);
		}
		else {
			logger.log(LogStatus.INFO, "List <WebElements> is empty");
			return null;
		}
		//return driver.findElements(element);
	}

	public String getElementText(By element) {
		
		logger.log(LogStatus.INFO, "getElementText");
		try {
			if (driver.findElement(element).isDisplayed()) {
				logger.log(LogStatus.INFO,"The element exists");
				return driver.findElement(element).getText();
			}
		}
		catch (NoSuchElementException e) {
			e.getStackTrace();
		}
		logger.log(LogStatus.INFO,"The element does not exist");
		return null;
	}

	public Boolean checkLinkExists(By element) {
		
		logger.log(LogStatus.INFO, "BEGIN: CheckLinkExists");
		try {
			if (driver.findElement(element).isEnabled()) {
				logger.log(LogStatus.INFO, "Link exists");
				return true;
			}
		}
		catch (NoSuchElementException e) {
			e.getStackTrace();
		}
		logger.log(LogStatus.INFO, "Link does not exist");
		return null;
	}

	public void clickElement(WebElement element) {
		
		logger.log(LogStatus.INFO, "BEGIN: clickElement");
		
		WebDriverWait wait = new WebDriverWait(driver, 15);
		try {
	        //System.out.println("The element is " +element.getText());
	        
	        //Wait.someSec(GlobalDataStore.WAIT_TIME);
			//WebElement clickableElement = wait.until(ExpectedConditions.elementToBeClickable(element));
			//clickableElement.click();
			
		} catch (StaleElementReferenceException e) {

			logger.log(LogStatus.INFO,"Element  " + element.getText() + "does not exist " + e.getStackTrace());

		} catch (NoSuchElementException e) {

			logger.log(LogStatus.INFO,"Element  " + element.getText() + "does not exist " + e.getStackTrace());
		} catch (Exception e) {
			
			logger.log(LogStatus.INFO,"Element does not exist " + e.getStackTrace());
		}
		
		logger.log(LogStatus.INFO, "END: clickElement");

	}
	
	public  static Alert AcceptAlert() {
		//Alert simpleAlert = driver.switchTo().alert();
		//String alertText = simpleAlert.getText();
		//System.out.println("Alert text is " + alertText);
		//simpleAlert.accept();
		logger.log(LogStatus.INFO, "AcceptAlert");
		return driver.switchTo().alert();
	}
	
	
	public static void Click(WebElement element, String objName) {
		
		//logger.log(LogStatus.INFO, "Click");
		if (element == null || !element.isDisplayed()) {
			logger.log(LogStatus.ERROR, objName + " element is not found");
		} else {
			logger.log(LogStatus.INFO, objName + " element is found");
			element.click();
		}
	}
	
	/*
	 * Name of the method: selectDropdown Brief Description: Select the Dropdown
	 * list Arguments: obj --> web object, objName--> name of the object Created by:
	 * Automation team Creation Date: Feb 12 2019 Last Modified: Feb 12 2019
	 */
	public static void SelectDropdown(WebElement obj, String objName) {

		if (obj.isDisplayed()) {
			logger.log(LogStatus.PASS, objName  + "is  selected");
			obj.click();
		} else {
			logger.log(LogStatus.FAIL, objName + " is not present.Please chk application");				
		}
	}

	/*
	 * name of the method: switchFrameid BriefDescription : iframe using webelement
	 * Arguments : driver--->driver obj--->iframe id createdby : Automation team
	 * created date :02/13/19 LastModified Date:02/13/19
	 */

	public static void SwitchFrame(String id) {
		driver.switchTo().frame(id);
		logger.log(LogStatus.PASS, " we can switch to the " + id + " frame");
	}
	
	public static void SwitchFrame(WebElement element) {
		driver.switchTo().frame(element);
		logger.log(LogStatus.PASS, " we can switch to the frame");
	}

	public static void SwitchFrame() {
		driver.switchTo().defaultContent();
		logger.log(LogStatus.PASS, " we can switch to the frame");
	}
	
	/* name of the method:   mouseOver
	 * BriefDescription  :   mouseOver  
	 * Arguments         :  obj,index 
	 *  createdby        :  Automation team 
	 *  created date     :02/13/19 
	 *  LastModified Date:02/13/19          
	 */ 

	public static void MouseOver(WebDriver driver, WebElement obj) {
		
		if(obj.isDisplayed()) {
			Actions action=new Actions(driver);
			action.moveToElement(obj).build().perform();
			//System.out.println("Pass: "+obj+" is present");
			logger.log(LogStatus.PASS,  obj + " is present" );
		}
		else {
			// System.out.println("Fail:"+obj+" is not present.Please chk application");
			logger.log(LogStatus.FAIL,  obj + " is not present.Please chk application" );
		}
	}

	public static String CurrentDate() {		
		Date today;
		String result;
		SimpleDateFormat formatter;

		formatter = new SimpleDateFormat("EEEE MMMM d, yyyy");
		today = new Date();
		result = formatter.format(today);
		//System.out.println("Locale: " + currentLocale.toString());
		//System.out.println("Result: " + result);
		return result;
	 }
	
	public static String CurrentDate2WksMMDDYYYY() {		
		Date today;
		String result;
		SimpleDateFormat formatter;
		
		Calendar calendar = Calendar.getInstance();
		//System.out.println("Current Date = " + calendar.getTime());
		// Adding 2 weeks
		calendar.add(Calendar.WEEK_OF_YEAR, 2);
		//System.out.println("Updated Date = " + calendar.getTime());

		formatter = new SimpleDateFormat("M/d/YYYY");
		//today = new Date();
		result = formatter.format(calendar.getTime());
		//System.out.println("Locale: " + currentLocale.toString());
		//System.out.println("Result: " + result);
		return result;
	 }
	
	public static void SwitchToNewWindow(String Object) {
		
		String subWindowHandler = null;
		
		Set<String> handles = driver.getWindowHandles(); // get all window handles
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()){
		    subWindowHandler = iterator.next();
		}
		driver.switchTo().window(subWindowHandler); // switch to new tab/window window
	
		// Now you are in the new window/tab, perform necessary actions here
		if (driver.getCurrentUrl() != null) {
			logger.log(LogStatus.PASS, driver.getCurrentUrl() + " " +  Object + " is correctly displayed");
		} else {
			logger.log(LogStatus.FAIL, driver.getCurrentUrl() + " " + Object + " is incorrectly displayed");
		}
		// closes the new tab/window
		closeBrowser();
	}
	
}