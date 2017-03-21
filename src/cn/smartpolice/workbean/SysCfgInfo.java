package cn.smartpolice.workbean;

/**
 * 系统配置信息
 * 配置之后不再改变 final
 * 在任何地方都可以读 static
 * @author 刘超
 *
 */
public final class SysCfgInfo {
	private int maxLoginDevices;
	private int maxLoginUsers;
	private String mysqlUserName;
	private String mysqlPassword;
	private short sessionPort; //通信端口
	private String ip;
	private short port;  //端口
	private String sessionPassword;  //服务器间通信认证码
	private int resentNum;
	private int overtime;
	private int state; //配置是否需要登录用户验证
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getMaxLoginDevices() {
		return maxLoginDevices;
	}
	public int getMaxLoginUsers() {
		return maxLoginUsers;
	}
	public void setMaxLoginUsers(int maxLoginUsers) {
		this.maxLoginUsers = maxLoginUsers;
	}
	public String getMysqlUserName() {
		return mysqlUserName;
	}
	public void setMysqlUserName(String mysqlUserName) {
		this.mysqlUserName = mysqlUserName;
	}
	public String getMysqlPassword() {
		return mysqlPassword;
	}
	public void setMysqlPassword(String mysqlPassword) {
		this.mysqlPassword = mysqlPassword;
	}
	public short getSessionPort() {
		return sessionPort;
	}
	public void setSessionPort(short sessionPort) {
		this.sessionPort = sessionPort;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public short getPort() {
		return port;
	}
	public void setPort(short port) {
		this.port = port;
	}
	public String getSessionPassword() {
		return sessionPassword;
	}
	public void setSessionPassword(String sessionPassword) {
		this.sessionPassword = sessionPassword;
	}
	public int getResentNum() {
		return resentNum;
	}
	public void setResentNum(int resentNum) {
		this.resentNum = resentNum;
	}
	public int getOvertime() {
		return overtime;
	}
	public void setOvertime(int overtime) {
		this.overtime = overtime;
	}
	public void setMaxLoginDevices(int maxLoginDevices) {
		this.maxLoginDevices = maxLoginDevices;
	}
	
}
