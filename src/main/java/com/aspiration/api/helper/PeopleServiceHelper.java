package com.aspiration.api.helper;

import com.aspiration.api.model.People;
import com.aspiration.api.model.PeopleModel;
import com.aspiration.api.model.ResultModel;
import com.aspiration.config.ExtentManager;
import com.aspiration.config.PropertyManager;
import com.fasterxml.jackson.core.type.TypeReference;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PeopleServiceHelper {
	private static final String API_BASE_URL = PropertyManager.getConfigProperties().getProperty("apiBaseURL");
	private static final String API_BASE_PATH = PropertyManager.getConfigProperties().getProperty("apiBasePath");
	private static final String GET_PEOPLE_ENDPOINT = "/people";
	private static final String GET_PEOPLE_ENDPOINT_BY_PAGE = "/people/?page={number}";

	private ArrayList<People> peopleArrayList = new ArrayList<People>();

	public Response getPeople(){
		Response response = RestAssured.given()
				.baseUri(API_BASE_URL)
				.basePath(API_BASE_PATH)
				.header("Accept", ContentType.JSON.getAcceptHeader())
				.contentType(ContentType.JSON)
				.get(GET_PEOPLE_ENDPOINT)
				.andReturn();

		if (response.getStatusCode() == HttpStatus.SC_OK){
			return response;
		}else{
			ExtentManager.info("Issue when try to get Project verify response info: <br>"+ response.getBody().asPrettyString());
			return null;
		}

	}

	public PeopleModel getPeopleByPage(String pageNumber){
		Response response = RestAssured.given()
				.baseUri(API_BASE_URL)
				.basePath(API_BASE_PATH)
				.header("Accept", ContentType.JSON.getAcceptHeader())
				.contentType(ContentType.JSON)
				.pathParam("number", pageNumber)
				.get(GET_PEOPLE_ENDPOINT_BY_PAGE)
				.andReturn();

		if (response.getStatusCode() == HttpStatus.SC_OK){
			Type type = new TypeReference<PeopleModel>(){}.getType();
			PeopleModel people = response.as(type);
			return people;
		}else{
			return null;
		}
	}

	public ArrayList<People> getAllPeopleFromAllPages(){
		PeopleModel peopleModel;
		int counter = 1;
		do{
			peopleModel = getPeopleByPage(counter+"");
			if(peopleModel == null)
				break;
			fillAllValuesOnPersonObj(peopleModel.getResults());
			counter++;
		} while(peopleModel != null);

		return peopleArrayList;
	}

	public void fillAllValuesOnPersonObj(List<ResultModel> listOfPeople){
		for (int x = 0; x < listOfPeople.size(); x++){
			List<String> films = new ArrayList<>();
			List<String> species = new ArrayList<>();
			List<String> vehicles = new ArrayList<>();
			List<String> starShips = new ArrayList<>();
			System.out.println("People" + x + ": "+listOfPeople.get(x).getName());
			System.out.println("Birthday: "+ listOfPeople.get(x).getBirthYear());

			for (int y = 0; y < listOfPeople.get(x).getFilms().size(); y++){
				films.add(listOfPeople.get(x).getFilms().get(y));
			}
			for (int y = 0; y < listOfPeople.get(x).getSpecies().size(); y++){
				species.add(listOfPeople.get(x).getSpecies().get(y));
			}
			for (int y = 0; y < listOfPeople.get(x).getVehicles().size(); y++){
				vehicles.add(listOfPeople.get(x).getVehicles().get(y));
			}
			for (int y = 0; y < listOfPeople.get(x).getStarships().size(); y++){
				starShips.add(listOfPeople.get(x).getStarships().get(y));
			}

			peopleArrayList.add(new People(listOfPeople.get(x).getName(), listOfPeople.get(x).getHeight(), listOfPeople.get(x).getMass(),
					listOfPeople.get(x).getHairColor(), listOfPeople.get(x).getSkinColor(), listOfPeople.get(x).getEyeColor(),
					listOfPeople.get(x).getBirthYear(), listOfPeople.get(x).getGender(), listOfPeople.get(x).getHomeworld(),
					films, species, vehicles, starShips, listOfPeople.get(x).getCreated(), listOfPeople.get(x).getEdited(),
					listOfPeople.get(x).getUrl(), "1"));
		}
	}

	public boolean validatePeopleWithHeightIsGreaterThan(int expectedCounter,
	                                                  int expectedHeightValue,
	                                                  ArrayList<People> arrayListPeople){

		int counter = 0;
		for(int x = 0; x < arrayListPeople.size(); x++){

			String getHeight = arrayListPeople.get(x).getHeight();
			int iHeight = 0;
			if(!getHeight.contentEquals("unknown")) {
				try {
					iHeight = Integer.parseInt(getHeight);
				} catch (NumberFormatException exception) {
					ExtentManager.info("There is a NumberFormat exception when want to convert the follow value to int: " + iHeight);
				}
				if (iHeight > expectedHeightValue) {
					++counter;
				}
			}
		}

		if(counter >= expectedCounter){
			return true;
		}else{
			return false;
		}
	}

	public boolean validateAllPeopleNamesExist(String [] peopleToFind, ArrayList<People> arrayListPeople){
		boolean found = false;
		int counter = 1;
		int expectedSize = peopleToFind.length;
		for(int x = 0; x < peopleToFind.length; x ++){
			int currenValue = x;
			found = arrayListPeople.stream().anyMatch(name -> name.getName().contains(peopleToFind[currenValue]));
			if(found){
				counter++;
			}
		}

		if(expectedSize == counter){
			return true;
		}else{
			return false;
		}

	}

	public ArrayList<People> getPeopleArrayList() {
		return peopleArrayList;
	}

	public void setPeopleArrayList(ArrayList<People> peopleArrayList) {
		this.peopleArrayList = peopleArrayList;
	}
}
