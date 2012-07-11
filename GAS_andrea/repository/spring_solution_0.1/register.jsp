<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="it.polito.ai.gas.beans.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>

<style>
.error {
	color: #ff0000;
	background-color: #ffEEEE;
	font-weight: bold;
}
</style>

<script src="WEB-INF/scripts/validator.js"></script>
<script type="text/javascript">
function toogleProducerForm()
{
	var pf = document.getElementById("producerForm");
	var cb = document.getElementById("is_producer");
	if (cb.checked)
		pf.style.display="block";
	else
		pf.style.display="none";
}
</script>

</head>

<body onload="toogleProducerForm()">

<%
	IUserBean user = (IUserBean) session.getAttribute("user");

/* USER NON LOGGATO */
//if (user == null || user.getUsername() == null)
{
%>

<h3>Register</h3><br>
   	
<form:form method="post" commandName="register" id="register">

	<form:hidden path="command" value="register"/>

	<form:errors path="username" cssClass="error" />
	Username: <form:input path="username" id="username" onkeyup="stringMinMax(this, 1, 20)" /><br>
	<form:errors path="password" cssClass="error" />
	Password: <form:password path="password" id="password" onkeyup="stringMinMax(this, 1, 32)"/><br>
	<form:errors path="name" cssClass="error" />
	Name: <form:input path="name" id="name" onkeyup="stringMinMax(this, 1, 20)" />
	<form:errors path="surname" cssClass="error" />
	Surname: <form:input path="surname" id="surname" onkeyup="stringMinMax(this, 1, 20)"/><br>
	Birth Date (day/month/year):
	<form:errors path="day" cssClass="error" />
	<form:input path="day" id="day" size="2" onkeyup="numberMinMax(this, 1, 31)" /> / 
	<form:errors path="month" cssClass="error" />
	<form:input path="month" id="month" size="2" onkeyup="numberMinMax(this, 1, 12)" /> / 
	<form:errors path="year" cssClass="error" />
	<form:input path="year" id="year" size="4" onkeyup="numberMinMax(this, 1900, 2000)" />
	
	<br><br>
	<form:radiobutton path="role" value="0" onclick="toogleProducerForm()" />Normal user<br>
	<form:radiobutton path="role" value="1" onclick="toogleProducerForm()" />Delegate<br>
	<form:radiobutton path="role" value="2" onclick="toogleProducerForm()" id="is_producer" />Producer<br>

	<div id="producerForm">
	<form:errors path="companyName" cssClass="error" />
	Company Name:<form:input path="companyName" id="companyName" onkeyup="stringMinMax(this, 1, 40)" /><br>
	<form:errors path="description" cssClass="error" />
	Description: <form:input path="description" id="description" onkeyup="stringMinMax(this, 1, 40)" /><br>
	<form:errors path="contact" cssClass="error" />
	Contact: <form:input path="contact" id="contact" onkeyup="stringMinMax(this, 1, 20)"/><br>
	<form:errors path="address" cssClass="error" />
	Address: <form:input path="address" id="address" onkeyup="stringMinMax(this, 1, 40)" /><br><br>
	<form:errors path="phoneNumber" cssClass="error" />
	Phone Number: <form:input path="phoneNumber" id="phoneNumber" onkeyup="stringMinMax(this, 1, 15)" /><br>
	<form:errors path="faxNumber" cssClass="error" />
	Fax Number: <form:input path="faxNumber" id="faxNumber" onkeyup="stringMinMax(this, 1, 15)" /><br>
	<form:errors path="email" cssClass="error" />
	Email: <form:input path="email" id="email" onkeyup="stringMinMax(this, 1, 30)"/><br><br>
	<form:errors path="paymentMode" cssClass="error" />
	Payment Mode: <form:input path="paymentMode" id="paymentMode" onkeyup="stringMinMax(this, 1, 20)" /><br><br>
	<form:errors path="delegate" cssClass="error" />
	<b>Delegate Username:</b> <form:input path="delegate" id="delegate" onkeyup="stringMinMax(this, 1, 20)" /><br>
	</div>
	
	<input type="submit" value="Register">

</form:form>

<% } %>
</body>
</html>