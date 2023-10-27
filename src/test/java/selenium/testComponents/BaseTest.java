package selenium.testComponents;

import org.testng.annotations.AfterMethod;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeMethod;

import Selenium.PageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingPage;

	public WebDriver initializeDriver() throws IOException

	{

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//selenium//resources//GlobalData.properties");
		prop.load(fis);
		
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") :prop.getProperty("browser");
		//prop.getProperty("browser");

		if (browserName.contains("chrome")) {
			WebDriverManager.chromedriver().setup();
	
			driver = new ChromeDriver();
			driver.manage().window().setSize(new Dimension(1440,900));//full screen

		} else if (browserName.equalsIgnoreCase("firefox")) {
			//add code
		} else if (browserName.equalsIgnoreCase("edge")) {
			//add code
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;

	}

	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException
	{
		
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	
		
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown()
	{
		driver.close();
	}
}