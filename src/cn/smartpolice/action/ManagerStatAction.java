package cn.smartpolice.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.smartpolice.webservice.ManagerAdminService;
import cn.smartpolice.webservice.ManagerStatService;


@SuppressWarnings("serial")
@Controller("managerStatAction")
@Scope("prototype")
public class ManagerStatAction {
	

	@Resource(name="managerStatService")
	private ManagerStatService managerStatService;

	public ManagerStatService getManagerStatService() {
		return managerStatService;
	}

	public void setManagerStatService(ManagerStatService managerStatService) {
		this.managerStatService = managerStatService;
	}
	
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpSession session = request.getSession();
	
	
	
	
	/*
	 * ��ʾ�����û�ע���������
	 */
	public String StatCompany() {


		List list = managerStatService.getCompanyData();// �������ͼ������
		session.setAttribute("list",list);

		return "success";

	}
	/*
	 * ��ʾǰ���豸ע���������
	 */
	public String StatDevice() {


		List list = managerStatService.getDeviceData();// �������ͼ������
		session.setAttribute("list",list);

		return "success";

	}
	public String StatMoveDevice() {


		List list = managerStatService.getMoveDeviceData();// �������ͼ������
		session.setAttribute("list",list);

		return "success";

	}
	public String StatUserinfo() {


		List list = managerStatService.getUserinfo();// �������ͼ������
		session.setAttribute("list",list);

		return "success";

	}
}
