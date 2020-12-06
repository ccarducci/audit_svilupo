<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@page
	language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<title>error</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<h4>The application has malfunctioned.</h4>
 
<p>  Please contact technical support with the following information:</p> 
 
<h4>Exception Name: <s:property value="exception" /> </h4>
 
<h4>Exception Details: <s:property value="exceptionStack" /></h4> 
</body>
</html>