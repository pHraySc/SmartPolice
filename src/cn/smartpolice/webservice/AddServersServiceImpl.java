package cn.smartpolice.webservice;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.smartpolice.hibernate.AddServers;
import cn.smartpolice.dao.AddServersDao;
import cn.smartpolice.dao.AddServersDaoImpl;

@Service("addServersService")
@Transactional(readOnly=false)
public class AddServersServiceImpl implements AddServersService {
	
	@Resource(name = "addServersDao")
	private AddServersDao addServersDao;

	@Override
	public void AddServers(AddServers addServers) {
		if(addServers != null){
			addServersDao.AddServers(addServers);
		}
	}
	public static void main(String[] args) {
		AddServersDaoImpl addServersDaoImpl = new AddServersDaoImpl();
		addServersDaoImpl.AddServers(null);
	}
}
