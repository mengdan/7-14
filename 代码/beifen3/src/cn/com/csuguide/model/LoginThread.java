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
 * 登录时启动的线程类
 * @author ronaldo
 *
 */
public class LoginThread extends Thread{

	private String accounts;
	private String password;
	public static boolean state=false;//登录连接服务器线程的状态，true表示登录成功
	 Context context = null;
	/**
	 * 构造器
	 * @param accounts 用户名
	 * @param password 密码
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
      System.out.println("执行了线程");
      
		try{
    	
    	//testLoginService发布在下列地址，将会执行public方法Login
        String serviceUrl = "http://198.74.97.66:8080/axis2/services/LoginService?wsdl";  
        String methodName = "Login";  
        SoapObject request = new SoapObject("http://Service",  
                methodName);  
        //建立请求，传入参数
        request.addProperty("uid", accounts);
        request.addProperty("password",password); 
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(  
                SoapEnvelope.VER11);  
        envelope.bodyOut = request;  
        HttpTransportSE ht = new HttpTransportSE(serviceUrl);  
        ht.call(null, envelope);  
        System.out.println("连接到了服务器...");
        //获取返回结果
        if (envelope.getResponse() != null)  
        {  
        	System.out.println("登录成功了");
            System.out.println("response--------->"+envelope.getResponse());
        	SoapObject soapObject = (SoapObject) envelope.getResponse();  
        	System.out.println("soapObject"+soapObject);
            String ID=  soapObject.getProperty("uid").toString();//用户ID 
            System.out.println("id------>"+ID);
//            String Name=(String) soapObject.getProperty("username");//用户名
//            
//            String pd=(String) soapObject.getProperty("password");//密码
            
            //将服务器中的该用户数据存储到本地
            
           
			SharePreferenceUtil spu=new SharePreferenceUtil(context,Config.SAVE_USER);
			
			spu.setId(ID);
			System.out.println("存储账号成功。。。");
//			spu.setName(Name);
//			spu.setPasswd(pd);
			
            //将状态设置为登录成功
			state=true;
			DemoApplication.isLogin=true;
			System.out.println("state在LoginThread的值"+LoginThread.state);
            
//            Result.setText("登陆成功");
            if(!ID.equals(null)){
//            	 Result.setText("你确定WebServiceZ正确运行了么？");
            }
            else{
//            	Result.setText("大三了就不要装新生了。。。。");
            }
        }  
        else  
        {  
//            Result.setText("你确定WebServiceZ正确运行了么？");  
        }
       
	}
		catch (Exception e)  
        {  
//            Result.setText("调用WebService错误.");  
			e.printStackTrace();
        }  
	
	
	
	}
}
