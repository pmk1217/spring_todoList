package com.pmk.todo.member.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pmk.todo.member.service.MemberService;
import com.pmk.todo.member.vo.MemberVO;

@Controller("memberController")
public class MemberControllerImpl implements MemberController{
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MemberVO memberVO;
	
	@Override
	@RequestMapping(value = "/index.do", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");
		ModelAndView mav;
		
		if(loginInfo == null) {
			 mav = new ModelAndView("index"); 
		} else {
			 mav = new ModelAndView("redirect:/todo/listTodo.do?userid="+loginInfo.getUserid());
		}
		 return mav;
	}
	
	@Override
	@RequestMapping(value="/member/listMembers.do",method = RequestMethod.GET)
	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);
		List membersList = memberService.listMembers();
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("membersList", membersList);
		System.out.println("멤버 리스트 출력");
		System.out.println("-------------------------------------");
		return mav;
	}
	
	
	@Override
	@RequestMapping(value="/member/addMembers.do", method = RequestMethod.POST)
	public ModelAndView addMember(@ModelAttribute("member") MemberVO memberVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		int result = memberService.addMember(memberVO);
		
		ModelAndView mav = new ModelAndView("redirect:/member/loginForm.do");
		System.out.println("멤버 추가");
		System.out.println("-------------------------------------");
		return mav;
	}
	
	
	@Override
	@RequestMapping(value = "/member/removeMember.do", method = RequestMethod.GET)
	public ModelAndView removeMember(@RequestParam("userid") String userid, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		memberService.removeMember(userid);
		ModelAndView mav = new ModelAndView("index");
		System.out.println(userid+" 회원 삭제 + 데이터 삭제");
		System.out.println("-------------------------------------");
		return mav;
	}
	
	
	@RequestMapping(value = "/member/login.do", method = RequestMethod.POST)
	public String login(String userid, String userpwd, HttpServletRequest request, HttpServletResponse response) throws Exception {
		MemberVO memberVO = memberService.login(userid, userpwd);
		if (memberVO != null) {
			HttpSession session = request.getSession();
			session.setAttribute("loginInfo", memberVO);
			System.out.println("로그인 성공");
			return "redirect:/todo/listTodo.do?userid="+userid;
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("ErrorMsg", "아이디 또는 패스워드가 일치하지 않습니다.");
			System.out.println("아이디 또는 패스워드 불일치");
			System.out.println("-------------------------------------");
			return "redirect:/member/loginForm.do";
		}
	}
	
	@RequestMapping(value = "/member/logout.do", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		 
		HttpSession session = request.getSession(false);
		 session.removeAttribute("loginInfo");
	     session.invalidate();
	     
	     System.out.println("로그아웃");
	    
	     return new ModelAndView("index");
	}
	
	@Override
	@RequestMapping(value = "/member/idDuplicated.do", method = RequestMethod.POST)
	@ResponseBody
	public String checkID(@RequestParam("userid") String userid, HttpServletRequest request, HttpServletResponse response) throws Exception {	
		String result = memberService.OverlappedID(userid);
        return result;
	}
	
	
	
	@RequestMapping(value = "/member/*Form.do", method = RequestMethod.GET)
	public ModelAndView form(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
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
		System.out.println("요청받은 url : " + fileName);
		System.out.println("-------------------------------------");
		/*
		 * if (fileName.lastIndexOf("/") != -1) { fileName =
		 * fileName.substring(fileName.lastIndexOf("/"), fileName.length()); }
		 * System.out.println("fileName : " + fileName);
		 */
		return fileName;

	}
}
