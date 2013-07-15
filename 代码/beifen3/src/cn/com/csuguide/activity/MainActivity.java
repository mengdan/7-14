package cn.com.csuguide.activity;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.R.anim;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.location.LocationListener;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ToggleButton;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import cn.com.csuguide.R;
import cn.com.csuguide.model.BuildingsInfo;
import cn.com.csuguide.model.Distance;
import cn.com.csuguide.searching.PointSearchingActivity;
import cn.com.csuguide.util.BuildingsInfoSet;
import cn.com.csuguide.util.Config;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.BDNotifyListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.MKGeneralListener;
import com.baidu.mapapi.map.ItemizedOverlay;
import com.baidu.mapapi.map.LocationData;
import com.baidu.mapapi.map.MKMapViewListener;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationOverlay;
import com.baidu.mapapi.map.OverlayItem;
import com.baidu.platform.comapi.basestruct.GeoPoint;



/**
 * 主功能界面，定位与路线搜索，并包含建筑物查询功能
 * @author GDK
 *
 */
@SuppressLint("NewApi")
public class MainActivity extends Activity {

	public MKMapViewListener mMapListener = null;//!
	FrameLayout mMapViewContainer = null;//!
	
	// 定位相关
	LocationClient mLocClient;//!
	public MyLocationListenner myListener = new MyLocationListenner();
    public NotifyLister mNotifyer=null;
    MyLocationOverlay myLocationOverlay = null;
	int index =0;
	LocationData locData = null;
	Button tucengqiehuan;
	int tempcount=0;
	public boolean IsSiteLite=false;
	
	Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            Toast.makeText(MainActivity.this, "msg:" +msg.what, Toast.LENGTH_SHORT).show();
        };
    };
	
	
	/* 添加百度地图的相关控件 */
	private MapView mapView;
	private BMapManager bMapManager;// 地图引擎管理类
//	private MKLocationManager mLocationManager;
	/* 百度地图的KEY */
	private String KEY = "D26959961A2CF0B04644010B2CF73984288AE85C";
	/* 在百度地图上添加一些控件，比如说放大或者缩小的控件 */
	private MapController mapController;
	private static boolean Isreturn = false; //判断是否有从intent返回资料
	private GeoPoint cen;
	private GeoPoint geoPoint;
	private String centerbuiding;
	private Button buttonCamera, buttonDelete, buttonWith, buttonPlace, buttonMusic, buttonThought, buttonSleep;
	private Animation animationTranslate, animationRotate, animationScale;
	private static int width, height;
	private LayoutParams params = new LayoutParams(0, 0);
	private static Boolean isClick = false;
	private TextView refresh;
	 private final static double EARTH_RADIUS = 6378137.0;
	 private double nowLat;
	 private double nowLot;
	 public static int latint;
	 public static int lotint;
	 public static boolean m_bKeyRight = true;
	 String centername; 
		double centerlat ;
		double centerlot ;
	public static boolean isIsreturn() {
		return Isreturn;
	}

	public static void setIsreturn(boolean isreturn) {
		Isreturn = isreturn;
	}

	LocationListener mLocationListener = null;// onResume时注册此listener，onPause时需要Remove
	
	Button mylocation; // 顶位到我位置的坐标
	Button changeSch; // 切换校区
	Button search; // 查找某个地点
	Button luxian; //路线查找按钮
	GeoPoint pt;
    private String solution ;
	private int  solu=-1;
	private ConnectivityManager cwjManager;
	private TextView    mypos;
	//画线的方案，来自AutoCompleteTextViewActivity
	
//	BMapApiDemoApp app;
	public void onCreate(Bundle savedInstanceState) {
		System.out.println("执行了onCreate方法");
		super.onCreate(savedInstanceState);
		DemoApplication app = (DemoApplication)this.getApplication();
		DemoApplication.getInstance().addActivity(this);
		cwjManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);  
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
		setContentView(R.layout.activity_main);
		if (app.mBMapManager == null) { //初始化BMapManager
            app.mBMapManager = new BMapManager(this);
            app.mBMapManager.init(DemoApplication.strKey,new DemoApplication.MyGeneralListener());
        }
		initialButton() ;
		
		/* 实例化控件 */
		mapView = (MapView) this.findViewById(R.id.bmapView);
		bMapManager = new BMapManager(MainActivity.this);
		//加载KEY
		bMapManager.init(KEY, new MKGeneralListener() {
		public void onGetPermissionState(int arg0) {

		}
			public void onGetNetworkState(int arg0) {
				if (arg0 == 300) {
					Toast.makeText(MainActivity.this, "验证失败", 1).show();
				}
			}
		});
		if (this.Isreturn) {
			Bundle bundle = this.getIntent().getExtras();
			//得到传回来的信息
			int flag=bundle.getInt("intFlag"); 
			if(flag==1){   //搜索地点界面返回的建筑物名称，将被查询建筑物显示为地图中心
				centerbuiding=bundle.getString("buildingInfo");
				cen = BuildingsInfoSet.buildHashMap.get(centerbuiding).getPt();
				centername =BuildingsInfoSet.buildHashMap.get(centerbuiding).getName();
				centerlat =BuildingsInfoSet.buildHashMap.get(centerbuiding).getLat();
				centerlot =BuildingsInfoSet.buildHashMap.get(centerbuiding).getLot();
				mapView.getOverlays().clear();
		        mapView.refresh();   //清屏
		        Drawable marker = getResources().getDrawable(R.drawable.locating);
                mapView.getOverlays().add(new OverItemPoint(marker));
                mapView.refresh();
			}
			Isreturn = false;
		}
		 mMapListener = new MKMapViewListener() {
				@Override
				public void onMapMoveFinish() {
					// TODO Auto-generated method stub
				}
				
				@Override
				public void onClickMapPoi(MapPoi mapPoiInfo) {
					// TODO Auto-generated method stub
					String title = "";
					if (mapPoiInfo != null){
						title = mapPoiInfo.strText;
						Toast.makeText(MainActivity.this,title,Toast.LENGTH_SHORT).show();
					}
				}

				@Override
				public void onGetCurrentMap(Bitmap b) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onMapAnimationFinish() {
					// TODO Auto-generated method stub
					
				}
			};
		
	
		
		

		/* 初始化mapController */
		mapController = mapView.getController();	
		/* 初始化一个坐标点，校本部图书馆。 */
		geoPoint = new GeoPoint((int) (28.1755 * 1E6), (int) (112.9381 * 1E6));

		mylocation = (Button) this.findViewById(R.id.buttonmylocation);

		final Animation rotate = AnimationUtils.loadAnimation(this,
				R.anim.rotatebyself);
		final Animation rotate2 = AnimationUtils.loadAnimation(this,
				R.anim.weiyi);
		final Animation rotate3 = AnimationUtils.loadAnimation(this,
				R.anim.fangda);
		final Animation rotate4 = AnimationUtils.loadAnimation(this,
				R.anim.weiyi2);
       //设置地图中心
		setCenter();
		
		mylocation.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				mapView.getOverlays().clear();
				 myLocationOverlay.setData(locData);
				 mapView.getOverlays().add(myLocationOverlay);
		        mapView.refresh();
				mypos =(TextView)findViewById(R.id.textMyposition);
				mapController.animateTo(new GeoPoint((int)(locData.latitude* 1e6), (int)(locData.longitude *  1e6)), mHandler.obtainMessage(1));
				try{
				/* 设置一个中心点 */
			if(cwjManager.getActiveNetworkInfo().isAvailable()){
				mylocation.startAnimation(rotate2);
//				mapController.setCenter(pt);
				
				int length=BuildingsInfoSet.buildHashMap.size();
				Distance distances[]=new Distance[length];  //排序数组
				Iterator iter = BuildingsInfoSet.buildHashMap.keySet().iterator(); 
				int count=0;
				while (iter.hasNext()) { 
					String key = (String) iter.next(); 
					BuildingsInfo build = BuildingsInfoSet.buildHashMap.get(key);  //得到value
					double a=	  gps2m(nowLat, nowLot,  build.getLat(),  build.getLot());
					Distance distance=new Distance(build.getName(),a);
					distances[count++]=distance;
					 System.out.println("当前位置距离"+build.getName()+">>>>>>距离"+a+"米"); 

					} 
				//冒泡排序
				for(int i=0;i<distances.length;i++){
					for(int j=i+1;j<distances.length;j++){
						if(distances[i].getDistance()>distances[j].getDistance()){
							Distance temp=distances[i];
							distances[i]=distances[j];
							distances[j]=temp;				
						}						
					}
				}
				System.out.println("我当前的位置是: "+distances[0].getName());
				mypos.setText(distances[0].getName());
				
				for(int i=0;i<distances.length;i++){
//					System.out.println("建筑物名称"+distances[i].getName()+"距离"+distances[i].getDistance());
//				System.out.println("我当前的位置是: "+distances[0].getName());
				}
				
			}else{
				Toast.makeText(MainActivity.this, "当前无网络连接，无法实现定位", Toast.LENGTH_SHORT)
				.show();
			}
				}catch(Exception ef){
					Toast.makeText(MainActivity.this, "当前无网络连接，无法实现定位", Toast.LENGTH_SHORT)
					.show();
				}
		}
		});


		changeSch = (Button) this.findViewById(R.id.changeSch);
		changeSch.setOnClickListener(new OnClickListener() {
			int i = 0;
			@Override
			public void onClick(View v) {
				mapView.getOverlays().clear();
				Drawable marker = getResources().getDrawable(R.drawable.locating2);
                mapView.getOverlays().add(new OverItemPoint(marker));
				mapView.refresh();
				changeSch.startAnimation(rotate4);
				System.out.println("BuildingsInfo.buildingArray   size  "+BuildingsInfo.buildingArray.size());
				mapController.setCenter(BuildingsInfo.buildingArray.get(i)
						.getPt());

				if (i <= 2) {
					i++;
				} else {
					i = 0;
				}
			}
		});
//*****************************2013.4.18新加入代码******************************
		 mLocClient = new LocationClient( this );
	        mLocClient.registerLocationListener( myListener );
	        
	        LocationClientOption option = new LocationClientOption();
	        option.setOpenGps(true);//打开gps
	        option.setCoorType("bd09ll");     //设置坐标类型
	        option.setScanSpan(5000);
	        mLocClient.setLocOption(option);
	        mLocClient.start();
	//***********************2013.4.18新加入代码************************************
	        
	        
		/* 设置mapController的缩放级别为12 */
		mapController.setZoom(18);
		/* 设置百度地图显示卫星地图 */
		mapView.setSatellite(false);
		// 初始化location模块
		mapView.regMapViewListener(DemoApplication.getInstance().mBMapManager, mMapListener);
		myLocationOverlay = new MyLocationOverlay(mapView);
		locData = new LocationData();
	    myLocationOverlay.setData(locData);
	    mapView.getOverlays().add(myLocationOverlay);
		myLocationOverlay.enableCompass();
		mapView.refresh();
	}
	protected void onStop() {
		super.onStop();
	}
	protected void onResume() {// 在用户得到焦点的时候（详见activity的生命周期），bMapManager启动。
		try{
			mapView.onResume();
		}catch(Exception ef){
			Toast.makeText(MainActivity.this, "当前无网络连接，无法实现定位", Toast.LENGTH_SHORT)
			.show();
		}
			if (bMapManager != null) {
			bMapManager.start();
		}
		super.onResume();

	}
	  protected void onDestroy() {
	        DemoApplication app = (DemoApplication)this.getApplication();
	        if (app.mBMapManager != null) {
	            app.mBMapManager.destroy();
	            app.mBMapManager = null;
	        }
	        super.onDestroy();
	    }
	protected void onPause() {// 这用户失去焦点的时候（详见activity的生命周期），bMapManager停止。
			if (bMapManager != null) {
			bMapManager.stop();
		}
		super.onPause();
	}

	//切换点击按钮的方法
		public void onToggleClick(View view) {
			ToggleButton toggle = (ToggleButton) view;
			boolean on = toggle.isChecked();

			if (on) {
				System.out.println("1______________________");
				mapView.setSatellite(true);
			} else {
				System.out.println("2______________________");
				mapView.setSatellite(false);
			}
		}
	protected boolean isRouteDisplayed() {
		return false;
	}
	static  double  lat_e =28.16428; //14
	  static  double lng_e=  112.9413;
	  static  double  lat_f =28.16475; //15
	  static  double lng_f=  112.9409;  
	public void setCenter() {//设置地图中心
		if (cen != null) {
			mapController.setCenter(cen);
		} else {
//遍历hashMap
			Iterator iter = BuildingsInfoSet.buildHashMap.keySet().iterator(); 
			while (iter.hasNext()) { 
				String key = (String) iter.next(); 
				BuildingsInfo build = BuildingsInfoSet.buildHashMap.get(key);  //得到value,取出来的建筑物

				} 
			mapController.setCenter(geoPoint);

		}
	}
	
	private static double gps2m(		GeoPoint s, GeoPoint   d) {
	       double radLat1 = (s.getLatitudeE6() * Math.PI / 180.0); 
	       double radLat2 = (d.getLatitudeE6() * Math.PI / 180.0);
	       double a = radLat1 - radLat2;
	       double b = (s.getLongitudeE6() - d.getLongitudeE6()) * Math.PI / 180.0;
	       double ss = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)

	              + Math.cos(radLat1) * Math.cos(radLat2)

	              * Math.pow(Math.sin(b / 2), 2)));

	       ss= ss * EARTH_RADIUS;

	       ss = Math.round(ss * 10000) / 10000;

	       return ss;

	    }
	private static double gps2m(double lat_a, double lng_a, double lat_b, double lng_b) {
	       double radLat1 = (lat_a * Math.PI / 180.0);
	       double radLat2 = (lat_b * Math.PI / 180.0);
	       double a = radLat1 - radLat2;
	       double b = (lng_a - lng_b) * Math.PI / 180.0;
	       double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)

	              + Math.cos(radLat1) * Math.cos(radLat2)

	              * Math.pow(Math.sin(b / 2), 2)));

	       s = s * EARTH_RADIUS;

	       s = Math.round(s * 10000) / 10000;
	       return s;

	    }

//自定义按钮组代码
	@SuppressLint("NewApi")
	private void initialButton() 
	{
		// TODO Auto-generated method stub
		Display display = getWindowManager().getDefaultDisplay(); 
		height = display.getHeight();  
		width = display.getWidth();
		Log.v("width  & height is:", String.valueOf(width) + ", " + String.valueOf(height));
		
		params.height = 50;
		params.width = 50;
		//设置边距  (int left, int top, int right, int bottom)
		params.setMargins(10, height - 98, 0, 0);
		
		buttonSleep = (Button) findViewById(R.id.net2);	
		buttonSleep.setLayoutParams(params);
		
		buttonThought = (Button) findViewById(R.id.sousuo2);
		buttonThought.setLayoutParams(params);
		
		buttonMusic = (Button) findViewById(R.id.music2);
		buttonMusic.setLayoutParams(params);
		
		buttonPlace = (Button) findViewById(R.id.luxian2);
		buttonPlace.setLayoutParams(params);
		
		buttonWith = (Button) findViewById(R.id.life2);
		buttonWith.setLayoutParams(params);

		buttonCamera = (Button) findViewById(R.id.study2);
		buttonCamera.setLayoutParams(params);
		
		buttonDelete = (Button) findViewById(R.id.button_friends_delete2);		
		buttonDelete.setLayoutParams(params);
		
		buttonDelete.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub					
				if(isClick == false)
				{
					isClick = true;
					buttonDelete.startAnimation(animRotate(-45.0f, 0.5f, 0.45f));					
					buttonCamera.startAnimation(animTranslate(0.0f, -180.0f, 10, height - 240, buttonCamera, 80));
					buttonWith.startAnimation(animTranslate(30.0f, -150.0f, 60, height - 230, buttonWith, 100));
					buttonPlace.startAnimation(animTranslate(70.0f, -120.0f, 110, height - 210, buttonPlace, 120));
					buttonMusic.startAnimation(animTranslate(80.0f, -110.0f, 150, height - 180, buttonMusic, 140));
					buttonThought.startAnimation(animTranslate(90.0f, -60.0f, 175, height - 135, buttonThought, 160));
					buttonSleep.startAnimation(animTranslate(170.0f, -30.0f, 190, height - 90, buttonSleep, 180));
				}
				else
				{					
					isClick = false;
					buttonDelete.startAnimation(animRotate(90.0f, 0.5f, 0.45f));
					buttonCamera.startAnimation(animTranslate(0.0f, 140.0f, 10, height - 98, buttonCamera, 180));
					buttonWith.startAnimation(animTranslate(-50.0f, 130.0f, 10, height - 98, buttonWith, 160));
					buttonPlace.startAnimation(animTranslate(-100.0f, 110.0f, 10, height - 98, buttonPlace, 140));
					buttonMusic.startAnimation(animTranslate(-140.0f, 80.0f, 10, height - 98, buttonMusic, 120));
					buttonThought.startAnimation(animTranslate(-160.0f, 40.0f, 10, height - 98, buttonThought, 80));
					buttonSleep.startAnimation(animTranslate(-170.0f, 0.0f, 10, height - 98, buttonSleep, 50));
				}
					
			}
		});
		buttonCamera.setOnClickListener(new OnClickListener() 
		{ //跳转到学习指南
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub	
				buttonCamera.startAnimation(setAnimScale(2.5f, 2.5f));
				buttonWith.startAnimation(setAnimScale(0.0f, 0.0f));	
				buttonPlace.startAnimation(setAnimScale(0.0f, 0.0f));
				buttonMusic.startAnimation(setAnimScale(0.0f, 0.0f));
				buttonThought.startAnimation(setAnimScale(0.0f, 0.0f));
				buttonSleep.startAnimation(setAnimScale(0.0f, 0.0f));
				buttonDelete.startAnimation(setAnimScale(0.0f, 0.0f));
			}
		});
		buttonWith.setOnClickListener(new OnClickListener() 
		{
				
			@TargetApi(Build.VERSION_CODES.ECLAIR)
			@SuppressLint("NewApi")
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub					
				buttonWith.startAnimation(setAnimScale(2.5f, 2.5f));	
				buttonCamera.startAnimation(setAnimScale(0.0f, 0.0f));	
				buttonPlace.startAnimation(setAnimScale(0.0f, 0.0f));
				buttonMusic.startAnimation(setAnimScale(0.0f, 0.0f));
				buttonThought.startAnimation(setAnimScale(0.0f, 0.0f));
				buttonSleep.startAnimation(setAnimScale(0.0f, 0.0f));
				buttonDelete.startAnimation(setAnimScale(0.0f, 0.0f));
			}
		});
		buttonPlace.setOnClickListener(new OnClickListener() 
		{ //路径搜索
				
			@SuppressLint("NewApi")
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub					
				buttonPlace.startAnimation(setAnimScale(2.5f, 2.5f));
				buttonWith.startAnimation(setAnimScale(0.0f, 0.0f));	
				buttonCamera.startAnimation(setAnimScale(0.0f, 0.0f));	
				buttonMusic.startAnimation(setAnimScale(0.0f, 0.0f));
				buttonThought.startAnimation(setAnimScale(0.0f, 0.0f));
				buttonSleep.startAnimation(setAnimScale(0.0f, 0.0f));
				buttonDelete.startAnimation(setAnimScale(0.0f, 0.0f));
				Intent intent=new Intent(MainActivity.this,RoutePlanShow.class);
				startActivity(intent);
				//第一个参数为启动时动画效果，第二个参数为退出时动画效果
				overridePendingTransition(R.anim.fade1, R.anim.hold1);
			}
		});
		buttonMusic.setOnClickListener(new OnClickListener() 
		{
				
			@SuppressLint("NewApi")
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub					
				buttonMusic.startAnimation(setAnimScale(2.5f, 2.5f));
				buttonPlace.startAnimation(setAnimScale(0.0f, 0.0f));
				buttonWith.startAnimation(setAnimScale(0.0f, 0.0f));	
				buttonCamera.startAnimation(setAnimScale(0.0f, 0.0f));	
				buttonThought.startAnimation(setAnimScale(0.0f, 0.0f));
				buttonSleep.startAnimation(setAnimScale(0.0f, 0.0f));
				buttonDelete.startAnimation(setAnimScale(0.0f, 0.0f));
				
			}
		});
		buttonThought.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub					
				buttonThought.startAnimation(setAnimScale(2.5f, 2.5f));
				buttonPlace.startAnimation(setAnimScale(0.0f, 0.0f));
				buttonWith.startAnimation(setAnimScale(0.0f, 0.0f));	
				buttonCamera.startAnimation(setAnimScale(0.0f, 0.0f));	
				buttonMusic.startAnimation(setAnimScale(0.0f, 0.0f));
				buttonSleep.startAnimation(setAnimScale(0.0f, 0.0f));
				buttonDelete.startAnimation(setAnimScale(0.0f, 0.0f));
				Intent intent=new Intent(MainActivity.this,PointSearchingActivity.class);
				startActivity(intent);
				//第一个参数为启动时动画效果，第二个参数为退出时动画效果
				overridePendingTransition(R.anim.fade1, R.anim.hold1);
			}
		});
		buttonSleep.setOnClickListener(new OnClickListener() 
		{
				
			@TargetApi(Build.VERSION_CODES.ECLAIR)
			@SuppressLint("NewApi")
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub					
				buttonSleep.startAnimation(setAnimScale(2.5f, 2.5f));
				buttonPlace.startAnimation(setAnimScale(0.0f, 0.0f));
				buttonWith.startAnimation(setAnimScale(0.0f, 0.0f));	
				buttonCamera.startAnimation(setAnimScale(0.0f, 0.0f));	
				buttonMusic.startAnimation(setAnimScale(0.0f, 0.0f));
				buttonThought.startAnimation(setAnimScale(0.0f, 0.0f));
				buttonDelete.startAnimation(setAnimScale(0.0f, 0.0f));
			}
		});
		
	}
	@SuppressLint("NewApi")
	protected Animation setAnimScale(float toX, float toY) 
	{
		// TODO Auto-generated method stub
		animationScale = new ScaleAnimation(1f, toX, 1f, toY, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.45f);
		animationScale.setInterpolator(MainActivity.this, anim.accelerate_decelerate_interpolator);
		animationScale.setDuration(500);
		animationScale.setFillAfter(false);
		return animationScale;
		
	}
	
	protected Animation animRotate(float toDegrees, float pivotXValue, float pivotYValue) 
	{
		// TODO Auto-generated method stub
		animationRotate = new RotateAnimation(0, toDegrees, Animation.RELATIVE_TO_SELF, pivotXValue, Animation.RELATIVE_TO_SELF, pivotYValue);
		animationRotate.setAnimationListener(new AnimationListener() 
		{
			public void onAnimationStart(Animation animation) 
			{
				
			}
			public void onAnimationRepeat(Animation animation) 
			{
				
			}
			public void onAnimationEnd(Animation animation) 
			{
				animationRotate.setFillAfter(true);
			}
		});
		return animationRotate;
	}
	//移动的动画效果        
	/* 
	 * TranslateAnimation(float fromXDelta, float toXDelta, float fromYDelta, float toYDelta) 
	 * 
	 * float fromXDelta:这个参数表示动画开始的点离当前View X坐标上的差值；
     *
　　       * float toXDelta, 这个参数表示动画结束的点离当前View X坐标上的差值；
     *
　　       * float fromYDelta, 这个参数表示动画开始的点离当前View Y坐标上的差值；
     *
　　       * float toYDelta)这个参数表示动画开始的点离当前View Y坐标上的差值；
	 */
	protected Animation animTranslate(float toX, float toY, final int lastX, final int lastY,
			final Button button, long durationMillis) 
	{
		// TODO Auto-generated method stub
		animationTranslate = new TranslateAnimation(0, toX, 0, toY);				
		animationTranslate.setAnimationListener(new AnimationListener()
		{
			public void onAnimationStart(Animation animation)
			{
								
			}
			public void onAnimationRepeat(Animation animation) 
			{
			}
			public void onAnimationEnd(Animation animation)
			{
				// TODO Auto-generated method stub
				params = new LayoutParams(0, 0);
				params.height = 50;
				params.width = 50;											
				params.setMargins(lastX, lastY, 0, 0);
				button.setLayoutParams(params);
				button.clearAnimation();
			}
		});																								
		animationTranslate.setDuration(durationMillis);
		return animationTranslate;
	}
	
	
	
	class OverItemPoint extends ItemizedOverlay<OverlayItem> {
		private  List<OverlayItem> GeoList = new ArrayList<OverlayItem>();
		 BuildingsInfo point ;
		public OverItemPoint(Drawable arg0) {
			super(arg0);
    point =new BuildingsInfo(centername, centerlat, centerlot);
        
        BuildingsInfo.buildingArraypoint.add(point);
			
        
        for (int i = 0; i < BuildingsInfo.buildingArraypoint.size(); i++) {
			GeoList.add(new OverlayItem(BuildingsInfo.buildingArraypoint.get(i)
					.getPt(), "A", "A"));
		}

		populate();
		}

		@Override
		protected OverlayItem createItem(int i) {
			return GeoList.get(i);
			
		}

		@Override
		public int size() {
			return GeoList.size();
			
		}
		protected boolean onTap(int i) {
			Toast.makeText(MainActivity.this, centername, Toast.LENGTH_SHORT)
			.show();
			setBuilding(point);
			return true;
			
		}
		
	}

	public void setBuilding(BuildingsInfo bd) {
		int type=getBuildingId(bd.getName());//根据建筑物名称得到id，该id是后面显示文字和图片的标志
		Toast.makeText(MainActivity.this, bd.getName(), Toast.LENGTH_SHORT)
				.show();
		
		Intent intents = new Intent(MainActivity.this, BuildingInfoShowActivity.class);
		
		intents.putExtra("type", type);
		
		startActivityForResult(intents, 0);
		overridePendingTransition(R.anim.push_left_in,
				R.anim.push_left_out);
	}
	private int getBuildingId(String buildingName){
		int type=0;
		if(buildingName.equals("D座")){
			type=Config.TYPE_D_Building;
			System.out.println("MainActivity---------->"+type);
		}if(buildingName.equals("C座")){
			type=Config.TYPE_C_Building;
		}if(buildingName.equals("B座")){
			type=Config.TYPE_B_Building;
		}if(buildingName.equals("A座")){
			type=Config.TYPE_A_Building;
		}
		return type;
	}
	/**
     * 监听函数，又新位置的时候，格式化成字符串，输出到屏幕中
     */
    @SuppressLint("NewApi")
    int count =10;
	public class MyLocationListenner implements BDLocationListener {
       
        @TargetApi(Build.VERSION_CODES.ECLAIR)
		@SuppressLint("NewApi")
		public void onReceiveLocation(BDLocation location) {
        	
//        	System.out.println("进行了定位");
            if (location == null)
                return ;
            
            locData.latitude = location.getLatitude();
            locData.longitude = location.getLongitude();
           nowLat= locData.latitude;
           nowLot= locData.longitude;
           latint=(int)(locData.latitude* 1e6);
           lotint=(int)(locData.longitude *  1e6);
//            System.out.println("locData.latitude"+locData.latitude);
//            System.out.println("locData.longitude"+locData.longitude);
            locData.accuracy = location.getRadius();
            locData.direction = location.getDerect();
            myLocationOverlay.setData(locData);
            if(count%10==0){
//            mapView.refresh();
            }
            count++;
//           mMapController.animateTo(new GeoPoint((int)(locData.latitude* 1e6), (int)(locData.longitude *  1e6)), mHandler.obtainMessage(1));
        
        }
        
        public void onReceivePoi(BDLocation poiLocation) {
            if (poiLocation == null){
                return ;
            }
        }
    }
    
    public class NotifyLister extends BDNotifyListener{
        public void onNotify(BDLocation mlocation, float distance) {
        }
    }

}
