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


//�����þ�̬�ķ�����ʼ����Ϣ���ü�һ��������
//��Ϊ�������ڳ���տ�ʼ������ʱ�򴴽����󣬲����÷���
/**
 * ��ʼ����HashMap����Ľ�������ṩ�ⲿ���ý������ʵ����
 * @author GDK
 *
 */
public class BuildingsInfoSet {
	// γ��
	double lat = 0;
	// ����
	double lot = 0;
	public  static String buildNameString[] = null;
	static double latStrings[] = null;
	static double lotStrings[] = null;
	//����ṹ    ���������֡����������
	public static HashMap<String, BuildingsInfo> buildHashMap = new HashMap<String, BuildingsInfo>();
	// ���ݳ�ʼ��
	public BuildingsInfoSet(Context context) {
		getData();
		setBuildingsInfo();
	}

	public static void getData(){
		
		// ����������
		buildNameString = StartUI.db.getNameArray();
		// γ��
		latStrings = StartUI.db.getLatArray();
		// ����
		lotStrings = StartUI.db.getLotArray();
	}
	public void setBuildingsInfo() {      //���ݳ�ʼ����������ȡ�������浽ӳ��buildHashMap�У��Թ�ȡ��
		
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
		List<BuildingsInfo> list = new ArrayList<BuildingsInfo>();   //���������ҵ��Ľ��������Ķ���
		for (int i = 0; i < buildNameString.length; i++) {
			if (buildNameString[i].indexOf(name) >= 0) {
				list.add(buildHashMap.get(buildNameString[i]));
			}
		}
		return list;
	}
	//����������ַ������жϸ��ַ���������ȵ��Ƿ������Ǹ����ȵ�֮��
	//δ����
	
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
