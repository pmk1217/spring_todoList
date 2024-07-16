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
	<%@ include file="./static/css/reset.css" %>
	<%@ include file="./static/css/login.css" %>
</style>
<style type="text/css">
	.loginbtn {
		color:black;
	}

	.loginbtn:hover{ 
		color:skyblue;
	}
	
</style>

<body>
<div class="main">
        <div class="container" style="background-color: #fdfeff;">
            <div class="logo">
                <a href="#"><span>min's TodoList</span></a>
            </div>
            <div class="content">
                <div class="tit"><a class="loginbtn" href="${contextPath}/member/loginForm.do" >로그인</a></div>
                <div class="form">   
				    <ul>
				        <li class="signin"><a href="${contextPath}/member/memberForm.do"  >회원가입</a></li>
				    </ul>				   
                </div>
            </div>
        </div>
    </div>
</body>
</html>