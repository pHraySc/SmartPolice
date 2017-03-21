package cn.smartpolice.test;

import java.util.Date;
import java.util.List;

import com.sun.org.apache.bcel.internal.generic.NEW;

import cn.smartpolice.netdao.DevLog;
import cn.smartpolice.netdao.DevLogDao;
import cn.smartpolice.netdao.DeviceDao;
import cn.smartpolice.netdao.DeviceInf;
import cn.smartpolice.netdao.RelateDao;
import cn.smartpolice.netdao.ServerDao;
import cn.smartpolice.netdao.ServerInf;
import cn.smartpolice.netdao.UserDao;
import cn.smartpolice.netdao.UserInf;
import cn.smartpolice.netdao.UserLog;
import cn.smartpolice.netdao.UserLogDao;

public class daotest {

	public static void main(String[] args) {
		
//		DeviceInf dev = new DeviceInf();
//		String userString = "13882758888";
//		dev.setSerial("12345");
//		dev.setCode("1234");
//		dev.setUsername(userString);
//		dev.setPassword("123456");
//		dev.setState("1");
//		dev.setLongitude("12.2222");
//		dev.setLatitude("23.3333");
//		dev.setDate(new Date());
//		dev.setType("1");
//		dev.setMaxconlimit(10);
//		dev.setMphone(userString);
//		new DeviceDao().insertDevInf(dev);
//		System.out.print(new DeviceDao().findDevByName(userString).getDeviceid());
//		UserInf app = new UserInf();
//		String userString = "13882758888";
//		app.setUserName(userString);
//		app.setPassword("12345678");
//		app.setSet("1");
//		app.setRegDate(new Date());
//		app.setAuthority("1");
//		app.setState("1");
//		app.setName("liuchao");
//		app.setBirth(new Date());
//   	app.setSex(true);
//		app.setType("1");
//		app.setMail("124568@qq.com");
//		app.setMphone(userString);
//		new UserDao().insertAppuser(app);
//		System.out.print(new UserDao().findAppuserByName(userString).getUserID());
//	    List<Integer> list = new RelateDao().findUserIdByDeviceId(1);
//	    System.out.print(list);
	}
}
