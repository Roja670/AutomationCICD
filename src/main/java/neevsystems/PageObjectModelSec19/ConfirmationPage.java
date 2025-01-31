package neevsystems.PageObjectModelSec19;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import neevsystems.AbstractComponents.AbstractCompnent;

public class ConfirmationPage extends AbstractCompnent {
	
	WebDriver driver;
	public ConfirmationPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	@FindBy(css=".hero-primary")
	WebElement confirmationMessage;
	
	
	public String getconfirmationMessage()
	{
		return confirmationMessage.getText();
	}

}
