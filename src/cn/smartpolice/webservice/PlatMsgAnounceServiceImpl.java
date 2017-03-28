package cn.smartpolice.webservice;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.smartpolice.dao.PlatMsgAnounceDao;
import cn.smartpolice.hibernate.PlatMsgAnnounce;

@Service("platMsgAnounceService")
@Transactional(readOnly=false)
public class PlatMsgAnounceServiceImpl implements PlatMsgAnounceService {

	@Resource(name = "platMsgAnounceDao")
	private PlatMsgAnounceDao anounceDao;

	@Override
	public void AddPlatMsgToDB(PlatMsgAnnounce announce) {
		if(announce != null){
			anounceDao.AddPlatMsgToDB(announce);
		}
	}
}
