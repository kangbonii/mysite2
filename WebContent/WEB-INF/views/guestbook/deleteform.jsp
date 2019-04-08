<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="/mysite/assets/css/mysite.css" rel="stylesheet"
	type="text/css">
<link href="/mysite/assets/css/guestbook.css" rel="stylesheet"
	type="text/css">
<title>Mysite</title>
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/includes/header.jsp"></jsp:include><!-- /header -->


		<jsp:include page="/WEB-INF/views/includes/navigation.jsp"></jsp:include><!-- /navigation -->

		<div id="content">
			<div id="c_box">
				<div id="guestbook" class="deleteform">
					<h2>방명록삭제</h2>

					<form class="form-box" method="GET" action="./gb">
						<input type="hidden" name="action" value="delete"> 
						<input type="hidden" name="no" value=${vo.no}>
						<div class="form-group">
							<label class="block-label">비밀번호</label> <input type="password"
								name="passwd" value="">
						</div>

						<input type="submit" value="확인">
					</form>
					<a href="">방명록 리스트</a>

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