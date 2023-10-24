<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>void Test</h1>
	<!-- dto가 아닌 일반자료형은 강제로 넘겨야함 -->
	<!-- dto를 꺼내올 때 클래스의 이름 앞글자를 소문자로 하면 됨 -->
	name = ${testDTO.name}<br>
	age = ${testDTO.age}<br>
</body>
</html>