package cn.smartpolice.workbean;

import java.util.Date;

import org.apache.mina.core.session.IoSession;

/**
 * 报文信息对象
 * @author 刘超
 *
 */
public class PacketInfo {
	private IoSession ioSession;
	
	private byte[] message; //协议数据缓冲区
	//基本首部成员
	private byte cmd;
	private byte type;
	private byte opt;
	private byte sort;
	private int sid;
	private int seq;
	private int ack;
	//首部选项成员
	private int did;
	private int keyseq;
	private String sip;
	private int sport;
	//报文数据域开始位置
	private int datapos;
	//报文接受时间和长度
	private Date date;
	private int length;
	//便于其他类处理报文的时候
	private AppNode appNode;
	private DevNode devNode;
	
	public IoSession getIoSession() {
		return ioSession;
	}
	public void setIoSession(IoSession ioSession) {
		this.ioSession = ioSession;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}

	public AppNode getAppNode() {
		return appNode;
	}
	public void setAppNode(AppNode appNode) {
		this.appNode = appNode;
	}
	public DevNode getDevNode() {
		return devNode;
	}
	public void setDevNode(DevNode devNode) {
		this.devNode = devNode;
	}
	public byte[] getMessage() {
		return message;
	}
	public void setMessage(byte[] message) {
		this.message = message;
	}
	public byte getCmd() {
		return cmd;
	}
	public void setCmd(byte cmd) {
		this.cmd = cmd;
	}
	public byte getType() {
		return type;
	}
	public void setType(byte type) {
		this.type = type;
	}
	public byte getOpt() {
		return opt;
	}
	public void setOpt(byte opt) {
		this.opt = opt;
	}
	public byte getSort() {
		return sort;
	}
	public void setSort(byte sort) {
		this.sort = sort;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public int getAck() {
		return ack;
	}
	public void setAck(int ack) {
		this.ack = ack;
	}
	public int getDid() {
		return did;
	}
	public void setDid(int did) {
		this.did = did;
	}
	public int getKeyseq() {
		return keyseq;
	}
	public void setKeyseq(int keyseq) {
		this.keyseq = keyseq;
	}
	public String getSip() {
		return sip;
	}
	public void setSip(String sip) {
		this.sip = sip;
	}
	public int getSport() {
		return sport;
	}
	public void setSport(int sport) {
		this.sport = sport;
	}
	public int getDatapos() {
		return datapos;
	}
	public void setDatapos(int datapos) {
		this.datapos = datapos;
	}

	
	
}
