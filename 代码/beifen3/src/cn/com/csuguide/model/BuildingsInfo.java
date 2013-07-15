package cn.com.csuguide.model;

import java.util.ArrayList;

import com.baidu.platform.comapi.basestruct.GeoPoint;





/**
 * ������ģ����
 * @author GDK
 *
 */
public class BuildingsInfo {
	
	
	//���췽��
	public BuildingsInfo (String name,double lat,double lot){
		this.name=name;
		this.lat=lat;
		this.lot=lot;
		
		this.pt =new GeoPoint((int) (lat * 1E6), (int) (lot * 1E6));  //�����
	}
	
	public GeoPoint getPt() {
		return pt;
	}
	public void setPt(GeoPoint pt) {
		this.pt = pt;
	}
	
	public  static ArrayList <BuildingsInfo>  buildingArray = new ArrayList <BuildingsInfo>  ( );
	public  static ArrayList <BuildingsInfo>  buildingArraypoint = new ArrayList <BuildingsInfo>  ( );
	private String name ;  //����
	private String intro ;   //����
	private double lat ; //γ��
	private double lot ; //����
	private String drawableID ;  //ʹ�õ�ͼƬ
	private String FloorXmlName ;  //ĳһ¥��ʹ�õĲ���
	private GeoPoint pt ;    //ͨ����γ�ȹ���Ķ�λ��
	
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
