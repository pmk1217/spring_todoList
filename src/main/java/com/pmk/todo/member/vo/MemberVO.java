package com.pmk.todo.member.vo;

import org.springframework.stereotype.Component;

@Component("memberVO")
public class MemberVO {
	private String userid;
	private String userpwd;
	private String username;
	
	public MemberVO(String userid, String userpwd, String username) {
		this.userid = userid;
		this.userpwd = userpwd;
		this.username = username;
	}
	
	public MemberVO() {
	
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserpwd() {
		return userpwd;
	}

	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
