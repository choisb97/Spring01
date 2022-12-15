<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>** After Delete Spring_MVC2 **</title>
	<link rel="stylesheet" type="text/css" href="resources/myLib/myStyle.css">
</head>
<body>

<h2>** After Delete Spring_MVC2 **</h2>

	<c:if test="${not empty message}">
	<hr>
	=> ${message}<br>
	</c:if>
<hr>
&nbsp;&nbsp;<a href="home">[Home]</a>

</body>
</html>