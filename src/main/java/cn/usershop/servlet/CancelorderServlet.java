package cn.usershop.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.usershop.connection.DbConnection;
import cn.usershop.userdao.Orderdao;


@WebServlet("/cancel-order")
public class CancelorderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try(PrintWriter out=response.getWriter()){
			String id=request.getParameter("id");
			if(id!=null) {
				Orderdao dao=new Orderdao(DbConnection.getConnection());
				dao.cancelorder(Integer.parseInt(id));
			}
			response.sendRedirect("orders.jsp");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
