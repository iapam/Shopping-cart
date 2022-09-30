<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@page import="cn.usershop.model.*" %>
	<%@page import="java.util.*"%>
	
	 <%
    user auth=(user)request.getSession().getAttribute("auth");
    if(auth!=null){
   response.sendRedirect("index.jsp");
    }
    ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
    List<Cart> cartproduct = null;
    if (cart_list != null) {
        
    	request.setAttribute("cart_list", cart_list);
    }
    %>
<!DOCTYPE html>
<html>
<head>

<title>Insert title here</title>
<%@include file="include/head.jsp"%>
</head>
<body>
<%@include file="include/nav-bar.jsp" %>
	<div class="container">
		<div class="card w-50 mx-auto my-5">
			<div class="card-header text-center color-red">User Login</div>
			<div class="card-body">

				<form action="LoginServlet" method="post">
					<div class="form-group">
						<label>Email Address</label> <input type="email"
							class="form-control" name="login-email" placeholder="email"
							required>
					</div>
					<div class="form-group">
						<label>Password</label> <input type="password"
							class="form-control" name="login-password" placeholder="********"
							required>
					</div>
					<div class="text-center">
						<button type="submit" class="btn btn-primary">Login</button>
					</div>

				</form>
			</div>
		</div>

	</div>
	<%@include file="include/Footer.jsp"%>
</body>
</html>