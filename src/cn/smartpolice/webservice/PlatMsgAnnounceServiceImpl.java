package cn.smartpolice.webservice;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.smartpolice.dao.PlatMsgAnnounceDao;
import cn.smartpolice.dao.PlatMsgAnnounceDaoImpl;
import cn.smartpolice.hibernate.PlatMsgAnnounce;

@Service("platMsgAnnounceService")
@Transactional(readOnly=false)
public class PlatMsgAnnounceServiceImpl implements PlatMsgAnnounceService {

	@Resource(name = "platMsgAnnounceDao")
	private PlatMsgAnnounceDao announceDao;

	@Override
	public void AddPlatMsgToDB(PlatMsgAnnounce announce) {
		if(announce != null){
			announceDao.AddPlatMsgToDB(announce);
		}
	}
	
	public static void main(String[] args) {
		PlatMsgAnnounceDaoImpl announceDaoImpl = new PlatMsgAnnounceDaoImpl();
		announceDaoImpl.AddPlatMsgToDB(null);
	}
}
