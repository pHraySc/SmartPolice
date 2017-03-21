package cn.smartpolice.test;

import cn.smartpolice.workbean.MsgTask;

public class javaObjectTest {
	public static void main(String[] args) {
		MsgTask msgTask = new MsgTask();
		msgTask.setMsgNum(1);
		System.out.print("num:"+msgTask.getMsgNum()+",sendId:"+msgTask.getSendUserID());
	}
}
