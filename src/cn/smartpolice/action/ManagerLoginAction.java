package cn.smartpolice.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.smartpolice.hibernate.ManagerInfo;
import cn.smartpolice.webservice.ManagerInfoService;

/**
 * 管理员动作类
 * 
 * 
 * */

@SuppressWarnings("serial")
@Controller("managerLoginAction")
@Scope("prototype")
public class ManagerLoginAction extends ActionSupport {
	@Resource(name="managerInfoService")
	private ManagerInfoService managerInfoService;

	public ManagerInfoService getManagerInfoService() {
		return managerInfoService;
	}

	public void setManagerInfoService(ManagerInfoService managerInfoService) {
		this.managerInfoService = managerInfoService;
	}
	HttpServletRequest request= ServletActionContext.getRequest();
	HttpSession session=request.getSession();
	public String login(){
		
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		List managermsg=this.managerInfoService.managerMsg();
		session.setAttribute("managermsg", managermsg);
		System.out.println("managermsg:"+managermsg);
		session.setAttribute("username", username);
		System.out.println("管理员登录");
		if(username!=null && password!=null && !username.equals("") && !password.equals("")){
			ManagerInfo manager=this.managerInfoService.login(username);
			if(manager!=null){
				if(username.equals(manager.getUserName()) && password.equals(manager.getPassword())){
					session.setAttribute("managerUser", manager);
					return SUCCESS;
				} else return ERROR;
			} else return ERROR;
		} else return ERROR;
	}
	
	public String logout(){
		
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.clear();
		return SUCCESS;
		
	}
}
