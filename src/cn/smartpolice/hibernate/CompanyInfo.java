package cn.smartpolice.hibernate;

import java.util.HashSet;
import java.util.Set;

public class CompanyInfo {
	
	private int companyId;
	private String name;
	private String address;
	private String type;
	private String demo;
	private String logo;
	
	private Set<DeviceAudit> deviceAudit = new HashSet<DeviceAudit>(); //device_audit “ª∂‘∂‡”≥…‰
	
	public Set<DeviceAudit> getDeviceAudit() {
		return deviceAudit;
	}
	public void setDeviceAudit(Set<DeviceAudit> deviceAudit) {
		this.deviceAudit = deviceAudit;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDemo() {
		return demo;
	}
	public void setDemo(String demo) {
		this.demo = demo;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	

}
