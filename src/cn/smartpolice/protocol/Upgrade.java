package cn.smartpolice.protocol;

public class Upgrade {
	
	    byte   serial;//请求查询程序类型码
	    String  version;//程序当前版本号
	    String   ver;//最大版本号
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