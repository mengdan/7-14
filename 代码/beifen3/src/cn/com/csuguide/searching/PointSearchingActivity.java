package cn.com.csuguide.searching;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import cn.com.csuguide.R;
import cn.com.csuguide.activity.BuildingNameListActivity;
import cn.com.csuguide.activity.DemoApplication;
import cn.com.csuguide.activity.MainActivity;
import cn.com.csuguide.model.BuildingsInfo;
import cn.com.csuguide.speak.YuYinMainActivity;
import cn.com.csuguide.util.MyApplication;





/**
 * ����������ר����
 * @author GDK
 *
 */
@TargetApi(5)
public class PointSearchingActivity extends  Activity {
	
//	   private MapView mapView;
//	    private BMapManager bMapManager;

	    private String KEY = "D26959961A2CF0B04644010B2CF73984288AE85C";
//	  private MapController mapController;
	 private BuildingsInfo bd ;
	private String name ;
	public static String mStrSuggestions[] = {};
	ListView mSuggestionList = null;
    Button	mSuggestionSearch ;
   private EditText   editSearchKey ;
   public  static boolean IsReturn =false;
//    TextView editSearchKey=null;
	private static ArrayList <BuildingsInfo>temp  =new ArrayList <BuildingsInfo>();   //��ʱ����
	

//	��������������һ��������
	
	@SuppressLint("NewApi")
	public void search(String buildingName){
		System.out.println("search---->"+buildingName);
		Intent intent=new Intent();
		intent.putExtra("buildingName", buildingName);   //������������ַ��͵�BuildingNameListActivity
		intent.setClass(PointSearchingActivity.this, BuildingNameListActivity.class);
		
	//�޸ķ�����������
		startActivity(intent);
		overridePendingTransition(R.anim.push_left_in,
				R.anim.push_left_out);

	}
		
	
	

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
//		requestWindowFeature(Window.FEATURE_NO_TITLE);// ȥ��������
		  setContentView(R.layout.activity_point_searching);
		  DemoApplication.getInstance().addActivity(this);
		 
		  editSearchKey=(EditText)findViewById(R.id.editText1);
		  Button  btnyuyin =(Button)findViewById(R.id.buttonyuyin);
		  
	

            mSuggestionSearch = (Button)findViewById(R.id.buttonasdasd);
//            
            
            
            TextView mudidi=(TextView)findViewById(R.id.textView1);
            TextView  yuyin =(TextView)findViewById(R.id.textView2);
            
           editSearchKey = (EditText)findViewById(R.id.editText1);
           
           OnClickListener clickListener2 = new OnClickListener(){
   			@SuppressLint("NewApi")
			public void onClick(View v) {

   				Intent  intent =new Intent(PointSearchingActivity.this, YuYinMainActivity.class) ;
   				startActivityForResult(intent, 0);
   				overridePendingTransition(R.anim.push_left_in,
   						R.anim.push_left_out);
   				
   			}

   		};
   		btnyuyin.setOnClickListener(clickListener2);
   		yuyin.setOnClickListener(clickListener2);
   		
    		OnClickListener clickListener1 = new OnClickListener(){
    			public void onClick(View v) {

    			String a =	editSearchKey.getText().toString();
    				search(a);
    				
    				
    			}

    		};
    		mSuggestionSearch.setOnClickListener(clickListener1);
    		mudidi.setOnClickListener(clickListener1);
    		
    		if (this.IsReturn) {
    			Bundle bundle = this.getIntent().getExtras();
    			//�õ�����������Ϣ
    			String text = bundle.getString("message");
    			System.out.println("pointsearching------>"+text);
    			editSearchKey.setText(text);
    			IsReturn = false;
    		}

	}

//	@SuppressLint("NewApi")
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

			finish(); //�������activity
		}
		return false;
	}
	
//    protected void onDestroy() {
//    	  if (bMapManager != null) {
//              bMapManager.destroy();
//              bMapManager = null;
//      }
//        super.onDestroy();
//        
//      
//}
protected void onResume() {//���û��õ������ʱ�����activity���������ڣ���bMapManager������
	 //��������Ϊ�գ��Ž���������ķ�����������
	
//	  if(editSearchKey.getText().toString()==null){
//	 
//		Bundle bundle =this.getIntent().getExtras();
//		String message =bundle.getString("message");
//		editSearchKey.setText(message);
//	  }
	  
//        if(bMapManager!=null){
//                bMapManager.start();
//        }
        super.onResume();
}
protected void onPause() {//���û�ʧȥ�����ʱ�����activity���������ڣ���bMapManagerֹͣ��
  
        
//
//        if(bMapManager!=null){
//                bMapManager.stop();
//        }
        super.onPause();
}


protected boolean isRouteDisplayed() {

	return false;
}




public boolean getIsReturn() {
	return IsReturn;
}




public void setIsReturn(boolean isReturn) {
	IsReturn = isReturn;
}



}
