package tests.ui;
import com.aspiration.config.PropertyManager;
import com.aspiration.pages.BasePage;
import com.aspiration.pages.HomePage;
import com.aspiration.pages.SpendAndSavePage;
import com.aspiration.tests.MainTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.lang.reflect.Method;


public class SmokeTests extends MainTest {

	@Test()
	public void UserCanViewProductsAndPricesTest(Method method) {
		BasePage page = new BasePage(driver, log);
		page.goingTo(PropertyManager.getConfigProperties().getProperty("webBase"));
		HomePage homePage = new HomePage(driver, log);
		homePage.acceptCookies();
		SpendAndSavePage spendAndSavePage = homePage.clickOnSpendAndSaveBtn();

		//Verify that as a user, they can view our products and prices by navigating from the home page to
		// our products page via the “Spend & Save” link at the top of the home page
		Assert.assertTrue(spendAndSavePage.verifyPageURL(), "Issue the page does not match with " +
				"expected URL for SpendAndSave page");
	}
}