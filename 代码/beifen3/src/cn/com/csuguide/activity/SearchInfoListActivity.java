package cn.com.csuguide.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.baidu.location.i;

import cn.com.csuguide.R;
import cn.com.csuguide.model.User;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class SearchInfoListActivity  extends Activity{
	ListView contentListView;
	List<User> list=new ArrayList<User>();
	int ids[]=new int[]{11,22,33};
	String userNames[]=new String[]{"user1","user2","user3"};
	String address[]=new String[]{"湖南","江苏","广州"};
	String emails[]=new String[]{"840965802@qq.com","512@123.com","454@qq.com"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_info);
		contentListView=(ListView) findViewById(R.id.contentList);
		for(int i=0;i<ids.length;i++){
			User user=new User();
			user.setUserId(ids[i]);
			user.setAddress(address[i]);
			user.setEmail(emails[i]);
			user.setUserName(userNames[i]);
			list.add(user);
		}
		
		SimpleAdapter adapter=getAdapter(list);
		contentListView.setAdapter(adapter);
		contentListView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				System.out.println("activity---position->"+position);
				User user=list.get(position);
				System.out.println("user ---..."+user);
				Intent intent=new Intent(SearchInfoListActivity.this,PersonalInfoShowActivity.class);
				intent.putExtra("user", user);
				System.out.println("success~~~~~~~~~~~~~~~~");
				startActivity(intent);
			}
		});
	}
  	//将list里面的内容显示出来
   	private SimpleAdapter getAdapter(List<User> list){
   		ArrayList<HashMap<String,String>> arrayList=new ArrayList<HashMap<String,String>>();
   		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			User user = (User) iterator.next();
			HashMap<String,String> map=new HashMap<String,String>();
			map.put("user_id", String.valueOf(user.getUserId()));
			map.put("user_name",user.getUserName());
			arrayList.add(map);
		}
   		SimpleAdapter adapter=new SimpleAdapter(this, arrayList, R.layout.user, new String[]{"user_id","user_name"}, new int[]{R.id.user_id,R.id.user_name});
   		return adapter;
   	}

}
 