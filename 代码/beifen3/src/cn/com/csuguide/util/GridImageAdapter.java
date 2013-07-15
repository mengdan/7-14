package cn.com.csuguide.util;

import cn.com.csuguide.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GridImageAdapter extends BaseAdapter {  
	 private Context context;  
	 private Integer[] images;  
	 private String[] texts; 
	 public GridImageAdapter(Context context) {  
	  this.context=context;  
	 }  
	 public GridImageAdapter(Context context,Integer[] images,String[] texts){
		 this.context=context;
		 this.images=images;
		 this.texts=texts;
	 }
	   
	
	   
	 //get the number  
	 @Override  
	 public int getCount() {  
	  return images.length;  
	 }  
	 
	 @Override  
	 public Object getItem(int position) {  
	  return position;  
	 }  
	 
	 //get the current selector's id number  
	 @Override  
	 public long getItemId(int position) {  
	  return position;  
	 }  
	 
	 //create view method  
	 @Override  
	 public View getView(int position, View view, ViewGroup viewgroup) {  
	  ImgTextWrapper wrapper;  
	  if(view==null) {  
	   wrapper = new ImgTextWrapper();  
	   LayoutInflater inflater = LayoutInflater.from(context);  
	   view = inflater.inflate(R.layout.grid_item, null);  
	   view.setTag(wrapper);  
	   view.setPadding(15, 15, 15, 15);  //每格的间距  
	  } else {  
	   wrapper = (ImgTextWrapper)view.getTag();  
	  }  
	    
	  wrapper.imageView = (ImageView)view.findViewById(R.id.MainActivityImage);  
	  wrapper.imageView.setBackgroundResource(images[position]);  
	  wrapper.textView = (TextView)view.findViewById(R.id.MainActivityText);  
	  wrapper.textView.setText(texts[position]);  
	    
	  return view;  
	 }  
	}  
	 
	class ImgTextWrapper {  
	 ImageView imageView;  
	 TextView textView;  
	   
	}  
