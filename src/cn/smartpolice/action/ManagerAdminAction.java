package cn.smartpolice.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.smartpolice.webservice.ManagerAdminService;


@SuppressWarnings("serial")
@Controller("managerAdminAction")
@Scope("prototype")
public class ManagerAdminAction {

	@Resource(name="managerAdminService")
	private ManagerAdminService managerAdminService;

	public ManagerAdminService getManagerAdminService() {
		return managerAdminService;
	}

	public void setManagerAdminService(ManagerAdminService managerAdminService) {
		this.managerAdminService = managerAdminService;
	}
	
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpSession session = request.getSession();
	
	//查找全部厂商
	public String allCompany(){
		
		List allCompany = this.managerAdminService.findAllCompany();
		
		/*System.out.println(allCompany);*/
		
		session.setAttribute("allCompany", allCompany);
		
		return "success";
	}
	//查找全部前端设备
	public String allDevice(){
		List allDevice = this.managerAdminService.findAllDevice();
		session.setAttribute("allDevice", allDevice);
		System.out.println(allDevice);
		return "success";
	}
	
	//查找全部移动设备
	public String allMoveDevice(){
		List allMoveDevice = this.managerAdminService.findAllMoveDevice();
		session.setAttribute("allMoveDevice", allMoveDevice);
		System.out.println(allMoveDevice);
		return "success";
	}
	//查找全部APP用户
	public String allUserinfo(){
		List allUserinfo = this.managerAdminService.findAllUserinfo();
		session.setAttribute("allUserinfo", allUserinfo);
		System.out.println("alluserinfo"+allUserinfo);
		return "success";
	}
	//查找全部服务器信息
		public String allWebservice(){
			List allWebservice = this.managerAdminService.findAllWebservice();
			session.setAttribute("allWebservice", allWebservice);
			System.out.println(allWebservice);
			return "success";
		}
		//查找全部设备报警消息
		public String allMsg_alarms(){
					List allmsg_alarms = this.managerAdminService.findAllMsg_alarms();
					session.setAttribute("allmsg_alarms", allmsg_alarms);
					System.out.println("allmsg_alarms"+allmsg_alarms);
					return "success";
				}
		//查找全部聊天消息
		public String allMsg_chat(){
					List allmsg_chat = this.managerAdminService.findAllMsg_chat();
					session.setAttribute("allmsg_chat", allmsg_chat);
					System.out.println("allmsg_chat"+allmsg_chat);
					return "success";
				}
		//查找全部通知消息
				public String allMsg_notice(){
							List allmsg_notice = this.managerAdminService.findAllMsg_notice();
							session.setAttribute("allmsg_notice", allmsg_notice);
							System.out.println("allmsg_notice"+allmsg_notice);
							return "success";
						}
		//查找全部反馈消息
				public String allComment(){
							List allcomment = this.managerAdminService.findAllComment();
							session.setAttribute("allcomment", allcomment);
							System.out.println("allcomment"+allcomment);
							return "success";
						}
				
		//查看厂商的详细信息		
			public String CompanyDetail1(){
					
					HttpServletRequest request = ServletActionContext.getRequest();
					String auditid1 = request.getParameter("id");
					int companyid = Integer.valueOf(auditid1); 
					List allCompanyInfo=this.managerAdminService.CompanyDetail1(companyid); 
					session.setAttribute("allCompanyInfo", allCompanyInfo);
					System.out.println("allCompanyInfo"+allCompanyInfo);
					return "success";
				   }
			
			//查看前端设备的详细信息		
			public String PredeviceDetailOfManager(){
					
					HttpServletRequest request = ServletActionContext.getRequest();
					String auditid1 = request.getParameter("id");
					int auditid = Integer.valueOf(auditid1); 
					List allPredeviceInfo=this.managerAdminService.PredeviceDetailOfManager(auditid); 
					session.setAttribute("allPredeviceInfo", allPredeviceInfo);
					System.out.println("allPredeviceInfo"+allPredeviceInfo);
					return "success";
				   }
			//查看移动设备的详细信息
			public String MovedeviceDetailOfManager(){
				
				HttpServletRequest request = ServletActionContext.getRequest();
				String auditid1 = request.getParameter("id");
				int auditid = Integer.valueOf(auditid1); 
				List allPredeviceInfo=this.managerAdminService.PredeviceDetailOfManager(auditid); 
				session.setAttribute("allPredeviceInfo", allPredeviceInfo);
				System.out.println("allPredeviceInfo"+allPredeviceInfo);
				return "success";
			   }
			
			//查看注册用户的详细信息
			public String UserDetail1(){
				
				HttpServletRequest request = ServletActionContext.getRequest();
				String userid1 = request.getParameter("id");
				int userid = Integer.valueOf(userid1); 
				List allUserInfo=this.managerAdminService.UserDetail1(userid); 
				session.setAttribute("allUserInfo", allUserInfo);
				System.out.println("allUserInfo"+allUserInfo);
				return "success";
			   }
			
			//查看服务器的详细信息
			public String ServiceOfChecked(){
				
				HttpServletRequest request = ServletActionContext.getRequest();
				String serviceid1 = request.getParameter("id");
				int serviceid = Integer.valueOf(serviceid1); 
				List allServiceInfo=this.managerAdminService.ServiceOfChecked(serviceid); 
				session.setAttribute("allServiceInfo", allServiceInfo);
				System.out.println("allServiceInfo"+allServiceInfo);
				return "success";
			   }
			//查看报警消息的详细信息
			public String All_msg_alarms(){
				
				HttpServletRequest request = ServletActionContext.getRequest();
				String alarmid1 = request.getParameter("id");
				int alarmid = Integer.valueOf(alarmid1); 
				List allalarmmsgInfo=this.managerAdminService.All_msg_alarms(alarmid); 
				session.setAttribute("allalarmmsgInfo", allalarmmsgInfo);
				System.out.println("allalarmmsgInfo"+allalarmmsgInfo);
				return "success";
			   }
			
			//查看聊天消息的详细信息
			public String All_msg_chat(){
				
				HttpServletRequest request = ServletActionContext.getRequest();
				String chatid1 = request.getParameter("id");
				int chatid = Integer.valueOf(chatid1); 
				List allchatmsgInfo=this.managerAdminService.All_msg_chat(chatid); 
				session.setAttribute("allchatmsgInfo", allchatmsgInfo);
				System.out.println("allchatmsgInfo"+allchatmsgInfo);
				return "success";
			   }
			
			//查看通知消息的详细信息
			public String All_msg_notice(){
				
				HttpServletRequest request = ServletActionContext.getRequest();
				String noticeid1 = request.getParameter("id");
				int noticeid = Integer.valueOf(noticeid1); 
				List allnoticemsgInfo=this.managerAdminService.All_msg_notice(noticeid); 
				session.setAttribute("allnoticemsgInfo", allnoticemsgInfo);
				System.out.println("allnoticemsgInfo"+allnoticemsgInfo);
				return "success";
			   }
			
			//查看反馈消息的详细信息
			public String Allcomment(){
				
				HttpServletRequest request = ServletActionContext.getRequest();
				String commentid1 = request.getParameter("id");
				int commentid = Integer.valueOf(commentid1); 
				List allcommentInfo=this.managerAdminService.Allcomment(commentid); 
				session.setAttribute("allcommentInfo", allcommentInfo);
				System.out.println("allcommentInfo"+allcommentInfo);
				return "success";
			   }

			
}
