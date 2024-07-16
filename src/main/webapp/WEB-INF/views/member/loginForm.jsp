<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"   isELIgnored="false"   errorPage="error.jsp"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>       
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"  />  
<%
request.setCharacterEncoding("UTF-8");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Login</title>
</head>
<style type="text/css">
	<%@ include file="../static/css/reset.css" %>
	<%@ include file="../static/css/login.css" %>
</style>

<body>

<div class="main">
        <div class="container" style="background-color: #fdfeff;">
            <div class="logo">
                <a href="#"><span>TodoList</span></a>
            </div>
            <div class="content">
                <div class="tit">Login</div>
                <div class="form">   
                    <form action="${contextPath}/member/login.do" method="post">
                    	<span>아이디</span>
				         <input type="text"  name="userid"  placeholder="ID"  value="min" required>
				        <span>패스워드</span>
				        <input type="password" name="userpwd"  id="password"  placeholder="password"  value="1111" required> 
				        <span style="text-align: center; color:red; font-size: 16px; font-weight: bold;"> ${ErrorMsg} </span>
				        <button type="submit"  id="submit">로그인</button>
				    </form>
				    <ul>
				        <li class="signin"><a href="${contextPath}/member/memberForm.do"  >회원가입</a></li>
				    </ul>				   
                </div>
            </div>
        </div>
    </div>
</body>
</html>