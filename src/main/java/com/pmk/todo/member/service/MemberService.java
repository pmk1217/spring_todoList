package com.pmk.todo.member.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.pmk.todo.member.vo.MemberVO;

public interface MemberService {
	public List listMembers() throws DataAccessException;
	public int addMember(MemberVO memberVO) throws DataAccessException;
	public int removeMember(String userid) throws DataAccessException;
	public MemberVO login(String userid, String userpwd) throws DataAccessException;
	public String OverlappedID(String userid) throws DataAccessException;
}
