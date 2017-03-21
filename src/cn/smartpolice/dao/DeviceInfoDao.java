package cn.smartpolice.dao;

import java.util.List;

public interface DeviceInfoDao {
	public List findAuditDevice(); //��ѯδ���ǰ���豸
	public List findAllDevice();//��ѯ����˵�ȫ��ǰ���豸�豸
	public List findAuditMoveDevice();//��ѯδ��˵�ǰ���豸
	public List findAllMoveDevice();//��ѯǰ���豸
	public List findAllDev();//����ȫ����¼�豸
	public String Devicepass(int auditid);//ǰ���豸���ͨ��

	public String Devicerefused(int auditid);//ǰ���豸��˲�ͨ��
	public String MoveDevicepass(int auditid);;//�ƶ��豸���ͨ��
	public String MoveDevicerefused(int auditid);
	public List findAuditPredeviceInfo(int auditid);//ǰ���豸�鿴���飨���ҳ�棩
	public List findManagerPredeviceInfo(int auditid);//ǰ���豸�鿴���飨����ҳ�棩
	public List ManagerAuditConpanyInfo(int userid);//�����û������ϸ��Ϣ
}
