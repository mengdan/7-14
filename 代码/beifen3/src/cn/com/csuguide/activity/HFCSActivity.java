package cn.com.csuguide.activity;
import cn.com.csuguide.R;
import cn.com.csuguide.util.Config;
import cn.com.csuguide.util.MyApplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
/*
 * àË·­ÖÐÄÏÄ£¿é
 * */
public class HFCSActivity extends Activity implements OnClickListener{
	private Button buttonSchoolInfo,buttonCampus,buttonInstituton;
	public void onCreate(Bundle bundle){
		super.onCreate(bundle);
		setContentView(R.layout.activity_hfcs);
		MyApplication.getInstance().addActivity(this);
		initView();
		addListener();
	}

	private void initView() {
		buttonSchoolInfo=(Button) findViewById(R.id.buttonSchoolInfo);
		buttonCampus=(Button) findViewById(R.id.buttonCompus);
		buttonInstituton=(Button) findViewById(R.id.buttonInstitution);
		}

	private void addListener() {
		buttonSchoolInfo.setOnClickListener(this);
		buttonCampus.setOnClickListener(this);
		buttonInstituton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int id=v.getId();
		switch(id){
		case R.id.buttonSchoolInfo:
			Intent intent1=new Intent(this,InfoShowActivity.class);
			intent1.putExtra("msg", R.string.school_info);
			startActivity(intent1);
			break;
		
		case R.id.buttonCompus:
			Intent intent=new Intent(this,StudentNavigationActivity.class);
			intent.putExtra("id", Config.CAM_INFO_ACTIVITY);
			startActivity(intent);
			break;
		case R.id.buttonInstitution:
			Intent intent5=new Intent(this,InstitutionActivity.class);
			startActivity(intent5);
			break;
		}
	}

}
