package com.pmk.todo.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.pmk.todo.member.dao.MemberDAO;
import com.pmk.todo.member.vo.MemberVO;

@Service("memberService")
@Transactional(propagation = Propagation.REQUIRED)
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberDAO memberDAO;
	
	@Override
	public List listMembers() throws DataAccessException {
		List membersList = null;
		membersList = memberDAO.selectAllMemberList();
		return membersList;
	}
	
	@Override
	public int addMember(MemberVO memberVO) throws DataAccessException {
		return memberDAO.insertMember(memberVO);
	}
	
	@Override
	public int removeMember(String userid) throws DataAccessException {
		return memberDAO.deleteMember(userid);
	}
	
	@Override
	public MemberVO login(String userid, String userpwd) throws DataAccessException {
		return memberDAO.login(userid, userpwd);
	}
	
	public String OverlappedID(String userid) throws DataAccessException {
		String overlapped = memberDAO.OverlappedID(userid);
		if (overlapped == "true") {
			return "true";
		} else {
			return "false";
		}
	}
	
}
