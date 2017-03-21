package cn.smartpolice.hibernate;

import java.util.HashSet;
import java.util.Set;

/**
 * 管理员数据表映射类
 * 
 * 
 * */
public class ManagerInfo {
	private int managerId;
	private String name;
	private String userName;
	private String password;
	private String number;
	private String email;
	private String state;
	private Set<DeviceAudit> deviceAudit = new HashSet<DeviceAudit>(); //device_audit 一对多映射
	private Set<WebserviceInfo> webserviceAdmin = new HashSet<WebserviceInfo>(); //device_audit 一对多映射
	
	public Set<DeviceAudit> getDeviceAudit() {
		return deviceAudit;
	}
	public void setDeviceAudit(Set<DeviceAudit> deviceAudit) {
		this.deviceAudit = deviceAudit;
	}
	public Set<WebserviceInfo> getWebserviceAdmin() {
		return webserviceAdmin;
	}
	public void setWebserviceAdmin(Set<WebserviceInfo> webserviceAdmin) {
		this.webserviceAdmin = webserviceAdmin;
	}
	public int getManagerId() {
		return managerId;
	}
	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
}