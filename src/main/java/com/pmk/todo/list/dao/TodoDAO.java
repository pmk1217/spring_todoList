package com.pmk.todo.list.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.pmk.todo.list.vo.TodoVO;

public interface TodoDAO {
	public List selectAllTodoList(String userid) throws DataAccessException;
	public int insertTodo(TodoVO todoVO) throws DataAccessException;
	public int deleteTodo(int todoseq) throws DataAccessException;
	public TodoVO getTodo(int todoseq) throws DataAccessException;
	public int modTodo(TodoVO todoVO) throws DataAccessException;
	public List<TodoVO> todoListPage(String userid, int start, int end) throws DataAccessException;
	public int countTodoList(String userid) throws DataAccessException;
	public List<TodoVO> searchTodoList(String userid, String keyword, String searchType,
																					int start, int end) throws DataAccessException;
	public int countSearch(String userid, String keyword, String searchType) throws DataAccessException;
}
