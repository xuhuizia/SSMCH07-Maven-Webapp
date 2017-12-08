<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	
</script>
</head>
<body>
	<div class="page-bar">
				<ul class="page-num-ul clearfix">
				<li>共${param.totalCount }条记录&nbsp;&nbsp; ${param.currentPageNo }/${param.totalPageCount }页</li>
				<c:if test="${param.currentPageNo > 1}">
					<a href="javascript:page_nav(document.forms[0],1);">首页</a>
					<a href="javascript:page_nav(document.forms[0],${param.currentPageNo-1});">上一页</a>
				</c:if>
				<c:if test="${param.currentPageNo < param.totalPageCount }">
					<a href="javascript:page_nav(document.forms[0],${param.currentPageNo+1 });">下一页</a>
				<a
					href="javascript:page_nav(document.forms[0],${param.totalPageCount });">最后一页</a>
			</c:if>
				&nbsp;&nbsp;
			</ul>
			<%-- <ul class="page-num-ul clearfix">
		<li>共${userNum }条记录&nbsp;&nbsp; ${currentPage }/${pageTimes }页</li>
		<c:if test="${currentPage == 1}">
			<span class="disabled"> 上一页</span>
		</c:if>
		<c:if test="${currentPage != 1}">
			<a href="info.do?page=${currentPage-1}"> 上一页</a>
		</c:if>
		<c:if test="${currentPage == 1}">
			<span class="current">1</span>
		</c:if>
		<c:if test="${currentPage != 1}">
			<a href="info.do?page=1">1</a>
		</c:if>
		<%
			int pageTimes = (Integer) session.getAttribute("pageTimes");
			for (int i = 1; i < pageTimes; i++) {
				request.setAttribute("page", i + 1);
		%>

		<c:if test="${currentPage == page}">
			<span class="current"><%=i + 1%></span>
		</c:if>

		<c:if test="${currentPage != page}">
			<a href="info.do?page=<%=i + 1%>"><%=i + 1%></a>
		</c:if>
		<%
			}
		%>
		<c:if test="${currentPage == pageTimes}">
			<span class="disabled">下一页 </span>
		</c:if>
		<c:if test="${currentPage != pageTimes}">
			<a href="info.do?page=${currentPage+1}">下一页 </a>
		</c:if>
		</ul> --%>
		<span class="page-go-form"><label>跳转至</label> <input
			type="text" name="currentPage" id="inputPage" class="page-key" />页
			<button type="button" class="page-btn"
				onClick='jump_to(document.forms[0],document.getElementById("currentPage").value)'>GO</button>
		</span>
	</div>
</body>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/rollpage.js"></script>
</html>