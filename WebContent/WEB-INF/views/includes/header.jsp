<%@page import="com.javaex.vo.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="header">
	<h1>MySite</h1>
	<ul>

	
			<!-- 로그인 전 -->
			<c:choose>
				<c:when test="${empty sessionScope.authUser}">
					<li><a href="${pageContext.request.contextPath}/user?action=loginform">로그인</a></li>
					<li><a href="">회원가입</a></li>
				</c:when>
				
				<c:otherwise>
					<li><a href="${pageContext.request.contextPath}/user?action=modifyform">회원정보수정</a></li>
					<li><a href="${pageContext.request.contextPath}/user?action=logout">로그아웃</a></li>
					<li>${sessionScope.authUser.name}님 안녕하세요^^;</li>
				</c:otherwise>
			</c:choose>

		
	</ul>
</div>
<!-- /header -->


