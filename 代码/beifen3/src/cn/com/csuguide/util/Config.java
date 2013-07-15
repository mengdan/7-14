package cn.com.csuguide.util;

import android.R.integer;

public interface Config {
	
	public static final int REGISTER_FAIL = 0;//注册失败
	public static final String ACTION = "com.way.message";//消息广播action
	public static final String MSGKEY = "message";//消息的key
	public static final String IP_PORT = "ipPort";//保存ip、port的xml文件名
	public static final String SAVE_USER = "saveUser";//保存用户信息的xml文件名
	public static final String BACKKEY_ACTION="com.way.backKey";//返回键发送广播的action
	public static final int NOTIFY_ID = 0x911;//通知ID
	public static final String DBNAME = "qq.db";//数据库名称
	
	public static String loginServiceUrl="http://198.74.97.66:8080/axis2/services/LoginService?wsdl";
	public static int STU_NAV_ACTIVITY=0;//新生导航
	public static int CAM_INFO_ACTIVITY=1;//校园信息
	public static int TYPE_D_Building=4;//D座
	public static int TYPE_C_Building=5;//C座
	public static int TYPE_B_Building=6;//B座
	public static int TYPE_A_Building=7;//A座
}
