<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>Jsp-basicLib</title>

<%@ include file="/WEB-INF/views/commonJsp/basicLib.jsp"%>

</head>

<body>

	<!-- header -->
	<%@ include file="/WEB-INF/views/commonJsp/header.jsp"%>

	<div class="container-fluid">
		<div class="row">

			<div class="col-sm-3 col-md-2 sidebar">
				
				<%@ include file="/WEB-INF/views/commonJsp/left.jsp"%>

			</div>

			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

				<div class="row">
					<div class="col-sm-8 blog-main">
						<h2 class="sub-header">제품리스트</h2>
						<div class="table-responsive">
						   	<table class="table table-striped" border='1'>
								<tr>
									<th>제품그룹명</th>
									<th>제품그룹번호</th>
									<th>바이어 이름</th>
									<th>제품 아이디</th>
									<th>제풍명</th>
									<th>제품 가격</th>
								</tr>
							<c:forEach items="${prodList }" var="list">
								<tr>
									<td>${list.lprod_nm }</td>
									<td>${list.prod_lgu }</td>
									<td>${list.buyer_name }</td>
									<td>${list.prod_id }</td>
									<td>${list.prod_name }</td>
									<td>${list.prod_price }</td>
								</tr>		
							</c:forEach>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>