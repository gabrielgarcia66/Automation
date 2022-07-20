package com.aspiration.pages;

import com.aspiration.api.model.People;
import com.aspiration.config.ExtentManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends BasePage {
	protected WebDriver driver;
	protected Logger log;
	protected By checkPointPage = By.xpath("//h1[@data-testid='hero-title']");
	protected By spendAndSaveBtn = By.xpath("//a[@data-testid='nav-bar-spendandsave']");
	ArrayList<People> peopleArray;

	public HomePage(WebDriver driver, Logger log) {
		super(driver, log);
		this.driver = driver;
		this.log = log;

		if(isEleVisible(checkPointPage, 20)){
			ExtentManager.info("HomePage page is loaded correctly.");
		}else{
			String message = "Issue loading HomePage";
			log.info(message);
		}
	}

	public SpendAndSavePage clickOnSpendAndSaveBtn(){
		click(spendAndSaveBtn);
		return new SpendAndSavePage(driver, log);
	}

	public void acceptCookies (){
		if (isEleVisible(By.id("onetrust-button-group-parent"), 20)){
			click(By.id("onetrust-accept-btn-handler"));
		}
	}

	public ArrayList<People> peopleProperties() {
		peopleArray = new ArrayList<>();
		List<String> films = new ArrayList<>();
		List<String> species = new ArrayList<>();
		List<String> vehicles = new ArrayList<>();
		List<String> starShips = new ArrayList<>();

		films.add("Chucky");
		films.add("Titanic");
		films.add("Avengers");

		species.add("Lion");
		species.add("Tiger");
		species.add("Wolf");

		vehicles.add("Corvette Stingray");
		vehicles.add("Cadillac CT4-V");
		vehicles.add("Aston Martin Rapide E");

		starShips.add("N1");
		starShips.add("Starship");
		starShips.add("Long March 9");

		peopleArray.add(new People("John Johnson", "177 cm", "75 kg", "blond", "fair",
				"green", "06-08-1990", "male", "", films, species, vehicles, starShips,
				"2022-02-10T13:10:51.357000Z", "2022-02-20T21:17:50.309000Z", "https://www.aspiration.com/",
				"?Page=1"));


		films = new ArrayList<>();
		films.add("The black phone");
		films.add("Jurassic World");
		films.add("Lightyear");

		species = new ArrayList<>();
		species.add("Giraffe");
		species.add("Elephant");
		species.add("Snake");

		vehicles = new ArrayList<>();
		vehicles.add("Mustang Shelby GT500");
		vehicles.add("Kia Soul EV");
		vehicles.add("McLaren GT");

		starShips = new ArrayList<>();
		starShips.add("Space Shuttle");
		starShips.add("Falcon Heavy");
		starShips.add("SLS Block 1");

		peopleArray.add(new People("Carl Carlson", "186 cm", "83 kg", "brown", "fair",
				"gray", "08-21-1986", "male", "", films, species, vehicles, starShips,
				"2022-05-10T13:10:51.357000Z", "2022-05-20T21:17:50.309000Z", "https://www.aspiration.com/",
				"?Page=2"));


		films = new ArrayList<>();
		films.add("Resident Evil");
		films.add("The Walking Dead");
		films.add("Notebook");

		species = new ArrayList<>();
		species.add("Crocodile");
		species.add("Tortoise");
		species.add("Rabbit");

		vehicles = new ArrayList<>();
		vehicles.add("Porsche Taycan");
		vehicles.add("Tesla Roadster");
		vehicles.add("Toyota GR Supra");

		starShips = new ArrayList<>();
		starShips.add("Saturn V");
		starShips.add("Energia");
		starShips.add("Yenisei");

		peopleArray.add(new People("Bryan Brown", "190 cm", "100 kg", "Gray", "fair",
				"brown", "02-30-1975", "male", "", films, species, vehicles, starShips,
				"2022-06-10T13:10:51.357000Z", "2022-06-20T21:17:50.309000Z", "https://www.aspiration.com/",
				"?Page=3"));


		return peopleArray;
	}

}
