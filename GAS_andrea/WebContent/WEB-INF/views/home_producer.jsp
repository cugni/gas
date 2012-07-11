<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="logoutform" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="productform" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="java.util.*" %>
<%@ page import="it.polito.ai.gas.beans.*" %>
<%@ page import="it.polito.ai.gas.business.*" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GAS - Home</title>

<style>
.error {
	color: #ff0000;
	background-color: #ffEEEE;
	font-weight: bold;
}
</style>
</head>

<body>
<%
IUserBean user = (IUserBean) session.getAttribute("user");
%>

<h3>Welcome ${user.getUsername()}</h3>

<logoutform:form method="post" commandName="logout">
	<logoutform:hidden path="command" value="logout" />
	
	<input type="submit" value="Logout">
</logoutform:form>

<hr>

Your products:<br>
<table>
<%
	List<Product> products = user.getProducts();
	for(int i=0; i<products.size(); i++)
	{
	%>
		<tr>
		<td>
		<b><% out.print((i+1) +") "+ products.get(i).getName()); %></b>
		</td>
		</tr>
	<%
	}
%>
</table>

<hr>

Add a new product:
<h4 class="error">${error}</h4>
<productform:form method="post" commandName="addProduct">
	<productform:hidden path="command" value="addProduct" />

	<productform:errors path="name" cssClass="error" />
		Name: <productform:input path="name" />
		<br>
	<productform:errors path="cost" cssClass="error" />
		Cost: <productform:input path="cost" />
		<br>
	<productform:errors path="units" cssClass="error" />
		Measure Unit(s):
		<productform:select path="units">
			<productform:option value="" selected="selected">- Choose -</productform:option>
			<productform:option value="kg">Kilograms</productform:option>
			<productform:option value="m">Meters</productform:option>
			<productform:option value="l">Liters</productform:option>
			<productform:option value="other">Other</productform:option>
		</productform:select>
		<br>
	<productform:errors path="quantity" cssClass="error" />
		Quantity: <productform:input path="quantity" />
		<br>
	<productform:errors path="description" cssClass="error" />
		A short description: <productform:textarea path="description" />
		<br>
	<productform:errors path="dimensions" cssClass="error" />
		Dimensions (AxBxC...): <productform:input path="dimensions" />
		<br>
	<productform:errors path="transportCost" cssClass="error" />
		Transport cost: <productform:input path="transportCost" />
		<br>
	<productform:errors path="stockQuantity" cssClass="error" />
		Stock quantity: <productform:input path="stockQuantity" />
		<br>
	<productform:errors path="minToBuy" cssClass="error" />
		Min. pieces to buy: <productform:input path="minToBuy" />
		<br>
	<productform:errors path="maxToBuy" cssClass="error" />
		Max. pieces to buy: <productform:input path="maxToBuy" />
		<br>
	<productform:errors path="available" cssClass="error" />
		Available from now? <productform:checkbox path="available" />
		<br>
		
	<input type="submit" value="Add">		
</productform:form>

</body>
</html>