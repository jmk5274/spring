<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<% 
	Date date = (Date) request.getAttribute("nowDt"); 
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
%>
</head>
<body>
	<h2>hello.jsp</h2>
	<%-- model 객체에 추가한 속성 두가지 nowDt, msg --%>
	<%=request.getAttribute("msg") %><br>
	현재시간 : <%=sdf.format(date) %><br><br>
	${msg }<br>
	현재시간 : <fmt:formatDate value="${nowDt }" pattern="yyyy-MM-dd"/><br>
</body>
</html>