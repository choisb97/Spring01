<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Home</title>
	<link rel="stylesheet" type="text/css" href="resources/myLib/myStyle.css">
</head>
<body>
	<h1> Hello Spring!!! </h1>
	<h1> 안녕하세요~~!!! </h1>
	
	<P>  The time on the server is ${serverTime}. </P>
	<hr>
	<!-- Login 전, 후 구분 기능 추가하기 : 인삿말, 하단의 메뉴 구분 -->
	<c:if test="${not empty loginID}"> <!-- loginID가 비어있지 않으면(정확한 ID를 입력했으면) -->
	<h2>=> ${loginName}님 안녕하세요 *^^* </h2>
	</c:if>

	<c:if test="${not empty message}"> <!-- message가 비어있지 않으면(message가 있으면) -->
		=> ${message}<br>
	</c:if>
	<hr>
	<img alt="main" src="resources/images/lucky.png" width="300" height="250"> <!-- 경로 맨 앞에 "/" 붙이지 않기 -->
	<hr>
	
	<!-- Login 전 -->
<!--<c:if test="${empty loginID}"> -->
		&nbsp; &nbsp;<a href="loginf">[LoginF]</a>
		&nbsp; &nbsp; &nbsp;<a href="joinForm.jsp">[JoinForm]</a>
<!--</c:if> -->
		
	<!-- Login 후 -->
<!--  	<c:if test="${not empty loginID}"> -->
		&nbsp; &nbsp;<a href="logout">[Logout]</a><br><br>
		&nbsp; &nbsp;<a href="mdetail">[내 정보 보기]</a>
		&nbsp;&nbsp; &nbsp;<a href="mdetail?jCode=U">[내 정보 수정]</a>
		&nbsp;&nbsp; &nbsp;<a href="mdelete">[회원 탈퇴]</a>
<!--  	</c:if> -->
	<br><br>
	&nbsp; &nbsp;<a href="mlist">[MemberList]</a>
	
	<br><hr>
	&nbsp; &nbsp;<a href="blist">[BoardList]</a><br>
	
</body>
</html>
