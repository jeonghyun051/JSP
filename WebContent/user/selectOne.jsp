<%@page import="com.cos.hello.model.Users"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file = "../layout/header.jsp"%>
<h1>User info</h1>

<%
	String result = (String)request.getAttribute("result");


%>

<%=result %>

<h1>${result}</h1>
<h1>${sessionScope.result }</h1> <!--tip. 세션은 명시적으로 적어두자 -->

<table border="1">
	<tr>
		<th>번호</th>
		<th>유저네임</th>
		<th>패스워드</th>
		<th>이메일</th>
	</tr>
	

	<tr>
		<td>${user.id}</td>
		<td>${user.username}</td>
		<td>${user.password}</td>
		<td>${user.email}</td>
	</tr>
</table>
<form action = "user?gubun=deleteProc" method ="post">
	<input type="hidden" name ="id" value=${user.id}"/>
	<button>삭제</button>
</form>
</body>
</html>