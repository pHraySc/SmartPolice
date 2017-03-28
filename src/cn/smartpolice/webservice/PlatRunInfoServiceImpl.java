package cn.smartpolice.webservice;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.smartpolice.dao.PlatMsgAnounceDao;

@Service("platRunInfoService")
@Transactional(readOnly=false)
public class PlatRunInfoServiceImpl implements PlatRunInfoService {

	@Resource(name = "platRunInfoDao")
	private PlatMsgAnounceDao anounceDao;
}
