package com.pmk.todo.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pmk.todo.member.vo.MemberVO;

public interface MemberController {
	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView addMember(@ModelAttribute("info") MemberVO memberVO,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView removeMember(@RequestParam("userid") String userid,	HttpServletRequest request,
																					HttpServletResponse response) throws Exception;
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public String checkID(@RequestParam("userid") String userid, HttpServletRequest request,
																					HttpServletResponse response) throws Exception;	

}
