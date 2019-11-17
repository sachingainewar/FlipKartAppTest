package test.FlipKart;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import io.appium.java_client.android.AndroidDriver;

public class CreateDriver extends AppiumServerJava{
	  private static Logger Log = Logger.getLogger(CreateDriver.class);

	private static final ThreadLocal<AndroidDriver> mobileDriver = new ThreadLocal<AndroidDriver>();
	private URL serverUrl = null;
	

	
	/**
	 * Method to create local driver
	 * @param capabilities
	 * @param testBedConfig
	 */
	
	public void createLocalDriver(){
//		AndroidDriver driver = null;
		try {
			serverUrl = new URL(AppiumServerJava.getURL());
			Log.info("createLocalDriver ::: serverUrl:::"+serverUrl);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		try{
			driver = new AndroidDriver(serverUrl, AppiumServerJava.AppiumServerCapabilities());
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			setDriver(driver);
		}catch(Exception ex){
			Log.error("Error while createLocalDriver:::"+ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	/**
	 * Method to get driver
	 * @param capabilities
	 * @param testBedConfig
	 * @return
	 */
	@BeforeSuite
	public AndroidDriver getDriverAndroid(){
//		AndroidDriver driver = null;
		if(mobileDriver.get()==null){
			createLocalDriver();
		}
		return mobileDriver.get();
	}
	
	/**
	 * Method to set driver
	 * @param localDriver
	 */
	public void setDriver(AndroidDriver localDriver){
		mobileDriver.set(localDriver);
	}
	
	/**
	 * Method to get driver
	 * @return
	 */
	public static AndroidDriver getDriver(){
		return driver =mobileDriver.get();
	}
	
}
