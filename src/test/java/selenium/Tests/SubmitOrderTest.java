package selenium.Tests;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Selenium.PageObjects.CartPage;
import Selenium.PageObjects.CheckoutPage;
import Selenium.PageObjects.ConfirmationPage;
import Selenium.PageObjects.OrderPage;
import Selenium.PageObjects.ProductCatalogue;
import selenium.testComponents.BaseTest;

public class SubmitOrderTest extends BaseTest {
	private String productName = "ZARA COAT 3";

	
	@Test(dataProvider="getData")
	public void submitOrder(String email, String password, String productName) throws IOException, InterruptedException {
		ProductCatalogue productCatalogue = landingPage.loginApplication(email, password);

		
//		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();

	
		Boolean match = cartPage.VerifyProductDisplay(productName);	
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		driver.findElement(By.cssSelector(".action__submit")).click();
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		}
	
		@Test(dependsOnMethods= {"submitOrder"})
		public void orderHistoryTest() {
			ProductCatalogue productCatalogue = landingPage.loginApplication("dumbo@yolomail.com", "Password123!");
			OrderPage ordersPage = productCatalogue.goToOrdersPage();
			Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
		}
		
		@DataProvider
		public Object[][] getData() {
			return new Object[][] {{"dumbo@yolomail.com", "Password123!", "ZARA COAT 3"}, {"mark@mall.com", "Password123", "ADIDAS ORIGINAL"}};
		}
		
	}
