# FlipKartAppTest
## 1. Log4j configuration : to get logs on -console ,txt, html format

  Project is configured with log4j for debug logs. 
  How to configure project with log4j property follow the link https://sachingainewar.blogspot.com/2019/11/log4j-implementation-to- generates-logs.html




## 2. Project is configured with reporting system "Allure report"
http://sachingainewar.blogspot.com/2019/11/allure-report-system-configuration-with.html




## 3.  driver factory:
     src.test.java.test.FlipKart.CreateDriver.java
     is an example for creating the private instance of driver with ThreadLocal class 


## 4.  desire capabilities:
    src/test/java/test/FlipKart/AppiumServerJava.java is an example for appium configuration 
    


## 5. testNG:  Add testNG dependancy in pom.xml 
   <!-- https://mvnrepository.com/artifact/org.testng/testng -->
		<dependency>
			<groupId>org.testng</groupId>
			<artifactId>testng</artifactId>
			<version>6.14.3</version>
			<scope>compile</scope>
		</dependency>
        
        
## 6. Maven -deploy:


## 7. Challenges:

   ### 7.1. Pop-up handling : 
    
```/Used the logic of "Goto the window by using tab and click on Ok button"

      Function " alertHandle() " -  in Class "src/test/java/test/FlipKart/Select.java"
  ```
  
   ### 7.2.   Handle click on Button whose clickable=‘False’
    
    //handle automate React Native application & I want to click on Button which does not have any Resource ID and content-desc and button is clickable=‘False’
    
    new TouchAction((MobileDriver) driver).press(618,1791).waitAction().moveTo(1059,1896).release().perform();
    
    
  ### 7.3.  Element identify  with concaniating xpath: 
    xpath=//android.view.ViewGroup//android.widget.ImageButton[@content-desc='Back Button']
    
  ###  7.4  Separated enum class for identification of locators' 
    ```   FlipKartAppTest/src/test/java/test/FlipKart/LocatorType.java
       //follow the formate of locators like "xpath=//*[@id='abc'], class="abc", id="asa",etc ". So first separate string by (=).
       	public By getLocator(String locator, LocatorType xpt) {
		By by = null;
			switch (xpt) {
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
		return by;
	}
	// finally use the By class to get the element 
	By by = getMobileLocator(locator, identifier);
	element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
       ```
      
  ### 7.5 Scroll on all directions 
    ``` public static void swipe(MobileDriver driver, DIRECTION direction, long duration) {
    Dimension size = driver.manage().window().getSize();

    int startX = 0;
    int endX = 0;
    int startY = 0;
    int endY = 0;

    switch (direction) {
        case RIGHT:
            startY = (int) (size.height / 2);
            startX = (int) (size.width * 0.90);
            endX = (int) (size.width * 0.05);
            new TouchAction(driver)
                    .press(startX, startY)
                    .waitAction(Duration.ofMillis(duration))
                    .moveTo(endX, startY)
                    .release()
                    .perform();
            break;

        case LEFT:
            startY = (int) (size.height / 2);
            startX = (int) (size.width * 0.05);
            endX = (int) (size.width * 0.90);
            new TouchAction(driver)
                    .press(startX, startY)
                    .waitAction(Duration.ofMillis(duration))
                    .moveTo(endX, startY)
                    .release()
                    .perform();

            break;

        case UP:
            endY = (int) (size.height * 0.70);
            startY = (int) (size.height * 0.30);
            startX = (size.width / 2);
            new TouchAction(driver)
                    .press(startX, startY)
                    .waitAction(Duration.ofMillis(duration))
                    .moveTo(endX, startY)
                    .release()
                    .perform();
            break;


        case DOWN:
            startY = (int) (size.height * 0.70);
            endY = (int) (size.height * 0.30);
            startX = (size.width / 2);
            new TouchAction(driver)
                    .press(startX, startY)
                    .waitAction(Duration.ofMillis(duration))
                    .moveTo(startX, endY)
                    .release()
                    .perform();

            break;

    }
} ```

