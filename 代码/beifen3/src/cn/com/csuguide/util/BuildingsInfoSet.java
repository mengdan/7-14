package cn.com.csuguide.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.content.res.Resources;
import cn.com.csuguide.R;
import cn.com.csuguide.activity.MainActivity;
import cn.com.csuguide.activity.RoutePlanShow;
import cn.com.csuguide.activity.StartUI;
import cn.com.csuguide.model.BuildingsInfo;


//不能用静态的方法初始化信息，得加一个构造器
//改为方法，在程序刚开始启动的时候创建对象，并调用方法
/**
 * 初始化由HashMap构造的建筑物，并提供外部设置建筑物的实现类
 * @author GDK
 *
 */
public class BuildingsInfoSet {
	// 纬度
	double lat = 0;
	// 经度
	double lot = 0;
	public  static String buildNameString[] = null;
	static double latStrings[] = null;
	static double lotStrings[] = null;
	//保存结构    建筑物名字—建筑物对象
	public static HashMap<String, BuildingsInfo> buildHashMap = new HashMap<String, BuildingsInfo>();
	// 数据初始化
	public BuildingsInfoSet(Context context) {
		getData();
		setBuildingsInfo();
	}

	public static void getData(){
		
		// 建筑物名称
		buildNameString = StartUI.db.getNameArray();
		// 纬度
		latStrings = StartUI.db.getLatArray();
		// 经度
		lotStrings = StartUI.db.getLotArray();
	}
	public void setBuildingsInfo() {      //数据初始化，将数据取出来保存到映射buildHashMap中，以供取用
		
		if(buildNameString==null){
			getData();
		}
		for (int i = 0; i < buildNameString.length; i++) {

			lat = latStrings[i];
			lot =lotStrings[i];
			BuildingsInfo buildingsInfo = new BuildingsInfo(buildNameString[i],
					lat, lot);
			buildHashMap.put(buildNameString[i], buildingsInfo);
		}
	}

	public static List<BuildingsInfo> search(String name) {
		if(buildNameString==null){
			getData();
		}
		List<BuildingsInfo> list = new ArrayList<BuildingsInfo>();   //用来保存找到的建筑物对象的队列
		for (int i = 0; i < buildNameString.length; i++) {
			if (buildNameString[i].indexOf(name) >= 0) {
				list.add(buildHashMap.get(buildNameString[i]));
			}
		}
		return list;
	}
	//根据输入的字符串，判断该字符串代表的热点是否在我们给的热点之中
	//未测试
	
	public static boolean isHotPoint(String hotName){
		if(buildNameString==null){
			getData();
		}
		for (int i = 0; i < buildNameString.length; i++) 
			if(buildNameString[i].equals(hotName))
				return true;
		return false;
	}


}
