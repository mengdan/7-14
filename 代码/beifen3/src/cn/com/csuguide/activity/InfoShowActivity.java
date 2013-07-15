package cn.com.csuguide.activity;
import cn.com.csuguide.R;
import cn.com.csuguide.R.id;
import cn.com.csuguide.util.MyApplication;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.widget.LinearLayout;
import android.widget.TextView;
/*��ʾ�ı����ܵ�activity*/
public class InfoShowActivity extends Activity{
	TextView tvInfoShow;
	Integer[] backIdsInteger=new Integer[]{R.drawable.text_back_blue,R.drawable.text_back_green,
			R.drawable.text_back_orange,R.drawable.text_back_pink};
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_info_show);
		LinearLayout view=(LinearLayout) findViewById(R.id.info_show_linearlayout);
		int backId=getBackId();
		view.setBackgroundResource(backId);//���ñ���ͼƬ
		MyApplication.getInstance().addActivity(this);
		Intent intent=getIntent();
		int id=intent.getIntExtra("msg", 0);//�õ����ݣ���ʾ�������
		String msg=getResources().getString(id);
		Spanned spanned=Html.fromHtml(msg);
		tvInfoShow=(TextView) findViewById(R.id.tvInfoShow);
		tvInfoShow.setText(spanned);
	}
	public int getBackId(){
		int positon=(int)(Math.random()*(backIdsInteger.length));
		System.out.println("pos--->"+positon);
		return backIdsInteger[positon];
	}

}
