package cn.smartpolice.hibernate;

public class PlatRunInfo {
	private String Id;
	private String ProductName;
	private String OnlineUserNum;
	private String RegistNum;
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getProductName() {
		return ProductName;
	}
	public void setProductName(String productName) {
		ProductName = productName;
	}
	public String getOnlineUserNum() {
		return OnlineUserNum;
	}
	public void setOnlineUserNum(String onlineUserNum) {
		OnlineUserNum = onlineUserNum;
	}
	public String getRegistNum() {
		return RegistNum;
	}
	public void setRegistNum(String registNum) {
		RegistNum = registNum;
	}
	@Override
	public String toString() {
		return "PlatRunInfo [Id=" + Id + ", ProductName=" + ProductName
				+ ", OnlineUserNum=" + OnlineUserNum + ", RegistNum="
				+ RegistNum + "]";
	}
	
}
