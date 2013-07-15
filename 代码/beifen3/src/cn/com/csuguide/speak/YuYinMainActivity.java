package cn.com.csuguide.speak;

import java.util.ArrayList;
import java.util.List;

import cn.com.csuguide.R;
import cn.com.csuguide.searching.PointSearchingActivity;
import cn.com.csuguide.util.MyApplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * android语言识别功能
 * 特别注意：如果手机有语言设别功能，请开启网络，因为系统会根据你的声音数据到google云端获取声音数据
 *
 */
/**
 * 语音功能类
 * @author GDK
 *
 */
public class YuYinMainActivity extends Activity implements OnClickListener{
	private Button btn ;
	private ListView listView;
	private static final int REQUEST_CODE = 1;
	List<String> lists =new ArrayList <String> ();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yuyinmain);
        MyApplication.getInstance().addActivity(this);
        btn = (Button) this.findViewById(R.id.btn);
        listView = (ListView) this.findViewById(R.id.listview);
        
        /**
         * 下面是判断当前手机是否支持语音识别功能
         */
        PackageManager pm = getPackageManager();
        List<ResolveInfo> list = pm.queryIntentActivities(new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);
        if(list.size()!=0)
        {
        	btn.setOnClickListener(this);
        }else{
        	btn.setEnabled(false);
        	btn.setText("当前语音识别设备不可用...");
        }
        listView.setOnItemClickListener(new ViewClickListner());
        

    }

	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.btn)
		{
			/**
			 * 启动手机内置的语言识别功能
			 * 
			 */
			Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
			intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);  //设置为当前手机的语言类型
			intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "请慢点，用标准的普通话，否则可能不准确");//出现语言识别界面上面需要显示的提示
			startActivityForResult(intent,REQUEST_CODE);
//			int a =listView.getCheckedItemPosition() ;
//			String b= lists.get(a);
//			System.out.println(b);
//			
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		/**
		 * 回调获取从谷歌得到的数据
		 */
		if(requestCode==REQUEST_CODE&&resultCode==RESULT_OK)
		{
			
		 lists = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
		 
			for (int i = 0; i < lists.size(); i++) {
				String a  =	lists.get(i);
				System.out.println(a);
//				if(a.indexOf("图书馆")>=0){
////					lists.get(listView.getCheckedItemPosition());
////					System.out.println(lists.get(listView.getCheckedItemPosition()));
//					
//					Toast.makeText(YuYinMainActivity.this,"找到了图书馆", Toast.LENGTH_SHORT).show();
//			
//				}
			}
			
			listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1 ,lists)); //把数据显示在listview中
			

		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
public boolean onKeyDown(int keyCode, KeyEvent event) {
		
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

			finish(); //结束这个activity
		}
		return false;
	}
	
	 class ViewClickListner implements OnItemClickListener {
				
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		     TextView thisTextView =(TextView)arg1 ;
		       String toastMessage =String.format(" %1$s with index =%2$d  id =%3$d", thisTextView.getText(), arg2,arg3);
		       
		         Toast.makeText(YuYinMainActivity.this, toastMessage, Toast.LENGTH_SHORT).show();
		         System.out.println(thisTextView.getText().toString());
		        
		         Intent intent =new Intent();
		         Bundle bundle =new Bundle();
		         //将获得的字符创放入intent   ,并回到搜索界面
		         intent.setClass(YuYinMainActivity.this, PointSearchingActivity.class);
		         bundle.putString("message",  thisTextView.getText().toString());
		         intent.putExtras(bundle);
		         PointSearchingActivity.IsReturn=true;
		         startActivityForResult(intent, 0);
					overridePendingTransition(R.anim.push_left_in,
							R.anim.push_left_out);
		         //结束当前的界面
		         finish();
		 	}

		 	

		 }
	
}



