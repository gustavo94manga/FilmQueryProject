package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
import java.util.InputMismatchException;
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

		app.launch();
	}

	private void launch() {
		Scanner input = new Scanner(System.in);

		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) {

		boolean running = true;
		int choice;

		do {
			System.out.println("Choose an option from the Menu");
			System.out.println("--------------------------------");
			System.out.println("1. Find a Film by ID");
			System.out.println("2. Find an Actor by their ID");
			System.out.println("3. Find Actors by Film ID");
			System.out.println("4. Search title or description by word search: ");
			System.out.println("5. See Movie Language");
			System.out.println("0. Exit");
			System.out.println("--------------------------------");
			try {
				choice = input.nextInt();
				int userInput;
				String wordInput;

				switch (choice) {

				case 1:

					try {
						System.out.print("Enter a number to find a film by ID: ");
						userInput = input.nextInt();
						Film film;
						film = db.findFilmById(userInput);
						if (film == null) {
							System.out.println("Not a valid Entry" + "\n");
						} else {
							System.out.println(film + "\n");
						}
					} catch (InputMismatchException e1) {
						input.next();
						System.out.println("That was not a valid entry \n");
					}
					break;

				case 2:
					try {
						System.out.print("Enter a number to find an Actor by their ID: ");
						userInput = input.nextInt();
						Actor actor;
						actor = db.findActorById(userInput);
						if (actor == null) {
							System.out.println("Not a valid Entry" + "\n");
						} else {
							System.out.println(actor + "\n");
						}
					} catch (Exception e) {

						input.next();
						System.out.println("That was not a valid entry \n");

					}
					break;

				case 3:

					try {
						List<Actor> actors;
						System.out.print("Enter a number to find Actors by Film ID: ");
						userInput = input.nextInt();
						actors = db.findActorsByFilmId(userInput);
						System.out.println(actors + "\n");
					} catch (InputMismatchException e) {
						input.next();
						System.out.println("That was not a valid entry \n");
					}
					break;

				case 4:

					System.out.print("Enter a Word to find a Film by Search Word: ");
					input.nextLine();
					wordInput = input.nextLine();
					List<Film> filmKeyWord;

					filmKeyWord = db.findFilmByKeyWord(wordInput);

					if (filmKeyWord.isEmpty()) {
						System.out.println("Not a valid Entry" + "\n");
					} else {
						System.out.println(filmKeyWord);
					}

					break;

				case 5:
					try {
						System.out.print("Find Only Movie Language by Film ID: ");
						userInput = input.nextInt();
						String filmLanguage;

						filmLanguage = db.findLanguageByFilmId(userInput);
						if (filmLanguage == null) {
							System.out.println("Not a valid Entry" + "\n");
						} else {
							System.out.println(filmLanguage + "\n");
						}
					} catch (InputMismatchException e) {
						input.next();
						System.out.println("That was not a valid entry \n");
					}
					break;

				case 0:
					System.out.println("Bye");
					running = false;
					break;

				default:
					try {
						if (choice != input.nextInt()) {
							System.out.println("That was not a valid entry, try again." + "\n");
						}
					} catch (InputMismatchException e) {
						input.next();
						System.out.println("That was not a valid entry \n");
					}
					break;
				}
			} catch (InputMismatchException e) {
				input.next();
				System.out.println("That was not a valid entry \n");
			}

		} while (running);

	}

}
