package cn.com.csuguide.activity;

import cn.com.csuguide.R;
import cn.com.csuguide.util.Config;
import cn.com.csuguide.util.MyApplication;
import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/*新生导航界面以及校园信息块功用界面*/
public class StudentNavigationActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.slidepageviewdemo_ui);
		MyApplication.getInstance().addActivity(this);
		int id=getIntent().getIntExtra("id", 0);
		System.out.println("id"+id);
		switch(id){
			case Config.STU_NAV_ACTIVITY:
				int pics[]=new int[]{R.drawable.step1,R.drawable.step2,R.drawable.step3,R.drawable.step4};
				initView(pics);
				break;
			case Config.CAM_INFO_ACTIVITY:
				int pic[]=new int[]{R.drawable.campus1,R.drawable.campus2,R.drawable.campus3,R.drawable.campus4};
				initView(pic);
				break;
		}
	
	}
	private void initView(int[] pics){
		LinearLayout linearLayoutOne=(LinearLayout) findViewById(R.id.linearLayoutOne);
		linearLayoutOne.setBackgroundResource(pics[0]);
		LinearLayout linearLayoutTwo=(LinearLayout) findViewById(R.id.linearLayoutTwo);
		linearLayoutTwo.setBackgroundResource(pics[1]);
		LinearLayout linearLayoutThree=(LinearLayout) findViewById(R.id.linearLayoutThree);
		linearLayoutThree.setBackgroundResource(pics[2]);
		LinearLayout linearLayoutFour=(LinearLayout) findViewById(R.id.linearLayoutFour);
		linearLayoutFour.setBackgroundResource(pics[3]);
	}

}
