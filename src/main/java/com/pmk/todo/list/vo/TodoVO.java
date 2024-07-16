package com.pmk.todo.list.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("todoVO")
public class TodoVO {
	private int todoseq;
	private String userid;
	private String title;
	private String content;
	private Date regdate;
	private String deadline;
	private String result;
	
	public TodoVO() {
		
	}

	public TodoVO(int todoseq, String userid, String title, String content, Date regdate, String deadline, String result) {
		this.todoseq = todoseq;
		this.userid = userid;
		this.title = title;
		this.content = content;
		this.regdate = regdate;
		this.deadline = deadline;
		this.result = result;
	}
	
	public int getTodoseq() {
		return todoseq;
	}

	public void setTodoseq(int todoseq) {
		this.todoseq = todoseq;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "TodoVO [todoseq=" + todoseq + ", userid=" + userid + ", title=" + title + ", content=" + content
				+ ", regdate=" + regdate + ", deadline=" + deadline + ", result=" + result + "]";
	}

	
}
