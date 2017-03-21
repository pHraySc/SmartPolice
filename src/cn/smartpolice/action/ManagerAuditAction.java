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
	//审核厂商
	public String AuditCompany(){
		
		List auditCompany = this.managerAuditService.findAuditCompany();
		
		
		session.setAttribute("auditCompany", auditCompany);
		return "success";
	}
	
	//审核前端设备
	public String AuditDevice(){
		
		List auditDevice = this.managerAuditService.findAuditDevice();
		
		System.out.println(auditDevice);
		
		session.setAttribute("auditDevice", auditDevice);
		return "success";
	}
	
	//审核移动设备
public String AuditMoveDevice(){
		
		List auditMoveDevice = this.managerAuditService.findAuditMoveDevice();
		
		System.out.println(auditMoveDevice);
		
		session.setAttribute("auditMoveDevice", auditMoveDevice);
		return "success";
	}
/*
 * 厂商审核通过
 */
public String PassCompany(){
	
	HttpServletRequest request = ServletActionContext.getRequest();
	String userid1 = request.getParameter("id");
	int userid = Integer.valueOf(userid1); 
	this.managerAuditService.PassCompany(userid); 
	
	return "success";
   }
/*
 * 厂商审核拒绝
 */
public String RefusedCompany(){
	
	HttpServletRequest request = ServletActionContext.getRequest();
	String userid1 = request.getParameter("id");
	int userid = Integer.valueOf(userid1); 
	this.managerAuditService.RefusedCompany(userid); 
	
	return "success";
   }
/*
 * 前端设备审核通过
 */
public String PassDevice(){
	
	HttpServletRequest request = ServletActionContext.getRequest();
	String auditid1 = request.getParameter("id");
	int auditid = Integer.valueOf(auditid1); 
	this.managerAuditService.PassDevice(auditid); 
	
	return "success";
   }
/*
 * 前端设备审核拒绝
 */
public String RefusedDevice(){
	
	HttpServletRequest request = ServletActionContext.getRequest();
	String auditid1 = request.getParameter("id");
	int auditid = Integer.valueOf(auditid1); 
	this.managerAuditService.RefusedDevice(auditid); 
	
	return "success";
   }
/*
 * 移动设备审核通过
 */
public String PassMoveDevice(){
	
	HttpServletRequest request = ServletActionContext.getRequest();
	String auditid1 = request.getParameter("id");
	int auditid = Integer.valueOf(auditid1); 
	this.managerAuditService.PassMoveDevice(auditid); 
	
	return "success";
   }
/*
 * 前端设备审核拒绝
 */
public String RefusedMoveDevice(){
	
	HttpServletRequest request = ServletActionContext.getRequest();
	String auditid1 = request.getParameter("id");
	int auditid = Integer.valueOf(auditid1); 
	this.managerAuditService.RefusedMoveDevice(auditid); 
	
	return "success";
   }
/*
 * 批量处理厂商用户（审核通过，或者删除）
 * 
 * 
 * */
public String batchHandleaCompanyuser(){
	
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpSession session = request.getSession();
	
	String actiontype=request.getParameter("actiontype");//判断通过、删除
	String[] userids=request.getParameterValues("ids");//ids
	
	//执行动作
	if(actiontype!=null&&userids!=null&&userids.length!=0){

		int[] userid=new int[userids.length];
		for(int i=0;i<userids.length;i++){
			userid[i]=Integer.parseInt(userids[i]);
		}
		//判断执行动作
		if(actiontype.equals("pass")){	
			//执行service动作
			this.managerAuditService.batchPassCompanyuser(userid);
		}
		else if(actiontype.equals("refused")){
			//执行service动作
			this.managerAuditService.batchRefusedCompanyuser(userid);
		}else{
			//数据有误
		}
		
	}else{
		//error
	}
	System.out.println(userids);
	
	
	
	return "success";
	
	}
/*
 * 前端设备审核，批量处理
 * 
 * */
public String batchHandlePredevice(){
	
	
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpSession session = request.getSession();
	
	String actiontype=request.getParameter("actiontype");//判断通过、删除
	String[] ids=request.getParameterValues("ids");//ids
	System.out.println(ids);
	//执行动作
	if(actiontype!=null&&ids!=null&&ids.length!=0){

		int[] auditid=new int[ids.length];
		for(int i=0;i<ids.length;i++){
			auditid[i]=Integer.parseInt(ids[i]);
		}
		//判断执行动作
		if(actiontype.equals("pass")){	
			//执行service动作
			this.managerAuditService.batchPassPredevice(auditid);
		}
		else if(actiontype.equals("refused")){
			//执行service动作
			this.managerAuditService.batchRefusedPredevice(auditid);
		}else{
			//数据有误
		}
		
	}else{
		//error
	}
	
	
	
	return "success";
}

/*
 *移动设备审核，批量处理
 * 
 * */
public String batchHandleMovedevice(){
	
	
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpSession session = request.getSession();
	
	String actiontype=request.getParameter("actiontype");//判断通过、删除
	String[] ids=request.getParameterValues("ids");//ids
	
	//执行动作
	if(actiontype!=null&&ids!=null&&ids.length!=0){

		int[] auditid=new int[ids.length];
		for(int i=0;i<ids.length;i++){
			auditid[i]=Integer.parseInt(ids[i]);
		}
		//判断执行动作
		if(actiontype.equals("pass")){	
			//执行service动作
			this.managerAuditService.batchPassMovedevice(auditid);
		}
		else if(actiontype.equals("refused")){
			//执行service动作
			this.managerAuditService.batchRefusedMovedevice(auditid);
		}else{
			//数据有误
		}
		
	}else{
		//error
	}
	
	
	
	return "success";
}
//查看前端设备的详细信息		
public String PredeviceDetailOfAuditing(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String auditid1 = request.getParameter("id");
		int auditid = Integer.valueOf(auditid1); 
		List allPredeviceInfo=this.managerAuditService.PredeviceDetailOfAuditing(auditid); 
		session.setAttribute("allPredeviceInfo", allPredeviceInfo);
		System.out.println("allPredeviceInfo"+allPredeviceInfo);
		return "success";
	   }

//查看移动设备的详细信息		
public String MovdeviceDetailOfChecking(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String auditid1 = request.getParameter("id");
		int auditid = Integer.valueOf(auditid1); 
		List allPredeviceInfo=this.managerAuditService.PredeviceDetailOfAuditing(auditid); 
		session.setAttribute("allPredeviceInfo", allPredeviceInfo);
		System.out.println("allPredeviceInfo"+allPredeviceInfo);
		return "success";
	   }

//厂商用户详细信息（审核页面）
public String ManagerAuditConpanyInfo(){
	
	HttpServletRequest request = ServletActionContext.getRequest();
	String userid1 = request.getParameter("id");
	int userid = Integer.valueOf(userid1); 
	List alluserInfo=this.managerAuditService.ManagerAuditConpanyInfo(userid); 
	session.setAttribute("alluserInfo", alluserInfo);
	return "success";
   }


}
