package cn.com.csuguide.model;

/*
 * ��������ģ�ͣ������ȵ�֮��ľ���
 * 
 * */
public class Distance {
	private String name;//�ȵ�����
	private double distance;//֮��ľ���
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @param name
	 * @param distance
	 */
	public Distance(String name, double distance) {
		super();
		this.name = name;
		this.distance = distance;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}

}
