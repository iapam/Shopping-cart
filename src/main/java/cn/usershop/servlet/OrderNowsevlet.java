package cn.usershop.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.*;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.usershop.connection.DbConnection;
import cn.usershop.model.Cart;
import cn.usershop.model.Order;
import cn.usershop.model.user;
import cn.usershop.userdao.Orderdao;


@WebServlet("/order-now")
public class OrderNowsevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try(PrintWriter out=response.getWriter()){
			SimpleDateFormat formater=new SimpleDateFormat("yyyy-MM-dd");
			Date date=new Date();
		user auth=(user) request.getSession().getAttribute("auth");
		if(auth!=null) {
			
			String productid=request.getParameter("id");
			ArrayList<Cart> cart_list=(ArrayList<Cart>) request.getSession().getAttribute("cart-list");
			if(cart_list!=null) {
				for(Cart c:cart_list) {
					if(c.getId()==Integer.parseInt(productid)) {
						cart_list.remove(cart_list.indexOf(c));
						break;
					}
				}
			
			}
			int quantity=Integer.parseInt(request.getParameter("quantity"));
			if(quantity<=0) {
				quantity=1;
			}
			Order ordermodel=new Order();
			ordermodel.setId(Integer.parseInt(productid));
			ordermodel.setUid(auth.getId());
			ordermodel.setDate(formater.format(date));
			ordermodel.setQuantity(quantity);
			
			Orderdao orderdao=new Orderdao(DbConnection.getConnection());
			boolean result=orderdao.inserOder(ordermodel);
			if(result) {
				response.sendRedirect("orders.jsp");
			}else {
				out.print("order failed");
			}
			
		}else {
			response.sendRedirect("login.jsp");
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	doGet(request,response);
	}


}
