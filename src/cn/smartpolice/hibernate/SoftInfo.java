package cn.smartpolice.hibernate;

import java.util.Date;

public class SoftInfo {
	private int softid;
	private int type;
	private String name;
	private String version;
	private String serial;
	private Date date;
	private int uploadid;
	private String md5;
	private int size;
	private String url;
	public int getSoftid() {
		return softid;
	}
	public void setSoftid(int softid) {
		this.softid = softid;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getUploadid() {
		return uploadid;
	}
	public void setUploadid(int uploadid) {
		this.uploadid = uploadid;
	}
}
