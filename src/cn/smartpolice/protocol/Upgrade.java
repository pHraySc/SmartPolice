package cn.smartpolice.protocol;

public class Upgrade {
	
	    byte   serial;//�����ѯ����������
	    String  version;//����ǰ�汾��
	    String   ver;//���汾��
		public byte getSerial() {
			return serial;
		}
		public void setSerial(byte serial) {
			this.serial = serial;
		}
		public String getVersion() {
			return version;
		}
		public void setVersion(String version) {
			this.version = version;
		}
		public String getVer() {
			return ver;
		}
		public void setVer(String ver) {
			this.ver = ver;
		}
}