package cn.usershop.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
	
	private static Connection connection=null;
	public static Connection getConnection() throws Exception {
		String url="jdbc:mysql://localhost:3306/shopping";
		String user="root";
		String password="Zingaro1";
		if(connection==null) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection(url,user,password);
			System.out.println("connected");
		}
		return connection;
		
	}
	

}
