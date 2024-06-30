package neevsystems.stepDefinationSec25;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import neevsystems.PageObjectModelSec19.CartPage;
import neevsystems.PageObjectModelSec19.CheckoutPage;
import neevsystems.PageObjectModelSec19.ConfirmationPage;
import neevsystems.PageObjectModelSec19.LandingPage;
import neevsystems.PageObjectModelSec19.ProductCatalogue;
import neevsystems.TestComponentsSec20.BaseTest;

public class StepDefinationImpl extends BaseTest {
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;
	
	@Given("I landed on Ecommerce Page")
	public void i_landed_on_ecommerce_page() throws IOException {
		landingPage=launchApplication();
	   
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password(String username,String password)
	{
	    productCatalogue=landingPage.loginApplication(username,password);
	}
	@When("^I add product (.+) to Cart$")
	public void I_add_product_to_Cart(String productName) throws InterruptedException
	{
		List<WebElement>products=productCatalogue.getproductList();
		productCatalogue.addProductToCart(productName);
	}
	@When("^Checkout (.+) and submit the order$")
	public void Checkout_and_submit_the_order(String productName)
	{
		CartPage cartPage=productCatalogue.goToCartPage();
		Boolean match=cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage=cartPage.goToCheckOut();
		checkoutPage.selectCountry("india");
	    confirmationPage=checkoutPage.submitOrder();
	}
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_displayed_confirmationpage(String string)
	{
		String confirmMessage = confirmationPage.getconfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
	}
	@Then("{string} message is displayed")
	public void message_is_displayed(String string) {
		Assert.assertEquals(string, landingPage.getErrorMessage());
	}
	
	


}
