package com.utkarsh.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseUtil {

	public static Connection getConnection() {
		try {
			String url = "jdbc:mysql://localhost:3306/sms";
			String user = "root";
			String pass = "root";

			
			Class.forName("com.mysql.cj.jdbc.Driver");

			return DriverManager.getConnection(url, user, pass);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}