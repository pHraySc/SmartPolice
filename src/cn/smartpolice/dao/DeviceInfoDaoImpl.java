package cn.smartpolice.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SQLQuery;
import org.hibernate.classic.Session;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("deviceInfoDao")
@Transactional(readOnly=false)
public class DeviceInfoDaoImpl implements DeviceInfoDao {

	@Resource(name="hibernateTemplate")
	private HibernateTemplate hibernateTemplate;
	@Override
	public List findAuditDevice() {
		Session session=this.hibernateTemplate.getSessionFactory().getCurrentSession();
		String sql="select dev.auditid,dev.devicename,dev.demo,dev.code,com.name"
				+" from device_audit as dev,company_inf as com"
			   + " where dev.state like '0' and dev.type like '0' and dev.companyid=com.companyid ";
		List auditDevice=(List) session.createSQLQuery(sql).
				setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		
		return auditDevice;
	}
	@Override
	public List findAllDevice() {
		Session session=this.hibernateTemplate.getSessionFactory().getCurrentSession();
		String sql="select  dev.auditid,dev.devicename,dev.demo,cominf.name,comuser.username,dev.state"
				+ " from device_audit as dev,company_inf as cominf,company_user as comuser where "
				+ "dev.companyid=cominf.companyid and comuser.companyid=cominf.companyid and "
				+ "dev.type like '0'";
		List allDevice=(List) session.createSQLQuery(sql).
				setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		
		return allDevice;
	}
	@Override
	public List findAuditMoveDevice() {
		// 查找全部未审核的移动设备
		Session session=this.hibernateTemplate.getSessionFactory().getCurrentSession();
		String sql="select dev.auditid,dev.devicename,dev.demo,dev.code,com.name"
				+" from device_audit as dev,company_inf as com"
				   + " where dev.state like '0' and dev.type like '1' and dev.companyid=com.companyid ";
		List auditMoveDevice=(List) session.createSQLQuery(sql).
				setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		
		return auditMoveDevice;
	}
	@Override
	public List findAllMoveDevice() {
		Session session=this.hibernateTemplate.getSessionFactory().getCurrentSession();
		String sql="select dev.auditid,dev.devicename,dev.demo,cominf.name,comuser.username,dev.state"
				+ " from device_audit as dev,company_inf as cominf,company_user as comuser where "
				+ "dev.companyid=cominf.companyid and comuser.companyid=cominf.companyid and "
				+ "dev.type like '1'";
		List allMoveDevice=(List) session.createSQLQuery(sql).
				setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		
		return allMoveDevice;
	}
	@Override
	public List findAllDev() {
		Session session=this.hibernateTemplate.getSessionFactory().getCurrentSession();
		String sql="select dev.auditid,dev.devicename,dev.demo,cominf.name,comuser.username,dev.state"
				+ " from device_audit as dev,company_inf as cominf,company_user as comuser where "
				+ "dev.companyid=cominf.companyid and comuser.companyid=cominf.companyid and "
				+ "dev.type like '1'";
		List allMoveDevice=(List) session.createSQLQuery(sql).
				setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		
		return allMoveDevice;
	}
	@Override
	public String Devicepass(int auditid) {
		Session session=this.hibernateTemplate.getSessionFactory().getCurrentSession();
		String sql="UPDATE device_audit SET state='1' WHERE auditid=?";
		SQLQuery query=session.createSQLQuery(sql);
		query.setInteger(0,auditid);
		query.executeUpdate();
		return "success";
	}

	@Override
	public String Devicerefused(int auditid) {
		Session session=this.hibernateTemplate.getSessionFactory().getCurrentSession();
		String sql="UPDATE device_audit SET state='-1' WHERE auditid=?";
		SQLQuery query=session.createSQLQuery(sql);
		query.setInteger(0,auditid);
		query.executeUpdate();
		return "success";
	}
	@Override
	public String MoveDevicepass(int auditid) {
		Session session=this.hibernateTemplate.getSessionFactory().getCurrentSession();
		String sql="UPDATE device_audit SET state='1' WHERE auditid=?";
		SQLQuery query=session.createSQLQuery(sql);
		query.setInteger(0,auditid);
		query.executeUpdate();
		return "success";
	}

	@Override
	public String MoveDevicerefused(int auditid) {
		Session session=this.hibernateTemplate.getSessionFactory().getCurrentSession();
		String sql="UPDATE device_audit SET state='-1' WHERE auditid=?";
		SQLQuery query=session.createSQLQuery(sql);
		query.setInteger(0,auditid);
		query.executeUpdate();
		return "success";
	}
	@Override
	public List findAuditPredeviceInfo(int auditid) {
		Session session=this.hibernateTemplate.getSessionFactory().getCurrentSession();
		String sql="SELECT d.devicename,d.code,d.demo,d.date,i.name FROM device_audit AS d,company_inf as i WHERE d.auditid=?"+
                    " and d.companyid=i.companyid";
		SQLQuery query=session.createSQLQuery(sql);
		query.setInteger(0,auditid);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		 List PredeviceInfo = query.list();
		return PredeviceInfo;
	}
	@Override
	public List findManagerPredeviceInfo(int auditid) {
		Session session=this.hibernateTemplate.getSessionFactory().getCurrentSession();
		String sql="SELECT d.devicename,d.code,d.demo,d.date,i.name FROM device_audit AS d,company_inf as i WHERE d.auditid=?"+
                    " and d.companyid=i.companyid";
		SQLQuery query=session.createSQLQuery(sql);
		query.setInteger(0,auditid);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		 List PredeviceInfo = query.list();
		return PredeviceInfo;
	}
	@Override
	public List ManagerAuditConpanyInfo(int userid) {
		Session session=this.hibernateTemplate.getSessionFactory().getCurrentSession();
		String sql="SELECT * from company_user where userid=? ";
		SQLQuery query=session.createSQLQuery(sql);
		query.setInteger(0,userid);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		 List alluserInfo = query.list();
		return alluserInfo;
	}

}
