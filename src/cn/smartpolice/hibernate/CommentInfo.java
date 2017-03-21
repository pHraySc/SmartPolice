package cn.smartpolice.hibernate;

import java.sql.Date;

public class CommentInfo {
	private int commentid;
	private int userid;
	private int type;
	private int replyid;
	private Date date;
	private String content;
	private int recvtype;
	private Date replydate;
	private String reply;
	private Byte hadle;
	public int getCommentid() {
		return commentid;
	}
	public void setCommentid(int commentid) {
		this.commentid = commentid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getReplyid() {
		return replyid;
	}
	public void setReplyid(int replyid) {
		this.replyid = replyid;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getRecvtype() {
		return recvtype;
	}
	public void setRecvtype(int recvtype) {
		this.recvtype = recvtype;
	}
	public Date getReplydate() {
		return replydate;
	}
	public void setReplydate(Date replydate) {
		this.replydate = replydate;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public Byte getHadle() {
		return hadle;
	}
	public void setHadle(Byte hadle) {
		this.hadle = hadle;
	}
	
	
}
