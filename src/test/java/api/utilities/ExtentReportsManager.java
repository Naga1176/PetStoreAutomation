package api.utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;



public class ExtentReportsManager implements ITestListener
{
	public ExtentSparkReporter sparkreporter;//ui of the report
	public ExtentReports Extent;//populate common info on report
	public ExtentTest Test;//creating test case entries in the report and update
	
	String repName;
	
	public void onStart(ITestContext context)
	{
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//timestamp
		repName = "Test-Report-" + timeStamp +".html";
		
		 sparkreporter = new ExtentSparkReporter(System.getProperty("user.dir")+"\\reportss\\"+repName);//report location
		
		
		sparkreporter.config().setDocumentTitle("Automation Report");//title of report
		sparkreporter.config().setReportName("API tetsing");//name of report
		sparkreporter.config().setTheme(Theme.DARK);//Ui of the report
		
		Extent = new ExtentReports();
		Extent.attachReporter(sparkreporter);//attach ui with spark
		
		Extent.setSystemInfo("Application name", "open cart");
		Extent.setSystemInfo("module", "Admin");
		Extent.setSystemInfo("Environment", "SIT1");
		Extent.setSystemInfo("Tester Name ", "Customers");
		Extent.setSystemInfo("User Name", System.getProperty("user.name"));
		
		String os =context.getCurrentXmlTest().getParameter("os");
		Extent.setSystemInfo("Operating System",os);
		
		String browser =context.getCurrentXmlTest().getParameter("browser");
		Extent.setSystemInfo("Browser",browser);
		
		List<String> includegroups =context.getCurrentXmlTest().getIncludedGroups();
		if(!includegroups.isEmpty())
			{
			Extent.setSystemInfo("Groups",includegroups.toString());
			}
		
		
		
	}
	
	public void onTestSuccess(ITestResult result)
	{
		Test = Extent.createTest(result.getTestClass().getName());//creating new entry into the report
		Test.assignCategory(result.getMethod().getGroups());
		Test.log(Status.PASS, "Test Case passed is :"+result.getName());
		
	
	}
	public void onTestFailure(ITestResult result)
	{
		Test = Extent.createTest(result.getName());//creating new entry into the report
		Test.assignCategory(result.getMethod().getGroups());
		
		Test.log(Status.FAIL, "Test Case Failed is :"+result.getName());
		Test.log(Status.FAIL, "Test Case Failed cause is :"+result.getThrowable().getMessage());
		/*try {
			String imgPath =  new BaseClas().captureScreen(result.getName());
			Test.addScreenCaptureFromPath(imgPath);
		//}
		//catch(IOException e1)
		{
			e1.printStackTrace();//it will print warning if it executes
		}*/
		
	
	}
	public void onTestSkipped(ITestResult result)
	{
		Test = Extent.createTest(result.getTestClass().getName());//creating new entry into the report
		Test.assignCategory(result.getMethod().getGroups());
		Test.log(Status.SKIP, "Test Case skipped is :"+result.getName());
		Test.log(Status.INFO, result.getThrowable().getMessage());
	
	}
	public void onFinish(ITestContext context)
	{
		Extent.flush();
		
		String pathOfExtentReport =System.getProperty("user.dir")+"\\reportss\\"+repName;
		
		File extentReport = new File(pathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		}
		catch(IOException e) {
			e.printStackTrace();
			
		}
		
		/*try {
			
			URL url = new URL("file:////"+System.getProperty("user.dir")+"\\reports\\"+repName);
			
			//create email message
			
			ImageHtmlEmail email = new ImageHtmlEmail();
			email.setDataSourceResolver(new DataSourceUrlResolver(url));
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("naga.raj7730@gmail.com","password"));
			email.setSSLOnConnect(true);
			email.setFrom("naga.raj7730@gmail.com");//sender
			email.setSubject("Test Results");
			email.setMsg("Please find attached report..");
			email.addTo("naga.mothuku@gmail.com");
			email.attach(url,"extent report","Please check report..");
			email.send();
		}
		catch(IOException | EmailException e) {
			e.printStackTrace();
		}
		
		*/
		
	}
	

}
