package cn.com.csuguide.activity;

import cn.com.csuguide.R;
import cn.com.csuguide.model.User;
import cn.com.csuguide.util.Config;
import cn.com.csuguide.util.SharePreferenceUtil;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
/*
 * 个人信息显示
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
			info="账号:"+util.getId();
		}else{
		 info="账号:"+user.getUserId()+"\n"+"姓名:"+user.getUserName()+"\n"+
				"籍贯:"+user.getAddress()+"\n"+"邮箱:"+user.getEmail();
		 }
		textView.setText(info);
	}
	

}
