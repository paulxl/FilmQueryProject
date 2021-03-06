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

		try {
			Connection conn = DriverManager.getConnection(URL, userName, passWord);
			String sqltxt = "SELECT film.title, film.description, film.release_year, film.rating,"
					+ "language.name FROM film JOIN language ON film.language_id = language.id  "
					+ "WHERE film.id= ?; ";
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
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return film;
	}

	@Override
	public List<Film> findFilmByKey(String inquire) {
		List<Film> filmList = new ArrayList<>();

		String sqltxt = "SELECT film.id, film.title, film.description, film.release_year, film.rating"
				+ " FROM film  WHERE title LIKE ? OR description LIKE ?; ";
		try {
			Connection conn = DriverManager.getConnection(URL, userName, passWord);
			PreparedStatement stmt = conn.prepareStatement(sqltxt);
			stmt.setString(1, "%" + inquire + "%");
			stmt.setString(2, "%" + inquire + "%");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Film film = new Film();
				film.setTitle(rs.getString("film.title"));
				film.setDescription(rs.getString("film.description"));
				film.setReleaseYear(rs.getInt("film.release_Year"));
				film.setRating(rs.getString("film.rating"));
				filmList.add(film);

			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return filmList;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		
		//List<Actor> actors = new ArrayList<>();
		this.actorList = new ArrayList<Actor>();
		String sqltxt = "SELECT actor.id, actor.first_name, actor.last_name FROM actor "
				+ "JOIN film_actor ON film_actor.actor_id = actor.id Join film ON film_actor.film_id =film.id  "
				+ "WHERE film.id = ?";
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
			}

		} catch (SQLException e) {
			System.err.println(e);
		}
		return actorList;
	}

	public Actor findActorById(int actorId) {
		Actor actor = null;
		String sqltxt = "SELECT actor.first_name, actor.last_name FROM actor WHERE actor.id = ?; ";

		try {
			Connection conn = DriverManager.getConnection(URL, userName, passWord);
			PreparedStatement stmt = conn.prepareStatement(sqltxt);
			stmt.setInt(1, actorId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				actor = new Actor();
				actor.setFirstName(rs.getString("actor.first_name"));
				actor.setLastName(rs.getString("actor.last_name"));
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return actor;
	}

}
