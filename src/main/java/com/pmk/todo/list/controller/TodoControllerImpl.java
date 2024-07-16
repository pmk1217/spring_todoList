package com.pmk.todo.list.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pmk.todo.list.service.TodoService;
import com.pmk.todo.list.vo.TodoVO;
import com.pmk.todo.member.vo.MemberVO;

@Controller("todoController")
public class TodoControllerImpl implements TodoController{
	
	@Autowired
	private TodoService todoService;
	
	@Autowired
	private TodoVO todoVO;
	
	/* 글 리스트 : 사용자의 아이디로 모든 글 select */
	@Override
	@RequestMapping(value = "/todo/listTodo.do", method = RequestMethod.GET)
	public ModelAndView listTodo(@RequestParam("userid") String userid, 
																   @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");
		
        if(loginInfo == null) {
        	ModelAndView mav = new ModelAndView("redirect:/member/loginForm.do");
            return mav;
        }
        
        int pageSize = 4;
        int totalCount = todoService.countTodoList(userid);
        int totalPages = (int) Math.ceil((double) totalCount / pageSize);
        
		List<TodoVO> todoList = todoService.todoListPage(userid, pageNo, pageSize);
		
		ModelAndView mav = new ModelAndView("/todo/listTodo");
		mav.addObject("todoList", todoList);
		mav.addObject("pageNo", pageNo);
        mav.addObject("totalPages", totalPages);
        System.out.println(loginInfo.getUserid() + "회원 TodoList / " +pageNo+"페이지");
        System.out.println("-------------------------------------");
		return mav;
	}
	
	/* 글 추가 */
	@Override
	@RequestMapping(value = "/todo/addTodo.do", method = RequestMethod.POST)
	public ModelAndView addTodo(@ModelAttribute("todo") TodoVO todoVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");
        
        if(loginInfo == null) {
        	return new ModelAndView("redirect:/member/loginForm.do");
        }
        
        todoVO.setUserid(loginInfo.getUserid());
        
		int result = todoService.addTodo(todoVO);
		
		ModelAndView mav = new ModelAndView("redirect:/todo/listTodo.do?userid="+loginInfo.getUserid());
		System.out.println("Todo 추가");
		return mav;
	}
	
	/* 글 삭제 */
	@Override
	@RequestMapping(value = "/todo/removeTodo.do", method = RequestMethod.GET)
	public ModelAndView removeTodo(@RequestParam("todoseq") int todoseq, HttpServletRequest request, HttpServletResponse response) throws Exception {	
		HttpSession session = request.getSession();
        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");
        
		request.setCharacterEncoding("utf-8");
		todoService.removeTodo(todoseq);
		ModelAndView mav = new ModelAndView("redirect:/todo/listTodo.do?userid="+loginInfo.getUserid());
		System.out.println("Todo 삭제");
		return mav;
	}
	
	/* 글 수정 폼 이동 */
	@RequestMapping(value = "/todo/modTodoForm.do", method = RequestMethod.GET)
	public ModelAndView modTodoForm(@RequestParam("todoseq") int todoseq, HttpServletRequest request, HttpServletResponse response) throws Exception {
	    HttpSession session = request.getSession();
	    MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");
	    
	    if (loginInfo == null) {
	        return new ModelAndView("redirect:/member/loginForm.do");
	    }
	    
	    TodoVO todoVO = todoService.getTodo(todoseq);
	    
	    ModelAndView mav = new ModelAndView();
	    mav.setViewName("/todo/modTodoForm"); 
	    mav.addObject("todo", todoVO);
	    return mav;
	}
	
	/* 글 수정 */
	@Override
	@RequestMapping(value = "/todo/modTodo.do", method = RequestMethod.POST)
	public ModelAndView modTodo(@ModelAttribute("todo") TodoVO todoVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
	    request.setCharacterEncoding("utf-8");
	    
	    HttpSession session = request.getSession();
	    MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");
	    
	    if (loginInfo == null) {
	        return new ModelAndView("redirect:/member/loginForm.do");
	    }
	    
	    todoVO.setUserid(loginInfo.getUserid());
	    
	    int result = todoService.modTodo(todoVO);
	    
	    ModelAndView mav = new ModelAndView("redirect:/todo/listTodo.do?userid=" + loginInfo.getUserid());
	    System.out.println("Todo 수정");
	    return mav;
	}
	
	
	
	
	@RequestMapping(value = "/todo/*Form.do", method = RequestMethod.GET)
	public ModelAndView form(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}
	
	/* 글 검색 */
	@RequestMapping(value = "/todo/todoSearch.do", method = RequestMethod.GET)
	public ModelAndView todoSearch(@RequestParam("userid") String userid,
																				@RequestParam("keyword") String keyword,
																	            @RequestParam("searchType") String searchType,
																	            @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
																	            @RequestParam(value = "pageSize", defaultValue = "4") int pageSize,
																	            HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
	    MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");
	    
	    if (loginInfo == null) {
	        return new ModelAndView("redirect:/member/loginForm.do");
	    }
	    
	    ModelAndView mav = new ModelAndView("/todo/todoSearch"); 
	    
	    List<TodoVO> todoList = todoService.searchTodoList(userid, keyword, searchType, pageNo, pageSize);
	    int totalCount = todoService.countSearch(userid, keyword, searchType);
	    int totalPages = (int) Math.ceil((double) totalCount / pageSize);
	    
	    mav.addObject("todoList", todoList);
	    mav.addObject("pageNo", pageNo);
	    mav.addObject("totalPages", totalPages);
	    
	    System.out.println(searchType +"(으)로 "+keyword+"검색");
	    System.out.println("검색결과 : "+ totalCount+"개의 데이터");
	    System.out.println("-------------------------------------"); 
	    
	    return mav;
	}
	
	private String getViewName(HttpServletRequest request) throws Exception {
		String contextPath = request.getContextPath();
		String uri  = (String) request.getAttribute("javax.servlet.include.request_uri");
		
		if (uri == null || uri.trim().equals("")) {
			uri = request.getRequestURI();
		}
		
		int begin = 0;
		if (!((contextPath == null) || ("".equals(contextPath)))) {
			begin = contextPath.length();
		}
		
		int end;
		if(uri.indexOf(";") != -1) {
			end = uri.indexOf(";");
		} else if (uri.indexOf("?") != -1) {
			end = uri.indexOf("?");
		} else {
			end = uri.length();
		}
		
		String fileName = uri.substring(begin, end);
		if (fileName.indexOf(".") != -1) {
			fileName = fileName.substring(0, fileName.lastIndexOf("."));
		}
		/*
		 * if (fileName.lastIndexOf("/") != -1) { fileName =
		 * fileName.substring(fileName.lastIndexOf("/"), fileName.length()); }
		 */
		System.out.println("요청받은 url : " + fileName);
		System.out.println("-------------------------------------");
		return fileName;

	}
}
