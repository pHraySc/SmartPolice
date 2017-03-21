package cn.smartpolice.webservice;



import java.util.List;


public interface ManagerAuditService {
	
	public List findAuditCompany();//���Ҵ���˵ĳ���
	public List findAuditDevice();//���Ҵ���˵�ǰ���豸
	public List findAuditMoveDevice();//���Ҵ���˵��ƶ��豸
	public String PassCompany(int userid);//ͨ�����
	public String RefusedCompany(int userid);//��˲�ͨ��
	public String PassDevice(int auditid);//ǰ���豸���ͨ��
	public String RefusedDevice(int auditid);//ǰ���豸��˲�ͨ��
	public String PassMoveDevice(int auditid);//�ƶ��豸���ͨ��
	public String RefusedMoveDevice(int auditid);//�ƶ��豸��˲�ͨ��
	public void batchPassCompanyuser(int[] userid);//�����������ͨ��
	public void batchRefusedCompanyuser(int[] userid);//�������������ͨ��
	public void batchPassPredevice(int[] auditid);//ǰ���豸�������ͨ��
	public void batchRefusedPredevice(int[] auditid);//ǰ���豸���������ͨ��
	public void batchPassMovedevice(int[] auditid);//�ƶ��豸�������ͨ��
	public void batchRefusedMovedevice(int[] auditid);//�ƶ��豸���������ͨ��
	public List PredeviceDetailOfAuditing(int auditid);//ǰ���豸�鿴����
	public List ManagerAuditConpanyInfo(int userid);//�����û���ϸ��Ϣ����ˣ�

}
