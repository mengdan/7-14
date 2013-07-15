package cn.com.csuguide.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.csuguide.R;
import cn.com.csuguide.util.BuildingsInfoSet;
import cn.com.csuguide.util.Config;
import cn.com.csuguide.util.MyApplication;


import android.R.color;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;


/**
 * ���������tag��Ľ��棬�����Ҫ���������������ڲ�ͼƬ���޸Ĵ��࣬�Լ�intent��ת����
 *
 */
public class BuildingInfoShowActivity extends Activity {

	private LinearLayout myListLayout ;
	private ListView tripListView ;
	int type=0;//�����Ǹ����������
	String[] introduce=null;

	List<Map<String,Object>> list =new ArrayList<Map<String,Object>>();
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_building_info_show);
	        MyApplication.getInstance().addActivity(this);
	        introduce=getResources().getStringArray(R.array.buildingIntroduce);//�õ����ܵ�string����
	        
	        for(int i=0;i<introduce.length;i++){
	        	System.out.println("introduce---->"+introduce[i]);
	        }
	        type=getIntent().getIntExtra("type", 0);
	        System.out.println("type------------>"+type);
	        myListLayout =(LinearLayout)this.findViewById(R.id.myListView);
	        tripListView =new ListView(this);
	        //�������ֲ���
	        LinearLayout.LayoutParams tripListViewParam =new LinearLayout.LayoutParams(
	        		LinearLayout.LayoutParams.FILL_PARENT,
	        		LinearLayout.LayoutParams.FILL_PARENT
	        		);
	        //��ҷ�б�ʱ��ʾ����ɫ����Ϊ��ɫ
	        tripListView.setCacheColorHint(color.white);
	        //���б���ӵ���ʽ������ȥ
	        myListLayout.addView(tripListView,tripListViewParam);
	        SimpleAdapter adapter=new SimpleAdapter(this,getTripListData() , R.layout.listviewrow, new String[]{
	        	"img","name"
	        }, new int []{
	        	R.id.imageViewtip,R.id.fuctioname
	        });
	        tripListView.setAdapter(adapter);
	        tripListView.setOnItemClickListener(new OnItemClickListener(){

				@SuppressLint("NewApi")
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					

					if(list.get(arg2).get("name").toString()=="���"){

						Intent intent = new Intent(BuildingInfoShowActivity.this,
								BuildingIntroduceActivity.class);
						
			    		if(type==Config.TYPE_A_Building){
			    			intent.putExtra("msg", introduce[3]);
			    		}
			    		if(type==Config.TYPE_B_Building){
			    			intent.putExtra("msg", introduce[2]);
			    		}
			    		if(type==Config.TYPE_C_Building){
			    			intent.putExtra("msg", introduce[1]);
			    		}
			    		if(type==Config.TYPE_D_Building){
			    			System.out.println("d type-------->"+type);
			    			intent.putExtra("msg", introduce[0]);
			    		}
			    		startActivity(intent);
					}
					
					if(list.get(arg2).get("name").toString()=="����ͼ"){

						
						Intent intent = new Intent(BuildingInfoShowActivity.this,
								PicShowActivity.class);
					if(type==Config.TYPE_A_Building){
			    			intent.putExtra("type", Config.TYPE_A_Building);
			    		}
			    		if(type==Config.TYPE_B_Building){
			    			intent.putExtra("type", Config.TYPE_B_Building);
			    		}
			    		if(type==Config.TYPE_C_Building){
			    			intent.putExtra("type", Config.TYPE_C_Building);
			    		}
			    		if(type==Config.TYPE_D_Building){
			    			System.out.println("d type di xing tu------>"+type);
			    			intent.putExtra("type", Config.TYPE_D_Building);
			    		}
			    		
						startActivityForResult(intent, 0);
						overridePendingTransition(R.anim.push_left_in,
								R.anim.push_left_out);
			    		
						
						
					}
				}
	        	
	        });
	        
	 }
	 
	 
	 public List<Map<String,Object>> getTripListData(){
		 
		 Map<String,Object> map =    new HashMap<String,Object>();
		 map.put("img", R.drawable.information);
		 map.put("name", "���");
		 list.add(map);
		 
		
		 
		 map =    new HashMap<String,Object>();
		 map.put("img", R.drawable.neibudaohang);
		 map.put("name", "����ͼ");
		 list.add(map);
		 
		 return list ;
	 }
}
