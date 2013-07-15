package cn.com.csuguide.activity;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;
import cn.com.csuguide.R;
import cn.com.csuguide.model.BuildingsInfo;
import cn.com.csuguide.util.BuildingsInfoSet;
import cn.com.csuguide.util.DataBase;

import com.baidu.location.i.b;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.RouteOverlay;
import com.baidu.mapapi.search.MKAddrInfo;
import com.baidu.mapapi.search.MKBusLineResult;
import com.baidu.mapapi.search.MKDrivingRouteResult;
import com.baidu.mapapi.search.MKPlanNode;
import com.baidu.mapapi.search.MKPoiResult;
import com.baidu.mapapi.search.MKSearch;
import com.baidu.mapapi.search.MKSearchListener;
import com.baidu.mapapi.search.MKSuggestionResult;
import com.baidu.mapapi.search.MKTransitRouteResult;
import com.baidu.mapapi.search.MKWalkingRouteResult;
import com.baidu.platform.comapi.basestruct.GeoPoint;
/*需要做的事 获得起点  终点的经纬度坐标  */
public class RoutePlanShow extends Activity {

	GeoPoint ptCenter=new GeoPoint(28154567, 112948087);
	Button mBtnCusRoute = null; //自定义路线
	private AutoCompleteTextView autoCompleteBegin=null;
	private AutoCompleteTextView autoCompleteEnd=null;
	private String beginString;
	private String endString;	
	MKPlanNode stNode;
	MKPlanNode enNode;
	//数组内容从文档获得
	static String[] hot;
	public static DataBase db=StartUI.db ;
	MapView mMapView = null;	// 地图View
	MKSearch mSearch = null;	// 搜索模块，也可去掉地图模块独立使用
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        DemoApplication app = (DemoApplication)this.getApplication();
        if(BuildingsInfoSet.buildNameString==null){
        	BuildingsInfoSet.getData();
        	System.out.println("getData----->");
        }
        hot=BuildingsInfoSet.buildNameString;    //设置热点String 数组
		if (app.mBMapManager == null) {
			app.mBMapManager = new BMapManager(this);
			app.mBMapManager.init(DemoApplication.strKey,new DemoApplication.MyGeneralListener());
		}
		
		setContentView(R.layout.activity_route_plan_show);
		//给起点自动完成组件设置样式以及内容
		autoCompleteBegin=(AutoCompleteTextView) findViewById(R.id.autoCompleteBegin);		
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, R.layout.auto_complete_item,hot);
		autoCompleteBegin.setAdapter(adapter);
		//给终点自动完成组件设置样式以及内容
		autoCompleteEnd=(AutoCompleteTextView) findViewById(R.id.autoCompleteEnd);		
		ArrayAdapter<String> adapter1=new ArrayAdapter<String>(this, R.layout.auto_complete_item,hot);
		autoCompleteEnd.setAdapter(adapter1);
		mMapView = (MapView)findViewById(R.id.bmapView);
        initMapView();
        mMapView.setSatellite(true);
        mMapView.getController().setCenter(ptCenter);
        // 初始化搜索模块，注册事件监听
        mSearch = new MKSearch();
        mSearch.init(app.mBMapManager, new MKSearchListener(){

            @Override
            public void onGetPoiDetailSearchResult(int type, int error) {
            }
            
			public void onGetDrivingRouteResult(MKDrivingRouteResult res,
					int error) {
				
			}

			public void onGetTransitRouteResult(MKTransitRouteResult res,
					int error) {
				
			}

			public void onGetWalkingRouteResult(MKWalkingRouteResult res,
					int error) {
				if (error != 0 || res == null) {
					Toast.makeText(RoutePlanShow.this, "抱歉，未找到结果", Toast.LENGTH_SHORT).show();
					return;
				}
				System.out.println("execute 路线搜索");
				RouteOverlay routeOverlay = new RouteOverlay(RoutePlanShow.this, mMapView);
			    // 此处仅展示一个方案作为示例
				routeOverlay.setData(res.getPlan(0).getRoute(0));
			    mMapView.getOverlays().clear();
			    mMapView.getOverlays().add(routeOverlay);
			    mMapView.refresh();
			    // 使用zoomToSpan()绽放地图，使路线能完全显示在地图上
			    mMapView.getController().zoomToSpan(routeOverlay.getLatSpanE6(), routeOverlay.getLonSpanE6());
			    mMapView.getController().animateTo(res.getStart().pt);
			    
			}
			public void onGetAddrResult(MKAddrInfo res, int error) {
			}
			public void onGetPoiResult(MKPoiResult res, int arg1, int arg2) {
			}
			public void onGetBusDetailResult(MKBusLineResult result, int iError) {
			}

			@Override
			public void onGetSuggestionResult(MKSuggestionResult res, int arg1) {
			}

        });
        
        // 设定搜索按钮的响应
        mBtnCusRoute = (Button)findViewById(R.id.cusroute);
        
        OnClickListener clickListener = new OnClickListener(){
			public void onClick(View v) {
					SearchButtonProcess(v);
			}
        };
        
        mBtnCusRoute.setOnClickListener(clickListener);
	}
	
	private void initMapView() {
	    mMapView.getController().enableClick(true);
	    mMapView.getController().setZoom(12);
	    mMapView.setBuiltInZoomControls(true);
	    mMapView.setDoubleClickZooming(true);  
	}
	
	@Override
    protected void onDestroy() {
        mMapView.destroy();
        DemoApplication app = (DemoApplication)this.getApplication();
        if (app.mBMapManager != null) {
            app.mBMapManager.destroy();
            app.mBMapManager = null;
        }
        super.onDestroy();
    }
	
	void SearchButtonProcess(View v) {
		beginString=autoCompleteBegin.getText().toString();
		endString=autoCompleteEnd.getText().toString();
		if(BuildingsInfoSet.isHotPoint(beginString)&&BuildingsInfoSet.isHotPoint(endString)){
			// 对起点终点的name进行赋值，也可以直接对坐标赋值，赋值坐标则将根据坐标进行搜索
			BuildingsInfo beginBuildingsInfo=BuildingsInfoSet.buildHashMap.get(beginString);
			GeoPoint pt1=beginBuildingsInfo.getPt();
			System.out.println("beginBu------>"+beginBuildingsInfo);
			System.out.println("pt1----->"+pt1);
			BuildingsInfo endBuildingsInfo=BuildingsInfoSet.buildHashMap.get(endString);
			GeoPoint pt2=endBuildingsInfo.getPt();
			//			int beginlat =beginBuildingsInfo.;
//			int beginlat =db.searchLatByName(beginString);
//			int beginlot =db.searchLotByName(beginString);
			int beginlot =db.searchLotByName(beginString);
//			System.out.println("beginlat----->"+beginlat);
//			System.out.println("beginlot---->"+beginlot);
//			int endlat =db.searchLatByName(endString);
//			int endlot =db.searchLotByName(endString);
			stNode = new MKPlanNode();
			stNode.name = beginString;
			enNode = new MKPlanNode();
			enNode.name = endString;
//			GeoPoint pt1 = new GeoPoint(beginlat, beginlot);
//		    GeoPoint pt2= new GeoPoint(endlat, endlot);
		    stNode.pt=pt1;
		    enNode.pt=pt2;
		    mSearch.walkingSearch("长沙", stNode, "长沙", enNode);
			mMapView.getController().setCenter(pt1);
		
		}else{
			Toast.makeText(RoutePlanShow.this, "输入不存在，请重新输入起点或终点", Toast.LENGTH_LONG).show();
		}
		
	}

    @Override
    protected void onPause() {
        mMapView.onPause();
        super.onPause();
    }
    @Override
    protected void onResume() {
        mMapView.onResume();
        super.onResume();
    }
    
    @Override
    protected void onSaveInstanceState(Bundle outState) {
    	super.onSaveInstanceState(outState);
    	mMapView.onSaveInstanceState(outState);
    	
    }
    
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
    	super.onRestoreInstanceState(savedInstanceState);
    	mMapView.onRestoreInstanceState(savedInstanceState);
    }
    


}
