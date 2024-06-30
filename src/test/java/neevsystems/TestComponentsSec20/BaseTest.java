package neevsystems.TestComponentsSec20;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import neevsystems.PageObjectModelSec19.LandingPage;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landingPage;
	
	public WebDriver initializeDriver() throws IOException
	{
		//properties class
		
		Properties prop=new Properties();
		//System.getProperty("user.dir")+
		FileInputStream fis=new FileInputStream("D:\\UdemyCourse\\SeleniumFrameWork\\src\\main\\java\\neevsystems\\resourcesSec20\\GlobalData.properties");
		//->it need inputstream for that we have to create object for input stream
		prop.load(fis);
		
		//for sec 23
		String browserName=System.getProperty("browser")!=null ? System.getProperty("browser") :prop.getProperty("browser");
		//String browserName=prop.getProperty("browser");
		//if(browserName.equalsIgnoreCase("chrome"))
		//{
		
		// System.setProperty("webdriver.chrome.driver","./drivers/ChromeDriver.exe");
		//	ChromeOptions options = new ChromeOptions();
		//	options.addArguments("--remote-allow-origins=*");
		//    driver = new ChromeDriver(options);
			
		//}
		//for head less browser
		if (browserName.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			System.setProperty("webdriver.chrome.driver","./drivers/ChromeDriver.exe");
			//ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			if(browserName.contains("headless")){
			options.addArguments("headless");
			}		
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900));//full screen
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			//firefox
		}
		else if(browserName.equalsIgnoreCase("edge"))
		{
			//edge
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		return driver;
	}
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
	{
		//read json to string
		String jsonContent=FileUtils.readFileToString(new File(filePath),
				StandardCharsets.UTF_8);
		//string to HashMap
		//one dependency Jackson Databind
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String,String>>data=mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){
			
		});
		return data;
	}
	//sec 22
	public String getScreenshot(String testCaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File file=new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
	}
		
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException
	{
		driver=initializeDriver();
	    landingPage=new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
		
	}
	@AfterMethod(alwaysRun=true)
	public void tearDown()
	{
		driver.close();
	}

}
