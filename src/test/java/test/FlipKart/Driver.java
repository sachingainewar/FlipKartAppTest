package test.FlipKart;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import test.Flipkart.TestListener;



	@Listeners({ TestListener.class})
public class Driver extends Select {
	public static AndroidDriver driver;

	

	@Test (priority=0, description="Launch Flipkart app and logged in user by reading details from excel sheet")
	@Step("Launch FK app and login with the user credential from excel sheet")
	public void driverClass() {
		/***
		  Launch Flipkart app and logged in user by reading details from excel sheet
		  and Navigate to Home page
		 */
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue((login()), "Login with email id funcationlity is failed ");
		softAssert.assertTrue((HomePage()), "Home Page navigation Confirmation funcationlity is failed ");
			softAssert.assertAll();
	}
	
	@Test (priority=1, description="Get all the products from excel sheet and add to cart one by one")
	@Step("Get all the products from excel sheet and add to cart")
	public void productAdd() {
		SoftAssert softAssert = new SoftAssert();
		/*** Once user logged in, Search for first item given in the excel sheet */
	
		/** 1. get all the products from excel sheet */
		String[] product = getProductList().split(",");
		softAssert.assertTrue(product.length > 1, "Retrive products from excel sheet funcationlity is failed ");

		
		/**
		  2. search for Product. Select Nth item from search results and add that to
		  cart (Recommended to select item after scrolling down
		 */
		// **************Adding all three products from excel sheet into cart one by
		// one.
		for (int i = 0; i < product.length; i++) {
			if (searchForProduct(product[i].toString())) {
				try {
					// **************Add a product to the cart. Verify that the added product is in
					// the cart.
					softAssert.assertTrue((addProductToCart(product[i].toString())),
							"Adding product to Cart by page scroll down  funcationlity is failed ");
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		softAssert.assertAll();
	}	
		
	
	
	@Test (priority=3, description="3 items added to cart, Do check out process until address/payments scree")
	@Step("Once all 3 items added to cart, Do check out process until address/payments screen")
	public void checkOut() {
		SoftAssert softAssert = new SoftAssert();
		/**
		  3. Once all 3 items added to cart, Do check out process until
		  address/payments screen
		 */
		softAssert.assertTrue((checkOutProcess()), "Check out process and payments  funcationlity is failed ");
		softAssert.assertAll();
	}
		
	@Test (priority=4, description="remove these items from cart and log out from the Flipkart app")
	@Step("Now remove these items from cart and log out from the Flipkart app")
	public void Logout() {
		SoftAssert softAssert = new SoftAssert();
		/** 4. Now remove these items from cart and log out from the Flipkart app. */
		softAssert.assertTrue((DeleteProductFromCart()), "Remove products and log out funcationlity is failed ");
		
		softAssert.assertTrue((logOut()), " log out funcationlity is failed ");

		softAssert.assertAll();

	}
}
