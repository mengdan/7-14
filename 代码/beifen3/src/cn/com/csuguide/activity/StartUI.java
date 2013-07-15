package cn.com.csuguide.activity;




import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import cn.com.csuguide.R;
import cn.com.csuguide.util.BuildingsInfoSet;
import cn.com.csuguide.util.DataBase;

/**
 * 起始欢迎界面
 * @author GDK
 *
 */
public class StartUI extends Activity {
	// 屏幕宽度
		public static int screenWidth;
		// 屏幕高度
		public static int screenHeight;
	ImageView imageView0;
	Animation animation0;
	DemoApplication myapp ;
	public static DataBase	db;
	@SuppressLint("NewApi")
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

     
        DemoApplication.getInstance().addActivity(this);
    	requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        setContentView(R.layout.start);
        screenWidth = getWindow().getWindowManager().getDefaultDisplay()
				.getWidth();
		screenHeight = getWindow().getWindowManager().getDefaultDisplay()
				.getHeight();
		
        DemoApplication myapp =(DemoApplication)this.getApplicationContext();
        
       

        imageView0=(ImageView) this.findViewById(R.id.imageView0);
        animation0 = AnimationUtils.loadAnimation(StartUI.this, R.anim.foralpha);
		AnimationSet as = new AnimationSet(true); 
		as.addAnimation(animation0);
		as.setFillBefore(false);
		as.setFillAfter(true);
		
		imageView0.startAnimation(as);
		
		 db=new DataBase(StartUI.this);
			db.init();
			BuildingsInfoSet buildingSInfoSet = new BuildingsInfoSet(this);
			buildingSInfoSet.setBuildingsInfo();
		as.setAnimationListener(new AnimationListener() {
			
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@SuppressLint("NewApi")
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
//				intent.setClass(StartUI.this, LoginUI.class);
				intent.setClass(StartUI.this, MainFuncActivity.class);
				startActivityForResult(intent, 0);
				overridePendingTransition(R.anim.fade, R.anim.hold);
				finish();
				
			}
		});
		
	}
}
