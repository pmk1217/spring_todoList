package com.pmk.todo.list.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.pmk.todo.list.vo.TodoVO;

@Repository("todoDAO")
public class TodoDAOImpl implements TodoDAO{
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List selectAllTodoList(String userid) throws DataAccessException {
		List<TodoVO> todoList = null;
		todoList = sqlSession.selectList("mapper.todo.selectAllTodoList", userid);
		return todoList;
	}
	
	@Override
	public int insertTodo(TodoVO todoVO) throws DataAccessException {
		int result = sqlSession.insert("mapper.todo.insertTodo", todoVO);
		return result;
	}
	
	@Override
	public int deleteTodo(int todoseq) throws DataAccessException {
		int result = sqlSession.delete("mapper.todo.deleteTodo", todoseq);
		return result;
	}
	
	@Override
	public TodoVO getTodo(int todoseq) throws DataAccessException {
		return (TodoVO) sqlSession.selectOne("mapper.todo.detailTodo", todoseq);
	}
	
	@Override
	public int modTodo(TodoVO todoVO) throws DataAccessException {
		int result = sqlSession.update("mapper.todo.updateTodo", todoVO);
		return result;
	}
	
	@Override
	public List<TodoVO> todoListPage(String userid, int start, int end) throws DataAccessException {
		Map<String, Object> result = new HashMap<>();
		result.put("userid", userid);
		result.put("start", start);
		result.put("end", end);
		return sqlSession.selectList("mapper.todo.todoListPage", result);
	}
	
	@Override
	public int countTodoList(String userid) throws DataAccessException {
		int result = (int) sqlSession.selectOne("mapper.todo.countTodoList", userid);
		return result;
	}
	
	@Override
	public List<TodoVO> searchTodoList(String userid, String keyword, String searchType, int start, int end) throws DataAccessException {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("userid", userid);
		result.put("keyword", keyword);
		result.put("searchType", searchType);
		result.put("start", start);
		result.put("end", end);
		return sqlSession.selectList("mapper.todo.searchTodoList", result);
	}
	
	@Override
	public int countSearch(String userid, String keyword, String searchType) throws DataAccessException {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("userid", userid);
		result.put("keyword", keyword);
		result.put("searchType", searchType);
		return (int) sqlSession.selectOne("mapper.todo.countSearch", result);
	}
}
