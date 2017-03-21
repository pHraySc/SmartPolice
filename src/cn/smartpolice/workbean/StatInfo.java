package cn.smartpolice.workbean;

/*** 
 * 全局数据结构：
 *  统计信息
 *  
 * */
public class StatInfo {
	private int receicvenum;//收到报文数
	private int sendnum;//发送报文数
	private int loginusernum;//登录用户数
	private int logindevnum;//登录设备数
	private int viewusernum;//界面用户数
	private int ordernum;//序号
	public int getReceicvenum() {
		return receicvenum;
	}
	public void setReceicvenum(int receicvenum) {
		this.receicvenum = receicvenum;
	}
	public int getSendnum() {
		return sendnum;
	}
	public void setSendnum(int sendnum) {
		this.sendnum = sendnum;
	}
	public int getLoginusernum() {
		return loginusernum;
	}
	public void setLoginusernum(int loginusernum) {
		this.loginusernum = loginusernum;
	}
	public int getLogindevnum() {
		return logindevnum;
	}
	public void setLogindevnum(int logindevnum) {
		this.logindevnum = logindevnum;
	}
	public int getViewusernum() {
		return viewusernum;
	}
	public void setViewusernum(int viewusernum) {
		this.viewusernum = viewusernum;
	}
	public int getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(int ordernum) {
		this.ordernum = ordernum;
	}
	
	
}
