package cn.smartpolice.protocol;
      import java.util.Date;

import sun.security.util.BigInt;
/**
 * 软件信息表
 * @author 吴明欢
 *
 */
     
public class Softlnf {
	 
	   private  int  softid;//软件编号
	   private  int    type;//0前端设备、1监控app、2管理app、3插件
	   private  char name;//软件名称
	   private  char version;//版本
	   private  char serial;//软件序列号，唯一标识
	   private  Date date;//上传时间
	   private  int uploadid;//上传者
	   private  char  md5;//Md5摘要信息
	   private  byte size; //软件大小，字节
	   private  char  url;//软件存放地址
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
	public char getName() {
		return name;
	}
	public void setName(char name) {
		this.name = name;
	}
	public char getVersion() {
		return version;
	}
	public void setVersion(char version) {
		this.version = version;
	}
	public char getSerial() {
		return serial;
	}
	public void setSerial(char serial) {
		this.serial = serial;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getUploadid() {
		return uploadid;
	}
	public void setUploadid(int uploadid) {
		this.uploadid = uploadid;
	}
	public char getMd5() {
		return md5;
	}
	public void setMd5(char md5) {
		this.md5 = md5;
	}
	public byte getSize() {
		return size;
	}
	public void setSize(byte size) {
		this.size = size;
	}
	public char getUrl() {
		return url;
	}
	public void setUrl(char url) {
		this.url = url;
	}

}
