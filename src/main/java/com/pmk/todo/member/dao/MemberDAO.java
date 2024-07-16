package com.pmk.todo.member.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.pmk.todo.member.vo.MemberVO;

public interface MemberDAO {
	public List selectAllMemberList() throws DataAccessException;
	public int insertMember(MemberVO memberVO) throws DataAccessException;
	public int deleteMember(String userid) throws DataAccessException;
	public MemberVO login(String userid, String userpwd) throws DataAccessException;
	public String OverlappedID(String userid) throws DataAccessException;
}
