<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>

</head>

<body>

<form method="post" name="addProduct">
	Product Name: <form:input path="productname" id="productname" />
	Description: <form:textarea path="description" id="description"/>
	Producer: <form:input path="producer" id="idproducer" />
	Quantity: <form:input path="quantity" id="quantity" />
	Measuring Unit: <form:select path="units" id="units">
			<option value="" selected="selected">- Choose -</option>
			<option value="kg">Kilograms</option>
			<option value="m">Meters</option>
			<option value="l">Liters</option>
			<option value="other">Other</option>
		</form:select>
	Dimensions: <form:input path="dimensions" id="dimensions" />
	Transport cost: <form:input	path="transportcost" id="transportcost" />
	Unit cost: <form:input path="unitcost" id="unitcost" />
	Maximum amount to buy: <form:input path="maxamount" id="maxamount" />
	Minimum amount to buy: <form:input path="minamount" id="minamount" />
	Available: <form:checkbox path="available" id="available"/>

	<input type="submit" value="Add">
</form>


</body>
</html>