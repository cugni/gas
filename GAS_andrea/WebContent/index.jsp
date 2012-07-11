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

<title>Please Wait...</title>

<%
	IUserBean user = (IUserBean) session.getAttribute("user");
if (user == null || user.getUsername() == null)
{
%>
<meta HTTP-EQUIV="REFRESH" content="3; url=pages/welcome">
<% } else { %>
<meta HTTP-EQUIV="REFRESH" content="3; url=pages/home">
<% } %>

</head>

<body>

You will be redirect in 3 seconds...

</body>
</html>