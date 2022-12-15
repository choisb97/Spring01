<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>** MemberDetail Spring_MVC2 **</title>
	<link rel="stylesheet" type="text/css" href="resources/myLib/myStyle.css">
</head>
<body>

<h2>** MemberDetail Spring_MVC2 **</h2>
=> Request Member_ID : ${param.id}<br>
<hr>

<c:if test="${not empty apple}">
	<table style="text-align: center;">
		<tr height="40">
			<td bgcolor="MediumPurple" style="text-align: center;">I D</td>
			<td>${apple.id}</td>
		</tr>
		
		<tr height="40">
			<td bgcolor="MediumPurple" style="text-align: center;">Password</td>
			<td>${apple.password}</td>
		</tr>
		
		<tr height="40">
			<td bgcolor="MediumPurple" style="text-align: center;">Name</td>
			<td>${apple.name}</td>
		</tr>
		
		<tr height="40">
			<td bgcolor="MediumPurple" style="text-align: center;">Info</td>
			<td>${apple.info}</td>
		</tr>
		
		<tr height="40">
			<td bgcolor="MediumPurple" style="text-align: center;">Birthday</td>
			<td>${apple.birthday}</td>
		</tr>
		
		<tr height="40">
			<td bgcolor="MediumPurple" style="text-align: center;">Jno</td>
			<td>${apple.jno}</td>
		</tr>
		
		<tr height="40">
			<td bgcolor="MediumPurple" style="text-align: center;">Age</td>
			<td>${apple.age}</td>
		</tr>
		
		<tr height="40">
			<td bgcolor="MediumPurple" style="text-align: center;">Point</td>
			<td>${apple.point}</td>
		</tr>
	</table>
</c:if>

<c:if test="${not empty message}">
	${message}<br>
</c:if>

<br>
&nbsp;&nbsp;&nbsp;<a href="mdetail?jCode=U&id=${apple.id}">[내 정보 수정]</a>
<!-- memberList에서 요청한 경우는 session에 보관된 id로는 수정할 수 없기 때문에 parameter로 처리 함 -->
&nbsp;&nbsp;&nbsp;<a href="mdelete?id=${apple.id}">[회원 탈퇴]</a><br>
<hr>
&nbsp;&nbsp;&nbsp;<a href="javascript:history.go(-1)">[이전으로]</a>
&nbsp; &nbsp; &nbsp;<a href="home">[Home]</a>
<hr>
&nbsp;&nbsp;&nbsp;<a href="logout">[Logout]</a>

</body>
</html>