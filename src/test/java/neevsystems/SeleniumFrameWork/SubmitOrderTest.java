package neevsystems.SeleniumFrameWork;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import neevsystems.PageObjectModelSec19.CartPage;
import neevsystems.PageObjectModelSec19.CheckoutPage;
import neevsystems.PageObjectModelSec19.ConfirmationPage;
import neevsystems.PageObjectModelSec19.LandingPage;
import neevsystems.PageObjectModelSec19.ProductCatalogue;


//Section 19
public class SubmitOrderTest {
	public static void main(String[] args) throws InterruptedException {
		String productName="ZARA COAT 3";
        System.setProperty("webdriver.chrome.driver","./drivers/ChromeDriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		WebDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		
		//login
		LandingPage landingPage=new LandingPage(driver);
		//url
		landingPage.goTo();
		ProductCatalogue productCatalogue=landingPage.loginApplication("nd.rojalizu@gmail.com", "Mnbvcxz@1");
		//Get list of products
		//ProductCatalogue1 productCatalogue1=new ProductCatalogue1(driver);
		List<WebElement>products=productCatalogue.getproductList();
		//add product to cart
		productCatalogue.addProductToCart(productName);
		//click on cart
		CartPage cartPage=productCatalogue.goToCartPage();
		//product name was same as selected product
		//CartPage1 cartPage1=new CartPage1(driver);
		Boolean match=cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage=cartPage.goToCheckOut();
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage=checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.getconfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();	
		
	}
		

}
