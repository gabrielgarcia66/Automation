package com.aspiration.pages;

import com.aspiration.config.ExtentManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SpendAndSavePage extends BasePage{
	protected WebDriver driver;
	protected Logger log;

	protected final String URL = "https://www.aspiration.com/spendandsave";
	protected By checkPointPage = By.xpath("//h1[contains(.,'The account built for the planet')]");

	public SpendAndSavePage(WebDriver driver, Logger log) {
		super(driver, log);
		this.driver = driver;
		this.log = log;

		if(isEleVisible(checkPointPage, 20)){
			ExtentManager.info("SpendAndSavePage is loaded correctly.");
		}else{
			String message = "Issue loading SpendAndSavePage.";
			log.info(message);
		}
	}
	public boolean verifyPageURL(){
		if(driver.getCurrentUrl().contains(URL)){
			return true;
		}else{
			return false;
		}
	}
}
