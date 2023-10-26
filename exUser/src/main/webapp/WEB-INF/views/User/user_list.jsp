<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>회원목록 보여주기</title>

<STYLE TYPE="text/css">
<!--
body {
	font-family: 돋움, Verdana;
	font-size: 9pt
}

td {
	font-family: 돋움, Verdana;
	font-size: 9pt;
	text-decoration: none;
	color: #000000
}
---
>
</STYLE>
</head>
<body>
	<table width="550" border="1" cellspacing="0" cellpadding="2" bordercolorlight="#173E7C" bordercolordark="white">
		<tr>
			<td width=50 align=center>번호</td>
			<td width=50 align=center>ID</td>
			<td width=80 align=center>이름</td>
			<td width=100 align=center>전화번호</td>
			<td width=100 align=center>등록일자</td>
			<td width=100 align=center>최근접속일</td>

		</tr>
		<c:set var="cnt" value="1" />
		<c:forEach var="user" items="${list}">
			<tr>
				<td align=center>${cnt}</td>
				<td align=center>${user.userid}</td>
				<td align=center>${user.name}</td>
				<td align=center>${user.tel}</td>
				<td align=center>${user.first_time.substring(0,10)}</td>
				<td align=center>${user.last_time.substring(0,10)}</td>
			</tr>
			<c:set var="cnt" value="${cnt+1}" />
		</c:forEach>
	</table>
	<table width=550>
		<tr>
			<td><select name="search_gubun">
					<option>이름</option>
					<option>주소</option></td>
			<td>찾는이름: 
				<input type="text" name="search_name" size=10>&nbsp; 
				<input type=hidden name="user_id">[조회]</a>
			</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<c:if test="${user==null}">
				<td><a href="user_login">로그인</a></td>
				<td><a href="user_insert">회원가입</a></td>
			</c:if>
			<c:if test="${user!=null}">
				<td><a href="user_logout">로그아웃</a></td>
				<td><a href="user_modify">정보수정 이동</a></td>
			</c:if>
		</tr>
	</table>
</body>
</html>
