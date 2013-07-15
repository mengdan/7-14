package cn.com.csuguide.util;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.app.Application;

public class MyApplication extends Application {
	private List<Activity> activityList = new LinkedList<Activity>(); 
	private static MyApplication instance;

	            private MyApplication()
	            {
	            }
	             //����ģʽ�л�ȡΨһ��MyApplicationʵ�� 
	             public static MyApplication getInstance()
	             {
	                            if(null == instance)
	                          {
	                             instance = new MyApplication();
	                          }
	                 return instance;             
	             }
	             //���Activity��������
	             public void addActivity(Activity activity)
	             {
	                            activityList.add(activity);
	             }
	             //��������Activity��finish
	             public void exit()
	             {
	                          for(Activity activity:activityList)
	                         {
	                           activity.finish();
	                         }
	                           System.exit(0);
	            }

}
