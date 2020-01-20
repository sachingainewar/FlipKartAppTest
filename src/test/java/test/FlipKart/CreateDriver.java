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
	
	///Code to initialized the Browserstake  
		/**
	 * Method to create driver in Browserstake  
	 * @param capabilities
	 * @param testBedConfig.
	 NOTE:  Do not forgot to add "BrowserStackLocal.exe " in folder.
	 */
	
	static Common getMethod = new Common();
	public static RemoteWebDriver driver;
	static Properties properties = new Properties();
	static int count = 0;
	public static final String USERNAME = "sachin510";
	public static final String AUTOMATE_KEY = properties.getProperty("AuthKey");
	public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
	static Delay delay = new Delay();

	
	public void setConnectionToBroserStack() throws InterruptedException, ExecuteException, IOException {
		// Initializing command line parameters...

		CommandLine cmdLine = new CommandLine("cmd.exe");
		cmdLine.addArgument(
				"C:\\Users\\sgainewar>\\Documents\\new\\9-1-2018\\BrowserStackLocal.exe --key zKpP3Muz6StvsebgqUqm");
		/*
		 * cmdLine.addArgument("--key");
		 * cmdLine.addArgument("zKpP3Muz6StvsebgqUqm");
		 */
		System.out.println(cmdLine);
		DefaultExecutor executor = new DefaultExecutor();
		int exitValue = executor.execute(cmdLine);
		System.out.println(exitValue);
	}

		// To load the property file
	@BeforeSuite
	public void loadPropertyFile() {
		FileInputStream fin = null;
		try {
			fin = new FileInputStream(System.getProperty("user.dir")
					+ "//resources//GlobalElementDetails.properties");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new CommonException("File Not found at the specified path");
		}
		try {
			properties.load(fin);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
	
	
//**************************************LOCAL DRIVER******************	
	
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
