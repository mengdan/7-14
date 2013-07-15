package cn.com.csuguide.util;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

//数据库类
public class DataBase {
	private static SQLiteDatabase _buildingDatabase = null;
	private final String DATABASE_NAME = "buildingDatabase"; // 数据库名称
	private final static String TABLE_NAME = "buildingTable"; // 表名称
	private final static String BLD_ID = "buildingid"; // 建筑编号
	private final static String BLD_NAME = "buildingname"; // 建筑名字
	private final static String BLD_LAT = "buildinglat"; // 建筑经度
	private final static String BLD_LOT = "buildinglot"; // 建筑纬度
	private final static String BLD_ITR = "buildingintro"; // 建筑简介
private int count;
	// 创建表的SQL语句
	private final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
			+ BLD_ID + " TEXT PRIMARY KEY," + BLD_NAME + " TEXT," + BLD_LAT
			+ " INTEGER," + BLD_LOT + " INTEGER," + BLD_ITR + " TEXT" + ")";
	Context ct;

	public DataBase(Context ct) {
		this.ct = ct;
	}

	public void init() {
		try {
			// 创建或打开数据库
			createDataBase(ct);
			if (!tabbleIsExist(TABLE_NAME)) {// 如果不存在，则创建表，存在，则不创建
				createTable();
			}
			int	count1=showItems();
			System.out.println("count1   "+count1);
			if(count1==0){
			insertRecord("0", "我的位置", 28155873, 112952296, "我所在的地方");
			insertRecord("1", "14栋", 28164017, 112941049, "14栋是个好地方啊");
			insertRecord("2", "15栋", 28164678, 112940977, "15栋");
			insertRecord("3", "二食堂", 28164953,112941912, "吃饭的地方");
			
			insertRecord("4", "新校区图书馆", 28155538, 112951016, "学霸集中营");
			insertRecord("5", "A座", 28156605, 112948105, "A座");
			insertRecord("6", "B座", 28156064, 112948069, "B座");
			insertRecord("7", "C座", 28155251, 112948069, "C座");
			insertRecord("8", "D座",28154567,112948087, "D座");
			
			insertRecord("9", "新校实验楼", 28156302, 112946506, "新校实验楼");
			insertRecord("10", "新校外语楼", 28156605, 112945087, "新校外语楼");
			insertRecord("11", "信息科学与工程学院（民主楼）", 28176811, 112936908, "民主楼");
			insertRecord("12", "计算机楼", 28177181, 112937637, "计算机楼");
			insertRecord("13", "科教南楼（逸夫楼）", 28176560, 112934901, "科教南楼（逸夫楼）");
			insertRecord("14", "化学化工学院", 28176102, 112935956, "化学化工学院");
			insertRecord("15", "地学楼", 28175219,112936999, "地学楼");
			insertRecord("16", "特冶楼", 28174718, 112935629, "特冶楼");
			insertRecord("17", "中南大学附属实验中学", 28174395, 112934928, "中南大学附属实验中学");
			insertRecord("18", "中南大学体育馆", 28173830, 112934969, "中南大学体育馆");
			insertRecord("19", "中南大学游泳池", 28173281,112934942, "中南大学游泳池");
			insertRecord("20", "南三舍", 28165442, 112944418, "南三舍");
			insertRecord("21", "能源科学与工程学院", 28173078, 112935346, "能源科学与工程学院");
			insertRecord("22", "资源与安全工程学院", 28173121, 112936164, "资源与安全工程学院");
			insertRecord("23", "文学院", 28173212, 112937079, "文学院");
			insertRecord("24", "校本部图书馆", 28175370, 112937897, "校本部图书馆");
			insertRecord("25", "米塔尔金属工业研究院",28176330, 112932323, "米塔尔金属工业研究院");
			insertRecord("26", "公共管理学院（升华楼）", 28176712, 112935872, "公共管理学院（升华楼）");
			insertRecord("27", "西苑", 28175195, 112933891, "西苑");
			insertRecord("28", "资源加工与生物工程学院（和平楼）", 28176811, 112938212, "资源加工与生物工程学院（和平楼）");
			insertRecord("29", "教工食堂（六食堂）", 28174140, 112938715, "教工食堂（六食堂）");
			insertRecord("30", "商业街", 28178925, 112936092, "商业街");
			insertRecord("31", "后街", 28178722, 112931852, "后街");
			insertRecord("32", "物理楼", 28173615, 112936878, "物理楼");
			
			
			insertRecord("33", "七食堂", 28164953, 112943925, "七食堂");
			insertRecord("34", "一教（文学院法学院）（文法楼）", 28167015, 112943413, "一教（文学院法学院）（文法楼）");
			insertRecord("35", "三教（马克思主义学院）（自习圣地）", 28166769, 112941962, "三教（马克思主义学院）（自习圣地）");
			insertRecord("36", "二教（数学院）", 28168974, 112944055, "二教（数学院）");
			insertRecord("37", "南校电影院", 28169448, 112943372, "南校电影院");
			insertRecord("38", "八食堂", 28163118, 112943507, "八食堂");
			insertRecord("39", "天鹅岛", 28154408, 112949741, "天鹅岛");
			insertRecord("40", "机电楼", 28153297, 11294684, "机电楼");
			insertRecord("41", "南二舍", 28165689,112944373, "南二舍");
			
}
			count=showItems();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
		}

	}

	// 创建数据库
	public void createDataBase(Context context) {
		_buildingDatabase = context.openOrCreateDatabase(DATABASE_NAME,
				Context.MODE_PRIVATE, null);

		System.out.println("createDataBase");
	}

	// 创建表
	public void createTable() {
		_buildingDatabase.execSQL(CREATE_TABLE);

		System.out.println("createTable()");
	}

	// 插入一条记录
	public void insertRecord(String id, String name, int lat, int lot,
			String intro) { // 规则： id已存在，就update。 id不存在，就insert。

		if (!IsIDExsist(id)) {// 不存在该ID则插入

			ContentValues cv = new ContentValues();

			cv.put(BLD_ID, id); // id

			cv.put(BLD_NAME, name); // 建筑物名称
			cv.put(BLD_LAT, lat); // 经度
			cv.put(BLD_LOT, lot); // 纬度
			cv.put(BLD_ITR, intro); // 建筑物介绍

			// 插入数据
			_buildingDatabase.insert(TABLE_NAME, null, cv);
			
			System.out.println("插入建筑:   " + name);
			// int aa=Integer.parseInt(sp.getValue("id")+1); //当前主键+1
			//
			// sp.putValue("id", String.valueOf(aa)); //保存主键

		} else {// 存在则修改

//			updateRecord(id, name, lat, lot, intro);
		}

	}

	// 根据建筑物名修改一条记录
	public void updateRecord(String id, String name, int lat, int lot,
			String intro) {
		ContentValues cv = new ContentValues();

		cv.put(BLD_ID, id);
		cv.put(BLD_NAME, name);
		cv.put(BLD_LAT, lat);
		cv.put(BLD_LOT, lot);
		cv.put(BLD_ITR, intro);
		// 修改条件
		String whereClause = BLD_ID + " =?";

		// 修改添加参数
		String[] whereArgs = { id };

		// 进行修改
		_buildingDatabase.update(TABLE_NAME, cv, whereClause, whereArgs);

		System.out.println("将建筑物 " + id + "    修改了");
	}

	// 通过建筑名字查找一条记录
	public void getRecordByName(String name) {
		Cursor c = _buildingDatabase.query(TABLE_NAME, null, null, null, null,
					null, null);

			if (c.moveToFirst() == true) {

				if (c.getString(0).equals("0")) {

					String buildingId = c.getString(0);
					String buildingName = c.getString(1);
					int buildingLat = c.getInt(2);
					int buildingLot = c.getInt(3);
					double ba = castInttoDouble(buildingLat);
					double bo = castInttoDouble(buildingLot);
					String intro = c.getString(4);
					// 返回结果
					if (name.indexOf(buildingName) != -1) {

						// 打印查找结果
						System.out.println("查询到建筑 \t  id:" + buildingId
								+ "\t name:" + buildingName + "\t lat:" + ba
								+ "\t lot:" + bo + "\t intro:" + intro);
					}
				}
				while (c.moveToNext()) {

					String buildingId = c.getString(0);
					String buildingName = c.getString(1);
					int buildingLat = c.getInt(2);
					int buildingLot = c.getInt(3);
					double ba = castInttoDouble(buildingLat);
					double bo = castInttoDouble(buildingLot);
					String intro = c.getString(4);
					// 返回结果
					if (name.indexOf(buildingName) != -1) {

						// 打印查找结果
						System.out.println("查询到建筑 \t  id:" + buildingId
								+ "\t name:" + buildingName + "\t lat:" + ba
								+ "\t lot:" + bo + "\t intro:" + intro);
					}
				}
				
			}
		
			c.close();
		
	}

	// 删除一条记录根据编号
	public void deleteRecord(String id) {
		// 删除条件
		String whereClause = BLD_ID + " =?";

		// 修改添加参数
		String[] whereArgs = { id };

		// 进行修改
		_buildingDatabase.delete(TABLE_NAME, whereClause, whereArgs);
		System.out.println("已删除  " + id + "  号建筑");
	}

	// 删除一条记录根据编号
	public void deleteRecordByName(String name) {
		// 删除条件
		String whereClause = BLD_NAME + " =?";

		// 修改添加参数
		String[] whereArgs = { name };

		// 进行修改
		_buildingDatabase.delete(TABLE_NAME, whereClause, whereArgs);
		_buildingDatabase.close();
		System.out.println("已删除建筑 " + name);
	}

	// 将int 切割成转成6位小数的double ,int a必须大于等于6位
	public static double castInttoDouble(int a) {

		String b = Integer.toString(a);
		int len = b.length();
		String c = b.substring(len - 6, len);
		String d = b.substring(0, len - c.length());
		String newd = d + "." + c;
		double dl = Double.valueOf(newd);
		// System.out.println("dl----->>>" + do);

		return dl;
	}

	// 通过名字查找ID
	public int getIDByName(String name) {
		Cursor c= _buildingDatabase.query(TABLE_NAME, null, null, null, null,
					null, null);
			if (c.moveToFirst() == true) {
				if (c.getString(0).equals("0")) {// 如果编号为0

					String buildingId = c.getString(0);
					String buildingName = c.getString(1);

					// 返回结果
					if (name.indexOf(buildingName) != -1) {
						return Integer.parseInt(buildingId);
						
					}
				}
				while (c.moveToNext()) { // 从编号1开始遍历

					String buildingId = c.getString(0);
					String buildingName = c.getString(1);

					// 返回结果
					if (name.indexOf(buildingName) != -1) {
						return Integer.parseInt(buildingId);
					}
				}
			}
			c.close();
		return 0;
	}

	/**
	 * 判断某张表是否存在
	 * 
	 * @param tabName
	 *            表名
	 * @return
	 */
	public boolean tabbleIsExist(String tableName) {
		boolean result = false;
		if (tableName == null) {
			return false;
		}
		SQLiteDatabase db = null;
		Cursor cursor = null;
		try {
			db = _buildingDatabase;
			String sql = "select count(*) as c from Sqlite_master  where type ='table' and name ='"
					+ tableName.trim() + "' ";
			cursor = db.rawQuery(sql, null);
			if (cursor.moveToNext()) {
				int count = cursor.getInt(0);
				if (count > 0) {
					result = true;
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			cursor.close();
//			_buildingDatabase.close();
		}
		return result;
	}

	// 查找一条记录
	public void getRecord(String id) {
		Cursor c  = _buildingDatabase.query(TABLE_NAME, null, null, null, null,
					null, null);
			if (c.moveToFirst() == true) {
				// System.out.println("2     "+c.getString(0));
				if (c.getString(0).equals("0")) {

					String buildingId = c.getString(0);
					String buildingName = c.getString(1);
					// c.getFloat(2);
					int buildingLat = c.getInt(2);
					int buildingLot = c.getInt(3);
					double ba = castInttoDouble(buildingLat);
					double bo = castInttoDouble(buildingLot);

					// 返回结果
					if (id.indexOf(buildingId) != -1) {

						// 打印查找结果
						System.out.println("查询到建筑 \t  id:" + buildingId
								+ "\t name:" + buildingName + "\t lat:" + ba
								+ "\t lot:" + bo);
					}
				}
				while (c.moveToNext()) {

					String buildingId = c.getString(0);
					String buildingName = c.getString(1);
					int buildingLat = c.getInt(2);
					int buildingLot = c.getInt(3);
					double ba = castInttoDouble(buildingLat);
					double bo = castInttoDouble(buildingLot);
					// 返回结果
					if (id.indexOf(buildingId) != -1) {

						// 打印查找结果
						System.out.println("查询到建筑 \t  id:" + buildingId
								+ "\t name:" + buildingName + "\t lat:" + ba
								+ "\t lot:" + bo);
					}
				}
			}
		
			c.close();
		
	}

	public void bianliTable() { // 遍历并打印表
		Cursor c=_buildingDatabase.query(TABLE_NAME, null, null, null, null,
					null, null);

			if (c.moveToFirst() == true) {

				if (c.getString(0).equals("0")) {

					String buildingId = c.getString(0);
					String buildingName = c.getString(1);

					int buildingLat = c.getInt(2);
					int buildingLot = c.getInt(3);
					double ba = castInttoDouble(buildingLat);
					double bo = castInttoDouble(buildingLot);

					System.out.println("建筑物编号：  " + buildingId + "   名称： "
							+ buildingName + "   经度： " + ba + "   纬度： " + bo);

				}
				while (c.moveToNext()) {

					String buildingId = c.getString(0);
					System.out.println("遍历buildingId" + buildingId);
					String buildingName = c.getString(1);
					int buildingLat = c.getInt(2);
					int buildingLot = c.getInt(3);
					double ba = castInttoDouble(buildingLat);
					double bo = castInttoDouble(buildingLot);
					// 返回结果
					System.out.println("建筑物编号：  " + buildingId + "   名称： "
							+ buildingName + "   经度： " + ba + "   纬度： " + bo);
				}
				
			}
		
			c.close();
		
	}

	public String [] getNameArray() {
		System.out.println("count"+count);
		String names[] =new String [count];
		Cursor c= _buildingDatabase.query(TABLE_NAME, null, null, null, null,
					null, null);

			if (c.moveToFirst() == true) {

				if (c.getString(0).equals("0")) {

					String buildingId = c.getString(0);
					String buildingName = c.getString(1);
			       names[0]=buildingName;

				}
				while (c.moveToNext()) {

					String buildingId = c.getString(0);
					String buildingName = c.getString(1);
					names[Integer.parseInt(buildingId)]=buildingName;
				}
				
			}
		c.close();	

        return names;
	}

	public double [] getLatArray() {
		double lats[] =new double [count];
		Cursor c=_buildingDatabase.query(TABLE_NAME, null, null, null, null,
					null, null);

			if (c.moveToFirst() == true) {

				if (c.getString(0).equals("0")) {

					
					int buildingLat = c.getInt(2);
					int buildingLot = c.getInt(3);
					double ba = castInttoDouble(buildingLat);
					double bo = castInttoDouble(buildingLot);
					lats[0]=ba;
			

				}
				while (c.moveToNext()) {

					String buildingId = c.getString(0);
			        int buildingLat = c.getInt(2);
					int buildingLot = c.getInt(3);
					double ba = castInttoDouble(buildingLat);
					double bo = castInttoDouble(buildingLot);
					lats[Integer.parseInt(buildingId)]=ba;
				}
				
			}
			c.close();
		return lats;
	}

	public double []  getLotArray() {
		double lots[]=new double [count];
		Cursor c =null;
		
			c = _buildingDatabase.query(TABLE_NAME, null, null, null, null,
					null, null);

			if (c.moveToFirst() == true) {

				if (c.getString(0).equals("0")) {

					
					int buildingLat = c.getInt(2);
					int buildingLot = c.getInt(3);
					double ba = castInttoDouble(buildingLat);
					double bo = castInttoDouble(buildingLot);
					lots[0]=bo;
			

				}
				while (c.moveToNext()) {

					String buildingId = c.getString(0);
			        int buildingLat = c.getInt(2);
					int buildingLot = c.getInt(3);
					double ba = castInttoDouble(buildingLat);
					double bo = castInttoDouble(buildingLot);
					lots[Integer.parseInt(buildingId)]=bo;
				}
				c.close();
			}
		
		return lots;
	}
	
	public String getIntroByName(String name){//通过名字查找建筑物
		Cursor c=null;
	
			c = _buildingDatabase.query(TABLE_NAME, null, null, null, null,
					null, null);

			if (c.moveToFirst() == true) {

				if (c.getString(0).equals("0")) {

					String buildingId = c.getString(0);
					String buildingName = c.getString(1);
					int buildingLat = c.getInt(2);
					int buildingLot = c.getInt(3);
					double ba = castInttoDouble(buildingLat);
					double bo = castInttoDouble(buildingLot);
					String intro = c.getString(4);
					// 返回结果
					if (name.indexOf(buildingName) != -1) {

						return intro;
					}
				}
				while (c.moveToNext()) {

					String buildingId = c.getString(0);
					String buildingName = c.getString(1);
					int buildingLat = c.getInt(2);
					int buildingLot = c.getInt(3);
					double ba = castInttoDouble(buildingLat);
					double bo = castInttoDouble(buildingLot);
					String intro = c.getString(4);
					// 返回结果
					if (name.indexOf(buildingName) != -1) {

						return intro;
					}
				}
				
			}
			c.close();
			return null;
	}
	
	
	public int searchLatByName(String name){//通过名字查找LAT，返回INT
			Cursor c =null;
			c = _buildingDatabase.query(TABLE_NAME, null, null, null, null,
					null, null);
			if (c.moveToFirst() == true) {
				// System.out.println("2     "+c.getString(0));
				if (c.getString(0).equals("0")) {

					String buildingId = c.getString(0);
					String buildingName = c.getString(1);
					// c.getFloat(2);
					int buildingLat = c.getInt(2);
					int buildingLot = c.getInt(3);
					double ba = castInttoDouble(buildingLat);
					double bo = castInttoDouble(buildingLot);

					// 返回结果
					if (name.indexOf(buildingName) != -1) {
			      return buildingLat;
					
					}
				}
				while (c.moveToNext()) {

					String buildingId = c.getString(0);
					String buildingName = c.getString(1);
					int buildingLat = c.getInt(2);
					int buildingLot = c.getInt(3);
					double ba = castInttoDouble(buildingLat);
					double bo = castInttoDouble(buildingLot);
					// 返回结果
					if (name.indexOf(buildingName) != -1) {  //名字匹配
			          return buildingLat;
					}
				}
				
			}
		
			c.close();
			
	
		return 0;
	}
	public int searchLotByName(String name){   //通过名字查找LOT，返回INT
		Cursor c=null;
		
			c = _buildingDatabase.query(TABLE_NAME, null, null, null, null,
					null, null);
			if (c.moveToFirst() == true) {
				// System.out.println("2     "+c.getString(0));
				if (c.getString(0).equals("0")) {

					String buildingId = c.getString(0);
					String buildingName = c.getString(1);
					// c.getFloat(2);
					int buildingLat = c.getInt(2);
					int buildingLot = c.getInt(3);
					double ba = castInttoDouble(buildingLat);
					double bo = castInttoDouble(buildingLot);

					// 返回结果
					if (name.indexOf(buildingName) != -1) {
			      return buildingLot;
					
					}
				}
				while (c.moveToNext()) {

					String buildingId = c.getString(0);
					String buildingName = c.getString(1);
					int buildingLat = c.getInt(2);
					int buildingLot = c.getInt(3);
					double ba = castInttoDouble(buildingLat);
					double bo = castInttoDouble(buildingLot);
					// 返回结果
					if (name.indexOf(buildingName) != -1) {  //名字匹配
			          return buildingLot;
					}
				}
			}
			c.close();
		return 0;
	}

	public boolean IsIDExsist(String id) { // id是要检测的ID位置

		Cursor c=null ;
		
			c= _buildingDatabase.query(TABLE_NAME, null, null, null, null,
					null, null);

			if (c.moveToFirst() == true) {

				if (c.getString(0).equals("0")) { // 最开始，遍历到0号位置

					String buildingId = c.getString(0);

					if (id.equals(buildingId)) {
						System.out.println("编号0存在");
						return true;
					}

				}
				while (c.moveToNext()) {// 这里从编号1开始遍历，
					String buildingId = c.getString(0);
					if (id.equals(buildingId)) {
						System.out.println("编号" + id + "存在");
						return true;
					} else {

						if (buildingId.equals(id)) {// 防止循环中途断裂
							System.out.println("编号" + id + "不存在");
							return false;
						}
					}
				}
			}
			System.out.println("编号" + id + "不存在");
			c.close();
		return false;
	}
	 private Integer showItems() {//查询有多少条记录

			String col[] = { BLD_ID, BLD_NAME,BLD_LAT,BLD_LOT,BLD_ITR };
				Cursor cur=null;
				Integer num=0;
				cur = _buildingDatabase.query(TABLE_NAME, col, null, null, null, null, null);
				num = cur.getCount();
				cur.close();
				return num;
			}
	 
}
