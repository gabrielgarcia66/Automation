package com.aspiration.api.model;

import java.util.List;

public class People {

	private String name;
	private String height;
	private String mass;
	private String hairColor;
	private String skinColor;
	private String eyeColor;
	private String birthYear;
	private String gender;
	private String homeWorld;
	private List<String> films;
	private List<String> species;
	private List<String> vehicles;
	private List<String> starShips;
	private String created;
	private String edited;
	private String url;
	private String pageNumber;

	public People(String name, String height, String mass, String hairColor, String skinColor, String eyeColor, String birthYear,
	              String gender, String homeWorld, List<String> films, List<String> species, List<String> vehicles,
	              List<String> starShips, String created, String edited, String url, String pageNumber){

		this.name = name;
		this.height = height;
		this.mass = mass;
		this.hairColor = hairColor;
		this.skinColor = skinColor;
		this.eyeColor = eyeColor;
		this.birthYear = birthYear;
		this.gender = gender;
		this.homeWorld = homeWorld;
		this.films = films;
		this.species = species;
		this.vehicles = vehicles;
		this.starShips = starShips;
		this.created = created;
		this.edited = edited;
		this.url = url;
		this.pageNumber = pageNumber;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getMass() {
		return mass;
	}

	public void setMass(String mass) {
		this.mass = mass;
	}

	public String getHairColor() {
		return hairColor;
	}

	public void setHairColor(String hairColor) {
		this.hairColor = hairColor;
	}

	public String getSkinColor() {
		return skinColor;
	}

	public void setSkinColor(String skinColor) {
		this.skinColor = skinColor;
	}

	public String getEyeColor() {
		return eyeColor;
	}

	public void setEyeColor(String eyeColor) {
		this.eyeColor = eyeColor;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getHomeWorld() {
		return homeWorld;
	}

	public void setHomeWorld(String homeWorld) {
		this.homeWorld = homeWorld;
	}

	public List<String> getFilms() {
		return films;
	}

	public void setFilms(List<String> films) {
		this.films = films;
	}

	public List<String> getSpecies() {
		return species;
	}

	public void setSpecies(List<String> species) {
		this.species = species;
	}

	public List<String> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<String> vehicles) {
		this.vehicles = vehicles;
	}

	public List<String> getStarShips() {
		return starShips;
	}

	public void setStarShips(List<String> starShips) {
		this.starShips = starShips;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getEdited() {
		return edited;
	}

	public void setEdited(String edited) {
		this.edited = edited;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(String pageNumber) {
		this.pageNumber = pageNumber;
	}

	public String getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(String birthYear) {
		this.birthYear = birthYear;
	}
}
