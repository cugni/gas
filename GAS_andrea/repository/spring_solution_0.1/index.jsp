<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="it.polito.ai.gas.beans.*" %>
<%@ page import="it.polito.ai.gas.dao.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GAS</title>

<%
//IUser user = (IUser) session.getAttribute("user");
%>
</head>
<body>

<div align="center">
<h1>GAS online</h1>
<h2>Gruppi Acquisto Solidale</h2>
</div>

<!-- <a href="form/login">LOGIN</a> -->

<table align="center" border="1" width="70%">
<tr>
<td>
	<c:import url="form/login"></c:import>
</td>
</tr>
<tr>
<td>
	<c:import url="form/register"></c:import>
</td>
</tr>
</table>

</body>
</html>