package cn.usershop.userdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cn.usershop.model.user;

public class Userdao {

	private Connection con;
	private PreparedStatement stmt;
	private String query;
	private ResultSet rs;
	public Userdao(Connection con) {
		
		this.con = con;
	} 
	
	public user User(String email,String password) {
		
		user userver=null;
		try {
			query="select*from users where email=? and password=?";
			stmt=this.con.prepareStatement(query);
			stmt.setString(1, email);
			stmt.setString(2, password);
			rs=stmt.executeQuery();
			if(rs.next()) {
				userver=new user();
				userver.setEmail(rs.getString("email"));
				userver.setId(rs.getInt("id"));
				userver.setName(rs.getString("name"));
				userver.setPassword(rs.getString("password"));
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return userver;
	}
	
	
}
