
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@page import="cn.usershop.model.*" %>
 <%@page import="java.util.*"%>
 <%@page import="java.text.*" %>
 <%@page import="cn.usershop.connection.*" %>
 <%@page import="cn.usershop.userdao.*" %>
	  
    
     <%
     DecimalFormat dcf=new DecimalFormat("#.##");
     request.setAttribute("dcf", dcf);
    user auth=(user)request.getSession().getAttribute("auth");
    List<Order>orders=null;
    if(auth!=null){
    request.setAttribute("auth", auth);
   orders=new Orderdao(DbConnection.getConnection()).userOrders(auth.getId());

    }else{
    	response.sendRedirect("login.jsp");
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
<%@include file="include/head.jsp" %>
</head>
<body>
<%@include file="include/nav-bar.jsp" %>

<div class="container">
		<table class="table table-light">
			<thead>
				<tr>
					<th scope="col">Date</th>
					<th scope="col">Name</th>
					<th scope="col">Category</th>
					<th scope="col">Quantity</th>
					<th scope="col">Price</th>
					<th scope="col">Cancel</th>
				</tr>
			</thead>
			<tbody>
			<%
			if(orders!=null){
				for(Order o:orders){%>
				<tr>
				<td><%=o.getDate() %></td>
				<td><%=o.getName() %></td>
				<td><%=o.getCategory() %></td>
				<td><%=o.getQuantity() %></td>
				<td><%=o.getPrice() %></td>
				<td><a class="btn btn-sm btn-danger" href="cancel-order?id=<%=o.getOrderid() %>">Cancel</a></tr>
				</tr>	
				<%}
			}
			%>
			</tbody>
			</table>
			</div>

<%@include file="include/Footer.jsp" %>
</body>
</html>