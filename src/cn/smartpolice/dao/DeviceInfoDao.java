package cn.smartpolice.dao;

import java.util.List;

public interface DeviceInfoDao {
	public List findAuditDevice(); //查询未审核前端设备
	public List findAllDevice();//查询已审核的全部前端设备设备
	public List findAuditMoveDevice();//查询未审核的前端设备
	public List findAllMoveDevice();//查询前端设备
	public List findAllDev();//查找全部登录设备
	public String Devicepass(int auditid);//前端设备审核通过

	public String Devicerefused(int auditid);//前端设备审核不通过
	public String MoveDevicepass(int auditid);;//移动设备审核通过
	public String MoveDevicerefused(int auditid);
	public List findAuditPredeviceInfo(int auditid);//前端设备查看详情（审核页面）
	public List findManagerPredeviceInfo(int auditid);//前端设备查看详情（管理页面）
	public List ManagerAuditConpanyInfo(int userid);//厂商用户审核详细信息
}
