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
/*主界面，是六个功能模块的入口*/
public class MainFuncActivity extends Activity {
	
	//屏幕宽度
		public static int screenWidth;
			// 屏幕高度
		public static int screenHeight;
	/**
	 * 侧滑布局对象，用于通过手指滑动将左侧的菜单布局进行显示或隐藏。
	 */
	private SlidingLayout slidingLayout;

	/**
	 * menu按钮，点击按钮展示左侧布局，再点击一次隐藏左侧布局。
	 */
	private Button menuButton;

	/**
	 * 放在content布局中的ListView。
	 */
	private ListView contentListView;

	/**
	 * 作用于contentListView的适配器。
	 */
	private ArrayAdapter<String> contentListAdapter;

	/**
	 * 用于填充contentListAdapter的数据源。
	 */
	private String[] contentItems = { "欢迎", "登陆","个人信息","查询","帮助",
			"关于", "注销登陆"
			};
	private Class clazz[]={StudentNavigationActivity.class,SingleInfoActivity.class,UniversityPreActivity.class,MainActivity.class
			,WZCSActivity.class,HFCSActivity.class};
	 private Integer[] images = {  
			   //九宫格图片的设置  
			   R.drawable.ic_launcher,  
			   R.drawable.ic_launcher,  
			   R.drawable.ic_launcher,  
			   R.drawable.ic_launcher,  
			   R.drawable.ic_launcher,  
			   R.drawable.ic_launcher,  
			  
			   };  
			   
			 private String[] texts = {  
			   //九宫格图片下方文字的设置  
			   "新生注册导航",  
			   "班级信息查询",  
			   "大学预备知识",  
			   "校园导航",  
			   "玩转长沙",  
			   "认识中南",  
	  
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
		View view=findViewById(R.id.mianLinearLayout);//修改 为了左右窗口都可滑动
		contentListView = (ListView) findViewById(R.id.contentList);
		contentListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
				contentItems);
		contentListView.setAdapter(contentListAdapter);
		contentListView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				switch (position) {
				case 1://登陆
					login();
					break;
				case 2://个人信息
					doPersonInfo();
					break;
				case 3://查询
					doSearch();
					break;
				case 4://帮助
					doHelp();
					break;
				case 5://关于
					doAbout();	
					break;
				case 6://退出登陆
					logout();		
					break;
				
				}
				
			}

			
			
		});
		// 将监听滑动事件绑定在contentListView上
		slidingLayout.setScrollEvent(view);
		menuButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 实现点击一下menu展示左侧布局，再点击一下隐藏左侧布局的功能
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
		//注册事件监听器
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent=new Intent(MainFuncActivity.this,clazz[position]);
				startActivity(intent);
				
			}
		});
	
		
	}

	//退出登陆处理
	private void logout() {
		// TODO Auto-generated method stub
		DemoApplication.getInstance().exit();//只是关闭了打开的activities，至于用户账号的退出还没写
		DemoApplication.isLogin=false;
	}
	//登陆处理，跳转
	private void login() {
		Intent intent=new Intent(this,LoginActivity.class);
		startActivity(intent);
	}
	//关于
	private void doAbout() {
		Intent intent=new Intent(this,InfoShowActivity.class);
		intent.putExtra("msg", R.string.about);
		startActivity(intent);
	}
	//帮助
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
			Toast.makeText(this, "先登录", Toast.LENGTH_LONG).show();
		}
	}
	//查询
	private void doSearch() {
		if(DemoApplication.isLogin){
			Intent intent=new Intent(this,SingleInfoActivity.class);
			startActivity(intent);
		}else{
			Toast.makeText(this, "先登录", Toast.LENGTH_LONG).show();
		}
	}


}
