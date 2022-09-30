<%@page import="java.util.*" %>
<%@page import="cn.usershop.connection.DbConnection" %>
<%@page import="cn.usershop.model.*" %>
<%@page import ="cn.usershop.userdao.*" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%
    user auth=(user)request.getSession().getAttribute("auth");
    if(auth!=null){
    request.setAttribute("auth", auth);
    }
    Productdao pd=new Productdao(DbConnection.getConnection());
    List<Product> products=pd.getAllProduct();
    
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
    <div class="card-header my-3">All product </div>
    <div class="row">
    <%
    if(!products.isEmpty()){
    	for(Product p: products){%>
    		
    		  <div class="col-md-3 my-3">
    		    
    		    <div class="card w-100" style="width: 18rem;">
    		  <img class="card-img-top" src="product-images/<%=p.getImage() %>" alt="Card image cap">
    		  <div class="card-body">
    		    <h5 class="card-title"><%=p.getName() %></h5>
    		    <h6 class="price">$<%=p.getPrice() %></h6>
    		     <h6 class="category">Category:<%=p.getCategory() %></h6>
    		    <div class="mt-3 d-flex justify-content-between">
    		     <a href="add-to-cart?id=<%=p.getId() %>" class="btn btn-dark">Add to Cart</a>
    		      <a href="order-now?quantity=1&id=<%=p.getId() %>" class="btn btn-primary">By now</a>
    		   </div>
    		    
    		  </div>
    		</div>
    		    
    		    </div>
    		     <% 	}
    }%> 
    		    </div>
    		 </div> 


<%@include file="include/Footer.jsp" %>
</body>
</html>