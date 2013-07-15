package cn.com.csuguide.model;

/*
 * 计算距离的模型，两个热点之间的距离
 * 
 * */
public class Distance {
	private String name;//热点名称
	private double distance;//之间的距离
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
