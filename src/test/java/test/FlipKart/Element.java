package test.FlipKart;

import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.JavascriptExecutor;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;

/** This class contains element action methods */
public class Element extends CreateDriver{
	  private static Logger Log = Logger.getLogger(Element.class);

	/**
	 * Method to identify element using mobile locator
	 * 
	 * @param locator
	 * @param identifier
	 * @return By reference object
	 */
	public By getMobileLocator(String locator, LocatorType identifier) {
		By by = null;
		try {
			switch (identifier) {
			case xpath:
				by = MobileBy.xpath(locator);
				break;
			case classname:
				by = MobileBy.className(locator);
				break;
			case id:
				by = MobileBy.id(locator);
				break;
			default:
				by = MobileBy.xpath(locator);
				break;
			}
		} catch (Exception e) {
			System.err.println("Failed to identify the locatorType:::" + locator + ". Error :::" + e.getMessage());
			e.printStackTrace();
		}
		if (by == null)
			System.err.println("Failed to identify the locatorType:::" + locator);
		return by;
	}

	/**
	 * Method to send Keys on element with some test data as string
	 * 
	 * @param locator
	 * @param text
	 */
	public boolean sendKeys(String locator, String text) {
		try {
			WebElement element = getElementByLocator(locator);
			element.clear();
			element.sendKeys(text);
			Log.info("Sending text on element having locator:::" + locator + " with text :::" + text);
			return true;
		} catch (Exception e) {
			System.err.println(
					"Failed to send keys to element having locator:::" + locator + " due to :::" + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	
	/**
	 * Method to verify element is seen on page
	 * 
	 * @param locator
	 * @return boolean true if element is present
	 */
	public boolean isElementPresent(String locator) {
		boolean flag = false;
		WebElement element = null;
		try {
			Thread.sleep(5000);
			WebDriverWait wait = new WebDriverWait(driver, 15);
			LocatorType identifier = LocatorType.valueOf(locator.substring(0, locator.indexOf("=")).toLowerCase());
			locator = locator.substring(locator.indexOf("=") + 1, locator.length());

			By by = getMobileLocator(locator, identifier);
			// Wait for the element to be visible
			element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
				Log.info("Element is found and is displayed using locator :::" + locator);
				flag = true;
		} catch (org.openqa.selenium.ElementNotVisibleException ex) {
			flag = false;
			Log.info(
					"Failed to find element using locator :::" + locator + ". Error message:::" + ex.getMessage());
			ex.printStackTrace();
		} catch (org.openqa.selenium.NoSuchElementException ex) {
			flag = false;
			Log.info(
					"Failed to find element using locator :::" + locator + ". Error message:::" + ex.getMessage());
			ex.printStackTrace();
		} catch (org.openqa.selenium.TimeoutException ex) {
			flag = false;
			Log.error(
					"Failed to find element using locator :::" + locator + ". Error message:::" + ex.getMessage());
//			ex.printStackTrace();
		} catch (java.lang.Exception ex) {
			flag = false;
			Log.info(
					"Failed to find element using locator :::" + locator + ". Error message:::" + ex.getMessage());
			ex.printStackTrace();
		}
		if (element == null) {
			flag = false;
			Log.info("Failed to find element as Element is null using locator :::" + locator);
		} else
			flag = true;
		return flag;
	}


	
	/**
	 * Method to scroll down the screen using number of scroll
	 * 
	 * @param numberOfScroll
	 */
	public void screenScrollDown( ) {
		Log.info("Scrolling Down the screen");
		try {
			TouchAction ts = new TouchAction(driver);
			Dimension windowSize = driver.manage().window().getSize();  // get the hight of window
			int starty = (int) (windowSize.height * 0.80);
			int endy = (int) (windowSize.height * 0.20);
			int startx = windowSize.width / 2;
			driver.swipe(startx, starty, startx, endy, 2000);
		} catch (Exception e) {
			System.err.println("Failed to scroll down the device screen due to :::" + e.getMessage());
			e.printStackTrace();
		}
	}


	public void scrollup() {
		
		try {
			TouchAction ts = new TouchAction(driver);
			Dimension windowSize = driver.manage().window().getSize();  
			int  endY = (int) (windowSize.height * 0.70);
			 int startY = (int) (windowSize.height * 0.30);
			 int startX = (windowSize.width / 2);
			 new TouchAction(driver)
			         .press(startX, startY)
			         .waitAction()
			         .moveTo(startX, endY)
			         .release()
			         .perform();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * This method returns the Element using explicit wait
	 * 
	 * @param locator
	 * @return element
	 */
	public WebElement getElementByLocator(String locator) {
		WebElement element = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			LocatorType identifier = LocatorType.valueOf(locator.substring(0, locator.indexOf("=")).toLowerCase());
			locator = locator.substring(locator.indexOf("=") + 1, locator.length());

			By by = getMobileLocator(locator, identifier);
			// Wait for the element to be visible
			element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
			
			Log.info("Element found ::presenceOfElementLocated::with locator : " + locator);

		} catch (TimeoutException e) {
			Log.info(" Failed to find element even on Explicit wait using locator - " + locator
					+ " Error Message:" + e.getMessage());
		
		} catch (Exception e) {
			Log.info("Failed to find element even on Explicit wait using locator - " + locator
					+ " Error Message :" + e.getMessage());
			e.printStackTrace();
		}
		if (element == null)
			Log.info("Failed to find element as element is NULL  with locator : " + locator);
		return element;
	}

//	/**
//	 * Method to get element text
//	 * 
//	 * @param locator
//	 * @return text of element
//	 */
//	public String getElementText(String locator) {
//		String text = "";
//		try {
//			WebElement element = getElementByLocator(locator);
//			text = element.getText().trim();
//			Log.info("Actual Element text::: " + text);
//		} catch (Exception ex) {
//			System.err.println("Exception occured while getting element text for:::" + locator);
//			System.err.println("Error :- " + ex.getMessage());
//			ex.printStackTrace();
//		}
//		return getUTF8Text(text);
//	}

	/**
	 * Method to convert Actual text to UTF8
	 * 
	 * @param text
	 *            of element
	 * @return UTF8 encoded text
	 */
	public String getUTF8Text(String text) {
		String retText = "";
		try {
			retText = new String(text.getBytes("UTF8"), "UTF8");
			Log.info("UTF8 Encoded text::: " + retText);
		} catch (Exception ex) {
			Log.info("Exception occured while encoding text");
			Log.info("Error :- " + ex.getMessage());
			ex.printStackTrace();
		}
		return retText;
	}

	public boolean click(String locator) {
		try {
			WebElement mapElement = getElementByLocator(locator);
			if (mapElement != null) {
				LocatorType identifier = LocatorType.valueOf(locator.substring(0, locator.indexOf("=")).toLowerCase());
				locator = locator.substring(locator.indexOf("=") + 1, locator.length());
				By by = getMobileLocator(locator, identifier);
				mapElement.click();
				Thread.sleep(1000);
				Log.info("Clicked on Element having locator:::" + locator);
				return true;
			} else {
				Log.info("Failed to click on element as element is NULL ::: Locator:::" + locator);
				return false;
			}
		} catch (Exception e) {
			Log.info("Unable to click on element :::" + locator);
			e.printStackTrace();
		}
		return false;
	}
}