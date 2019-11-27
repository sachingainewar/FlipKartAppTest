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
       FlipKartAppTest/src/test/java/test/FlipKart/LocatorType.java
