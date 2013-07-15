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
/*��Ҫ������ ������  �յ�ľ�γ������  */
public class RoutePlanShow extends Activity {

	GeoPoint ptCenter=new GeoPoint(28154567, 112948087);
	Button mBtnCusRoute = null; //�Զ���·��
	private AutoCompleteTextView autoCompleteBegin=null;
	private AutoCompleteTextView autoCompleteEnd=null;
	private String beginString;
	private String endString;	
	MKPlanNode stNode;
	MKPlanNode enNode;
	//�������ݴ��ĵ����
	static String[] hot;
	public static DataBase db=StartUI.db ;
	MapView mMapView = null;	// ��ͼView
	MKSearch mSearch = null;	// ����ģ�飬Ҳ��ȥ����ͼģ�����ʹ��
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        DemoApplication app = (DemoApplication)this.getApplication();
        if(BuildingsInfoSet.buildNameString==null){
        	BuildingsInfoSet.getData();
        	System.out.println("getData----->");
        }
        hot=BuildingsInfoSet.buildNameString;    //�����ȵ�String ����
		if (app.mBMapManager == null) {
			app.mBMapManager = new BMapManager(this);
			app.mBMapManager.init(DemoApplication.strKey,new DemoApplication.MyGeneralListener());
		}
		
		setContentView(R.layout.activity_route_plan_show);
		//������Զ�������������ʽ�Լ�����
		autoCompleteBegin=(AutoCompleteTextView) findViewById(R.id.autoCompleteBegin);		
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, R.layout.auto_complete_item,hot);
		autoCompleteBegin.setAdapter(adapter);
		//���յ��Զ�������������ʽ�Լ�����
		autoCompleteEnd=(AutoCompleteTextView) findViewById(R.id.autoCompleteEnd);		
		ArrayAdapter<String> adapter1=new ArrayAdapter<String>(this, R.layout.auto_complete_item,hot);
		autoCompleteEnd.setAdapter(adapter1);
		mMapView = (MapView)findViewById(R.id.bmapView);
        initMapView();
        mMapView.setSatellite(true);
        mMapView.getController().setCenter(ptCenter);
        // ��ʼ������ģ�飬ע���¼�����
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
					Toast.makeText(RoutePlanShow.this, "��Ǹ��δ�ҵ����", Toast.LENGTH_SHORT).show();
					return;
				}
				System.out.println("execute ·������");
				RouteOverlay routeOverlay = new RouteOverlay(RoutePlanShow.this, mMapView);
			    // �˴���չʾһ��������Ϊʾ��
				routeOverlay.setData(res.getPlan(0).getRoute(0));
			    mMapView.getOverlays().clear();
			    mMapView.getOverlays().add(routeOverlay);
			    mMapView.refresh();
			    // ʹ��zoomToSpan()���ŵ�ͼ��ʹ·������ȫ��ʾ�ڵ�ͼ��
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
        
        // �趨������ť����Ӧ
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
			// ������յ��name���и�ֵ��Ҳ����ֱ�Ӷ����긳ֵ����ֵ�����򽫸��������������
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
		    mSearch.walkingSearch("��ɳ", stNode, "��ɳ", enNode);
			mMapView.getController().setCenter(pt1);
		
		}else{
			Toast.makeText(RoutePlanShow.this, "���벻���ڣ����������������յ�", Toast.LENGTH_LONG).show();
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
