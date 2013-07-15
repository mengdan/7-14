package cn.com.csuguide.activity;



import cn.com.csuguide.R;
import cn.com.csuguide.util.Config;
import cn.com.csuguide.util.MyApplication;
import cn.com.csuguide.util.MyGallery;
import cn.com.csuguide.util.MyGalleryAdapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.AdapterView.OnItemSelectedListener;
/*ר����ʾͼƬ��activity*/
public class PicShowActivity extends Activity implements OnTouchListener{
	
	private MyGallery gallery;
	
		
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
					WindowManager.LayoutParams.FLAG_FULLSCREEN);
			setContentView(R.layout.activity_schoolbus);
			MyApplication.getInstance().addActivity(this);
			Intent intent=getIntent();//�õ�������ʾ��ʲô���͵�ͼƬ
			int type=intent.getIntExtra("type", 0);
			System.out.println("pic show  type-------->"+type);
			int pics[]=getPics(type);
		
			
			
			
			gallery = (MyGallery) findViewById(R.id.gallery);
			gallery.setVerticalFadingEdgeEnabled(false);// ȡ����ֱ����߿�
			gallery.setHorizontalFadingEdgeEnabled(false);// ȡ��ˮƽ����߿�
//			GalleryAdapter adapter=new GalleryAdapter(this);//Ϊ�������ṩ����
			
			MyGalleryAdapter adapter=new MyGalleryAdapter(this,pics);//Ϊ�������ṩ����
			
			gallery.setAdapter(adapter);
			
		}
		/***
		 * �����������洫���id,���ز�ͬ��ͼƬid���飬һ���㲻ͬͼƬ��ʾ�����ظ����ô���
		 * @param type
		 * @return
		 */
		private int[] getPics(int type) {
			int pics[]=null;
			switch(type){
				case Config.TYPE_A_Building:
					pics=new int[]{R.drawable.a1,R.drawable.a2,R.drawable.a3,R.drawable.a4};
					break;
				case Config.TYPE_B_Building:
					pics=new int[]{R.drawable.b1,R.drawable.b2,R.drawable.b3,R.drawable.b4};
					break;
				case Config.TYPE_C_Building:
					pics=new int[]{R.drawable.c1,R.drawable.c2,R.drawable.c3,R.drawable.c4};
						break;
				case Config.TYPE_D_Building:
					System.out.println("d__pic------------>init");
					pics=new int[]{R.drawable.d1,R.drawable.d2,R.drawable.d3};
							break;
			}
			// TODO Auto-generated method stub
			return pics;
		}
		float beforeLenght = 0.0f; // ���������
		float afterLenght = 0.0f; // ���������
		boolean isScale = false;
		float currentScale = 1.0f;// ��ǰͼƬ�����ű���

		private class GalleryChangeListener implements OnItemSelectedListener {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				currentScale = 1.0f;
				isScale = false;
				beforeLenght = 0.0f;
				afterLenght = 0.0f;

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}

		}
		/*�����¼�����*/
		public boolean onTouch(View v, MotionEvent event) {

			switch (event.getAction() & MotionEvent.ACTION_MASK) {
			case MotionEvent.ACTION_POINTER_DOWN:// �������
				beforeLenght = spacing(event);
				if (beforeLenght > 5f) {
					isScale = true;
				}
				break;
			case MotionEvent.ACTION_MOVE:
				if (isScale) {
					afterLenght = spacing(event);
					if (afterLenght < 5f)
						break;
					float gapLenght = afterLenght - beforeLenght;
					if (gapLenght == 0) {
						break;
					} else if (Math.abs(gapLenght) > 5f) {

						float scaleRate = gapLenght / 854;// ���ű���

						Animation myAnimation_Scale = new ScaleAnimation(
								currentScale, currentScale + scaleRate,
								currentScale, currentScale + scaleRate,
								Animation.RELATIVE_TO_SELF, 0.5f,
								Animation.RELATIVE_TO_SELF, 0.5f);

						myAnimation_Scale.setDuration(100);
						myAnimation_Scale.setFillAfter(true);
						myAnimation_Scale.setFillEnabled(true);

						currentScale = currentScale + scaleRate;

						gallery.getSelectedView().setLayoutParams(
								new Gallery.LayoutParams(
										(int) (480 * (currentScale)),
										(int) (854 * (currentScale))));

						beforeLenght = afterLenght;
					}
					return true;
				}
				break;
			case MotionEvent.ACTION_POINTER_UP:
				isScale = false;
				break;
			}

			return false;
		}

		/**
		 * ���������ľ���
		 */
		@SuppressLint("NewApi")
		private float spacing(MotionEvent event) {
			float x = event.getX(0) - event.getX(1);
			float y = event.getY(0) - event.getY(1);
			return FloatMath.sqrt(x * x + y * y);
		}
}

