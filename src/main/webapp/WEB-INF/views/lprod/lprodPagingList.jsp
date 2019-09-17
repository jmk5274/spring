<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<script>
	$(document).ready(function(){
		$(".lprodTr").click(function(){
// 			$(".lprod_gu").val($("td:eq(1)",this).text());
			$(".lprod_gu").val($(this).data("lprod_gu"));
			$("#frm").submit();
		});
	});
</script>
</head>

<body>
<form id="frm" action="${cp }/prodList">
	<input type="hidden" class="lprod_gu" name="lprod_gu"/>
</form>
	<%@ include file="/WEB-INF/views/commonJsp/header.jsp"%>

	<div class="container-fluid">
		<div class="row">

			<div class="col-sm-3 col-md-2 sidebar">
				
				<%@ include file="/WEB-INF/views/commonJsp/left.jsp"%>

			</div>

			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">


				<div class="row">
					<div class="col-sm-8 blog-main">
						<h2 class="sub-header">제품그룹</h2>
						<div class="table-responsive">
							<table class="table table-striped">
								<tr>
									<th>LPROD_ID</th>
									<th>LPROD_GU</th>
									<th>LPROD_NM</th>
								</tr>

								<c:forEach items="${lprodList }" var="list">
									<tr class="lprodTr" data-lprod_gu="${list.lprod_gu }">
										<td>${list.lprod_id }</td>
										<td>${list.lprod_gu }</td>
										<td>${list.lprod_nm}</td>
									</tr>
								</c:forEach>
							</table>
						</div>

						<a class="btn btn-default pull-right">제품 등록</a>

						<div class="text-center">
							<ul class="pagination">
								<%-- 이전 페이지 가기 : 지금 있는 페이지에서 한페이지 전으로 --%>
								<c:choose>
									<c:when test="${pageVo.page == 1 }">
										<li class="disabled">
											<span aria-label="Previous">&laquo;</span>
									</c:when>
									<c:otherwise>
										<li>
							 				<a href="${cp }/lprod/lprodPagingList?page=${pageVo.page-1}&pagesize=5" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
											</a>
									</c:otherwise>
								</c:choose>
										</li>
								
								<c:forEach begin="1" end="${paginationSize }" var="page">
									<c:choose>
										<c:when test="${page == pageVo.page }">
											<li class=active><span>${page }</span></li>
										</c:when>
										<c:otherwise>
											<li><a href="${cp }/lprod/lprodPagingList?page=${page }&pagesize=5">${page }</a></li>
										</c:otherwise>
									</c:choose>
<%-- 									<li <c:if test="${page == param.page }"> class="active" </c:if>><a href="${cp }/userPagingList?page=${page }&pagesize=10">${page }</a></li> --%>
								</c:forEach>
								
								<c:choose>
									<c:when test="${pageVo.page == paginationSize }">
										<li class="disabled">
											<span aria-label="Previous">&raquo;</span>
									</c:when>
									<c:otherwise>
										<li>
							 				<a href="${cp }/lprod//lprodPagingList?page=${pageVo.page+1}&pagesize=5" aria-label="Next">
												<span aria-hidden="true">&raquo;</span>
											</a>
									</c:otherwise>
								</c:choose>
										</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>