package cn.smartpolice.webservice;



import javax.annotation.Resource;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.smartpolice.dao.CompanyInfoDao;
import cn.smartpolice.dao.DeviceInfoDao;
import cn.smartpolice.dao.ManagerInfoDao;


@Service("managerAuditService")
@Transactional(readOnly=false)
public class ManagerAuditServiceImpl implements ManagerAuditService {
	
	@Resource(name="companyInfoDao")
	private CompanyInfoDao companyInfoDao;
	
	@Resource(name="deviceInfoDao")
	private DeviceInfoDao deviceInfoDao;
	
	@Override
	public List findAuditCompany() {
		
		List auditCompany = companyInfoDao.findAuditCompany();
		return auditCompany;
	}

	@Override
	public List findAuditDevice() {
		List auditDevice = deviceInfoDao.findAuditDevice();
		return auditDevice;
	}
	@Override
	public List findAuditMoveDevice() {
		List auditMoveDevice = deviceInfoDao.findAuditMoveDevice();
		return auditMoveDevice;
	}

	@Override
	public String PassCompany(int userid) {
		String Companypass = companyInfoDao.Companypass(userid);
		return Companypass;
		
	}

	@Override
	public String RefusedCompany(int userid) {
		String Companyrefused = companyInfoDao.Companyrefused(userid);
		return Companyrefused;
		
	}

	@Override
	public String PassDevice(int auditid) {
		String PassDevice = deviceInfoDao.Devicepass(auditid);
		return PassDevice;
	}

	@Override
	public String RefusedDevice(int auditid) {
		String RefusedDevice = deviceInfoDao.Devicerefused(auditid);
		return RefusedDevice;
	}

	@Override
	public String PassMoveDevice(int auditid) {
		String PassMoveDevice = deviceInfoDao.MoveDevicepass(auditid);
		return PassMoveDevice;
	}

	@Override
	public String RefusedMoveDevice(int auditid) {
		String RefusedMoveDevice = deviceInfoDao.MoveDevicerefused(auditid);
		return RefusedMoveDevice;
	}

	@Override
	public void batchPassCompanyuser(int[] userid) {
		for(int i =0;i<userid.length;i++){
			this.companyInfoDao.Companypass(userid[i]);
		}

		
	}

	@Override
	public void batchRefusedCompanyuser(int[] userid) {
		for(int i =0;i<userid.length;i++){
			this.companyInfoDao.Companyrefused(userid[i]);
		}

	}

	@Override
	public void batchPassPredevice(int[] auditid) {
		for(int i =0;i<auditid.length;i++){
			this.deviceInfoDao.Devicepass(auditid[i]);
		}
		
	}

	@Override
	public void batchRefusedPredevice(int[] auditid) {
		for(int i =0;i<auditid.length;i++){
			this.deviceInfoDao.Devicerefused(auditid[i]);
		}
		
	}
	@Override
	public void batchPassMovedevice(int[] auditid) {
		for(int i =0;i<auditid.length;i++){
			this.deviceInfoDao.MoveDevicepass(auditid[i]);
		}
		
	}

	@Override
	public void batchRefusedMovedevice(int[] auditid) {
		for(int i =0;i<auditid.length;i++){
			this.deviceInfoDao.MoveDevicerefused(auditid[i]);
		}
		
	}

	@Override
	public List PredeviceDetailOfAuditing(int auditid) {
		List PredeviceInfo = deviceInfoDao.findAuditPredeviceInfo(auditid);
		return PredeviceInfo;
	}

	@Override
	public List ManagerAuditConpanyInfo(int userid) {
		List alluserInfo = deviceInfoDao.ManagerAuditConpanyInfo(userid);
		return alluserInfo;
	}


}
