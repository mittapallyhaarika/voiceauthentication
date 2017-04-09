package com.voicerecog.conn;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class OracleConnUtils {
	private static Connection conn;

	public static Connection getConnection() throws ClassNotFoundException {
		if( conn != null )
			return conn;

		//Properties file in the src directory for adding DB Details.
		InputStream inputStream = OracleConnUtils.class.getClassLoader().getResourceAsStream( "db.properties" );
		Properties properties = new Properties();
		try {
			
			//Load the properties file
			properties.load( inputStream );
			String url = properties.getProperty( "url" );
			String user = properties.getProperty( "user" );
			String password = properties.getProperty("password");


			//Get connection
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection( url, user, password );

			System.out.println("Connection to the database established successfully...");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return conn;
	}

	public static void closeConnection( Connection toBeClosed ) {
		if( toBeClosed == null )
			return;
		try {
			toBeClosed.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}