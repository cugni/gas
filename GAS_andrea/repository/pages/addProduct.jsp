<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="it.polito.ai.gas.business.*" %>
<%@ page import="it.polito.ai.gas.dao.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
<%
if (request.getParameter("action") != null && request.getParameter("action").equals("register"))
{
	String username = ""+request.getParameter("username");
	String password = ""+request.getParameter("password");
	String name = ""+request.getParameter("name");
	String surname = ""+request.getParameter("surname");
	int day = Integer.parseInt(""+request.getParameter("day"));
	int month = Integer.parseInt(""+request.getParameter("month")) -1;
	int year = Integer.parseInt(""+request.getParameter("year")) -1900;
	
	// NULL-CHECKS!!
	
	Date birth_date = new Date(year, month, day);
	
	User add = new User();
	add.setUsername(username);
	add.setPassword(password);
	add.setName(name);
	add.setSurname(surname);
	add.setRole(0); // default
	add.setBirthDate(birth_date);
	add.setStatistics(new Statistics());
	
	boolean producer_info = request.getParameter("producer_info") != null ? true : false;
	if (producer_info)
	{
		String company_name = ""+request.getParameter("company_name");
		String description = ""+request.getParameter("description");
		String contact = ""+request.getParameter("contact");
		String address = ""+request.getParameter("address");
		String phone_number = ""+request.getParameter("phone_number");
		String fax_number = ""+request.getParameter("fax_number");
		String email = ""+request.getParameter("email");
		String payment_mode = ""+request.getParameter("payment_mode");
		String delegate_name = ""+request.getParameter("delegate");
		
		// NULL-CHECKS!!
		
		User delegate = new User();
		delegate.setUsername(delegate_name);
		ProducerInfo producerInfo = new ProducerInfo();
		producerInfo.setCompanyName(company_name);
		producerInfo.setDescription(description);
		producerInfo.setContact(contact);
		producerInfo.setAddress(address);
		producerInfo.setPhoneNumber(phone_number);
		producerInfo.setFaxNumber(fax_number);
		producerInfo.setEmail(email);
		producerInfo.setPaymentMode(payment_mode);
		producerInfo.setDelegate(delegate);

		DAOFactory.getDAOFactoryHibernate().
				getProducerInfoDAOHibernate().addProducerInfo(producerInfo);
		
		add.setProducerInfo(producerInfo);
	}
	
	Approval approval = new Approval();
	approval.setUser(add);
	
	// ADD USER + ADD APPROVAL
	if (DAOFactory.getDAOFactoryHibernate().getUserDAOHibernate().addUser(add) != null
	&& DAOFactory.getDAOFactoryHibernate().getApprovalDAOHibernate().addApproval(approval) >=0)
	{
		System.out.println(add.getName()+" registered");
	}
	else
		System.out.println("Registering failed...");
}
%>
</head>
<body>

<form action="addProduct.jsp" method="post">
Name: <input type="text" name="name" value="Mele"/><br>
Description: <input type="text" name="description"/><br>
Cost: <input type="text" name="cost" value="10"/><br>
Quantity: <input type="text" name="quantity" value="65"/><br>
Units: <input type="text" name="units" value="Kg"/><br>
Dimensions: <input type="text" name="dimensions" value="Kg"/><br>

<input type="hidden" name="action" value="addProduct"/>
<input type="submit" value="Add"/>
</form>


</body>
</html>