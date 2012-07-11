<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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

/* USER NON LOGGATO */
//if (user == null || user.getUsername() == null)
{
%>

<h3>Login</h3>
<form:form method="post" commandName="login">
	
	<form:hidden path="command" value="login"/>
	
	<form:errors path="username" cssClass="error" />
	Username: <form:input path="username" id="username" />
	<br>
	<form:errors path="password" cssClass="error" />
	Password: <form:password path="password" id="password" />
	<br>
		
	<input type="submit" value="Login">

</form:form>
<% } %>
</body>
</html>