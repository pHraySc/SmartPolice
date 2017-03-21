package cn.smartpolice.action;



import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.smartpolice.webservice.ManagerAuditService;

@SuppressWarnings("serial")
@Controller("managerAuditAction")
@Scope("prototype")
public class ManagerAuditAction {

	@Resource(name="managerAuditService")
	private ManagerAuditService managerAuditService;
	
	public ManagerAuditService getManagerAuditService() {
		return managerAuditService;
	}

	public void setManagerAuditService(ManagerAuditService managerAuditService) {
		this.managerAuditService = managerAuditService;
	}
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpSession session = request.getSession();
	//��˳���
	public String AuditCompany(){
		
		List auditCompany = this.managerAuditService.findAuditCompany();
		
		
		session.setAttribute("auditCompany", auditCompany);
		return "success";
	}
	
	//���ǰ���豸
	public String AuditDevice(){
		
		List auditDevice = this.managerAuditService.findAuditDevice();
		
		System.out.println(auditDevice);
		
		session.setAttribute("auditDevice", auditDevice);
		return "success";
	}
	
	//����ƶ��豸
public String AuditMoveDevice(){
		
		List auditMoveDevice = this.managerAuditService.findAuditMoveDevice();
		
		System.out.println(auditMoveDevice);
		
		session.setAttribute("auditMoveDevice", auditMoveDevice);
		return "success";
	}
/*
 * �������ͨ��
 */
public String PassCompany(){
	
	HttpServletRequest request = ServletActionContext.getRequest();
	String userid1 = request.getParameter("id");
	int userid = Integer.valueOf(userid1); 
	this.managerAuditService.PassCompany(userid); 
	
	return "success";
   }
/*
 * ������˾ܾ�
 */
public String RefusedCompany(){
	
	HttpServletRequest request = ServletActionContext.getRequest();
	String userid1 = request.getParameter("id");
	int userid = Integer.valueOf(userid1); 
	this.managerAuditService.RefusedCompany(userid); 
	
	return "success";
   }
/*
 * ǰ���豸���ͨ��
 */
public String PassDevice(){
	
	HttpServletRequest request = ServletActionContext.getRequest();
	String auditid1 = request.getParameter("id");
	int auditid = Integer.valueOf(auditid1); 
	this.managerAuditService.PassDevice(auditid); 
	
	return "success";
   }
/*
 * ǰ���豸��˾ܾ�
 */
public String RefusedDevice(){
	
	HttpServletRequest request = ServletActionContext.getRequest();
	String auditid1 = request.getParameter("id");
	int auditid = Integer.valueOf(auditid1); 
	this.managerAuditService.RefusedDevice(auditid); 
	
	return "success";
   }
/*
 * �ƶ��豸���ͨ��
 */
public String PassMoveDevice(){
	
	HttpServletRequest request = ServletActionContext.getRequest();
	String auditid1 = request.getParameter("id");
	int auditid = Integer.valueOf(auditid1); 
	this.managerAuditService.PassMoveDevice(auditid); 
	
	return "success";
   }
/*
 * ǰ���豸��˾ܾ�
 */
public String RefusedMoveDevice(){
	
	HttpServletRequest request = ServletActionContext.getRequest();
	String auditid1 = request.getParameter("id");
	int auditid = Integer.valueOf(auditid1); 
	this.managerAuditService.RefusedMoveDevice(auditid); 
	
	return "success";
   }
/*
 * �����������û������ͨ��������ɾ����
 * 
 * 
 * */
public String batchHandleaCompanyuser(){
	
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpSession session = request.getSession();
	
	String actiontype=request.getParameter("actiontype");//�ж�ͨ����ɾ��
	String[] userids=request.getParameterValues("ids");//ids
	
	//ִ�ж���
	if(actiontype!=null&&userids!=null&&userids.length!=0){

		int[] userid=new int[userids.length];
		for(int i=0;i<userids.length;i++){
			userid[i]=Integer.parseInt(userids[i]);
		}
		//�ж�ִ�ж���
		if(actiontype.equals("pass")){	
			//ִ��service����
			this.managerAuditService.batchPassCompanyuser(userid);
		}
		else if(actiontype.equals("refused")){
			//ִ��service����
			this.managerAuditService.batchRefusedCompanyuser(userid);
		}else{
			//��������
		}
		
	}else{
		//error
	}
	System.out.println(userids);
	
	
	
	return "success";
	
	}
/*
 * ǰ���豸��ˣ���������
 * 
 * */
public String batchHandlePredevice(){
	
	
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpSession session = request.getSession();
	
	String actiontype=request.getParameter("actiontype");//�ж�ͨ����ɾ��
	String[] ids=request.getParameterValues("ids");//ids
	System.out.println(ids);
	//ִ�ж���
	if(actiontype!=null&&ids!=null&&ids.length!=0){

		int[] auditid=new int[ids.length];
		for(int i=0;i<ids.length;i++){
			auditid[i]=Integer.parseInt(ids[i]);
		}
		//�ж�ִ�ж���
		if(actiontype.equals("pass")){	
			//ִ��service����
			this.managerAuditService.batchPassPredevice(auditid);
		}
		else if(actiontype.equals("refused")){
			//ִ��service����
			this.managerAuditService.batchRefusedPredevice(auditid);
		}else{
			//��������
		}
		
	}else{
		//error
	}
	
	
	
	return "success";
}

/*
 *�ƶ��豸��ˣ���������
 * 
 * */
public String batchHandleMovedevice(){
	
	
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpSession session = request.getSession();
	
	String actiontype=request.getParameter("actiontype");//�ж�ͨ����ɾ��
	String[] ids=request.getParameterValues("ids");//ids
	
	//ִ�ж���
	if(actiontype!=null&&ids!=null&&ids.length!=0){

		int[] auditid=new int[ids.length];
		for(int i=0;i<ids.length;i++){
			auditid[i]=Integer.parseInt(ids[i]);
		}
		//�ж�ִ�ж���
		if(actiontype.equals("pass")){	
			//ִ��service����
			this.managerAuditService.batchPassMovedevice(auditid);
		}
		else if(actiontype.equals("refused")){
			//ִ��service����
			this.managerAuditService.batchRefusedMovedevice(auditid);
		}else{
			//��������
		}
		
	}else{
		//error
	}
	
	
	
	return "success";
}
//�鿴ǰ���豸����ϸ��Ϣ		
public String PredeviceDetailOfAuditing(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String auditid1 = request.getParameter("id");
		int auditid = Integer.valueOf(auditid1); 
		List allPredeviceInfo=this.managerAuditService.PredeviceDetailOfAuditing(auditid); 
		session.setAttribute("allPredeviceInfo", allPredeviceInfo);
		System.out.println("allPredeviceInfo"+allPredeviceInfo);
		return "success";
	   }

//�鿴�ƶ��豸����ϸ��Ϣ		
public String MovdeviceDetailOfChecking(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String auditid1 = request.getParameter("id");
		int auditid = Integer.valueOf(auditid1); 
		List allPredeviceInfo=this.managerAuditService.PredeviceDetailOfAuditing(auditid); 
		session.setAttribute("allPredeviceInfo", allPredeviceInfo);
		System.out.println("allPredeviceInfo"+allPredeviceInfo);
		return "success";
	   }

//�����û���ϸ��Ϣ�����ҳ�棩
public String ManagerAuditConpanyInfo(){
	
	HttpServletRequest request = ServletActionContext.getRequest();
	String userid1 = request.getParameter("id");
	int userid = Integer.valueOf(userid1); 
	List alluserInfo=this.managerAuditService.ManagerAuditConpanyInfo(userid); 
	session.setAttribute("alluserInfo", alluserInfo);
	return "success";
   }


}
