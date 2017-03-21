package cn.smartpolice.webservice;

import java.util.List;

public  interface ManagerStatService {

	public List getCompanyData();//获取厂商注册报表数据

	public List getDeviceData();//获取设备注册报表数据

	public List getMoveDeviceData();//获取所有移动监控设备的报表数据

	public List getUserinfo();//获取所有注册用户的报表数据

}
