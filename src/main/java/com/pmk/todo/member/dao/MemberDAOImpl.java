package com.pmk.todo.member.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.pmk.todo.member.vo.MemberVO;

@Repository("memberDAO")
public class MemberDAOImpl implements MemberDAO{
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List selectAllMemberList() throws DataAccessException {
		List<MemberVO> membersList = null;
		membersList = sqlSession.selectList("mapper.member.selectAllMemberList");
		return membersList;
	}
	
	@Override
	public int insertMember(MemberVO memberVO) throws DataAccessException {
		int result = sqlSession.insert("mapper.member.insertMember", memberVO);
		return result;
	}
	
	@Override
	public int deleteMember(String userid) throws DataAccessException {
		int result = sqlSession.delete("mapper.member.deleteMember", userid);
		return result;
	}
	
	@Override
	public MemberVO login(String userid, String userpwd) throws DataAccessException {
		Map<String, String> loginInfo = new HashMap<>();
		loginInfo.put("userid", userid);
		loginInfo.put("userpwd", userpwd);
		MemberVO memberVO = new MemberVO();
		memberVO = (MemberVO) sqlSession.selectOne("mapper.member.login", loginInfo);
		return memberVO;
	}
	
	@Override
	public String OverlappedID(String userid) throws DataAccessException {
		String result = (String) sqlSession.selectOne("mapper.member.idDuplicated", userid);
		return result != null ? "true" : "false";
	}
}
