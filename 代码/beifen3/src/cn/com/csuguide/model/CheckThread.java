package cn.com.csuguide.model;

import cn.com.csuguide.activity.MainFuncActivity;
import android.app.Activity;
import android.content.Intent;

/**
 * ����Ƿ������Ϸ��������߳�
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
		
		System.out.println("ִ�е���CheckThread�߳�����");
		System.out.println("state��ֵ"+LoginThread.state);
		
		if(LoginThread.state==true){//����ɹ���¼����ֹͣ�߳�,�����ص�������
    	
//    	Toast.makeText(this, "��½�ɹ�������ʹ�����Ѳ�ѯ���༶ͬѧ��ѯ����", Toast.LENGTH_LONG).show();
    	System.out.println("�̱߳���ֹ");
    	//��ת��������
    	Intent intent=new Intent(LActivity,MainFuncActivity.class);
    	LActivity.startActivity(intent);
    }
	}
}
