package cn.com.csuguide.activity;
import cn.com.csuguide.R;
import cn.com.csuguide.util.MyApplication;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
/*
 * 新生个人信息，宿舍舍友，班级同学信息查询的入口
 * 涉及到与服务器的交互，可能有些复杂，先放着
 * */
import android.widget.Button;
import android.widget.Toast;
/*班级室友信息查询块的入口*/
public class SingleInfoActivity extends Activity implements OnClickListener{
	private Button buttonClassInfo,buttonDormitoryInfo;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_info);
		MyApplication.getInstance().addActivity(this);
		initView();
		addListener();
	}
/*初始化组件*/
	private void initView() {
		buttonClassInfo=(Button) findViewById(R.id.buttonClassInfo);
		buttonDormitoryInfo=(Button) findViewById(R.id.buttonDormitoryInfo);
	}
	/*添加监听器*/
	private void addListener(){
		buttonClassInfo.setOnClickListener(this);
		buttonDormitoryInfo.setOnClickListener(this);
	}
	/*事件处理*/
	public void onClick(View v) {
		int id=v.getId();
		Intent intent=new Intent(SingleInfoActivity.this,SearchInfoListActivity.class);
		switch(id){
		case R.id.buttonDormitoryInfo:
			if(DemoApplication.isLogin){
				System.out.println("singleinfo---->execute");
				startActivity(intent);
			}else{
				Toast.makeText(this, "需要登录才能使用该功能", Toast.LENGTH_LONG).show();
			}
			break;
		case R.id.buttonClassInfo:
			if(DemoApplication.isLogin){
				System.out.println("singleinfo---->execute");
				startActivity(intent);
			}else{
				Toast.makeText(this, "需要登录才能使用该功能", Toast.LENGTH_LONG).show();
			}
			break;
		}
	}
	


}
