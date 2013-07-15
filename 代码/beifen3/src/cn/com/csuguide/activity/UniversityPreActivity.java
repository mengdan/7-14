package cn.com.csuguide.activity;


import cn.com.csuguide.R;
import cn.com.csuguide.util.MyApplication;
import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
/*大学预备模块功能的入口*/
public class UniversityPreActivity extends Activity implements OnClickListener{
	Button buttonGuide,buttonGrowth,buttonRights;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_university_pre);
		MyApplication.getInstance().addActivity(this);
		initView();
		addListener();
	}

	private void addListener() {
		// TODO Auto-generated method stub
		buttonGuide.setOnClickListener(this);
		buttonGrowth.setOnClickListener(this);
		buttonRights.setOnClickListener(this);
	}

	private void initView() {
		// TODO Auto-generated method stub
		buttonGuide=(Button) findViewById(R.id.buttonGuide);
		buttonGrowth=(Button) findViewById(R.id.buttonGrowth);
		buttonRights=(Button) findViewById(R.id.buttonRights);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id=v.getId();
		switch(id){
			case R.id.buttonGuide:
				Intent intent1=new Intent(this,InfoShowActivity.class);
				intent1.putExtra("msg", R.string.guide);
				startActivity(intent1);
				break;
			case R.id.buttonGrowth:
				Intent intent2=new Intent(this,InfoShowActivity.class);
				intent2.putExtra("msg", R.string.growth);
				startActivity(intent2);
				break;
			case R.id.buttonRights:
				Intent intent3=new Intent(this,InfoShowActivity.class);
				intent3.putExtra("msg", R.string.rights);
				startActivity(intent3);
			break;
		}
		
	}

}
