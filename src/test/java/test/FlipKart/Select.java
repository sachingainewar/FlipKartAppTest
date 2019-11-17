package test.FlipKart;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Sleeper;

import com.google.common.collect.ImmutableMap;
import com.google.errorprone.annotations.Immutable;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.remote.HideKeyboardStrategy;

public class Select extends Element {
	ReadExcel getTestData = new ReadExcel();
	  private static Logger Log = Logger.getLogger(Select.class);

	public boolean HomePage() {
		try {
			Thread.sleep(4000);
			Log.info("Verifying home ");
			isElementPresent("xpath=//android.widget.RelativeLayout[@index='1']");

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.info("********Welcome To Home Page of Flipkart ************");
		return true;
	}

	
	public boolean checkOutProcess() {
		boolean flag = false;
		try {
			if(gotoCart()) {
				Thread.sleep(6000);
				isElementPresent("xpath=//android.view.ViewGroup//android.view.ViewGroup[0]");
					Log.info("placing  order     of all products ");// handle automate React Native application & I want to click on Button which does not have any Resource ID and content-desc and button is clickable=‘False’
				new TouchAction((MobileDriver) driver).press(618,1791).waitAction().moveTo(1059,1896).release().perform();
				Thread.sleep(5000);
				Log.info("From Order page continued to Delivery address page ");
				click("xpath=//*[@text='CONTINUE']");
				Thread.sleep(2000);
				if(isElementPresent("xpath=//android.view.View[@text='Continue checkout with other items?']")) {
					click("xpath=//*[@text='YES, CONTINUE']");
					Log.info("Navigate to payment");
					Thread.sleep(5000);
					if(isElementPresent("xpath=//*[@text='Payments']")) {
					Log.info("navigate to previous page ");
					click("xpath=//android.view.ViewGroup//android.widget.ImageButton[@content-desc='Back Button']");
					Thread.sleep(2000);
					}
					Log.info("navigate to previous page ");
					click("xpath=//android.view.ViewGroup//android.widget.ImageButton[@content-desc='Back Button']");
					
				}
				if(flag=isElementPresent("xpath=//android.widget.EditText[@resource-id='pincode']")) 
				{
				deliveryAddress();
				}
				Log.info("CheckOut is complated successfully");
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	
	private boolean deliveryAddress() {
		try {
			Thread.sleep(3000);
			Log.info("Entering all the address details...");
			hidekeyBoard();
			sendKeys("xpath=//android.widget.EditText[@resource-id='pincode']", "440009");
			Thread.sleep(100);
			sendKeys("xpath=//android.widget.EditText[@resource-id='addressLine1']", "test ");
			Thread.sleep(100);
			sendKeys("xpath=//android.widget.EditText[@resource-id='addressLine2']", "Bhande plot, umrer road");
			scrollup();
			Thread.sleep(200);
			sendKeys("xpath=//android.widget.EditText[@resource-id='city']", "Nagpur");
			Thread.sleep(100);
			sendKeys("xpath=//android.widget.EditText[@resource-id='state']", "Maharashtra");
			Thread.sleep(100);
			sendKeys("xpath=//android.widget.EditText[@resource-id='name']", "sachin");
			Thread.sleep(100);
			sendKeys("xpath=//android.widget.EditText[@resource-id='phone']", "9665040076");
			scrollup();
			Log.info("And Save the address information and continued to Payment page");
			click("xpath=//*[@text='SAVE']");
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isElementPresent("xpath=//*[@text='Edit details']");
	}
	private boolean gotoCart() {
		try {
			Thread.sleep(500);
			Log.info("navigate to cart page form home page ");
			click("xpath=//android.widget.RelativeLayout[@resource-id='com.flipkart.android:id/cart_icon']");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}///isElementPresent("xpath=//*[@text='Place Order']")
		return true;
	}
	
	
	public boolean addProductToCart(String prod) {
		try {
			Thread.sleep(2000);
			Log.info("Adding products to cart by scrolling drown the page ");
			screenScrollDown();
			Thread.sleep(2000);  
			Log.info("select product to add into cart");
//			selectProduct("xpath=//android.view.ViewGroup[@index='0']");
			if(!prod.contains("shoes")) {
				selectProduct("xpath=//android.view.ViewGroup[@index='0']");
			}else {
				selectShoe("xpath=//android.widget.ImageView[@index='0']");
			}
			Thread.sleep(2500);
			Log.info("Click on  'ADD TO CART' button ");
			click("xpath=//android.widget.TextView[@text='ADD TO CART']");
			Thread.sleep(3000);
			if(isElementPresent("xpath=//*[@text='SKIP & GO TO CART']")) {
				skipGotoCart();
			}
			else if (isElementPresent("xpath=//*[@text='Select Size- UK/India']")) {
				Log.info("Selecting faverate shoe of size 7 ");
				click("xpath=//*[@text='7']");
				Thread.sleep(500);
				Log.info("navigate to cart page ");
				click("xpath=//*[@text='CONTINUE']");
				goToCartButton();
			}
			else {
				goToCartButton();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	
	public boolean goToCartButton() {
		try {
			Thread.sleep(1000);
			Log.info("navigate to cart page by clicking on GO TO CART button");
			 click("xpath=//*[@text='GO TO CART']");
			Thread.sleep(2000);
			Log.info("Back to home page");
			backHomeFromCart();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;	
	}
	public boolean skipGotoCart() {
		try {
			Thread.sleep(1000);
			Log.info("navigate to cart page by clicking on SKIP & GO TO CART button");
			 click("xpath=//*[@text='SKIP & GO TO CART']");
			Thread.sleep(2000);
			backHomeFromCart();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;	
	}
	
	public void alertHandle() {
		try {
		Log.info("Alter pop is displayed and handled correctly");
		// Navigate to 1st item
		driver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
		Thread.sleep(500);
		// Click the current item
		driver.pressKeyCode(AndroidKeyCode.ENTER);
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public boolean VerifyCart() {
		try {
			Thread.sleep(2000);
			click("xpath=//android.widget.ImageView[@resource-id='com.flipkart.android:id/cart_bg_icon']");
			Thread.sleep(1000);
			if (isElementPresent("xpath=//android.widget.TextView[@text='Your cart is empty!']")) {
				/// verify count of porduct
				Log.info("Verified Product is added to cart ");
			}
			Thread.sleep(2000);
			backHomeFromCart();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	public String getProductList() {
			String prodlist = null;
		try {
			Log.info("collect all the items form excel sheet ");
			 prodlist = getTestData.getColVal("Items");
		} catch (InvalidFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prodlist ;	
	}

	public boolean searchForProduct(String product) {
		try {
		Thread.sleep(5000);
		Log.info("search for product  ");
		click("xpath=//android.widget.LinearLayout[@resource-id='com.flipkart.android:id/search_widget']");
		Thread.sleep(500);
		sendKeys("xpath=//android.widget.EditText[@resource-id='com.flipkart.android:id/search_autoCompleteTextView']", product);
		Thread.sleep(600);
		click("xpath=//android.widget.TextView[@resource-id='com.flipkart.android:id/txt_title']");
		Thread.sleep(3000);
		alertHandle();	
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// isElementPresent("xpath=//android.widget.ListView[@index='0']");
		return true;
	}

	public boolean selectShoe(String produvt) {
		try {	Thread.sleep(2000);
			Log.info("Selecting faverate shoes ");
			return	click(produvt);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
		
	}
			

	public boolean selectProduct(String produvt) {
		Log.info("choosing the  product");
		return click(produvt);
	}

	
	public void backHomeFromCart() {
		try {
			Thread.sleep(1000);
			Log.info("navigate to previous page ");
			click("xpath=//android.view.ViewGroup//android.widget.ImageButton[@content-desc='Back Button']");
			screenScrollDown( );
			scrollup();
			Thread.sleep(1000);
			Log.info("Check if are on home page");
			if (isElementPresent("xpath=//android.widget.ImageButton[@content-desc='Back Button']")) {
				Thread.sleep(500);
				Log.info("since we are not home page again so back to previous page");
				backHomeFromCart();
			}
			Thread.sleep(1000);
			click("xpath=//android.widget.ImageView[@resource-id='com.flipkart.android:id/back_icon']");
			Thread.sleep(1000);
			HomePage() ;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	

	public boolean DeleteProductFromCart() {
		boolean flag=false;
		try {
			Thread.sleep(2000);
			Log.info("back to cart from page from address ");
//			click("xpath=//android.view.ViewGroup//android.widget.ImageButton[@content-desc='Back Button']");
			Thread.sleep(1000);
			makeCartEmpty();
			flag=true;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	
		private void makeCartEmpty() {
			Log.info("check if  products are present  in cart");
			if(isElementPresent("xpath=//android.view.ViewGroup/android.view.ViewGroup[@index='6']")){
				try {
					Log.info("removing product from cart page ");
					click("xpath=//android.view.ViewGroup/android.view.ViewGroup[@index='6']");
					Thread.sleep(2000);
					Log.info("Confirm remove the item from cart");
					click("xpath=//android.view.ViewGroup[@index='3']");
					Thread.sleep(5000);
					makeCartEmpty();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	  
		
		public boolean logOut() {
			boolean flag=false;
//			if(isElementPresent("xpath=//*[@text='Your cart is empty! ']"))
			{
//				click("xpath=//android.view.ViewGroup//android.widget.ImageButton[@content-desc='Back Button']");
				gotoAccountPage();
				flag=true;
				driver.closeApp();
			}
			return flag;
		}
		
		private void gotoAccountPage() {
			try {
				Thread.sleep(1000);
				Log.info("Navigate to Account page to get log out ");
				Log.info("click on menu from home page ");
				click("xpath=//android.widget.ImageButton[@content-desc='Drawer']");
				Thread.sleep(2500);
				Log.info("Scroll down page till MyAccount link");
				screenScrollDown();
				click("xpath=//android.widget.RelativeLayout/android.widget.TextView[@text='My Account']");
				Thread.sleep(3000);
				Log.info("Scroll down page till log out link");
				screenScrollDown();
				Thread.sleep(1000);
				Log.info("Click on LOG OUT the link");
				click("xpath=//android.view.View[@resource-id='fk-cp-accounts']/android.view.View[@index='6']");
				Thread.sleep(2500);
				Log.info("confirmation of log out ");
				click ("xpath=//android.widget.Button[@text='YES']");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	public void hidekeyBoard() {
		try {
			Log.info("Hide the key board ");
			Thread.sleep(1000);
			driver.hideKeyboard();
			Thread.sleep(800);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	public boolean login() {
		boolean flag = false;
		try {
			Thread.sleep(1000);
			alertHandle();
			String user = getTestData.getColVal("Email");
			click("xpath=//android.widget.TextView[@text='Use Email-ID']");
			Thread.sleep(4000);
//			isElementPresent("xpath=//android.widget.EditText[@text='Email ID']");
			sendKeys("xpath=//android.widget.EditText[@text='Email ID']", user);
			Thread.sleep(600);
			click("xpath=//android.widget.TextView[@text='Continue']");
			flag = enterPassword() ;
			Thread.sleep(2000);
		} catch (InterruptedException | InvalidFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	private boolean enterPassword() {
			boolean flag=false;
			try {
			Thread.sleep(1000);
			String pwd = getTestData.getColVal("Password");		
			Thread.sleep(4000);
			sendKeys("xpath=//android.widget.EditText[@text='Password']", pwd);
			Thread.sleep(600);
			flag=click("xpath=//android.widget.TextView[@text='Continue']");
			} catch (InterruptedException |InvalidFormatException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return flag;
		}


	private boolean loginWithPhone() {
		boolean flag = false;
		try {
			Thread.sleep(700);
			isElementPresent("xpath=//android.widget.EditText[@resource-id='phone_input']");
			sendKeys("xpath=//android.widget.EditText[@resource-id='phone_input']", "68184905465");
			Thread.sleep(500);
			click("xpath=//android.widget.TextView[@text='Send OTP']");
			Thread.sleep(3000);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
}
