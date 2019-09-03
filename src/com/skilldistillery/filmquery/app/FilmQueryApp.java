package com.skilldistillery.filmquery.app;

import java.util.List;
import java.util.Scanner;
import java.sql.*;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();
	boolean keepGoing = true;

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
		// app.test();
		app.launch();
		// app.findActByFilmID();
	}

//private void test() {
//   Film film = db.findFilmById(110);
//    //System.out.println(film);
//    List<Actor>actors = db.findActorsByFilmId(1);
//    List<Film> filmX = db.findFilmByKey("ALA");
//  }
	private void launch() {
		Scanner input = new Scanner(System.in);
		startUserInterface(input);
		input.close();
	}

	private void startUserInterface(Scanner input) {
		int choice, filmId, actorId;
		
		do {
			System.out.println("===================================================================");
			System.out.println();// blank line to give space for readability
			System.out.println("This APP lets you look up the following:");
			System.out.println("(1) Look up a film by its id  ");
			System.out.println("(2) Look up film by a search word ");
			System.out.println("(3) List of actors in a film where the user inputs the film id ");
			System.out.println("(4) Look up actor by actor id ");
			System.out.println("(8) Exits the program.");
			System.out.println("Enter your number choice:");
			choice = input.nextInt();

			switch (choice) {
			case 1:
				System.out.println("We now need the film id : Please enter its number.");

				filmId = input.nextInt();
				Film film = db.findFilmById(filmId);
				if (film == null) {
					System.out.println(filmId + "  That id does not match a film.  Try again.\n");
					keepGoing = true;
					break;

				} else {
					film.filmMostInfo();
					System.out.println("Cast :");
					List<Actor> actors = db.findActorsByFilmId(filmId);
					for (Actor actor : actors) {
						actor.justActor();
					}
				}
				keepGoing = true;

				break;
			case 2:
				System.out.println("Now enter a keyword to search film titles or description \n");
				String key = input.next();
				List<Film> filmX = db.findFilmByKey(key);
				for (Film film2 : filmX) {
					film2.filmBasics();
				}
				if (filmX.isEmpty()) {
					System.out.println(key + "  Does not match any keywords in films.\n");
				}
				keepGoing = true;

				break;
			case 3:
				System.out.println("We now need the film id : Please enter the number.");
				filmId = input.nextInt();
				List<Actor> actors2 = db.findActorsByFilmId(filmId);
				for (Actor actor : actors2) {
					actor.justActor();
				}
				
				if (actors2.isEmpty()) {
					System.out.println( filmId + "  Does not match any film ids.\n");
				}
				keepGoing = true;
				break;
			case 4:
				System.out.println("We need to know the actor id.  Please enter the actor's id.");

				actorId = input.nextInt();
				Actor actor2 = db.findActorById(actorId);
				if (actor2 == null) {
					System.out.println(actorId + " That id does not match an actor.\n");
				} else {
					actor2.justActor();
				}
				keepGoing = true;
				break;
			case 8:
				System.out.println("Ending Session.");
				keepGoing = false;
				break;
			default:
				System.out.println("ERROR try again!");
				keepGoing = true;
				break;
			}
		} while (keepGoing);
	}

}
