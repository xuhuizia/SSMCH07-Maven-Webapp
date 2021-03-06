<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="/jsp/common/head.jsp"%>
<div class="right">
	<div class="location">
		<strong>你现在所在的位置是:</strong> <span>用户管理页面</span>
	</div>
	<div class="search">
		<form method="post"
			action="${pageContext.request.contextPath }/info.do">
			<input name="method" value="query" class="input-text" type="hidden">
			<span>用户名：</span> <input name="queryname" class="input-text"
				type="text" value="${queryname }"> <span>用户角色：</span> <select
				name="queryUserRole">
				<c:if test="${roleList != null }">
					<option value="0">--请选择--</option>
					<c:forEach var="role" items="${roleList}">
						<option
							<c:if test="${role.id == queryUserRole }">selected="selected"</c:if>
							value="${role.id}">${role.roleName}</option>
					</c:forEach>
				</c:if>
			</select> <input type="hidden" name="pageIndex" value="1" /> <input
				value="查 询" type="submit" id="searchbutton"> <a
				href="${pageContext.request.contextPath}/goAdd.do">添加用户</a>
		</form>
	</div>
	<!--用户-->
	<table class="providerTable" cellpadding="0" cellspacing="0">
		<tr class="firstTr">
			<th width="10%">用户编码</th>
			<th width="20%">用户名称</th>
			<th width="10%">性别</th>
			<th width="10%">年龄</th>
			<th width="10%">电话</th>
			<th width="10%">用户角色</th>
			<th width="30%">操作</th>
		</tr>
		<c:forEach var="user" items="${user }" varStatus="status">
			<tr data-id="${user.id }">
				<td><span>${user.userCode }</span></td>
				<td><span>${user.userName }</span></td>
				<td><span> <c:if test="${user.gender==1}">男</c:if> <c:if
							test="${user.gender==2}">女</c:if>
				</span></td>
				<td><span><fmt:formatDate value="${user.birthday}"
							pattern="yyyy-MM-dd" /></span></td>
				<td><span>${user.phone}</span></td>
				<td><span>${user.roleName}</span></td>
				<td><span><a class="viewUser" href="javascript:;"
						userid=${user.id } username=${user.userName }><img
							src="${pageContext.request.contextPath }/images/read.png"
							alt="查看" title="查看" /></a></span> <span><a class="modifyUser"
						href="javascript:;" userid=${user.id } username=${user.userName }><img
							src="${pageContext.request.contextPath }/images/xiugai.png"
							alt="修改" title="修改" /></a></span> <span><a class="deleteUser"
						href="javascript:;" userid=${user.id } username=${user.userName }><img
							src="${pageContext.request.contextPath }/images/schu.png"
							alt="删除" title="删除" /></a></span></td>
			</tr>
		</c:forEach>
	</table>
	<input type="hidden" id="totalPageCount" value="${totalPageCount}" />
	<c:import url="rollpage.jsp">
		<c:param name="totalCount" value="${totalCount}" />
		<c:param name="currentPageNo" value="${currentPageNo}" />
		<c:param name="totalPageCount" value="${totalPageCount}" />
	</c:import>
	<div id="userInfo" style="border:1px black solid;position:absolute;margin-top:5px;top:0px;left:0px;background-color:white;padding:5px;display:none;">
		<p>
			<strong>用户编号：</strong><span id="userCode"></span>
		</p>
		<p>
			<strong>用户名称：</strong><span id="userName"></span>
		</p>
		<p>
			<strong>用户性别：</strong><span id="gender"></span>
		</p>
		<p>
			<strong>出生日期：</strong><span id="birthday"></span>
		</p>
		<p>
			<strong>用户电话：</strong><span id="phone"></span>
		</p>
		<p>
			<strong>用户地址：</strong><span id="address"></span>
		</p>
		<p>
			<strong>用户角色：</strong><span id="roleName"></span>
		</p>
	</div>
	</section>

	<!--点击删除按钮后弹出的页面-->
	<div class="zhezhao"></div>
	<div class="remove" id="removeUse">
		<div class="removerChid">
			<h2>提示</h2>
			<div class="removeMain">
				<p>你确定要删除该用户吗？</p>
				<a href="#" id="yes">确定</a> <a href="#" id="no">取消</a>
			</div>
		</div>
	</div>

	<%@include file="/jsp/common/foot.jsp"%>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/js/userlist.js"></script>