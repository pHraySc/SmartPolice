package cn.smartpolice.webservice;

import java.util.List;

import cn.smartpolice.hibernate.CompanyUser;

public interface FirmAdminService {

	public List firmAdminSelf(int id);


	public String updateNumber(int id, String number);


	public String updateEmail(int id, String email);


	public String updatPassword(int id, String pwd2);


	public List firmAdminDevive(int id);


	public List firmAdmindeviceInfo(int id);


	public List firmAdminSoft(int id);


	public List firmAdminSoftInfo(int id);


	public List firmAdminmsg(int id);


	public List firmAdminMsgInfo(int id);



}
