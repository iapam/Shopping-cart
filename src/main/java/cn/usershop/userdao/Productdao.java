package cn.usershop.userdao;
import cn.usershop.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Productdao {

	private Connection con;
	private PreparedStatement stmt;
	private String query;
	private ResultSet rs;
	public Productdao(Connection con) {
		super();
		this.con = con;
	}
	public List<Product>getAllProduct(){
		List<Product> product=new ArrayList<Product>();
		try {
			query="select* from products";
			stmt=this.con.prepareStatement(query);
			rs=stmt.executeQuery();
			while(rs.next()) {
				Product row=new Product();
				row.setId(rs.getInt("id"));
				row.setName(rs.getString("name"));
				row.setCategory(rs.getString("category"));
				row.setPrice(rs.getDouble("price"));
				row.setImage(rs.getString("image"));
				product.add(row);
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		
		}

		return product;
	
	}

	
	
	public List<Cart>getCartProducts(ArrayList<Cart> cartlist){
		
		List<Cart>products=new ArrayList<Cart>();
		
		try {
			if(cartlist.size()>0) {
				for(Cart item: cartlist) {
					query="select * from products where id=?";
					stmt=this.con.prepareStatement(query);
					stmt.setInt(1, item.getId());
					rs=stmt.executeQuery();
					while(rs.next()) {
						Cart row=new Cart();
						row.setId(rs.getInt("id"));
						row.setName(rs.getString("name"));
						row.setCategory(rs.getString("category"));
						row.setPrice(rs.getDouble("price")*item.getQuantity());
						row.setQuantity(item.getQuantity());
						products.add(row);
					}
				}
			}
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return products;
	}
	public Product getSingleProduct(int pid) {
		Product row=null;
		try {
			
			query="select* from products where id=?";
			stmt=this.con.prepareStatement(query);
			stmt.setInt(1, pid);
			rs=stmt.executeQuery();
			while(rs.next()) {
				row=new Product();
				row.setId(rs.getInt("id"));
				row.setName(rs.getString("name"));
				row.setPrice(rs.getDouble("price"));
				row.setImage(rs.getString("image"));
				row.setCategory(rs.getString("category"));
			}
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	
	return row;
	}
	public double gettotalCartPrice(ArrayList<Cart> cartlist) {
		double sum=0;
		try {
			if(cartlist.size()>0) {
				for(Cart item:cartlist) {
					query="select price from products where id=?";
					stmt=this.con.prepareStatement(query);
					stmt.setInt(1, item.getId());
					rs=stmt.executeQuery();
					while(rs.next()) {
						sum+=rs.getDouble("price")*item.getQuantity();
					}
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return sum;
	}

}
