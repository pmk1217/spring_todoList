<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"   isELIgnored="false"   errorPage="error.jsp"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<%
   request.setCharacterEncoding("UTF-8");
%> 
<%
   HttpSession SessionCheck = request.getSession();

   Object loginInfo = SessionCheck.getAttribute("loginInfo");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Todo 작성</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
 <script>
    $(document).ready(function() {
        $("#insert").click(function() {
            var loginCheck = <%= session.getAttribute("loginInfo") != null %>;

            if (!loginCheck) {
            	alert("로그인 해주세요");
                window.location.href =  "<%= request.getContextPath() %>/member/loginForm.do";
            } 
        });
    });
    
    document.addEventListener("DOMContentLoaded", function() {
        document.getElementById('deadlineInput').addEventListener('change', function() {
            var selectedDate = new Date(this.value);
            var currentDate = new Date();

            if (selectedDate < currentDate) {
                alert("선택한 날짜는 현재 날짜보다 이전입니다. 다시 선택해주세요.");
                this.value = '';
            }
        });
    });
    
    function memberDelete() {
        var result = confirm("정말 탈퇴하시겠습니까?");
        return result;
    }
</script>
<style>
 	<%@ include file="../static/css/form.css" %>
</style>
</head>
<body>
<header>
	<nav>
		<a href="${contextPath}/todo/listTodo.do?userid=${loginInfo.userid}" class="logo">min's TodoList</a>
		<div class="logbox">
		<c:choose>
			<c:when test="${loginInfo == null}">
				<a href="${contextPath}/member/loginForm.do">로그인</a>
				<a href="${contextPath}/member/memberForm.do">회원가입</a>
			</c:when>
			<c:otherwise>
				<span> ${loginInfo.userid}님 안녕하세요! &nbsp;</span>
				<b><a href="${contextPath}/member/logout.do">로그아웃</a></b>
			<b><a href="${contextPath}/member/removeMember.do?userid=${loginInfo.userid}" onclick="return memberDelete()">회원탈퇴</a></b>
			</c:otherwise>
		</c:choose>
		</div>
	</nav>
</header>
<div class="main">
<div id="form">
	<form method="post"   action="${contextPath}/todo/addTodo.do">
	<h1  class="text_center">Todo 작성</h1>
	<table  align="center">
	   <tr>
	      <td width="100"><p align="right">목표 ! &nbsp;</p></td>
	      <td width="400"><input type="text" name="title"  size="30" required></td>
	    </tr>
	    <tr>
	       <td width="100"><p align="right">내용! &nbsp;</p></td>
	       <td width="400"><p><input type="text" name="content"  size="30"  required></td>
	    </tr>
	     <tr>
	       <td width="100"><p align="right">언제까지 ! &nbsp;</p></td>
	       <td width="400"><p><input type="date" name="deadline"  id="deadlineInput"  style="width: 310px;" required></td>
	    </tr>
	     <tr>
	       <td width="100"><p align="right">결과 ! &nbsp;</p></td>
	       <td width="400">
	       	   <p>
            <input type="radio" name="result" value="complete" required> 완료
            <input type="radio" name="result" value="fail"> 실패
            <input type="radio" name="result" value="pend"> 보류
    		   </p>
    		 </td>
	    </tr>
	    <tr>
	       <td colspan="2" width="400" align="right">
	       <input class="btn"  style="background-color: gray;" id="insert" type="submit" value="작성하기">
	       <input class="btn"  style="background-color: #9287eb;" type="button" onclick="history.back()"  value="취소"></td>
	    </tr>
	</table>
	</form>
</div>
</div>
<footer>
	<div class="footer">
		<span>Copyright &copy; min's TodoList</span>
	</div>
</footer>
</body>
</html>
