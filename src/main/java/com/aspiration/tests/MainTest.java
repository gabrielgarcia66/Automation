package com.aspiration.tests;

import com.aspiration.api.helper.PeopleServiceHelper;
import com.aspiration.config.*;
import com.beust.jcommander.Parameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Properties;

@Listeners({com.aspiration.config.TestListener.class})
public class MainTest {
	protected WebDriver driver;
	protected Logger log;
	protected String fileName;
	protected String suiteName;

	protected Properties config = new Properties();

	protected PeopleServiceHelper peopleServiceHelper;

	@BeforeMethod(alwaysRun = true)
	public void setUp(ITestContext context) {
		String testName = context.getCurrentXmlTest().getName();
		log = (Logger) LogManager.getLogger(testName);
		setConfigFile();
		if(!context.getSuite().getName().contains("APITestSuite")) {
			BrowserDriver bd = new BrowserDriver(log);
			driver = bd.createDriver();
		}

		this.suiteName = context.getSuite().getName();
		peopleServiceHelper = new PeopleServiceHelper();

	}

	@AfterMethod(alwaysRun = true)
	public void tearDown(Method method, ITestResult testResult) {
			if (DriverManager.getDriver() != null) {
				// Close browser
				try {
					if(driver != null) {
						DriverManager.getDriver().quit();
						driver.quit();
						log.info("Driver quited");
					}
				} catch (Exception e) {
					log.info("Exception; " + e);
				}
			}
	}

	@BeforeSuite(alwaysRun = true)
	public void BeforeSuite(ITestContext context) {
		Date d = new Date();
		String documentTitle = "<-+ Aspiration Test +->";
		fileName = "AspirationTest_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";
		ExtentManager.createInstance(System.getProperty("user.dir") + "/target/" + fileName, documentTitle);
	}

	public void setConfigFile() {
		if (config != null) {
			configCreation();
		}else{
			config = new Properties();
			configCreation();
		}
	}

	public void configCreation() {
		try {
			config.load(getClass().getResourceAsStream("/prop/configuration.properties"));
			PropertyManager.setConfigProperties(config);
			log.info("Properties loaded!");
		} catch (IOException e) {
			log.error("IOException on configCreation method, Exception:  " + e);
		}
	}

	public void info(String message) {
		log.info(message);
		TestListener.testR.get().info("<b>" + "<font color=" + "black>" + message + "." + "" +
				" </font>" + "</b>");
	}
}