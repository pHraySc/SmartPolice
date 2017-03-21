package cn.smartpolice.dao;

import java.util.List;

import cn.smartpolice.hibernate.CompanyUser;

public interface FirmUserInfoDao {

	List firmAdminSelf(int id);


	String updateNumber(int id, String number);

	String updateEmail(int id, String email);


	String updatPassword(int id, String pwd2);


	List firmAdminDevive(int id);


	List firmAdmindeviceInfo(int id);


	List firmAdminSoft(int id);


	List firmAdminSoftInfo(int id);


	List firmAdminmsg(int id);


	List firmAdminmsgInfo(int id);


}
