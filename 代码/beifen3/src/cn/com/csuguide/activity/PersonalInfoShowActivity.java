package cn.com.csuguide.activity;

import cn.com.csuguide.R;
import cn.com.csuguide.model.User;
import cn.com.csuguide.util.Config;
import cn.com.csuguide.util.SharePreferenceUtil;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
/*
 * ������Ϣ��ʾ
 * 
 * */
public class PersonalInfoShowActivity extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personalinfo_show);
		User user=(User) getIntent().getSerializableExtra("user");
		System.out.println("user---->"+user);
		TextView textView=(TextView) findViewById(R.id.tvPersonInfo);
		String info;
		if(user==null){
			SharePreferenceUtil util = new SharePreferenceUtil(
					this, Config.SAVE_USER);
			info="�˺�:"+util.getId();
		}else{
		 info="�˺�:"+user.getUserId()+"\n"+"����:"+user.getUserName()+"\n"+
				"����:"+user.getAddress()+"\n"+"����:"+user.getEmail();
		 }
		textView.setText(info);
	}
	

}
