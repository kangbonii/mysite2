<%@page import="com.javaex.vo.GuestBookVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet"
	type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet"
	type="text/css">
<title>Mysite</title>
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/includes/header.jsp"></jsp:include>
		<!-- /header -->


		<jsp:include page="/WEB-INF/views/includes/navigation.jsp"></jsp:include>
		<!-- /navigation -->

		<div id="content">
			<div id="c_box">
				<div id="guestbook">
					<h2>방명록</h2>

					<form id="addform" action="${pageContext.request.contextPath}/gb" method="post">
					<input type="hidden" name="action" value="add">
						<table>
							<tr>
								<td>이름</td>
								<td><input type="text" name="name"></td>
								<td>비밀번호</td>
								<td><input type="password" name="passwd"></td>
							</tr>
							<tr>
								<td colspan=4><textarea cols="75" rows="8" form="addform"
										name="cols"></textarea></td>
							</tr>
							<tr>
								<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
							</tr>
						</table>
					</form>

					<c:forEach items="addlist" var="vo" varStatus="status">
						<table border="1" width="510">
						<tr>
							<td>${vo.no}</td>
							<td>${vo.name}</td>
							<td>${vo.reg_date}</td>
							<td><a
								href="${pageContext.request.contextPath}/gb?action=dform&no=${vo.no}">삭제</a></td>
						</tr>
						<tr>
							<td colspan="4">${vo.content}</td>
						</tr>
					</table>
					<br>
					</c:forEach>
					
	
				</div>
				<!-- /guestbook -->
			</div>
			<!-- /c_box -->
		</div>
		<!-- /content -->



		<jsp:include page="/WEB-INF/views/includes/footer.jsp"></jsp:include>
		<!-- /footer -->
	</div>
	<!-- /container -->
</body>
</html>

