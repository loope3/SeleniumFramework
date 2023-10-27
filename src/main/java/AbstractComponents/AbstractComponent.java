package AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Selenium.PageObjects.CartPage;
import Selenium.PageObjects.OrderPage;

public class AbstractComponent {
	
	WebDriver driver;
	
	@FindBy(css ="[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy(css ="[routerlink*='myorders']")
	WebElement orderHeader;

	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));

	}
	
	public void waitForWebElementToAppear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(findBy));

	}
	
	public CartPage goToCartPage() {
		cartHeader.click();
		return new CartPage(driver);
	}
	
	public OrderPage goToOrdersPage() {
		orderHeader.click();
		return new OrderPage(driver);
	}
	
	public void waitForElementToDisappear(WebElement ele) throws InterruptedException {
		Thread.sleep(1000);
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//		wait.until(ExpectedConditions.invisibilityOf(ele));

	}


}
