package cn.smartpolice.net;

import java.io.File;

import java.util.Iterator;  
import org.dom4j.Document;  
import org.dom4j.Element;  
import org.dom4j.io.SAXReader;

import cn.smartpolice.workbean.SysCfgInfo;
import cn.smartpolice.workbean.SysInfo;

/**
 * 读取配置文件内容，赋值给SysCfgInfo对象
 * @author 刘超
 *
 */
public class ReadSysCfgInfo {
	
	public SysCfgInfo sysCfgInfo;
	
	public void loadSysCfgInfo(String path){
		if(sysCfgInfo == null){
			sysCfgInfo = getSysCfgInfo(path);
			SysInfo.setSysCfgInfo(sysCfgInfo);
		}
	}
	
	
	public SysCfgInfo getSysCfgInfo(String path){
		SysCfgInfo sysCfgInfo = new SysCfgInfo();
		try {
            File f = new File(path);
            if (!f.exists()) {
            	System.out.println(path);
                System.out.println("Error : SysCfgInfo file doesn't exist!");
                System.exit(1);
            }
            
            SAXReader reader = new SAXReader();
            Document doc;
            doc = reader.read(f);
            Element root = doc.getRootElement();
            Element data;
            Iterator<?> itr = root.elementIterator("VALUE");
            data = (Element) itr.next();
  
            int maxLoginDevices = Integer.parseInt(data.elementText("maxLoginDevices").trim());
            int maxLoginUsers = Integer.parseInt(data.elementText("maxLoginUsers").trim());
            Short sessionPort = new Short(data.elementText("sessionPort").trim());
            Short port = new Short(data.elementText("port").trim());
            int resentNum = Integer.parseInt(data.elementText("resentNum").trim());
            int overtime = Integer.parseInt(data.elementText("overtime").trim());
            int state = Integer.parseInt(data.elementText("state").trim());

            sysCfgInfo.setMaxLoginDevices(maxLoginDevices);
            sysCfgInfo.setMaxLoginUsers(maxLoginUsers);
            sysCfgInfo.setMysqlUserName(data.elementText("mysqlUserName").trim());
            sysCfgInfo.setMysqlPassword(data.elementText("mysqlPassword").trim());
            sysCfgInfo.setSessionPort(sessionPort);
            sysCfgInfo.setIp(data.elementText("ip").trim());
            sysCfgInfo.setPort(port);
            sysCfgInfo.setSessionPassword(data.elementText("sessionPassword").trim());
            sysCfgInfo.setResentNum(resentNum);
            sysCfgInfo.setOvertime(overtime);
            sysCfgInfo.setState(state);
        } catch (Exception ex) {  
            System.out.println("   Error : " + ex.toString());  
        }  
		return sysCfgInfo;
	}
}
