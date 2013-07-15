package cn.com.csuguide.model;

import java.util.ArrayList;

import com.baidu.platform.comapi.basestruct.GeoPoint;





/**
 * 建筑物模型类
 * @author GDK
 *
 */
public class BuildingsInfo {
	
	
	//构造方法
	public BuildingsInfo (String name,double lat,double lot){
		this.name=name;
		this.lat=lat;
		this.lot=lot;
		
		this.pt =new GeoPoint((int) (lat * 1E6), (int) (lot * 1E6));  //构造点
	}
	
	public GeoPoint getPt() {
		return pt;
	}
	public void setPt(GeoPoint pt) {
		this.pt = pt;
	}
	
	public  static ArrayList <BuildingsInfo>  buildingArray = new ArrayList <BuildingsInfo>  ( );
	public  static ArrayList <BuildingsInfo>  buildingArraypoint = new ArrayList <BuildingsInfo>  ( );
	private String name ;  //名字
	private String intro ;   //介绍
	private double lat ; //纬度
	private double lot ; //经度
	private String drawableID ;  //使用的图片
	private String FloorXmlName ;  //某一楼层使用的布局
	private GeoPoint pt ;    //通过经纬度构造的定位点
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLot() {
		return lot;
	}
	public void setLot(double lot) {
		this.lot = lot;
	}
	public String getDrawableID() {
		return drawableID;
	}
	public void setDrawableID(String drawableID) {
		this.drawableID = drawableID;
	}
	public String getFloorXmlName() {
		return FloorXmlName;
	}
	public void setFloorXmlName(String floorXmlName) {
		FloorXmlName = floorXmlName;
	}
	
	
	
	
}
