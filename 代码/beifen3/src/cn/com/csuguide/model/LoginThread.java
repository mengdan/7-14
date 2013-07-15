package cn.com.csuguide.model;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.content.Context;
import cn.com.csuguide.activity.DemoApplication;
import cn.com.csuguide.util.SharePreferenceUtil;
import cn.com.csuguide.util.Config;

/**
 * ��¼ʱ�������߳���
 * @author ronaldo
 *
 */
public class LoginThread extends Thread{

	private String accounts;
	private String password;
	public static boolean state=false;//��¼���ӷ������̵߳�״̬��true��ʾ��¼�ɹ�
	 Context context = null;
	/**
	 * ������
	 * @param accounts �û���
	 * @param password ����
	 */
	public LoginThread(String accounts,String password){
		this.accounts=accounts;
		this.password=password;
	}
	public LoginThread(Context context,String accounts,String password){
		this.accounts=accounts;
		this.password=password;
		this.context=context;
	}
	public void run(){
      System.out.println("ִ�����߳�");
      
		try{
    	
    	//testLoginService���������е�ַ������ִ��public����Login
        String serviceUrl = "http://198.74.97.66:8080/axis2/services/LoginService?wsdl";  
        String methodName = "Login";  
        SoapObject request = new SoapObject("http://Service",  
                methodName);  
        //�������󣬴������
        request.addProperty("uid", accounts);
        request.addProperty("password",password); 
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(  
                SoapEnvelope.VER11);  
        envelope.bodyOut = request;  
        HttpTransportSE ht = new HttpTransportSE(serviceUrl);  
        ht.call(null, envelope);  
        System.out.println("���ӵ��˷�����...");
        //��ȡ���ؽ��
        if (envelope.getResponse() != null)  
        {  
        	System.out.println("��¼�ɹ���");
            System.out.println("response--------->"+envelope.getResponse());
        	SoapObject soapObject = (SoapObject) envelope.getResponse();  
        	System.out.println("soapObject"+soapObject);
            String ID=  soapObject.getProperty("uid").toString();//�û�ID 
            System.out.println("id------>"+ID);
//            String Name=(String) soapObject.getProperty("username");//�û���
//            
//            String pd=(String) soapObject.getProperty("password");//����
            
            //���������еĸ��û����ݴ洢������
            
           
			SharePreferenceUtil spu=new SharePreferenceUtil(context,Config.SAVE_USER);
			
			spu.setId(ID);
			System.out.println("�洢�˺ųɹ�������");
//			spu.setName(Name);
//			spu.setPasswd(pd);
			
            //��״̬����Ϊ��¼�ɹ�
			state=true;
			DemoApplication.isLogin=true;
			System.out.println("state��LoginThread��ֵ"+LoginThread.state);
            
//            Result.setText("��½�ɹ�");
            if(!ID.equals(null)){
//            	 Result.setText("��ȷ��WebServiceZ��ȷ������ô��");
            }
            else{
//            	Result.setText("�����˾Ͳ�Ҫװ�����ˡ�������");
            }
        }  
        else  
        {  
//            Result.setText("��ȷ��WebServiceZ��ȷ������ô��");  
        }
       
	}
		catch (Exception e)  
        {  
//            Result.setText("����WebService����.");  
			e.printStackTrace();
        }  
	
	
	
	}
}
