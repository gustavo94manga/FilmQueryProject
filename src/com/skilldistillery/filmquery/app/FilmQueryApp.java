package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {
  
  DatabaseAccessor db = new DatabaseAccessorObject();

  public static void main(String[] args) {
    FilmQueryApp app = new FilmQueryApp();
    app.test();
//    app.launch();
  }

  private void test() {
//    Film film = db.findFilmById(1);
	  List<Actor> actors;
    actors = db.findActorsByFilmId(1);
    System.out.println(actors);
    
  }

  private void launch() {
    Scanner input = new Scanner(System.in);
    
    startUserInterface(input);
    
    input.close();
  }

  private void startUserInterface(Scanner input) {
	  
	  //MENU GOES HERE!
	  
	  System.out.print("Enter a number to find a film by ID: ");
	  int userInput = input.nextInt();
    Film film;
	
		film = db.findFilmById(userInput);
		if(film == null) {
			System.out.println("Not a valid Entry");
		} else {
		System.out.println(film);
		}
	
  }

}
