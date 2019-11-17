package test.FlipKart;

//import static test.FlipKart.AppiumServerJava.Log;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.apache.commons.cli.CommandLine;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AppiumServerJava {
	private static DesiredCapabilities cap;
	public static AndroidDriver driver;
	public static AppiumServiceBuilder builder = null;
	static AppiumDriverLocalService service = null;
	  private static Logger Log = Logger.getLogger(AppiumServerJava.class);

//	@BeforeSuite
	public static DesiredCapabilities AppiumServerCapabilities() {
		DOMConfigurator.configure(System.getProperty("user.dir") + File.separator + "log4j.xml");
		Log.debug("This is debug message ");
		Log.info("desire capabilities initized");
		File appDir = new File(System.getProperty("user.dir") + "/src/test/java/appStore/");
		File app = new File(appDir, "Flipkart Online Shopping App_v6.17_apkpure.com.apk");
		Log.info(app + "_____");
		cap = new DesiredCapabilities(); // Cabaliliesties for server
		cap.setCapability("noReset", "false");
//		cap.setCapability(MobileCapabilityType.BROWSER_NAME, "Android");
		cap.setCapability(MobileCapabilityType.UDID, "4200cab8de3b747f"); // Give Device ID of your mobile phone
		cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Galaxy On Max");
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
		cap.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.flipkart.android");
		cap.setCapability("appActivity", "com.flipkart.android.SplashActivity");
		builder = new AppiumServiceBuilder().withCapabilities(cap);
		DesiredCapabilities capClient = new DesiredCapabilities(); // Capabilities for client
		return cap;
	}

	public static String getURL() {
		AppiumServerCapabilities();
		service = builder.build();
		Log.info("appium service started ");
		service.start();
		Log.error(service.getUrl().toString());
		return service.getUrl().toString();
	}
}
