package cn.smartpolice.workbean;

import java.util.Date;

/**
 * ��Ϣ���нڵ����
 * @author ����
 *
 */
public class MsgTask {

	private int msgNum; //��Ϣ����
	private int sendUserID; //������Ϣ���˺�
	
	private int mType;  //class��Ϣ����
	private Date mDate;
	private String content; //��Ϣ����
	private String attach; //��Ϣ����
	private int revUserID; //������Ϣ��ID
	public int getmType() {
		return mType;
	}
	public void setmType(int mType) {
		this.mType = mType;
	}
	public Date getmDate() {
		return mDate;
	}
	public void setmDate(Date mDate) {
		this.mDate = mDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	private PacketInfo packetInfo; //δ����Ϣ����
	public int getMsgNum() {
		return msgNum;
	}
	public void setMsgNum(int msgNum) {
		this.msgNum = msgNum;
	}
	public int getSendUserID() {
		return sendUserID;
	}
	public void setSendUserID(int sendUserID) {
		this.sendUserID = sendUserID;
	}
	public int getRevUserID() {
		return revUserID;
	}
	public void setRevUserID(int revUserID) {
		this.revUserID = revUserID;
	}
	public PacketInfo getPacketInfo() {
		return packetInfo;
	}
	public void setPacketInfo(PacketInfo packetInfo) {
		this.packetInfo = packetInfo;
	}
}