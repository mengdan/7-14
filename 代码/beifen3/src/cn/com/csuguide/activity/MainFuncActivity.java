package cn.com.csuguide.activity;



import cn.com.csuguide.R;
import cn.com.csuguide.model.SlidingLayout;
import cn.com.csuguide.util.GridImageAdapter;
import cn.com.csuguide.util.MyApplication;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;
/*�����棬����������ģ������*/
public class MainFuncActivity extends Activity {
	
	//��Ļ���
		public static int screenWidth;
			// ��Ļ�߶�
		public static int screenHeight;
	/**
	 * �໬���ֶ�������ͨ����ָ���������Ĳ˵����ֽ�����ʾ�����ء�
	 */
	private SlidingLayout slidingLayout;

	/**
	 * menu��ť�������ťչʾ��಼�֣��ٵ��һ��������಼�֡�
	 */
	private Button menuButton;

	/**
	 * ����content�����е�ListView��
	 */
	private ListView contentListView;

	/**
	 * ������contentListView����������
	 */
	private ArrayAdapter<String> contentListAdapter;

	/**
	 * �������contentListAdapter������Դ��
	 */
	private String[] contentItems = { "��ӭ", "��½","������Ϣ","��ѯ","����",
			"����", "ע����½"
			};
	private Class clazz[]={StudentNavigationActivity.class,SingleInfoActivity.class,UniversityPreActivity.class,MainActivity.class
			,WZCSActivity.class,HFCSActivity.class};
	 private Integer[] images = {  
			   //�Ź���ͼƬ������  
			   R.drawable.ic_launcher,  
			   R.drawable.ic_launcher,  
			   R.drawable.ic_launcher,  
			   R.drawable.ic_launcher,  
			   R.drawable.ic_launcher,  
			   R.drawable.ic_launcher,  
			  
			   };  
			   
			 private String[] texts = {  
			   //�Ź���ͼƬ�·����ֵ�����  
			   "����ע�ᵼ��",  
			   "�༶��Ϣ��ѯ",  
			   "��ѧԤ��֪ʶ",  
			   "У԰����",  
			   "��ת��ɳ",  
			   "��ʶ����",  
	  
			   };  
	private GridView gridView;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_fuc);
		MyApplication.getInstance().addActivity(this);
		screenWidth = getWindow().getWindowManager().getDefaultDisplay()
				.getWidth();
		screenHeight = getWindow().getWindowManager().getDefaultDisplay()
				.getHeight();
		slidingLayout = (SlidingLayout) findViewById(R.id.slidingLayout);
		menuButton = (Button) findViewById(R.id.menuButton);
		View view=findViewById(R.id.mianLinearLayout);//�޸� Ϊ�����Ҵ��ڶ��ɻ���
		contentListView = (ListView) findViewById(R.id.contentList);
		contentListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
				contentItems);
		contentListView.setAdapter(contentListAdapter);
		contentListView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				switch (position) {
				case 1://��½
					login();
					break;
				case 2://������Ϣ
					doPersonInfo();
					break;
				case 3://��ѯ
					doSearch();
					break;
				case 4://����
					doHelp();
					break;
				case 5://����
					doAbout();	
					break;
				case 6://�˳���½
					logout();		
					break;
				
				}
				
			}

			
			
		});
		// �����������¼�����contentListView��
		slidingLayout.setScrollEvent(view);
		menuButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// ʵ�ֵ��һ��menuչʾ��಼�֣��ٵ��һ��������಼�ֵĹ���
				if (slidingLayout.isLeftLayoutVisible()) {
					slidingLayout.scrollToRightLayout();
				} else {
					slidingLayout.scrollToLeftLayout();
				}
			}
		});
		
		GridImageAdapter adapter=new GridImageAdapter(this,images,texts);
		gridView=(GridView) findViewById(R.id.mainActivityGrid);
		gridView.setAdapter(adapter);
		//ע���¼�������
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent=new Intent(MainFuncActivity.this,clazz[position]);
				startActivity(intent);
				
			}
		});
	
		
	}

	//�˳���½����
	private void logout() {
		// TODO Auto-generated method stub
		DemoApplication.getInstance().exit();//ֻ�ǹر��˴򿪵�activities�������û��˺ŵ��˳���ûд
		DemoApplication.isLogin=false;
	}
	//��½������ת
	private void login() {
		Intent intent=new Intent(this,LoginActivity.class);
		startActivity(intent);
	}
	//����
	private void doAbout() {
		Intent intent=new Intent(this,InfoShowActivity.class);
		intent.putExtra("msg", R.string.about);
		startActivity(intent);
	}
	//����
	private void doHelp() {
		Intent intent=new Intent(this,InfoShowActivity.class);
		intent.putExtra("msg", R.string.help);
		startActivity(intent);
		
	}
	private void doPersonInfo(){
		if(DemoApplication.isLogin){
			Intent intent=new Intent(this,PersonalInfoShowActivity.class);
			startActivity(intent);
		}else{
			Toast.makeText(this, "�ȵ�¼", Toast.LENGTH_LONG).show();
		}
	}
	//��ѯ
	private void doSearch() {
		if(DemoApplication.isLogin){
			Intent intent=new Intent(this,SingleInfoActivity.class);
			startActivity(intent);
		}else{
			Toast.makeText(this, "�ȵ�¼", Toast.LENGTH_LONG).show();
		}
	}


}
