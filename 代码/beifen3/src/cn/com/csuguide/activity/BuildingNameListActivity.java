package cn.com.csuguide.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import cn.com.csuguide.R;
import cn.com.csuguide.model.BuildingsInfo;
import cn.com.csuguide.util.BuildingsInfoSet;
import cn.com.csuguide.util.MyApplication;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class BuildingNameListActivity extends ListActivity{
	//list用来存储搜索到的建筑物对象
	List<BuildingsInfo> list=new ArrayList<BuildingsInfo>();
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_building_name_list);
		MyApplication.getInstance().addActivity(this);
		//得到PointSearching发送过来的intent,并取出用户输入的建筑物名称
		Intent intent=getIntent();
		String buildingName=intent.getStringExtra("buildingName");
		System.out.println("BuildingNameListActivity--------->"+buildingName);
		list=BuildingsInfoSet.search(buildingName);//模糊查询 （搜索字段的包含关系）
		
		showBuildingName(list);
	}
	//用来从list中读出建筑物的名称，并用适配器显示数据
	private void showBuildingName(List<BuildingsInfo> list){
		if(list.size()>0){
			//arrayList为适配器提供数据
			ArrayList<HashMap<String, String>>  arrayList=new ArrayList<HashMap<String,String>>();
			
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {    //遍历保存建筑物对象的队列
				//取出符合搜索条件的建筑物，以及其名称，并将其显示在ListView组件当中
				BuildingsInfo buildingsInfo = (BuildingsInfo) iterator.next();
				String string=buildingsInfo.getName(); //建筑物的名字
				HashMap<String,String> hashMap=new HashMap<String, String>();     //构建键值对并加入到提供数据的队列中
				hashMap.put("building_name", string);
				arrayList.add(hashMap);
			}
		
		SimpleAdapter adapter=new SimpleAdapter(this,arrayList,R.layout.building_name_list,new String[]{"building_name"},new int[]{R.id.building_name});
		setListAdapter(adapter);
		}else{
			Toast.makeText(this, "亲，没有信息，请检查是否出错！", Toast.LENGTH_LONG).show();
		}
	}
	
	//ListView中的条目被点击时的事件监听
	protected void onListItemClick(ListView l, View v, int position, long id) {

		MainActivity.setIsreturn(true); 
	     Intent intent =new Intent();
         Bundle bundle =new Bundle();
         //将获得的字符创放入intent   ,并回到搜索界面
         intent.setClass(BuildingNameListActivity.this, MainActivity.class);
         bundle.putString("buildingInfo", list.get(position).getName());   
         bundle.putInt("intFlag", 1);//标志位，在主activity中用来判断intent的来源，后期可以设置为常量，方便管理和阅读
         intent.putExtras(bundle);

         startActivity(intent);


		super.onListItemClick(l, v, position, id);
	}

}
