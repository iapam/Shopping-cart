package cn.usershop.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.usershop.connection.DbConnection;
import cn.usershop.model.*;
import cn.usershop.userdao.Orderdao;

@WebServlet("/check-out-cart" )
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try(PrintWriter out=response.getWriter()){
			SimpleDateFormat formater=new SimpleDateFormat("yyyy-MM-dd");
			Date date=new Date();
			ArrayList<Cart> cart_list=(ArrayList<Cart>) request.getSession().getAttribute("cart-list");
			user auth=(user)request.getSession().getAttribute("auth");
			if(auth!=null && cart_list!=null) {
				for(Cart c:cart_list) {
					Order order=new Order();
					order.setUid(auth.getId());
					order.setId(c.getId());
					order.setQuantity(c.getQuantity());
					order.setDate(formater.format(date));
					Orderdao dao=new Orderdao(DbConnection.getConnection());
					boolean result=dao.inserOder(order);
					if(!result)break;
				}
				cart_list.clear();
				response.sendRedirect("orders.jsp");
			}else {
				if(auth==null) response.sendRedirect("login.jsp");
				
					response.sendRedirect("cart.jsp");
	
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
