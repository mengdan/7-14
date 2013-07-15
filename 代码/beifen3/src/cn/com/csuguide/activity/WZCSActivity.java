
package cn.com.csuguide.activity;


import cn.com.csuguide.R;
import cn.com.csuguide.util.Config;
import cn.com.csuguide.util.MyApplication;


import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/*
 * Íæ×ª³¤É³Ä£¿é
 * */
public class WZCSActivity extends Activity implements OnClickListener{
	Button buttonStarCityTourism,buttonStarCityGourmet,buttonStarCityShopping,buttonStarCityEntertainment;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wzcs);
		MyApplication.getInstance().addActivity(this);
		initView();
		addListener();
		
	}

	private void addListener() {
		buttonStarCityTourism.setOnClickListener(this);
		 buttonStarCityGourmet.setOnClickListener(this);
		 buttonStarCityShopping.setOnClickListener(this);
		 buttonStarCityEntertainment.setOnClickListener(this);
	}

	private void initView() {
		buttonStarCityTourism=(Button) findViewById(R.id.buttonStarCityTourism);
		buttonStarCityGourmet=(Button) findViewById(R.id.buttonStartCityGourmet);
		buttonStarCityShopping=(Button) findViewById(R.id.buttonStarCityShopping);
		buttonStarCityEntertainment=(Button) findViewById(R.id.buttonStarCityEntertainment);
		
	}

	@Override
	public void onClick(View v) {
		int id=v.getId();
		Intent intent=new Intent(this,InfoShowActivity.class);
		switch(id){
	
			case R.id.buttonStarCityTourism:
				intent.putExtra("msg", R.string.tourism);
				break;
			case R.id.buttonStartCityGourmet:
				intent.putExtra("msg", R.string.gourmet);
				break;
			case R.id.buttonStarCityShopping:
				intent.putExtra("msg", R.string.shopping);
				break;
			case R.id.buttonStarCityEntertainment:
				intent.putExtra("msg", R.string.entertainment);
				break;
		}
			startActivity(intent);
	}

}
