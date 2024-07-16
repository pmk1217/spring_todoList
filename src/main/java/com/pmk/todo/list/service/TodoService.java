package com.pmk.todo.list.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.pmk.todo.list.vo.TodoVO;

public interface TodoService {
	public List listTodo(String userid) throws DataAccessException;
	public int addTodo(TodoVO todoVO) throws DataAccessException;
	public int removeTodo(int todoseq) throws DataAccessException;
	public int modTodo(TodoVO todoVO) throws DataAccessException;
	public TodoVO getTodo(int todoseq) throws DataAccessException;
	public List<TodoVO> todoListPage(String userid, int pageNo, int pageSize) throws DataAccessException;
	public int countTodoList(String userid) throws DataAccessException;
	public List<TodoVO> searchTodoList(String userid, String keyword, String searchType,
			int start, int end) throws DataAccessException;
	public int countSearch(String userid, String keyword, String searchType) throws DataAccessException;
}
