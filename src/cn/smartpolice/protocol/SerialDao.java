package cn.smartpolice.protocol;

import java.util.List;
import java.util.Locale;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import sun.security.util.BigInt;
import cn.smartpolice.hibernate.SoftInfo;
import cn.smartpolice.netdao.HibernateUtil;
import cn.smartpolice.netdao.UserInf;
 /**
  * Soft_lnf表的操作
  * @author 吴明欢
 * @param <S>
  *
  */
public class SerialDao {
	   
	     private Session session = HibernateUtil.currentSession();
	     private Transaction ts = session.beginTransaction();
	     //通过SERIAL查找
	 	public Softlnf  findMaxVerBySERIAL(String ver){
	 		Query query = session.createQuery("from SoftInf where version=?");
	 		query.setString(0, ver);
	 		List<Softlnf> list = query.list();
	 		ts.commit();
	 		if(list != null && list.size()==1){
	 			return list.get(0);
	 		}
	 		return null;
	 	}
	 	//通过ver查找
	 	public Softlnf  findMd5ByVer(char  md5){
	 		Query query = session.createQuery("from SoftInf where md5=?");
	 		query.setLong(0, md5);
	 		List<Softlnf> list = query.list();
	 		ts.commit();
	 		if(list != null && list.size()==1){
	 			return list.get(0);
	 		}
	 		return null;
	 	}
	 	public Softlnf  findSizeByVer(   byte  size){
	 		Query query = session.createQuery("from SoftInf wheresize=?");
	 		query.setLong(0,size);
	 		List<Softlnf> list = query.list();
	 		ts.commit();
	 		if(list != null && list.size()==1){
	 			return list.get(0);
	 		}
	 		return null;
	 	}
	
		public Softlnf  findUrlByVer(   char  url){
	 		Query query = session.createQuery("from SoftInf wheresize=?");
	 		query.setLong(0,url);
	 		List<Softlnf> list = query.list();
	 		ts.commit();
	 		if(list != null && list.size()==1){
	 			return list.get(0);
	 		}
	 		return null;
	 	}
	 	//插入记录 该方法有问题：SQLGrammarException
	 	public void insertMaxVer(Softlnf softInf){
	 		this.session.save(softInf);
	 		ts.commit();
	 	}
	 	////置state=0（删除标记 注销协议）
	 	public void changeStateToZero(int id){
	 		Query query = session.createQuery("update SoftInf soft set soft.state = 0 where soft.deviceid =?");
	 		query.setInteger(0, id);
	 		ts.commit();
	 	}
}
