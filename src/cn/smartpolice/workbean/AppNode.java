package cn.smartpolice.workbean;

import java.util.Date;

/**
 * 用户登录信息（哈希表）
 * @author 刘超
 *
 */
public class AppNode extends UserNode {

	private DevNode devData;
	private Date talkDate;
	
	public DevNode getDevData() {
		return devData;
	}
	public void setDevData(DevNode devData) {
		this.devData = devData;
	}
	public Date getTalkDate() {
		return talkDate;
	}
	public void setTalkDate(Date talkDate) {
		this.talkDate = talkDate;
	}
	
}