package cn.com.csuguide.activity;


import java.util.LinkedList;
import java.util.List;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;
import cn.com.csuguide.util.Config;
import cn.com.csuguide.util.SharePreferenceUtil;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.MKGeneralListener;
import com.baidu.mapapi.map.MKEvent;


public class DemoApplication extends Application {
	
    private static DemoApplication mInstance = null;
    public boolean m_bKeyRight = true;
    BMapManager mBMapManager = null;
    public static boolean isLogin=false;

	public static boolean isLogin() {
		return isLogin;
	}
	public static void setLogin(boolean isLogin) {
		DemoApplication.isLogin = isLogin;
	}





	public static final String strKey = "D26959961A2CF0B04644010B2CF73984288AE85C";

    
    /**************************************************************/
    private List<Activity> activityList = new LinkedList<Activity>(); 
 	private NotificationManager mNotificationManager;
 	private int recentNum = 0;
 	 /**************************************************************/
 	
 	
 	 public DemoApplication()
     {
     }
	@Override
    public void onCreate() {
	    super.onCreate();
	    SharePreferenceUtil util = new SharePreferenceUtil(this,
				Config.SAVE_USER);
		mInstance = this;
		initEngineManager(this);
//		try {  
//		    StrictModeWrapper.init(this);  
//		}  
//		catch(Throwable throwable) {  
//		    Log.v("StrictMode", "... is not available. Punting...");  
//		}  
	}
	
	@Override
	//建议在您app的退出之前调用mapadpi的destroy()函数，避免重复初始化带来的时间消耗
	public void onTerminate() {
		// TODO Auto-generated method stub
	    if (mBMapManager != null) {
            mBMapManager.destroy();
            mBMapManager = null;
        }
		super.onTerminate();
	}
	
	public void initEngineManager(Context context) {
        if (mBMapManager == null) {
            mBMapManager = new BMapManager(context);
        }

        if (!mBMapManager.init(strKey,new MyGeneralListener())) {
            Toast.makeText(DemoApplication.getInstance().getApplicationContext(), 
                    "BMapManager  初始化错误!", Toast.LENGTH_LONG).show();
        }
	}
	
	public static DemoApplication getInstance() {
		
		if(null == mInstance)
        {
			mInstance = new DemoApplication();
        }
		return mInstance;
	}
	  //添加Activity到容器中
    public void addActivity(Activity activity)
    {
                   activityList.add(activity);
    }
    //遍历所有Activity并finish
    public void exit()
    {
                 for(Activity activity:activityList)
                {
                  activity.finish();
                }
                  System.exit(0);
   }
    

 	

	
	// 常用事件监听，用来处理通常的网络错误，授权验证错误等
    static class MyGeneralListener implements MKGeneralListener {
        
        @Override
        public void onGetNetworkState(int iError) {
            if (iError == MKEvent.ERROR_NETWORK_CONNECT) {
                Toast.makeText(DemoApplication.getInstance().getApplicationContext(), "您的网络出错啦！",
                    Toast.LENGTH_LONG).show();
            }
            else if (iError == MKEvent.ERROR_NETWORK_DATA) {
                Toast.makeText(DemoApplication.getInstance().getApplicationContext(), "输入正确的检索条件！",
                        Toast.LENGTH_LONG).show();
            }
            // ...
        }

        @Override
        public void onGetPermissionState(int iError) {
            if (iError ==  MKEvent.ERROR_PERMISSION_DENIED) {
                //授权Key错误：
                Toast.makeText(DemoApplication.getInstance().getApplicationContext(), 
                        "请在 DemoApplication.java文件输入正确的授权Key！", Toast.LENGTH_LONG).show();
                DemoApplication.getInstance().m_bKeyRight = false;
            }
        }
        
        
       
    }
    static class StrictModeWrapper {  
		public static void init(Context context) {  
            // check if android:debuggable is set to true  
            int appFlags = context.getApplicationInfo().flags;  
            if ((appFlags & ApplicationInfo.FLAG_DEBUGGABLE) != 0) {  
                StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()  
                    .detectDiskReads()  
                    .detectDiskWrites()  
                    .detectNetwork()  
                    .penaltyLog()  
                    .build());  
                StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()  
                    .detectLeakedSqlLiteObjects()  
                    .penaltyLog()  
                    .penaltyDeath()  
                    .build());  
            }  
        }  
    }  
}