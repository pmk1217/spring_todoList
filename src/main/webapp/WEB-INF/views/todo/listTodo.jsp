<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false"   errorPage="error.jsp"  %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<%
  request.setCharacterEncoding("UTF-8");
%>    
<%
   HttpSession SessionCheck = request.getSession();

   Object loginInfo = SessionCheck.getAttribute("loginInfo");
%>
<html>
<head>
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
 <script>
    $(document).ready(function() {
        $("#insert").click(function() {
            var loginCheck = <%= session.getAttribute("loginInfo") != null %>;

            if (!loginCheck) {
            	alert("로그인 해주세요");
                window.location.href =  "<%= request.getContextPath() %>/member/loginForm.do";
            } else {
                window.location.href = "<%= request.getContextPath() %>/todo/todoForm.do";
            }
        });
    });
    
    function confirmDelete() {
        var result = confirm("삭제하시겠습니까?");
        return result;
    }
    
    function memberDelete() {
        var result = confirm("정말 탈퇴하시겠습니까?");
        return result;
    }
</script>

<meta charset="UTF-8">
<title>Todo 리스트</title>
<style type="text/css">

	<%@ include file="../static/css/list.css" %>
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
<div style="width:60%; margin: 0 auto;">
		<div style="display: flex; justify-content: space-between; align-items: center;">
			<div> 
			<button id="insertbtn"  style="font-size: 18px; margin-top: 20px;">Todo작성</button>
			</div>
			
			<h1 style="margin: 0 auto;">Todo List</h1>
			
			<div>
			<button id="insert"  style="font-size: 18px; margin-top: 20px;">Todo작성</button>
			</div>
		</div>
		
			<div id="container">
				<c:choose>
					<c:when test="${todoList.size() < 1}">	
						<div id="notable">		
							<span>작성한 TodoList가 없습니다~</span>
						</div>	
					</c:when>
					<c:otherwise>
						 <c:forEach var="todo" items="${todoList}" > 
						 <div id="onetable">
							<div class="content" style="border-bottom: 1px solid #ddd;">
					    		<span class="title">${todo.title}</span>
						    	<div class="date">
							    	<span>${todo.regdate}</span>
							    	~
								    <span>${fn:substring(todo.deadline, 0, 10)}</span>
							    	  <c:choose>
											<c:when test="${todo.result == 'complete'}">
												<span class="result" style="color: white">완료</span>
											</c:when>
											<c:when test="${todo.result == 'fail'}">
												<span class="result" style="background-color: yellow;">실패</span>
											</c:when>
											<c:when test="${todo.result == 'pend'}">
												<span class="result" style="background-color: orange;">보류</span>
											</c:when>
									  </c:choose>
								  </div>
							 </div>
							 <div class="content">
								<span class="con">${todo.content}</span>
							</div>
							<div class="content" style="text-align: right;">
					       		 <a href="${contextPath}/todo/modTodoForm.do?todoseq=${todo.todoseq}" class="btn" style="border:2px solid gray;">수정</a>
					       		 <a href="${contextPath}/todo/removeTodo.do?todoseq=${todo.todoseq}" class="btn"  onclick="return confirmDelete()">삭제</a>
							</div>
						</div> 
					 </c:forEach>
				</c:otherwise>     
			</c:choose>
			</div>
					
					<div class="page">
				       <c:if test="${pageNo > 1}">
						    <a class="next" href="?userid=${loginInfo.userid}&amp;pageNo=${pageNo - 1}">이전</a>
						</c:if>				
						<c:forEach var="page" begin="1" end="${totalPages}">
						    <c:choose>
						        <c:when test="${pageNo == page}">
						            <strong class="thispage">${page}</strong>
						        </c:when>
						        <c:otherwise>
						            <a class="pagebtn" href="?userid=${loginInfo.userid}&amp;pageNo=${page}">${page}</a>
						        </c:otherwise>
						    </c:choose>
						</c:forEach>					
						<c:if test="${pageNo < totalPages}">
						    <a class="next"  href="?userid=${loginInfo.userid}&amp;pageNo=${pageNo + 1}">다음</a>
						</c:if>
    				</div>
    			
    			<div class="search">
    			<form action="${contextPath}/todo/todoSearch.do" method="get">
    			<input type="hidden" name="userid" value="${loginInfo.userid}">
    			  <select id="searchType" name="searchType">
		                <option value="title">제목</option>
		                <option value="content">내용</option>
		                <option value="regdate">등록일</option>
		                <option value="deadline">마감일</option>
		            </select>
		            <input type="text" id="keyword" name="keyword">	         
		            <button type="submit">검색</button>
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
