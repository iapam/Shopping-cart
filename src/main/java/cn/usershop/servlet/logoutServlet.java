package cn.usershop.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/logoutServlet")
public class logoutServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try(PrintWriter out=response.getWriter()){
		if(request.getSession().getAttribute("auth")!=null) {
		request.getSession().removeAttribute("auth");
		response.sendRedirect("login.jsp");
		}else {
			response.sendRedirect("index.jsp");
		}
	}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
