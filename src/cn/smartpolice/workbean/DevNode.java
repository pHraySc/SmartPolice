package cn.smartpolice.workbean;

/**
 * �豸��¼��Ϣ����ϣ��
 * @author ����
 *
 */
import java.util.Date;

import org.apache.mina.core.session.IoSession;
public class DevNode extends UserNode {

	private int state;
	
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
}
