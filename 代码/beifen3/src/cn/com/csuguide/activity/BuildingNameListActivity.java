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
	//list�����洢�������Ľ��������
	List<BuildingsInfo> list=new ArrayList<BuildingsInfo>();
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_building_name_list);
		MyApplication.getInstance().addActivity(this);
		//�õ�PointSearching���͹�����intent,��ȡ���û�����Ľ���������
		Intent intent=getIntent();
		String buildingName=intent.getStringExtra("buildingName");
		System.out.println("BuildingNameListActivity--------->"+buildingName);
		list=BuildingsInfoSet.search(buildingName);//ģ����ѯ �������ֶεİ�����ϵ��
		
		showBuildingName(list);
	}
	//������list�ж�������������ƣ�������������ʾ����
	private void showBuildingName(List<BuildingsInfo> list){
		if(list.size()>0){
			//arrayListΪ�������ṩ����
			ArrayList<HashMap<String, String>>  arrayList=new ArrayList<HashMap<String,String>>();
			
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {    //�������潨�������Ķ���
				//ȡ���������������Ľ�����Լ������ƣ���������ʾ��ListView�������
				BuildingsInfo buildingsInfo = (BuildingsInfo) iterator.next();
				String string=buildingsInfo.getName(); //�����������
				HashMap<String,String> hashMap=new HashMap<String, String>();     //������ֵ�Բ����뵽�ṩ���ݵĶ�����
				hashMap.put("building_name", string);
				arrayList.add(hashMap);
			}
		
		SimpleAdapter adapter=new SimpleAdapter(this,arrayList,R.layout.building_name_list,new String[]{"building_name"},new int[]{R.id.building_name});
		setListAdapter(adapter);
		}else{
			Toast.makeText(this, "�ף�û����Ϣ�������Ƿ����", Toast.LENGTH_LONG).show();
		}
	}
	
	//ListView�е���Ŀ�����ʱ���¼�����
	protected void onListItemClick(ListView l, View v, int position, long id) {

		MainActivity.setIsreturn(true); 
	     Intent intent =new Intent();
         Bundle bundle =new Bundle();
         //����õ��ַ�������intent   ,���ص���������
         intent.setClass(BuildingNameListActivity.this, MainActivity.class);
         bundle.putString("buildingInfo", list.get(position).getName());   
         bundle.putInt("intFlag", 1);//��־λ������activity�������ж�intent����Դ�����ڿ�������Ϊ���������������Ķ�
         intent.putExtras(bundle);

         startActivity(intent);


		super.onListItemClick(l, v, position, id);
	}

}
