package cn.smartpolice.test;

import java.util.HashSet;

import cn.smartpolice.workbean.AppNode;
import cn.smartpolice.workbean.DevNode;
import cn.smartpolice.workbean.SysInfo;
import cn.smartpolice.workbean.UserNode;

public class queuetest {
	public static void main(String[] args) {
		
		AppNode app = new AppNode();
		app.setId(1);
		DevNode dev = new DevNode();
		dev.setId(1);
		SysInfo.getInstance().addUserNode(app);
		SysInfo.getInstance().addUserNode(dev);
		AppNode app1 = SysInfo.getInstance().getAppNodeById(1);
		DevNode dev1 = SysInfo.getInstance().getDevNodeById(1);
		System.out.println(app1);
		System.out.println("dasdfas");
		System.out.println(dev1);
	}
}
