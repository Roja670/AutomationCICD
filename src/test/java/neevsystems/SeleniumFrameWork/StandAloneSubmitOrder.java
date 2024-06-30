package neevsystems.SeleniumFrameWork;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import neevsystems.PageObjectModelSec19.CartPage;
import neevsystems.PageObjectModelSec19.CheckoutPage;
import neevsystems.PageObjectModelSec19.ConfirmationPage;
import neevsystems.PageObjectModelSec19.LandingPage;
import neevsystems.PageObjectModelSec19.OrderPage;
import neevsystems.PageObjectModelSec19.ProductCatalogue;
import neevsystems.TestComponentsSec20.BaseTest;


//for section 20
public class StandAloneSubmitOrder extends BaseTest {
	String productName="ZARA COAT 3";
	@Test(dataProvider="getData",groups="Purchase")
	//public void submitOrder(String email,String password,String productName) throws IOException, InterruptedException {
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {
		//login
		
		//LandingPage landingPage=launchApplication();
		//url
		//landingPage.goTo();
		//ProductCatalogue productCatalogue=landingPage.loginApplication("nd.rojalizu@gmail.com", "Mnbvcxz@1");
		//sec21
		//ProductCatalogue productCatalogue=landingPage.loginApplication(email, password);
		ProductCatalogue productCatalogue=landingPage.loginApplication(input.get("email"),input.get("password"));
		//Get list of products
		//ProductCatalogue1 productCatalogue1=new ProductCatalogue1(driver);
		List<WebElement>products=productCatalogue.getproductList();
		//add product to cart
		//productCatalogue.addProductToCart(productName);
		productCatalogue.addProductToCart(input.get("product"));
		//click on cart
		CartPage cartPage=productCatalogue.goToCartPage();
		//product name was same as selected product
		//CartPage1 cartPage1=new CartPage1(driver);
		//Boolean match=cartPage.VerifyProductDisplay(productName);
		Boolean match=cartPage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage=cartPage.goToCheckOut();
		checkoutPage.selectCountry("india");
		//ConfirmationPage confirmationPage=checkoutPage.submitOrder();
		//String confirmMessage = confirmationPage.getconfirmationMessage();
		//Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistoryTest()
	{
		//"ZARA COAT 3"
		ProductCatalogue productCatalogue=landingPage.loginApplication("nd.rojalizu@gmail.com", "Mnbvcxz@1");
		OrderPage ordersPage=productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
		
	}
	//sec 21
	@DataProvider
	public Object[][] getData() throws IOException
	{
		//for json file separate create
		List<HashMap<String, String>> data=getJsonDataToMap("D:\\UdemyCourse\\SeleniumFrameWork\\src\\test\\java\\neevsystems\\dataSec21Json\\PurchaseOrder.json");
		//HashMap<String,String>map=new HashMap<String,String>();
		//map.put("email","nd.rojalizu@gmail.com");
		//map.put("password","Mnbvcxz@1");
		//map.put("product","ZARA COAT 3");
		
		//HashMap<String,String>map1=new HashMap<String,String>();
		//map1.put("email","rojalin@gmail.com");
		//map1.put("password","Mnbvcxz@1");
		//map1.put("product","ADIDAS ORIGINAL");
		//return new Object[][] {{"nd.rojalizu@gmail.com","Mnbvcxz@1","ZARA COAT 3"},{"rojalin@gmail.com","Mnbvcxz@1","ADIDAS ORIGINAL"}};
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
		

}
