package cn.smartpolice.webservice;



import java.util.List;


public interface ManagerAuditService {
	
	public List findAuditCompany();//查找待审核的厂商
	public List findAuditDevice();//查找待审核的前端设备
	public List findAuditMoveDevice();//查找待审核的移动设备
	public String PassCompany(int userid);//通过审核
	public String RefusedCompany(int userid);//审核不通过
	public String PassDevice(int auditid);//前端设备审核通过
	public String RefusedDevice(int auditid);//前端设备审核不通过
	public String PassMoveDevice(int auditid);//移动设备审核通过
	public String RefusedMoveDevice(int auditid);//移动设备审核不通过
	public void batchPassCompanyuser(int[] userid);//厂商批量审核通过
	public void batchRefusedCompanyuser(int[] userid);//厂商批量不审核通过
	public void batchPassPredevice(int[] auditid);//前端设备批量审核通过
	public void batchRefusedPredevice(int[] auditid);//前端设备批量不审核通过
	public void batchPassMovedevice(int[] auditid);//移动设备批量审核通过
	public void batchRefusedMovedevice(int[] auditid);//移动设备批量不审核通过
	public List PredeviceDetailOfAuditing(int auditid);//前端设备查看详情
	public List ManagerAuditConpanyInfo(int userid);//厂商用户详细信息（审核）

}
