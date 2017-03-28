package cn.smartpolice.hibernate;

public class AddServers {
	private String ServerType;
	private String Memory;
	private String CPUVersion;
	private String ServerDesc;
	public String getServerType() {
		return ServerType;
	}
	public void setServerType(String serverType) {
		ServerType = serverType;
	}
	public String getMemory() {
		return Memory;
	}
	public void setMemory(String memory) {
		Memory = memory;
	}
	public String getCPUVersion() {
		return CPUVersion;
	}
	public void setCPUVersion(String cPUVersion) {
		CPUVersion = cPUVersion;
	}
	public String getServerDesc() {
		return ServerDesc;
	}
	public void setServerDesc(String serverDesc) {
		ServerDesc = serverDesc;
	}
	@Override
	public String toString() {
		return "AddServers [ServerType=" + ServerType + ", Memory=" + Memory
				+ ", CPUVersion=" + CPUVersion + ", ServerDesc=" + ServerDesc
				+ "]";
	}
}
