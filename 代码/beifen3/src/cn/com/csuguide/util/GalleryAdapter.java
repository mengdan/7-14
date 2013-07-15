
package cn.com.csuguide.util;

import cn.com.csuguide.R;
import cn.com.csuguide.activity.MyImageView;
import cn.com.csuguide.model.BuildingsInfo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Gallery;


/**
 * 为BuilidingDixingPicture所使用的图片浏览提供适配器的工具类
 * @author GDK
 *
 */
public class GalleryAdapter extends BaseAdapter {

	private Context context;
	
	private int images_d[] = { R.drawable.d1,R.drawable.d2,R.drawable.d3};
	private int images_c[] = { R.drawable.c1,R.drawable.c2,R.drawable.c3,R.drawable.c4};
	private int images_b[] = { R.drawable.b1,R.drawable.b2,R.drawable.b3,R.drawable.b4,R.drawable.b5};
	private int images_a[] = { R.drawable.a1,R.drawable.a2,R.drawable.a3,R.drawable.a4};
	
	private int images_bentu[] = { R.drawable.bentu};
	
	
	private static BuildingsInfo bd ;
	private static String  neirong ;
	private  Bitmap bmp;
	
	public GalleryAdapter(Context context) {
		this.context = context;

	}

	public static BuildingsInfo getBd() {
		return bd;
	}

	public static void setBd(BuildingsInfo bd) {
		GalleryAdapter.bd = bd;
	}

	@Override
	public int getCount() {
		if(bd!=null){
		if(bd.getName().equals("D座")){
			return images_d.length;
			
			}
		if(bd.getName().equals("C座")){
			
			return images_c.length;
		}
		if(bd.getName().equals("B座")){
			
			return images_b.length;
		}if(bd.getName().equals("A座")){
				
				return images_a.length;
			}
		}if(bd.getName().equals("校本部图书馆")){
			
			return images_bentu.length;
		}

			return 0;
	
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

   //得到应该显示的图片
	public View getView(int position, View convertView, ViewGroup parent) {
		if(bd!=null){
	if(bd.getName().equals("D座")){
		
	 bmp = BitmapFactory.decodeResource(context.getResources(), images_d[position]);
	}
	else if (bd.getName().equals("C座")){
		
		 bmp = BitmapFactory.decodeResource(context.getResources(), images_c[position]);
		}
	else if (bd.getName().equals("B座")){
		
		 bmp = BitmapFactory.decodeResource(context.getResources(), images_b[position]);
		}else if (bd.getName().equals("A座")){
		
	 bmp = BitmapFactory.decodeResource(context.getResources(), images_a[position]);
	}if(bd.getName().equals("校本部图书馆")){
		bmp = BitmapFactory.decodeResource(context.getResources(), images_bentu[position]);
		
	}else{
	
		 
	}
		}
		MyImageView view = new MyImageView(context, bmp.getWidth(), bmp.getHeight());
		view.setLayoutParams(new Gallery.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		view.setImageBitmap(bmp);
		return view;
	}



}
