package cn.smartpolice.protocol;



/**
 * �궨���ļ�
 * @author ����
 *
 */
public class ConstParam {
	//����ȫ�ֳ���
	public static final int MASSAGE_LEN = 20; //������̳���
	
	public static final int MAX_CMD = 12; //���cmd
	public static final int CMD_0 = 0; //��ͨ���Ա���
	public static final int CMD_1 = 1; //����Э�����ͣ����ӹ���Э�飩
	public static final int CMD_2 = 2; 
	public static final int CMD_3 = 3;
	public static final int CMD_4 = 4;
	public static final int CMD_5 = 5;//ƽ̨�ʺŹ���Э��
	public static final int CMD_7 = 7;//�ļ�����Э��
	public static final int CMD_10 = 10;//��ϵ�˹���Э��
	
	public static final int TYPE_0 = 0; //��������
	public static final int TYPE_1 = 1;
	public static final int TYPE_2 = 2;
	public static final int TYPE_3 = 3;
	public static final int TYPE_4 = 4;
	public static final int TYPE_5 = 5;
	public static final int TYPE_6 = 6;
	public static final int TYPE_7 = 7;
	public static final int TYPE_8 = 8;
	public static final int TYPE_9 = 9;
	public static final int TYPE_10 = 10;
	public static final int TYPE_11 = 11;
	
	public static final int SORT_0 = 0; //app user
	public static final int SORT_2 = 2; //dev user
	public static final int SORT_3 = 3; //������
	
	public static final int OPT_1 = 1;
	public static final int OPT_8 = 8;
	public static final int OPT_16 = 16;
	
	public static final int LOGIN_STATE_0 = 0; //��¼״̬  δ��ֻ֤���ڶ����д��ڴ˽ڵ�
	public static final int LOGIN_STATE_1 = 1; //�ѷ���Ҫ����֤����
	public static final int LOGIN_STATE_2 = 2; //��¼�ɹ�
	
	public static final int CHECK_STATE_0 = 0; //ϵͳ�����Ƿ���Ҫ������֤     ����Ҫ
	public static final int CHECK_STATE_1 = 1; //��Ҫ������֤
	
	public static final int SENT_PKT_TYPE_1 = 1; //���첻ͬ�ķ��ر���   ���ش�����
	public static final int SENT_PKT_TYPE_2 = 2; //�����öԷ�������֤������de����
	public static final int SENT_PKT_TYPE_3 = 3; //�����¼�ɹ��ı���
	public static final int SENT_PKT_TYPE_4 = 4; //���챣��Ӧ����
	
	public static final int ERROR_PKT_STATE_0 = 0; // ������Ϣ��ƥ��
	public static final int ERROR_PKT_STATE_1 = 1; // ������֤ʧ��
	public static final int ERROR_PKT_STATE_2 = 2;
	
	public static final int LOGIN_OPERATE_0 = 0;  //log���б�ǵǳ�
	public static final int LOGIN_OPERATE_1 = 1;  //log���б�ǵ�¼
	public static final int LOGIN_OPERATE_2 = -1;  //log���б��ע��
	
	public static final int SERVER_ID = 1; //�����ݿ��л�ȡ��   �ȶ���Ϊ����
	
	public static final int CONTACT_0 = 0;//�ȴ�ȷ��    ����
	public static final int CONTACT_1 = 1;//�ʺ��Ѿ�������    ������
	public static final int CONTACT_2 = -1;//�����ϵ��ʧ��  ����ʧ��
	
	public static final int REQUEST_INFORM_0 = 0;//�������
	public static final int REQUEST_INFORM_1 = 1;//ȷ�����
	public static final int REQUEST_INFORM_2 = 2;//�������
	public static final int REQUEST_INFORM_3 = 3;//���֪ͨ
	public static final int REQUEST_INFORM_4 = 4;//ɾ��֪ͨ
}
