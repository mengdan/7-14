package cn.com.csuguide.activity;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import cn.com.csuguide.R;
import cn.com.csuguide.util.GridImageAdapter;
/*���Ž������*/
public class InstitutionActivity extends Activity {
	
	private GridView gridView;
	 private Integer[] images = {  
			   //�Ź���ͼƬ������  
			   R.drawable.ic_launcher,  
			   R.drawable.ic_launcher,  
			   R.drawable.ic_launcher,  
			   };  
	 private String[] texts = {  
			   //�Ź���ͼƬ�·����ֵ�����  
			   "ѧ����",  
			   "ѧ����",  
			   "�����",  
			  
			   };  
	 private int ids[]=new int[]{R.string.xue_sheng_hui,R.string.xue_she_lian,R.string.guo_qi_ban};
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_instition);
		gridView=(GridView) findViewById(R.id.gridView);
		GridImageAdapter adapter=new GridImageAdapter(this,images,texts);
		gridView.setAdapter(adapter);
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent=new Intent(InstitutionActivity.this,InfoShowActivity.class);
				intent.putExtra("msg", ids[position]);
				startActivity(intent);
				
			}
		});
		}	
}


