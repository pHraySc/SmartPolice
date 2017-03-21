package cn.smartpolice.action;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.smartpolice.hibernate.CompanyUser;
import cn.smartpolice.webservice.FirmAdminService;
import cn.smartpolice.webservice.ManagerAdminService;

@SuppressWarnings("serial")
@Controller("firmAdminAction")
@Scope("prototype")

public class FirmAdminAction {
	@Resource(name="firmAdminService")
	private FirmAdminService firmAdminService;

	public FirmAdminService getFirmAdminService() {
		return firmAdminService;
	}

	public void setFirmAdminService(FirmAdminService firmAdminService) {
		this.firmAdminService = firmAdminService;
	}
	
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpSession session = request.getSession();
	
	public String firmAdminSelf(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String id1 = request.getParameter("id");
		if(id1!=null){
			int id = Integer.valueOf(id1);
			List firmInfo=this.firmAdminService.firmAdminSelf(id); 
			session.setAttribute("firmInfo", firmInfo);
			System.out.println("id"+id);
			System.out.println("firmInfo"+firmInfo);
		}else
		{
			int id=(int) session.getAttribute("id2");
			List firmInfo=this.firmAdminService.firmAdminSelf(id); 
			session.setAttribute("firmInfo", firmInfo);
			System.out.println("id"+id);
			System.out.println("firmInfo"+firmInfo);
		}
		return "success";
		
	   }
	
public String firmAdminChangeInfo(){
		
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpSession session = request.getSession();
	
	List f=(List) session.getAttribute("firmInfo");
	HashMap hp=(HashMap) f.get(0);
	int id= (int) hp.get("userid");
	String number=request.getParameter("number");
	String email=request.getParameter("email");
	
	if(number!=null&&!number.equals("")&&!number.equals(hp.get("number"))){
		String firmInfo=this.firmAdminService.updateNumber(id,number);
	
	}
	
	if(email!=null&&!email.equals("")&&!email.equals(hp.get("email"))){
		String firmInfo=this.firmAdminService.updateEmail(id,email);
	
	}
	session.setAttribute("id2", id);
	System.out.println("id:"+id);
	return "success";

	
	
	}

		
		/*
		 * 修改密码
		 * 
		 * */

	public String firmAdimnPassword(){
			
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();

			List f=(List) session.getAttribute("firmInfo");
			HashMap hp=(HashMap) f.get(0);
			int id= (int) hp.get("userid");// 登录厂商
			
			String pwd1=request.getParameter("pwd1");
			String pwd2=request.getParameter("pwd2");
			String pwd3=request.getParameter("Rpwd");
			if(pwd1!=null&&hp.get("password").equals(pwd1)&&pwd2!=null&&!pwd2.equals("")&&pwd3!=null&&!pwd3.equals("")&&pwd2.equals(pwd3)){
				String firmInfo=this.firmAdminService.updatPassword(id,pwd2);
				request.setAttribute("backMsg", "密码修改成功！");
		
		
			}

			
	return "success";
	}
	
	
	/*
	 * 设备信息
	 * 
	 * */
	
	public String firmAdminDevive(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		int id=(int) session.getAttribute("id");// 登录厂商
		
		List deviceInfo=this.firmAdminService.firmAdminDevive(id); 
		session.setAttribute("deviceInfo", deviceInfo);
		System.out.println("deviceInfo"+deviceInfo);
	
	

		
		return "success";
	}
	/*
	 * 设备详细信息
	 * 
	 * */
	
	public String firmAdmindeviceInfo(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		int id=(int) session.getAttribute("id");// 登录厂商
		
		List deviceInfo=this.firmAdminService.firmAdmindeviceInfo(id); 
		session.setAttribute("deviceInfo", deviceInfo);
		System.out.println("deviceInfo"+deviceInfo);
	
	

		
		return "success";
	}
	
	/*
	 *软件信息管理
	 * 
	 */
	public String firmAdminSoft(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		int id=(int) session.getAttribute("id");// 登录厂商
		
		List allsoft=this.firmAdminService.firmAdminSoft(id); 
		session.setAttribute("allsoft", allsoft);
		System.out.println("allsoft"+allsoft);
	
	

		
		return "success";
	}
	/*
	 *软件详细信息
	 * 
	 */
	public String firmAdminSoftInfo(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String id1 = request.getParameter("id");
		int id = Integer.valueOf(id1); 
		
		List softInfo=this.firmAdminService.firmAdminSoftInfo(id); 
		session.setAttribute("softInfo", softInfo);
		System.out.println("softInfo"+softInfo);
	
	

		
		return "success";
	}
	
	/*
	 *厂商通知信息
	 * 
	 */
	public String firmAdminmsg(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		int id=(int) session.getAttribute("id");// 登录厂商
		List firmmsg=this.firmAdminService.firmAdminmsg(id); 
		session.setAttribute("firmmsg", firmmsg);
		System.out.println("firmmsg"+firmmsg);
	
	

		
		return "success";
	}
	public String firmAdminMsgInfo(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String id1 = request.getParameter("id");
		int id = Integer.valueOf(id1); 
		System.out.println("id:"+id);
		List msgInfo=this.firmAdminService.firmAdminMsgInfo(id); 
		session.setAttribute("msgInfo", msgInfo);
		System.out.println("msgInfo"+msgInfo);
	
	

		
		return "success";
	}
	
	
	
}
