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

  public static void main(String[] args) {
    FilmQueryApp app = new FilmQueryApp();
    //app.test();
    app.launch();
    //app.findActByFilmID();    
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
	  int var, rav, act;
	  String wxy;
	  System.out.println("===================================================================");
	  System.out.println();// blank line to give space for readability
	  System.out.println("This APP lets you look up the following:");
	  System.out.println("(1) Look up a film by its id  ");
	  System.out.println("(2) Look up film by a search word ");
	  System.out.println("(3) List of actors in a film where the user inputs the film id ");
	  System.out.println("(4) Look up actor by actor id ");
	  System.out.println("(8) Exits the program.");
	  System.out.println("Enter your number choice:");
	  var = input.nextInt();

		switch (var) {
		case 1:
			System.out.println("We now need the film id (where the id(s) range from integer 1-1000)");
			System.out.println("Please enter a number from 1 to 1000 ");
			rav = input.nextInt();
			if (rav <1 || rav >1000) {
				System.out.println(rav +" error! id not in-bounds ");
				startUserInterface(input);
			}	else {
			Film film = db.findFilmById(rav);
			film.filmMostInfo();
			System.out.println("Cast :");
			List<Actor>actors = db.findActorsByFilmId(rav);
			for (Actor actor : actors) {
				actor.justActor();
			}
			//System.out.println(film);
			startUserInterface(input);
			}
			break;
		case 2:
			System.out.println("Now enter a word or part of word to search film titles ");
			wxy = input.next();
			List<Film> filmX = db.findFilmByKey(wxy);
			for (Film film : filmX) {
				film.filmMostInfo();
			}
			if(filmX.isEmpty()) {
				System.out.println(wxy + "  Does not match any keywords in films.");
			}
			startUserInterface(input);
			break;
		case 3:
			System.out.println("We now need the film id (where the id(s) range from integer 1-1000)");
			System.out.println("Please enter a number from 1 to 1000 ");
			rav = input.nextInt();
			if( rav < 1 || rav >1000) {
				System.out.println(rav + " error! id not in-bounds ");
				startUserInterface(input);
			}	else {
			List<Actor>actors = db.findActorsByFilmId(rav);
			for (Actor actor : actors) {
				actor.justActor();
			}
			startUserInterface(input);
			}
			break;
		case 4:
			System.out.println("We need to know the actor id (where the id(s) range from integer 1-200)");
			System.out.println("Please enter a number from 1 to 200");
			act = input.nextInt();
			if(act <1 || act>200) {
				System.out.println(act+ " error! id not in-bounds ");
				startUserInterface(input);
			} else {
				Actor actor2 = db.findActorById(act);
				actor2.justActor();
			}
			startUserInterface(input);
			break;
		case 8:
			System.out.println("Ending Session.");
			break;
		default:
			System.out.println("ERROR try again!");
			startUserInterface(input);
			break;
    
  }
  }
}
