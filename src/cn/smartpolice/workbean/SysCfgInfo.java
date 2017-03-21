package cn.smartpolice.workbean;

/**
 * ϵͳ������Ϣ
 * ����֮���ٸı� final
 * ���κεط������Զ� static
 * @author ����
 *
 */
public final class SysCfgInfo {
	private int maxLoginDevices;
	private int maxLoginUsers;
	private String mysqlUserName;
	private String mysqlPassword;
	private short sessionPort; //ͨ�Ŷ˿�
	private String ip;
	private short port;  //�˿�
	private String sessionPassword;  //��������ͨ����֤��
	private int resentNum;
	private int overtime;
	private int state; //�����Ƿ���Ҫ��¼�û���֤
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
