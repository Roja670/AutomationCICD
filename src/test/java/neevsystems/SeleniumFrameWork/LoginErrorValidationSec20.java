package neevsystems.SeleniumFrameWork;

import java.io.IOException;

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
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import neevsystems.PageObjectModelSec19.CartPage;
import neevsystems.PageObjectModelSec19.CheckoutPage;
import neevsystems.PageObjectModelSec19.ConfirmationPage;
import neevsystems.PageObjectModelSec19.LandingPage;
import neevsystems.PageObjectModelSec19.ProductCatalogue;
import neevsystems.TestComponentsSec20.BaseTest;


//for section 20
public class LoginErrorValidationSec20 extends BaseTest {
	@Test(groups={"ErrorHandling"},retryAnalyzer=neevsystems.TestComponentsSec20.Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException {
		//login
		String productName="ZARA COAT 3";
		//LandingPage landingPage=launchApplication();
		//url
		//landingPage.goTo();
		landingPage.loginApplication("nd.rojalizu@gmail.com", "Mnbvcx@1");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	}
	@Test
	public void ProductErrorValidation()  throws IOException, InterruptedException
	{
		String productName="ZARA COAT 3";
		ProductCatalogue productCatalogue=landingPage.loginApplication("rojalin@gmail.com", "Mnbvcxz@1");
		List<WebElement>products=productCatalogue.getproductList();
		productCatalogue.addProductToCart(productName);
	    CartPage cartPage=productCatalogue.goToCartPage();
	    
		Boolean match=cartPage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
		
		
		
	}
}