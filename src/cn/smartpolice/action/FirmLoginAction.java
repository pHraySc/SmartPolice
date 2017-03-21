package cn.smartpolice.action;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.components.Password;
import org.hibernate.type.NumericBooleanType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.w3c.dom.ls.LSInput;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.xml.internal.bind.v2.model.core.ID;

import antlr.collections.List;
import cn.smartpolice.dao.StatDao;
import cn.smartpolice.hibernate.CompanyInfo;
import cn.smartpolice.hibernate.CompanyUser;
import cn.smartpolice.webservice.FirmInfoService;
import javassist.expr.NewArray;

/**
 * 厂商动作类
 * 
 * 
 * */


@SuppressWarnings("serial")
@Controller("firmLoginAction")
@Scope("prototype")
public class FirmLoginAction extends ActionSupport {
	@Resource(name="firmInfoService")
	private FirmInfoService firmInfoService;

	public FirmInfoService getFirmInfoService() {
		return firmInfoService;
	}
	public void setFirmInfoService(FirmInfoService firmInfoService) {
		this.firmInfoService = firmInfoService;
	}
	HttpServletRequest request= ServletActionContext.getRequest();
	HttpSession session=request.getSession();
	public String login(){
		
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		System.out.println("厂商登录");
		if(username!=null && password!=null && !username.equals("") && !password.equals("")){
			CompanyUser firm=this.firmInfoService.login(username);
			int id1=firm.getUserId();
			System.out.println(id1);
			session.setAttribute("id", id1);
			session.setAttribute("username", username);
			if(firm!=null){
				if(username.equals(firm.getUserName()) && password.equals(firm.getPassword())){
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
	
	/*
	 * 方法功能：厂商用户注册
	 */
	public String firmregister(){
		
		System.out.println("-----厂商用户注册-----");
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String username = request.getParameter("username");
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		String email= request.getParameter("email");
		String companyid = request.getParameter("companyid");
		String number = request.getParameter("number");
		String position = request.getParameter("position");
		String state="0";
		//String string=this.firmInfoService.checkuser(username);//验证用户名

			CompanyUser firm=new CompanyUser();
			
			firm.setCompanyid(Integer.valueOf(companyid));
			firm.setPassword(pwd);
			firm.setUserName(username);
			firm.setName(name);
			firm.setNumber(number);
			firm.setPosition(position);
			firm.setState(state);
			firm.setEmail(email);
	
			
			this.firmInfoService.register(firm);
			return SUCCESS;
		}
	/*else{
			System.out.println("用户名重复！");
			return ERROR;
		}
*/
	

}

	

