
package cn.com.csuguide.util;
import cn.com.csuguide.activity.MyImageView;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Gallery;


/**
 * Ϊ���ͼƬ�ṩ�������Ĺ�����
 * @author GDK
 *
 */
public class MyGalleryAdapter extends BaseAdapter {

	private Context context;
	
//��Ҫ����У����ͼƬ
	private int images[]=null;
	private  Bitmap bmp;
	
	public MyGalleryAdapter(Context context,int images[]) {
		this.context = context;
		this.images=images;

	}
	public MyGalleryAdapter(Context context) {
		this.context = context;
		

	}
	


	@Override
	public int getCount() {
		
			return images.length;
	
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

   //�õ�Ӧ����ʾ��ͼƬ
	public View getView(int position, View convertView, ViewGroup parent) {
	
		bmp=BitmapFactory.decodeResource(context.getResources(), images[position]);
		MyImageView view = new MyImageView(context, bmp.getWidth(), bmp.getHeight());
		view.setLayoutParams(new Gallery.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		view.setImageBitmap(bmp);
		return view;
	}



}
