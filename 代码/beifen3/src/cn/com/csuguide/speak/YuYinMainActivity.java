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
 * android����ʶ����
 * �ر�ע�⣺����ֻ�����������ܣ��뿪�����磬��Ϊϵͳ���������������ݵ�google�ƶ˻�ȡ��������
 *
 */
/**
 * ����������
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
         * �������жϵ�ǰ�ֻ��Ƿ�֧������ʶ����
         */
        PackageManager pm = getPackageManager();
        List<ResolveInfo> list = pm.queryIntentActivities(new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);
        if(list.size()!=0)
        {
        	btn.setOnClickListener(this);
        }else{
        	btn.setEnabled(false);
        	btn.setText("��ǰ����ʶ���豸������...");
        }
        listView.setOnItemClickListener(new ViewClickListner());
        

    }

	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.btn)
		{
			/**
			 * �����ֻ����õ�����ʶ����
			 * 
			 */
			Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
			intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);  //����Ϊ��ǰ�ֻ�����������
			intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "�����㣬�ñ�׼����ͨ����������ܲ�׼ȷ");//��������ʶ�����������Ҫ��ʾ����ʾ
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
		 * �ص���ȡ�ӹȸ�õ�������
		 */
		if(requestCode==REQUEST_CODE&&resultCode==RESULT_OK)
		{
			
		 lists = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
		 
			for (int i = 0; i < lists.size(); i++) {
				String a  =	lists.get(i);
				System.out.println(a);
//				if(a.indexOf("ͼ���")>=0){
////					lists.get(listView.getCheckedItemPosition());
////					System.out.println(lists.get(listView.getCheckedItemPosition()));
//					
//					Toast.makeText(YuYinMainActivity.this,"�ҵ���ͼ���", Toast.LENGTH_SHORT).show();
//			
//				}
			}
			
			listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1 ,lists)); //��������ʾ��listview��
			

		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
public boolean onKeyDown(int keyCode, KeyEvent event) {
		
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

			finish(); //�������activity
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
		         //����õ��ַ�������intent   ,���ص���������
		         intent.setClass(YuYinMainActivity.this, PointSearchingActivity.class);
		         bundle.putString("message",  thisTextView.getText().toString());
		         intent.putExtras(bundle);
		         PointSearchingActivity.IsReturn=true;
		         startActivityForResult(intent, 0);
					overridePendingTransition(R.anim.push_left_in,
							R.anim.push_left_out);
		         //������ǰ�Ľ���
		         finish();
		 	}

		 	

		 }
	
}



