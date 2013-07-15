package cn.com.csuguide.model;

import cn.com.csuguide.activity.MainFuncActivity;
import android.app.Activity;
import android.content.Intent;

/**
 * 检查是否连接上服务器的线程
 * @author ronaldo
 *
 */
public class CheckThread extends Thread{

	
	private Activity LActivity;
	public CheckThread (Activity LActivity) {
		this.LActivity=LActivity;
	}
	public void run(){
		try{
			Thread.sleep(2000);
		}catch (Exception e) {
            e.printStackTrace();
		}
		
		System.out.println("执行到了CheckThread线程里面");
		System.out.println("state的值"+LoginThread.state);
		
		if(LoginThread.state==true){//如果成功登录，就停止线程,并返回到主界面
    	
//    	Toast.makeText(this, "登陆成功！可以使用室友查询，班级同学查询功能", Toast.LENGTH_LONG).show();
    	System.out.println("线程被终止");
    	//跳转到主界面
    	Intent intent=new Intent(LActivity,MainFuncActivity.class);
    	LActivity.startActivity(intent);
    }
	}
}
