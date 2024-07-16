package com.pmk.todo.list.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.pmk.todo.list.dao.TodoDAO;
import com.pmk.todo.list.vo.TodoVO;

@Service("todoService")
@Transactional(propagation = Propagation.REQUIRED)
public class TodoServiceImpl implements TodoService{
	
	@Autowired
	private TodoDAO todoDAO;
	
	@Override
	public List listTodo(String userid) throws DataAccessException {
		List todoList = null;
		todoList = todoDAO.selectAllTodoList(userid);
		return todoList;
	}
	
	@Override
	public int addTodo(TodoVO todoVO) throws DataAccessException {
		return todoDAO.insertTodo(todoVO);
	}
	
	@Override
	public int removeTodo(int todoseq) throws DataAccessException {
		return todoDAO.deleteTodo(todoseq);
	}
	
	@Override
	public int modTodo(TodoVO todoVO) throws DataAccessException {
		return todoDAO.modTodo(todoVO);
	}

	@Override
	public TodoVO getTodo(int todoseq) throws DataAccessException {
		return todoDAO.getTodo(todoseq);
	}
	
	@Override
	public List<TodoVO> todoListPage(String userid, int pageNo, int pageSize) throws DataAccessException {
		int start = (pageNo - 1) * pageSize + 1;
		int end = start + pageSize - 1;
		return todoDAO.todoListPage(userid, start, end);
	}
	
	@Override
	public int countTodoList(String userid) throws DataAccessException {
		return todoDAO.countTodoList(userid);
	}
	
	@Override
	public List<TodoVO> searchTodoList(String userid, String keyword, String searchType, int pageNo, int pageSize) throws DataAccessException {
	    int start = (pageNo - 1) * pageSize + 1;
	    int end = start + pageSize - 1;
	    return todoDAO.searchTodoList(userid, keyword, searchType, start, end);
	}

	@Override
	public int countSearch(String userid, String keyword, String searchType) throws DataAccessException {
	    return todoDAO.countSearch(userid, keyword, searchType);
	}
	
}
