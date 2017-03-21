package cn.smartpolice.hibernate;

import java.sql.Date;

public class DeviceAudit {

	private int auditId;
	private String deviceName;
	private String code;
	private String demo;
	private CompanyInfo companyId;
	private Date date;
	private ManagerInfo checkId;
	private Date checkDate;
	private String state;
	private String type;
	public int getAuditId() {
		return auditId;
	}
	public void setAuditId(int auditId) {
		this.auditId = auditId;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDemo() {
		return demo;
	}
	public void setDemo(String demo) {
		this.demo = demo;
	}
	public CompanyInfo getCompanyId() {
		return companyId;
	}
	public void setCompanyId(CompanyInfo companyId) {
		this.companyId = companyId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public ManagerInfo getCheckId() {
		return checkId;
	}
	public void setCheckId(ManagerInfo checkId) {
		this.checkId = checkId;
	}
	public Date getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
