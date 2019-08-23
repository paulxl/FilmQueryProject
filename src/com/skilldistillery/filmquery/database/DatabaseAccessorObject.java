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
	
	
	
static {
	 try {
		Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		
		e.printStackTrace();
	}
}
  @Override
  public Film findFilmById(int filmId) {
	  Film film =null;
	  
	  try {
		Connection conn = DriverManager.getConnection(URL, userName, passWord);
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
	  
    return null;
  }
  public Actor findActorById(int filmId) {
	  Actor actor =null;
	  
	  try {
		Connection conn = DriverManager.getConnection(URL, userName, passWord);
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
	  
    return null;
  }
@Override
public List<Actor> findActorsByFilmId(int filmId) {
	//public Actor findActorById(int actorId) {
	 List<Actor> actors = new ArrayList<>();
		  Actor actor = null;
		  String sqltxt = "";
		  try ( Connection conn=DriverManager.getConnection(URL,userName ,passWord);
			        PreparedStatement stmt = conn.prepareStatement(sqltxt);
			        ResultSet rs = stmt.executeQuery(); ) {
			  
			  if (rs.next()) {
				  actor = new Actor(); // Create the object
				  // Here is our mapping of query columns to our object fields:
				  actor.setActorId(rs.getInt(actorId));
				  actor.setFirstName(rs.getString(actorLastName));
				  actor.setLastName(rs.getString(actorFirstName));
			  }
//			    while (rs.next()) {
//			      System.out.println(rs.getString(1) + " "
//			                          + rs.getString(2) + " " + rs.getString(3));
//			    }
			  }
			  catch (SQLException e) {
			    System.err.println(e);
			  }//...
		  String sql = "SELECT actor.id, actor.first_name, actor.last_name FROM actor WHERE film.id = ?";
		  //PreparedStatement stmt = conn.prepareStatement(sql);
		  //stmt.setInt(1,actorId);
		  //ResultSet actorResult = stmt.executeQuery();
		  
	return actors;
}

}
