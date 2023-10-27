package selenium.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import Selenium.PageObjects.CartPage;
import Selenium.PageObjects.ProductCatalogue;
import selenium.testComponents.BaseTest;

public class ErrorValidations extends BaseTest {
	
	@Test
	public void loginErrorValidation() throws IOException, InterruptedException {
		String productName = "ZARA COAT 3";
		landingPage.loginApplication("dumbo@yolomil.com", "Passd123!");
		AssertJUnit.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
		
		}
	
	
	@Test
	public void productErrorValidation() throws IOException, InterruptedException {
		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginApplication("dumbo@yolomail.com", "Password123!");

		
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();

	
		Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");	
		Assert.assertFalse(match);
		
		}
		
	}
