package neevsystems.PageObjectModelSec19;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import neevsystems.AbstractComponents.AbstractCompnent;

public class LandingPage extends AbstractCompnent{
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement passwordEle;
	
	@FindBy(id="login")
	WebElement submit;
	
	//sec20
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	
	
	public ProductCatalogue loginApplication(String email,String password)
	{
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		submit.click();
		ProductCatalogue productCatalogue1=new ProductCatalogue(driver);
		return productCatalogue1;
	}
	
	//sec 20
	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}

}
