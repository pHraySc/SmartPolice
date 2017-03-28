package cn.smartpolice.dao;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("platRunInfoDao")
@Transactional(readOnly=false)
public class PlatRunInfoDaoImpl implements PlatRunInfoDao{
	
	@Resource(name="hibernateTemplate")
	private HibernateTemplate hibernateTemplate;

}
