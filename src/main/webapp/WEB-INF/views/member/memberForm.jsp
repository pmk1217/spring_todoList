<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"   isELIgnored="false"   errorPage="error.jsp"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<%
   request.setCharacterEncoding("UTF-8");
%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입창</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
    function checkDuplicateId() {
        var userid = $("#id").val();

        if(userid == "") {
        	alert("아이디를 입력해주세요.");
        	return false;
        }
        
        var regExp = /[a-zA-Z0-9]/g;
        if (!regExp.test(userid)) {
            alert("영문 숫자만 가능합니다.");
            return false;
        }

        if (userid.trim().length < 3 || userid.trim() === "") {
            alert("아이디는 3자 이상, 공백을 포함하지 않아야 합니다.");
            return false;
        }
        
        $.ajax({
            type: "POST",
            url: "${contextPath}/member/idDuplicated.do",
            data: { userid: userid },
            success: function(response) {
                if (response == "false") {
                    alert("사용할 수 있는 ID입니다.");
                    $('#checkButton').prop("disabled", true);
                    $('#id').prop("readonly", true);
                } else {
                    alert("이미 존재하는 ID입니다.");
                }
            },
            error: function(xhr, status, error) {
                alert("서버와의 통신에 실패했습니다.");
            }
        });
    }
    
    $(document).ready(function() {
        $("form").submit(function(event) {
            if (!$('#checkButton').prop("disabled")) {
                event.preventDefault(); 
                alert("아이디 중복을 확인해주세요.");
            }
        });
    });

    
    function checkInput() {
        var userpwd = $("#password").val();
        var username = $("#name").val();

        
        if (userpwd.trim().length < 3 || userpwd.trim() === "") {
            alert("비밀번호는 3자 이상, 공백을 포함하지 않아야 합니다.");
            return false;
        }

        if (username.trim().length < 2 || userpwd.trim() === "") {
            alert("이름은 2자 이상, 공백을 포함하지 않아야 합니다.");
            return false;
        }
        return true;
    }
    
    
</script>
<style type="text/css">
	<%@ include file="../static/css/reset.css" %>
	<%@ include file="../static/css/login.css" %>
</style>
</head>
<body>
	
	<div class="main">
        <div class="container" style="background-color: #fdfeff;">
            <div class="logo">
                <a href="#"><span>TodoList</span></a>
            </div>
            <div class="content">
                <div class="tit">Sign up</div>
                <div class="form" style="width: 80%">   
                    <form method="post"   action="${contextPath}/member/addMembers.do"  onsubmit="return checkInput()">
                    <table>
						<tr>
							<td> 아이디 </td>
							<td width="60%"><input type="text"  name="userid"  placeholder="ID"  id="id" required></td>
							<td width="20%"><input type="button"  value="중복체크"  id="checkButton"  onclick="checkDuplicateId()"></td>
				         </tr>
				         <tr>
					         <td> 패스워드 </td>
					         <td colspan="2"><input type="password" name="userpwd"   id="password"  placeholder="password" required> </td>
				        </tr>
				         <tr>
					         <td> 이름 </td>
					         <td colspan="2"><input type="text" name="username"  id="name" placeholder="name" required> </td>
				        </tr>
				        <tr>
					        <td colspan="3">
					        <button type="submit"  id="submit">회원가입</button>
				        </td>
				      </table>
				    </form>
				    <ul>
				        <li class="signin"><a href="#"  onclick="history.back()">뒤로가기</a></li>
				    </ul>				   
                </div>
            </div>
        </div>
    </div>
</body>
