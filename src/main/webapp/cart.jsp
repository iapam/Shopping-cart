<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>
<%@page import="cn.usershop.model.*"%>
<%@page import="cn.usershop.userdao.Productdao"%>
<%@page import="cn.usershop.connection.DbConnection"%>
<%@page import="java.text.*" %>
<%
DecimalFormat dcf=new DecimalFormat("#.##");
 request.setAttribute("dcf", dcf);
user auth = (user) request.getSession().getAttribute("auth");
if (auth != null) {
	request.setAttribute("auth", auth);
}
ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
List<Cart> cartproduct = null;
if (cart_list != null) {
	Productdao pdao = new Productdao(DbConnection.getConnection());
	cartproduct = pdao.getCartProducts(cart_list);
	double total=pdao.gettotalCartPrice(cart_list);
	request.setAttribute("cart_list", cart_list);
	request.setAttribute("total", total);
}
%>
<!DOCTYPE html>
<html>
<head>

<title>Insert title here</title>
<%@include file="include/head.jsp"%>
<style type="text/css">
.table tbody td {
	vertical-align: middle;
}

.btn-incre, .btn-decre {
	box-shadow: none;
	font-size: 25px;
}
</style>
</head>
<body>
	<%@include file="include/nav-bar.jsp"%>
	<div class="container">
		<div class="d-flex py-3">
			<h3>Total Price:$ ${(total>0)? dcf.format(total):0}</h3>
			<a href="check-out-cart" class="mx-3 btn btn-primary">Check Out</a>
		</div>
		<table class="table table-light">
			<thead>
				<tr>
					<th scope="col">Name</th>
					<th scope="col">Category</th>
					<th scope="col">Price</th>
					<th scope="col">Buy now</th>
					<th scope="col">Cancel</th>
				</tr>
			</thead>
			<tbody>
				<%
				if (cart_list != null) {
					for (Cart c : cartproduct) {
				%>
				<tr>
					<td><%=c.getName()%></td>
					<td><%=c.getCategory()%></td>
					<td>$<%=dcf.format(c.getPrice())%></td>
					<td>
						<form action="order-now" method="post" class="form-inline ">
							<input type="hidden" name="id" value="<%=c.getId() %>" class="form-input ">
							<div class="form-group d-flex justify-content-between w-50">
								<a class="btn btn-sm btn-decre" href="quantity-incre-decre?action=decr&id=<%= c.getId()%>"><i
									class="fas fa-minus-square"></i></a> <input type="text"
									name="quantity" class="form-control w-50" value="<%=c.getQuantity() %>" readonly>

								<a class="btn btn-sm btn-incre" href="quantity-incre-decre?action=incr&id=<%= c.getId()%>"><i
									class="fas fa-plus-square"></i></a>
							</div>
							<button type="submit" class="btn btn-primary btn-sm">Buy</button>
						</form>
					<td><a href="remove-from-cart?id=<%=c.getId() %>" class="btn btn-sm btn-danger">Remove</a></td>
				</tr>
				<%
				}
				}
				%>

			</tbody>
		</table>
	</div>
	<%@include file="include/Footer.jsp"%>
</body>
</html>