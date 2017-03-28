package cn.smartpolice.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.smartpolice.hibernate.AddServers;
import cn.smartpolice.hibernate.PlatMsgAnnounce;
import cn.smartpolice.hibernate.SystemRunInfo;
import cn.smartpolice.webservice.AddServersService;
import cn.smartpolice.webservice.PlatMsgAnounceService;
import cn.smartpolice.webservice.SystemRunInfoService;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Controller("serverRunAction")
@Scope("prototype")

public class ServerRunAction extends ActionSupport {
	
	private AddServers addServers;
	
	private PlatMsgAnnounce platMsgAnnounce;
	
	private SystemRunInfo systemRunInfo;
	
	@Resource(name = "addServersService")
	private AddServersService addServersService;
	
	@Resource(name = "platMsgAnounceService")
	private PlatMsgAnounceService platMsgAnounceService;
	
	@Resource(name  = "systemRunInfoService")
	private SystemRunInfoService systemRunInfoService;
	
	HttpServletRequest request = ServletActionContext.getRequest();
	
	HttpSession session = request.getSession();

	public AddServers getAddServers() {
		return addServers;
	}

	public void setAddServers(AddServers addServers) {
		this.addServers = addServers;
	}

	public PlatMsgAnnounce getPlatMsgAnnounce() {
		return platMsgAnnounce;
	}

	public void setPlatMsgAnnounce(PlatMsgAnnounce platMsgAnnounce) {
		this.platMsgAnnounce = platMsgAnnounce;
	}

	public SystemRunInfo getSystemRunInfo() {
		return systemRunInfo;
	}

	public void setSystemRunInfo(SystemRunInfo systemRunInfo) {
		this.systemRunInfo = systemRunInfo;
	}

	public AddServersService getAddServersService() {
		return addServersService;
	}

	public void setAddServersService(AddServersService addServersService) {
		this.addServersService = addServersService;
	}

	public PlatMsgAnounceService getPlatMsgAnounceService() {
		return platMsgAnounceService;
	}

	public void setPlatMsgAnounceService(PlatMsgAnounceService platMsgAnounceService) {
		this.platMsgAnounceService = platMsgAnounceService;
	}

	public SystemRunInfoService getSystemRunInfoService() {
		return systemRunInfoService;
	}

	public void setSystemRunInfoService(SystemRunInfoService systemRunInfoService) {
		this.systemRunInfoService = systemRunInfoService;
	}
	
	public String AddServers(){
		 addServersService.AddServers(addServers);
		 return "addservers";
	}
	
	public String PlatMsgAnounce(){
		platMsgAnounceService.AddPlatMsgToDB(platMsgAnnounce);
		return "platmsganounce";
	}
	
	public String SystemRunInfo(){
		List info = systemRunInfoService.getSystemRunInfoFromDB();
		session.setAttribute("systemRunInfo", info);
		return "systemruninfo";
	}
}
