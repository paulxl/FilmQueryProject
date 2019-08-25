package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
	private final String userName = "student";
	private final String passWord = "student";
	private ArrayList<Actor> actorList;
	private ArrayList<Film> filmList;

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
	}

	@Override
	public Film findFilmById(int filmId) {
		Film film = null;
		String sqltxt ="SELECT film.title, film.description, film.release_year, film.rating,"
				+ "language.name FROM film JOIN language ON film.language_id = language.id  WHERE film.id= ?; ";

		try {
			Connection conn = DriverManager.getConnection(URL, userName, passWord);
			PreparedStatement stmt = conn.prepareStatement(sqltxt);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				film = new Film();
				film.setTitle(rs.getString("film.title"));
				film.setDescription(rs.getString("film.description"));
				film.setReleaseYear(rs.getInt("film.release_Year"));
				film.setRating(rs.getString("film.rating"));
				film.setLanguage(rs.getString("language.name"));
				System.out.println(film.filmMostInfo());
//				switch ("film.language_id") {
//				case "1":
//					System.out.println("Language:  English");
//					break;
//				case "2":
//					System.out.println("Language:  Italian");
//					break;
//				case "3":
//					System.out.println("Language:  Japanese");
//					break;
//				case "4":
//					System.out.println("Language:  Maderine");
//					break;
//				case "5":
//					System.out.println("Language:  French");
//					break;
//				case "6":
//					System.out.println("Language:  German");
//					break;
//				default:
//					System.out.println("Language:  Unknown");
//				}
				System.out.println("film.onlyLanguage");
				System.out.println("Cast :");
				findActorsByFilmId(filmId);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return null;
	}
	@Override
	public List<Film> findFilmByKey(String inquire) {
		//List<Film> films = new ArrayList<>();
		this.filmList = new ArrayList<Film>();
		try {
			Connection conn = DriverManager.getConnection(URL, userName, passWord);
			String sqltxt ="SELECT film.title FROM film WHERE title LIKE ?; ";
			
			PreparedStatement stmt = conn.prepareStatement(sqltxt);
			stmt.setString(1, "%" + inquire + "%" );
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Film film = new Film();
				film.setTitle(rs.getString("film.title"));
				filmList.add(film);
				System.out.println(film.titleOnly());
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
	
		return filmList;
	}


	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		// public Actor findActorById(int actorId) {
		List<Actor> actors = new ArrayList<>();
		this.actorList = new ArrayList<Actor>();
		//Actor actor = null;

		String sqltxt = "SELECT actor.id, actor.first_name, actor.last_name FROM actor "
				+ "JOIN film_actor ON film_actor.actor_id = actor.id "
				+ "Join film ON film_actor.film_id =film.id  "
				+ "WHERE film.id = ?";
//		int actorId = 0;		
//		String firstName = null;
//		String lastName = null;

		try (Connection conn = DriverManager.getConnection(URL, userName, passWord);) {
			PreparedStatement stmt = conn.prepareStatement(sqltxt);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Actor actor = new Actor(); // Create the object				
				actor.setActorId(rs.getInt("actor.id"));// this one represents actorId
				actor.setLastName(rs.getString("actor.first_name"));
				actor.setFirstName(rs.getString("actor.last_name"));				
				actorList.add(actor);
				//System.out.println(actor);
				System.out.println("\t" + actor.justActor());
			}
//			    while (rs.next()) {
//			      System.out.println(rs.getString(1) + " "
//			                          + rs.getString(2) + " " + rs.getString(3));
//			    }
		} catch (SQLException e) {
			System.err.println(e);
		} // ...
		// PreparedStatement stmt = conn.prepareStatement(sql);
		// stmt.setInt(1,actorId);
		// ResultSet actorResult = stmt.executeQuery();

		return actorList;
	}
	public Actor findActorById(int filmId) {
		Actor actor = null;
		String sqltxt =" ";
		
		try {
			Connection conn = DriverManager.getConnection(URL, userName, passWord);
			PreparedStatement stmt = conn.prepareStatement(sqltxt);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return null;
	}



}
