<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Enumeration" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>BAM!</title>
</head>
<body>

<%
Enumeration<String> attributes = session.getAttributeNames();
while(attributes.hasMoreElements())
	session.removeAttribute(attributes.nextElement());
%>
<a href="index.jsp">index.jsp</a>

</body>
</html>