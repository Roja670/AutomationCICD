package neevsystems.resourcesSec20;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


//sec 22
public class ExtentReporterNG {
	
	public static ExtentReports getObjectReport()
	{

		//Threre are two classes using to get extent reports
		//ExtentReports     //ExtentSparkReport
		String path=System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter reporter= new ExtentSparkReporter(path);
		//to change the name of the report
		reporter.config().setReportName("Web Automation Results");
		//To change the title
		reporter.config().setDocumentTitle("Test Results");
		
	    ExtentReports extent=new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Rojalin Nanda");
		//extent.createTest(path);
		return extent;
	}

}
