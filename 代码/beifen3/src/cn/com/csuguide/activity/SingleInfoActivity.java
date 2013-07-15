package cn.com.csuguide.activity;
import cn.com.csuguide.R;
import cn.com.csuguide.util.MyApplication;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
/*
 * ����������Ϣ���������ѣ��༶ͬѧ��Ϣ��ѯ�����
 * �漰����������Ľ�����������Щ���ӣ��ȷ���
 * */
import android.widget.Button;
import android.widget.Toast;
/*�༶������Ϣ��ѯ������*/
public class SingleInfoActivity extends Activity implements OnClickListener{
	private Button buttonClassInfo,buttonDormitoryInfo;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_info);
		MyApplication.getInstance().addActivity(this);
		initView();
		addListener();
	}
/*��ʼ�����*/
	private void initView() {
		buttonClassInfo=(Button) findViewById(R.id.buttonClassInfo);
		buttonDormitoryInfo=(Button) findViewById(R.id.buttonDormitoryInfo);
	}
	/*��Ӽ�����*/
	private void addListener(){
		buttonClassInfo.setOnClickListener(this);
		buttonDormitoryInfo.setOnClickListener(this);
	}
	/*�¼�����*/
	public void onClick(View v) {
		int id=v.getId();
		Intent intent=new Intent(SingleInfoActivity.this,SearchInfoListActivity.class);
		switch(id){
		case R.id.buttonDormitoryInfo:
			if(DemoApplication.isLogin){
				System.out.println("singleinfo---->execute");
				startActivity(intent);
			}else{
				Toast.makeText(this, "��Ҫ��¼����ʹ�øù���", Toast.LENGTH_LONG).show();
			}
			break;
		case R.id.buttonClassInfo:
			if(DemoApplication.isLogin){
				System.out.println("singleinfo---->execute");
				startActivity(intent);
			}else{
				Toast.makeText(this, "��Ҫ��¼����ʹ�øù���", Toast.LENGTH_LONG).show();
			}
			break;
		}
	}
	


}
