package neevsystems.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import neevsystems.PageObjectModelSec19.CartPage;
import neevsystems.PageObjectModelSec19.OrderPage;

public class AbstractCompnent {
	WebDriver driver;

	public AbstractCompnent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}

	@FindBy(css = "[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy(css = "[routerlink*='myorders']")
	WebElement orderHeader;


	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	//sec20
	public void waitForWebElementToAppear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	

	public CartPage goToCartPage() 
	{
		cartHeader.click();
		CartPage cartPage=new CartPage(driver);
		return cartPage;
	}
	public OrderPage goToOrdersPage() 
	{
		orderHeader.click();
		OrderPage orderPage=new OrderPage(driver);
		return orderPage;
	}
		

	

	public void waitForElementToDisAppear(WebElement ele) throws InterruptedException {
		Thread.sleep(1000);
		// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		// wait.until(ExpectedConditions.invisibilityOf(ele));
	}

}
