<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="loginform" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="registerform" uri="http://www.springframework.org/tags/form"%>

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

<style>
.error {
	color: #ff0000;
	background-color: #ffEEEE;
	font-weight: bold;
}
</style>

<script src="/WEB-INF/scripts/validator.js"></script>
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

<div align="center">
<h1>GAS online</h1>
<h2>Gruppi Acquisto Solidale</h2>
</div>

<h4 class="error">${error}</h4>

<table align="center" border="1" width="70%">
<tr><th>Login or register to continue</th></tr>
<tr>
<td>

	<h3>Login</h3>
	<loginform:form method="post" commandName="login">
		
		<loginform:hidden path="command" value="login"/>
		
		<loginform:errors path="username" cssClass="error" />
		Username: <loginform:input path="username" id="username" />
		<br>
		<loginform:errors path="password" cssClass="error" />
		Password: <loginform:password path="password" id="password" />
		<br>
			
		<input type="submit" value="Login">
	
	</loginform:form>
</td>
</tr>
<tr>
<td>

	<h3>Register</h3><br>
	   	
	<registerform:form method="post" commandName="register">
	
		<registerform:hidden path="command" value="register"/>
	
		<registerform:errors path="username" cssClass="error" />
		Username: <registerform:input path="username" id="username" onkeyup="stringMinMax(this, 1, 20)" /><br>
		<registerform:errors path="password" cssClass="error" />
		Password: <registerform:password path="password" id="password" onkeyup="stringMinMax(this, 1, 32)"/><br>
		<registerform:errors path="name" cssClass="error" />
		Name: <registerform:input path="name" id="name" onkeyup="stringMinMax(this, 1, 20)" />
		<registerform:errors path="surname" cssClass="error" />
		Surname: <registerform:input path="surname" id="surname" onkeyup="stringMinMax(this, 1, 20)"/><br>
		Birth Date (day/month/year):
		<registerform:errors path="day" cssClass="error" />
		<registerform:input path="day" id="day" size="2" onkeyup="numberMinMax(this, 1, 31)" /> / 
		<registerform:errors path="month" cssClass="error" />
		<registerform:input path="month" id="month" size="2" onkeyup="numberMinMax(this, 1, 12)" /> / 
		<registerform:errors path="year" cssClass="error" />
		<registerform:input path="year" id="year" size="4" onkeyup="numberMinMax(this, 1900, 2000)" />
		
		<br><br>
		<registerform:radiobutton path="role" value="0" onclick="toogleProducerForm()" />Normal user<br>
		<registerform:radiobutton path="role" value="1" onclick="toogleProducerForm()" />Delegate<br>
		<registerform:radiobutton path="role" value="2" onclick="toogleProducerForm()" id="is_producer" />Producer<br>
	
		<div id="producerForm">
		<registerform:errors path="companyName" cssClass="error" />
		Company Name:<registerform:input path="companyName" id="companyName" onkeyup="stringMinMax(this, 1, 40)" /><br>
		<registerform:errors path="description" cssClass="error" />
		Description: <registerform:input path="description" id="description" onkeyup="stringMinMax(this, 1, 40)" /><br>
		<registerform:errors path="contact" cssClass="error" />
		Contact: <registerform:input path="contact" id="contact" onkeyup="stringMinMax(this, 1, 20)"/><br>
		<registerform:errors path="address" cssClass="error" />
		Address: <registerform:input path="address" id="address" onkeyup="stringMinMax(this, 1, 40)" /><br><br>
		<registerform:errors path="phoneNumber" cssClass="error" />
		Phone Number: <registerform:input path="phoneNumber" id="phoneNumber" onkeyup="stringMinMax(this, 1, 15)" /><br>
		<registerform:errors path="faxNumber" cssClass="error" />
		Fax Number: <registerform:input path="faxNumber" id="faxNumber" onkeyup="stringMinMax(this, 1, 15)" /><br>
		<registerform:errors path="email" cssClass="error" />
		Email: <registerform:input path="email" id="email" onkeyup="stringMinMax(this, 1, 30)"/><br><br>
		<registerform:errors path="paymentMode" cssClass="error" />
		Payment Mode: <registerform:input path="paymentMode" id="paymentMode" onkeyup="stringMinMax(this, 1, 20)" /><br><br>
		<registerform:errors path="delegate" cssClass="error" />
		<b>Delegate Username:</b> <registerform:input path="delegate" id="delegate" onkeyup="stringMinMax(this, 1, 20)" /><br>
		</div>
		
		<input type="submit" value="Register">
	
	</registerform:form>

</td>
</tr>
</table>

</body>
</html>