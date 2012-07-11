<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="logoutform" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="it.polito.ai.gas.beans.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>

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

// USER LOGGATO 
//if (user != null && user.getUsername() != null)
{
%>
<h3>Welcome ${user.getUsername()}</h3>
<logoutform:form method="post" commandName="logout">
	<logoutform:hidden path="command" value="logout" />
	
	<input type="submit" value="Logout">
</logoutform:form>

<% } %>
</body>
</html>