package com.skilldistillery.filmquery.entities;

import java.util.List;
import java.util.Objects;

public class Film {
	
	private int id;
	private String title;
	private String description;
	private Integer releaseYear;
	private int languageId;
	private int rentalDuration;
	private double rentalRate;
	private Integer length;
	private double replacementCost;
	private String rating;
	private String specialFeatures;
	private List<Actor> actors;
	
	
	public Film(int filmId, String title2, String desc, Integer releaseYear2, 
			int langId, int rentDur, double rate,
			int length2, double repCost, String rating2, String features) {
		this.id = filmId;
		this.title = title2;
		this.description = desc;
		this.releaseYear = releaseYear2;
		this.languageId = langId;
		this.rentalDuration = rentDur;
		this.rentalRate = rate;
		this.length = length2;
		this.replacementCost = repCost;
		this.rating = rating2;
		this.specialFeatures = features;
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getReleaseYear() {
		return releaseYear;
	}
	public void setReleaseYear(Integer releaseYear) {
		this.releaseYear = releaseYear;
	}
	public int getLanguageId() {
		return languageId;
	}
	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}
	public int getRentalDuration() {
		return rentalDuration;
	}
	public void setRentalDuration(int rentalDuration) {
		this.rentalDuration = rentalDuration;
	}
	public double getRentalRate() {
		return rentalRate;
	}
	public void setRentalRate(double rentalRate) {
		this.rentalRate = rentalRate;
	}
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
	public double getReplacementCost() {
		return replacementCost;
	}
	public void setReplacementCost(double replacementCost) {
		this.replacementCost = replacementCost;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getSpecialFeatures() {
		return specialFeatures;
	}
	public void setSpecialFeatures(String specialFeatures) {
		this.specialFeatures = specialFeatures;
	}
	public List<Actor> getFilms() {
		return actors;
	}
	public void setFilms(List<Actor> films) {
		this.actors = films;
	}
	@Override
	public String toString() {
		return "Film Id: " + id + ", Title: " + title + "\n" + "Description: " 
				+ description + "\n" + "ReleaseYear: " + releaseYear
				+ ", LanguageId: " + languageId + ", RentalDuration: " + rentalDuration + ", RentalRate: " + rentalRate
				+ ", Length: " + length + "\n" + "ReplacementCost: " + replacementCost + ", Rating: " + rating
				+ "\n" + "SpecialFeatures: " + specialFeatures;
	}
	@Override
	public int hashCode() {
		return Objects.hash(description, id, languageId, length, rating, releaseYear, rentalDuration, rentalRate,
				replacementCost, specialFeatures, title);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		return Objects.equals(description, other.description) && id == other.id && languageId == other.languageId
				&& Objects.equals(length, other.length) && Objects.equals(rating, other.rating)
				&& Objects.equals(releaseYear, other.releaseYear) && rentalDuration == other.rentalDuration
				&& Double.doubleToLongBits(rentalRate) == Double.doubleToLongBits(other.rentalRate)
				&& Double.doubleToLongBits(replacementCost) == Double.doubleToLongBits(other.replacementCost)
				&& Objects.equals(specialFeatures, other.specialFeatures) && Objects.equals(title, other.title);
	}
	
	
	//methods
	
	
	
	
}
