<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>탱큐</h2>
<c:choose>
	<c:when test="${row==1}">
		<script>
			alert("회원가입이 완료되었습니다.");
			location.replace("user_login");//로그인 폼으로 이동
		</script>
	</c:when>
	<c:when test="${row==0}">
		<script>
			alert("접속자가 많아서 등록이 지연되고 있습니다\n 잠시후에....");
			history.back();
		</script>
	</c:when>
</c:choose>

</body>
</html>