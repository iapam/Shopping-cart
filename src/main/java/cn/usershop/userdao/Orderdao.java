package cn.usershop.userdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import cn.usershop.connection.DbConnection;
import cn.usershop.model.Order;
import cn.usershop.model.Product;

public class Orderdao {
	private Connection con;
	private PreparedStatement stmt;
	private String query;
	private ResultSet rs;
	public Orderdao(Connection con) {
		super();
		this.con = con;
	}
	public boolean inserOder(Order model) {
		
	boolean result=false;
	
	try {
		
		query="insert into orders(p_id,u_id,o_quantity,o_date) values(?,?,?,?)";
		stmt=this.con.prepareStatement(query);
		stmt.setInt(1, model.getId());
		stmt.setInt(2, model.getUid());
		stmt.setInt(3, model.getQuantity());
		stmt.setString(4, model.getDate());
		stmt.executeUpdate();
		result=true;
	}catch(Exception e) {
		System.out.println(e.getMessage());
	}
	
	return result;
	}
	
public List<Order>userOrders(int id){
	
	List<Order> list=new ArrayList<>();
	try {
		query="select* from orders where u_id=? order by orders.o_id desc";
		stmt=this.con.prepareStatement(query);
		stmt.setInt(1, id);
		rs=stmt.executeQuery();
		while(rs.next()) {
			Order order=new Order();
			Productdao pdao=new Productdao(DbConnection.getConnection());
			int Pid=rs.getInt("p_id");
			Product product=pdao.getSingleProduct(Pid);
			order.setOrderid(rs.getInt("o_id"));
			order.setName(product.getName());
			order.setId(Pid);
			order.setCategory(product.getCategory());
			order.setPrice(product.getPrice()*rs.getInt("o_quantity"));
			order.setQuantity(rs.getInt("o_quantity"));
			order.setDate(rs.getString("o_date"));
			list.add(order);
		}
	}catch(Exception e) {
		System.out.println(e.getMessage());
	}
	return list;
}
public void cancelorder(int id) {
	try {
		query="delete from orders where o_id=?";
		stmt=this.con.prepareStatement(query);
		stmt.setInt(1, id);
		stmt.execute();
	}catch(Exception e) {
		e.printStackTrace();
	}
}

}
