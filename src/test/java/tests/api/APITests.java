package tests.api;

import com.aspiration.api.model.People;
import com.aspiration.api.model.PeopleModel;
import com.aspiration.tests.MainTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

public class APITests extends MainTest {

	@Test()
	public void apiValidationsToCheckValuesExist() {
		SoftAssert sAssertAPI = new SoftAssert();
		//Verify that the people endpoint is returning a successful response
		Response reponse = peopleServiceHelper.getPeople();
		Assert.assertNotNull(reponse, "There is not a status code 200 on people endpoint");

		ArrayList<People> arrayListPeople = peopleServiceHelper.getAllPeopleFromAllPages();
		Assert.assertTrue(arrayListPeople.size() > 0, "There is an issue with the request " +
				"never we got any valid request");

		//Verify that the total number of people where height is greater than
		//200 matches the expected count (10 at the time this was assigned)
		boolean meetCondition = peopleServiceHelper.validatePeopleWithHeightIsGreaterThan(10,
				200,
				arrayListPeople);
		sAssertAPI.assertTrue(meetCondition, "There are not 10 or more people with height greater than 200");

		//Verify that the 10 individuals returned are:
		//Darth Vader, Chewbacca, Roos Tarpals, Rugor Nass, Yarael Poof, Lama Su, Tuan Wu, Grievous, Tarfful, Tion Medon
		String[] peopleToFind = {"Darth Vader", "Chewbacca", "Roos Tarpals", "Rugor Nass", "Yarael Poof", "Lama Su",
				"Tuan Wu", "Grievous", "Tarfful", "Tion Medon"};
		meetCondition = peopleServiceHelper.validateAllPeopleNamesExist(peopleToFind, arrayListPeople);
		sAssertAPI.assertTrue(meetCondition, "The following expected names are not present on People API end point"
				+ Arrays.toString(peopleToFind));

		//Verify that the total number of people checked equals the expected count (82 at the time)
		PeopleModel people = peopleServiceHelper.getPeopleByPage("1");
		int expectedPeopleCount = people.getCount();
		Assert.assertEquals(expectedPeopleCount, arrayListPeople.size(), "The number of people checked does not " +
				"do match with the expected count");
		sAssertAPI.assertAll();
	}
}
