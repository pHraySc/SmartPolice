package cn.smartpolice.protocol;
      import java.util.Date;

import sun.security.util.BigInt;
/**
 * �����Ϣ��
 * @author ������
 *
 */
     
public class Softlnf {
	 
	   private  int  softid;//������
	   private  int    type;//0ǰ���豸��1���app��2����app��3���
	   private  char name;//�������
	   private  char version;//�汾
	   private  char serial;//������кţ�Ψһ��ʶ
	   private  Date date;//�ϴ�ʱ��
	   private  int uploadid;//�ϴ���
	   private  char  md5;//Md5ժҪ��Ϣ
	   private  byte size; //�����С���ֽ�
	   private  char  url;//�����ŵ�ַ
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
