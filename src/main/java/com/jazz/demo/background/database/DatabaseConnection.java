package com.jazz.demo.background.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

	 public static Connection getConnection()  {

	        String urlConnector = "jdbc:mysql://localhost:3306/webscrp?serverTimezone=Europe/Rome";
	        String usernameConnector = "root"; 
	        String passwordConnetor = "razal";  //root
	        Connection connection = null;
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            connection = DriverManager.getConnection(urlConnector,usernameConnector,passwordConnetor);


	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	            System.out.println("Problem with database connection");
	        }

	        return connection;
	    }
	
	
}
