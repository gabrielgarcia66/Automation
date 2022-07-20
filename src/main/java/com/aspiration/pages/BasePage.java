package com.aspiration.pages;
import com.aspiration.config.ExtentManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;

public class BasePage {
	protected WebDriver driver;
	protected Logger log;
	private final int waitingLoadPage = 20;

	public BasePage(WebDriver driver, Logger log) {
		this.driver = driver;
		this.log = log;
	}

	public void goingTo(String URL) {
		try{
			driver.navigate().to(URL);
		}catch(TimeoutException e){
			log.info("TimeoutException on goingTo method with URL: "+ URL);
		}catch (Exception e){
			log.info("Exception with URL: "+ URL+ " Exception: "+e);
		}
	}

	protected boolean isEleVisible(By locator, long timeOutInSeconds) {
		java.time.Duration timeOut = java.time.Duration.ofSeconds(timeOutInSeconds);
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void scrollMiddleOfElement(WebElement element) {
		try{
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("const elementRect = arguments[0].getBoundingClientRect();const absoluteElementTop = elementRect.top + window.pageYOffset;" +
					"const middle = absoluteElementTop - (window.innerHeight / 2);window.scrollTo(0, middle);", element);

		}catch(Exception exception){
			log.info("Exception is showing on scrollMiddleOfElement method, exception: " +exception);
		}
	}

	protected void click(WebElement element) {
		scrollMiddleOfElement(element);
		try {
			element.click();
		}catch (ElementClickInterceptedException e){
			clickWithJavaScript(element);
		}catch (ElementNotInteractableException e){
			log.info("ElementNotInteractableException on clicking method, try again clicking method");
			clickWithJavaScript(element);
		}catch (Exception e){
			log.info("Exception  on clicking method, try again clicking method");
			clickWithJavaScript(element);
		}

		waitingPageLoad();
	}

	protected WebElement waitingForClickFluentWait(By locator, long timeOut){
		WebElement element = null;
		try {

			java.time.Duration timeOut1 = java.time.Duration.ofSeconds(timeOut);
			java.time.Duration durationInterval = java.time.Duration.ofMillis(250);
			Wait<WebDriver> wait = new FluentWait<>(driver)
					.withTimeout(timeOut1)
					.pollingEvery(durationInterval )
					.ignoring(NoSuchElementException.class);

			element = wait.until(ExpectedConditions.elementToBeClickable(locator));

		}catch (TimeoutException e) {
			log.info("TimeoutException the element is not clickable with locator: "+locator);
			return element;
		}catch (Exception e){
			log.info("Exception when want element wait to be clickable with locator:"+locator + " specific exception: "+e);
			Assert.fail("There is an issue when want to find the element with locator:"+locator+ " specific exception:" +e);
		}

		return element;
	}

	protected void click(By locator) {
		WebElement  element = find(locator);
		waitingForClickFluentWait(locator, 10);
		click(element);
	}

	protected WebElement find(By locator) {
		WebElement element;
		element = waitingElementFW(4,locator, 3);
		return element;
	}

	public WebElement waitingElementFW(int timesToTry, By locator, long timeout){
		WebElement element = null;
		for(int x = 0; x < timesToTry; x++){
			element = waitingPresenceEleFW(locator, timeout);
			if(element != null){
				break;
			}
		}

		if(element == null){
			String message = "Locator: " + locator + " is not found the element was null";
			ExtentManager.info(message);
			Assert.fail(message);
		}

		return element;
	}

	protected WebElement waitingPresenceEleFW(By locator, long timeOut){
		WebElement element = null;
		try {
			java.time.Duration timeOut1 = java.time.Duration.ofSeconds(timeOut);
			java.time.Duration durationInterval = java.time.Duration.ofMillis(250);
			Wait<WebDriver> wait = new FluentWait<>(driver)
					.withTimeout(timeOut1)
					.pollingEvery(durationInterval )
					.ignoring(NoSuchElementException.class);

			element = wait.until(driver -> driver.findElement(locator));

		}catch (TimeoutException e) {
			log.info("TimeoutException the element is not finding with next locator:  "+locator);
			return element;
		}catch (Exception e){
			log.info("Exception the element is not finding with next locator:  "+locator);
			Assert.fail("Exception the element is not finding with next locator:  "+locator+ " and specific exception:  " +e);
		}
		return element;
	}

/*	public void goToSpecificPixel(int toPixels) {
		try{
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("window.scrollTo(0, "+toPixels+")");
		}catch (JavascriptException e){
			log.info("There is an issue with JavascriptException on scrollToSpecificPixel");
		}catch(Exception e){
			log.info("There is an Exception on scrollToSpecificPixel; specific Exception: "+"");
		}
	}*/

	protected void clickWithJavaScript(WebElement element) {
		waitingPageLoad();
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		scrollMiddleOfElement(element);
		try {
			executor.executeScript("arguments[0].click();", element);
		}catch(StaleElementReferenceException e){
			log.info("StaleElementReferenceException when want use clickWithJavaScript method with this element: "+ element);
		}catch (Exception e){
			log.info("Exception when want use clickWithJavaScript method with this element: "+ element+ "Exception: "+ e);
		}
		waitingPageLoad();
	}

	public void waitingPageLoad(){
		ExpectedCondition<Boolean> conditionOfPageLoading = input -> ((JavascriptExecutor)driver).executeScript(
					"return document.readyState").toString().equals("complete");
		try {
			java.time.Duration timeOut = java.time.Duration.ofSeconds(waitingLoadPage);
			WebDriverWait wait = new WebDriverWait(driver, timeOut);
			wait.until(conditionOfPageLoading);
		} catch (Exception e){

		}
	}

/*	public void pageRefresh() {
		try{
			driver.navigate().refresh();
			if(alertIsShowing(1000)){
				driver.switchTo().alert().accept();
			}
		}catch(TimeoutException e){
			log.info("TimeoutException when page is refreshing, exception: "+e);
		}catch (Exception e){
			log.info("Exception when page is refresing, exception: "+e);
		}
	}
	protected boolean alertIsShowing(long timeOutInMilliSeconds) {
		java.time.Duration timeOut = java.time.Duration.ofMillis(timeOutInMilliSeconds);
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		try {
			wait.until(ExpectedConditions.alertIsPresent());
			return true;
		} catch (Exception e) {
			return false;
		}
	}*/
}
