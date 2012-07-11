<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="it.polito.ai.gas.business.*" %>
<%@ page import="it.polito.ai.gas.dao.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Products... test</title>
<jsp:useBean id="user" class="it.polito.ai.gas.business.User" scope="session" />
</head>
<body>

<%

if (request.getParameter("action") != null && request.getParameter("action").equals("logout"))
{
	user = new User();
	session.setAttribute("user", new User());
	System.out.println("Logged out");
}

//Login
if (user.getName() == null && request.getParameter("username") != null	&& request.getParameter("password") != null)
{
	String username = ""+request.getParameter("username");
	String password = ""+request.getParameter("password");
	
	if (request.getParameter("action").equals("login"))
	{		
		User check = DAOFactory.getDAOFactoryHibernate().getUserDAOHibernate().login(username, password);
		if (check != null)
		{
			user.setName(check.getName());
			user.setPassword(check.getPassword());
			user.setRole(check.getRole());
			
			System.out.println("Logged in as: "+user);
		}
		else
			System.out.println("Login failed");
	}
	
	if (request.getParameter("action").equals("register"))
	{
		User add = new User();
		add.setName(username);
		add.setPassword(password);
		add.setRole(0);
		if (DAOFactory.getDAOFactoryHibernate().getUserDAOHibernate().addUser(add) != null)
		{
			System.out.println(add.getName()+" registered");
			
			//NON fare "user = add", poiche' le stringhe non si copiano...
			user.setName(add.getName());
			user.setPassword(add.getPassword());
			user.setRole(add.getRole());
		}
		else
			System.out.println("Registering failed");
	}

}

//Add Product
if (request.getParameter("name") != null && request.getParameter("cost") != null &&
	request.getParameter("units") != null && user!=null)
{
	Product product = new Product();
	
	product.setName(""+request.getParameter("name"));
	product.setCost(Integer.parseInt(""+request.getParameter("cost")));
	//product.setUnits(Integer.parseInt(""+request.getParameter("units")));
	product.setProducer(user);
	
	DAOFactory.getDAOFactoryHibernate().getProductDAOHibernate().addProduct(product);
	
	System.out.println("Added: "+product);
}

if (user.getName() != null)
{
%>
Welcome <%= user.getName() %><br><br>

Add a product:
<form action="index.jsp" method="post">
Name: <input type="text" name="name"><br>
Cost: <input type="text" name="cost"/><br>
Units: <input type="text" name="units"/><br>
<input type="submit" value="Add"/>
</form>
<br>

<form action="index.jsp" method="post">
<input type="hidden" name="action" value="logout"/>
<input type="submit" value="Logout"/>
</form>

<br>My Products:
<%
List<Product> products = DAOFactory.getDAOFactoryHibernate().getProductDAOHibernate()
							.getProductByProducer(user);

for(Product p : products)
{
	out.println("<li>"+p);
}
} else { %>

<br>
Login:
<form action="index.jsp" method="post">
Username: <input type="text" name="username"/><br>
Password: <input type="password" name="password"/><br>
<input type="hidden" name="action" value="login"/>
<input type="submit" value="Login"/>
</form>

<br>or<br>Register:
<form action="index.jsp" method="post">
Username: <input type="text" name="username"/><br>
Password: <input type="password" name="password"/><br>
<input type="hidden" name="action" value="register"/>
<input type="submit" value="Register"/>
</form>
<% } %>

</body>
</html>