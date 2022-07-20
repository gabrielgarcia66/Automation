package com.aspiration.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BrowserDriver {
	private WebDriver driver;
	private Logger log;
	public BrowserDriver(Logger log) {
		this.log = log;
	}
	// Create Driver
	public WebDriver createDriver() {
		log.info("Creating web driver");
		configureDriver();
		return DriverManager.getDriver();
	}

	//Configuring selenium web driver
	public void configureDriver(){
			try {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver(chromeOptions());
				DriverManager.setWebDriver(driver);
				log.info("web driver initialized");
			} catch (WebDriverException e) {
				log.info("WebDriverException; Exception: "+e);
			}catch (Exception e) {
				log.info("Exception: "+e);
			}
	}

	public ChromeOptions chromeOptions(){

		ChromeOptions chromeOptions = new ChromeOptions();
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setBrowserName("chrome");
		capabilities.setPlatform(Platform.WINDOWS);
		chromeOptions.setAcceptInsecureCerts(true);
		chromeOptions.addArguments("--window-size=1440, 900");
		chromeOptions.addArguments("--start-maximized");
		chromeOptions.addArguments("--lang=en_US");
		chromeOptions.merge(capabilities);

		return chromeOptions;
	}
}
