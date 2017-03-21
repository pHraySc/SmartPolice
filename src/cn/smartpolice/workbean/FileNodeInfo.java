package cn.smartpolice.workbean;

import java.util.Date;

/**
 * 传输文件信息（链表队列）
 * @author 刘超
 *
 */
public class FileNodeInfo {

	private String fileName;
	private String fileOpenObj;
	private String MD5;
	private int fileSize;
	private boolean type;
	private Date reqDatel;
	private long fileTransObjId;
	private boolean fileTransObjType;
}
