<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false"   errorPage="error.jsp"  %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<%
  request.setCharacterEncoding("UTF-8");
%>    


<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 출력창</title>
<style>
	.text_center {
		text-align: center;
	}
	body {
		font-size: 17px;
	}
	table {
		border-collapse:collapse;
	}
	td {
		padding-top:10px;
		padding-bottom:10px;
		border: none;
		border-bottom: 1px solid #ddd;
		
	}
	th {
		padding-top:10px;
		padding-bottom:10px;
		border:none;
		border-bottom: 2px solid #ddd;
		border-top: 2px solid #ddd;
	}
	input {
		padding-top: 5px;
		padding-bottom: 5px;
	}
	a {
		text-decoration: none;
		color:black;
	}
</style>
</head>
<body>
	<div style="width:80%; margin: 0 auto;">
		<h1 style="text-align: center;">멤버 정보</h1>
	
		<table align="center"  width="100%">
		    <tr align="center" >
		      <th><b>아이디</b></th>
		      <th><b>비밀번호</b></th>
		      <th><b>이름</b></th>
		      <th><b>삭제</b></th>
		   </tr>
	   
			 <c:forEach var="member" items="${membersList}" >     
			   <tr align="center">
			      <td>${member.userid}</td>
			      <td>${member.userpwd}</td>
			      <td>${member.username}</td>
			      <td><a href="${contextPath}/member/removeMember.do?userid=${member.userid}">삭제하기</a></td>
			    </tr>
			  </c:forEach>   
		</table>
		<a  href="${contextPath}/member/memberForm.do"   style="float: right; padding: 10px; font-size: 20px;">회원가입</a>
	</div>
</body>
</html>
