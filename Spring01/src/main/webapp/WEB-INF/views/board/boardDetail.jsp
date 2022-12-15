<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>** Board Detail Web_MVC2 **</title>
	
	<link rel="stylesheet" type="text/css" href="/Web02/myLib/myStyle.css">
</head>
<body>

<h2>** Board Detail Web_MVC2 **</h2>
=> Request Member_ID : ${param.id}<br>

<c:if test="${not empty message}">
	${message}<br>
</c:if>
<hr>

<c:if test="${not empty apple}">
	<table>
		<tr height="40">
			<td bgcolor="DarkKhaki" style="text-align: center;">Seq</td>
			<td>${apple.seq}</td>
		</tr>
		
		<tr height="40">
			<td bgcolor="DarkKhaki" style="text-align: center;">I  D</td>
			<td>${apple.id}</td>
		</tr>
		
		<tr height="40">
			<td bgcolor="DarkKhaki" style="text-align: center;">Title</td>
			<td>${apple.title}</td>
		</tr>
		
		<tr height="40">
			<td bgcolor="DarkKhaki" style="text-align: center;">Content</td>
			<td> <textarea rows="5" cols="50" readonly>${apple.content}</textarea></td>
		</tr>
		
		<tr height="40">
			<td bgcolor="DarkKhaki" style="text-align: center;">RegDate</td>
			<td>${apple.regdate}</td>
		</tr>
		
		<tr height="40">
			<td bgcolor="DarkKhaki" style="text-align: center;">조회수</td>
			<td>${apple.cnt}</td>
		</tr>
	</table>
</c:if>

<br>
<c:if test="${loginID == apple.id || loginID == 'admin'}">
	&nbsp;&nbsp;&nbsp;<a href="/Web02/bdetail?jCode=U&seq=${apple.seq}">[게시글 수정]</a>
	&nbsp;&nbsp;&nbsp;<a href="/Web02/bdelete?seq=${apple.seq}">[게시글 삭제]</a>
</c:if>

<c:if test="${not empty loginID}"> <!-- 로그인ID가 비어있지 않다면 = 로그인을 했다면 -->
	&nbsp;&nbsp;&nbsp;<a href="/Web02/board/rinsertForm.jsp?root=${apple.root}&step=${apple.step}&indent=${apple.indent}">[댓글 달기]</a><br>
</c:if>
<hr>
&nbsp;&nbsp;&nbsp;<a href="/Web02/blist">[BoardList]</a>
&nbsp; &nbsp; &nbsp; &nbsp;<a href="javascript:history.go(-1)">[이전으로]</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="/Web02/index.jsp">[Home]</a>

</body>
</html>