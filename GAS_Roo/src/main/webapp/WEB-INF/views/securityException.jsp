<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<div xmlns:c="http://java.sun.com/jsp/jstl/core" xmlns:field="urn:jsptagdir:/WEB-INF/tags/form/fields" xmlns:form="urn:jsptagdir:/WEB-INF/tags/form" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:spring="http://www.springframework.org/tags" version="2.0">
    <jsp:directive.page contentType="text/html;charset=UTF-8"/>

<html>
<head>
    <title>Security exception</title>
</head>
<body>
<b><h2>Permission denied</h2></b>

<c:if test="${not empty exception}">

    <spring:message var="message" code="exception_message" htmlEscape="false" />

        <c:out value="${exception.localizedMessage}" />

</c:if>

</body>

</html>

</div>