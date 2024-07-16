package com.pmk.todo.list.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pmk.todo.list.vo.TodoVO;

public interface TodoController {
	public ModelAndView listTodo(@RequestParam("userid") String userid, @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
																	HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView addTodo(@ModelAttribute("todo") TodoVO todoVO,
																	HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView removeTodo(@RequestParam("todoseq") int todoseq,
																	HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView modTodo(@ModelAttribute("todo") TodoVO todoVO,
																	HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView modTodoForm(@RequestParam("todoseq") int todoseq, 
																	HttpServletRequest request, HttpServletResponse response) throws Exception;
}
