package cn.com.csuguide.util;

import android.R.integer;

public interface Config {
	
	public static final int REGISTER_FAIL = 0;//ע��ʧ��
	public static final String ACTION = "com.way.message";//��Ϣ�㲥action
	public static final String MSGKEY = "message";//��Ϣ��key
	public static final String IP_PORT = "ipPort";//����ip��port��xml�ļ���
	public static final String SAVE_USER = "saveUser";//�����û���Ϣ��xml�ļ���
	public static final String BACKKEY_ACTION="com.way.backKey";//���ؼ����͹㲥��action
	public static final int NOTIFY_ID = 0x911;//֪ͨID
	public static final String DBNAME = "qq.db";//���ݿ�����
	
	public static String loginServiceUrl="http://198.74.97.66:8080/axis2/services/LoginService?wsdl";
	public static int STU_NAV_ACTIVITY=0;//��������
	public static int CAM_INFO_ACTIVITY=1;//У԰��Ϣ
	public static int TYPE_D_Building=4;//D��
	public static int TYPE_C_Building=5;//C��
	public static int TYPE_B_Building=6;//B��
	public static int TYPE_A_Building=7;//A��
}
