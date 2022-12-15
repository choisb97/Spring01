<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>** LoginForm Spring_MVC2 **</title>
	<link rel="stylesheet" type="text/css" href="resources/myLib/myStyle.css">
</head>
<body>

<h2>** LoginForm Spring_MVC2 **</h2>

<form action="login" method="post">
	<table style="text-align: center;">
		<tr>
			<td bgcolor="MediumSlateBlue">I D</td>
			<td><input type="text" name="id" value="admin"></td>
		</tr>
		
		<tr>
			<td bgcolor="MediumSlateBlue">PassWord</td>
			<td><input type="password" name="password" value="12345!"></td>
		</tr>
		
		<tr><td></td>
			<td><input type="submit" value="Login">&nbsp;&nbsp;
			    <input type="reset" value="Reset"></td>
		</tr>
	</table>

</form>

	<c:if test="${not empty message}">
	<hr>
		${message}<br>
	</c:if>
<hr>
&nbsp;&nbsp;<a href="javascript:history.go(-1)">[이전으로]</a>
&nbsp;&nbsp;<a href="home">[Home]</a>

</body>
</html>