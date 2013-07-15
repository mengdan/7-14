package cn.com.csuguide.util;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

//���ݿ���
public class DataBase {
	private static SQLiteDatabase _buildingDatabase = null;
	private final String DATABASE_NAME = "buildingDatabase"; // ���ݿ�����
	private final static String TABLE_NAME = "buildingTable"; // ������
	private final static String BLD_ID = "buildingid"; // �������
	private final static String BLD_NAME = "buildingname"; // ��������
	private final static String BLD_LAT = "buildinglat"; // ��������
	private final static String BLD_LOT = "buildinglot"; // ����γ��
	private final static String BLD_ITR = "buildingintro"; // �������
private int count;
	// �������SQL���
	private final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
			+ BLD_ID + " TEXT PRIMARY KEY," + BLD_NAME + " TEXT," + BLD_LAT
			+ " INTEGER," + BLD_LOT + " INTEGER," + BLD_ITR + " TEXT" + ")";
	Context ct;

	public DataBase(Context ct) {
		this.ct = ct;
	}

	public void init() {
		try {
			// ����������ݿ�
			createDataBase(ct);
			if (!tabbleIsExist(TABLE_NAME)) {// ��������ڣ��򴴽������ڣ��򲻴���
				createTable();
			}
			int	count1=showItems();
			System.out.println("count1   "+count1);
			if(count1==0){
			insertRecord("0", "�ҵ�λ��", 28155873, 112952296, "�����ڵĵط�");
			insertRecord("1", "14��", 28164017, 112941049, "14���Ǹ��õط���");
			insertRecord("2", "15��", 28164678, 112940977, "15��");
			insertRecord("3", "��ʳ��", 28164953,112941912, "�Է��ĵط�");
			
			insertRecord("4", "��У��ͼ���", 28155538, 112951016, "ѧ�Լ���Ӫ");
			insertRecord("5", "A��", 28156605, 112948105, "A��");
			insertRecord("6", "B��", 28156064, 112948069, "B��");
			insertRecord("7", "C��", 28155251, 112948069, "C��");
			insertRecord("8", "D��",28154567,112948087, "D��");
			
			insertRecord("9", "��Уʵ��¥", 28156302, 112946506, "��Уʵ��¥");
			insertRecord("10", "��У����¥", 28156605, 112945087, "��У����¥");
			insertRecord("11", "��Ϣ��ѧ�빤��ѧԺ������¥��", 28176811, 112936908, "����¥");
			insertRecord("12", "�����¥", 28177181, 112937637, "�����¥");
			insertRecord("13", "�ƽ���¥���ݷ�¥��", 28176560, 112934901, "�ƽ���¥���ݷ�¥��");
			insertRecord("14", "��ѧ����ѧԺ", 28176102, 112935956, "��ѧ����ѧԺ");
			insertRecord("15", "��ѧ¥", 28175219,112936999, "��ѧ¥");
			insertRecord("16", "��ұ¥", 28174718, 112935629, "��ұ¥");
			insertRecord("17", "���ϴ�ѧ����ʵ����ѧ", 28174395, 112934928, "���ϴ�ѧ����ʵ����ѧ");
			insertRecord("18", "���ϴ�ѧ������", 28173830, 112934969, "���ϴ�ѧ������");
			insertRecord("19", "���ϴ�ѧ��Ӿ��", 28173281,112934942, "���ϴ�ѧ��Ӿ��");
			insertRecord("20", "������", 28165442, 112944418, "������");
			insertRecord("21", "��Դ��ѧ�빤��ѧԺ", 28173078, 112935346, "��Դ��ѧ�빤��ѧԺ");
			insertRecord("22", "��Դ�밲ȫ����ѧԺ", 28173121, 112936164, "��Դ�밲ȫ����ѧԺ");
			insertRecord("23", "��ѧԺ", 28173212, 112937079, "��ѧԺ");
			insertRecord("24", "У����ͼ���", 28175370, 112937897, "У����ͼ���");
			insertRecord("25", "������������ҵ�о�Ժ",28176330, 112932323, "������������ҵ�о�Ժ");
			insertRecord("26", "��������ѧԺ������¥��", 28176712, 112935872, "��������ѧԺ������¥��");
			insertRecord("27", "��Է", 28175195, 112933891, "��Է");
			insertRecord("28", "��Դ�ӹ������﹤��ѧԺ����ƽ¥��", 28176811, 112938212, "��Դ�ӹ������﹤��ѧԺ����ƽ¥��");
			insertRecord("29", "�̹�ʳ�ã���ʳ�ã�", 28174140, 112938715, "�̹�ʳ�ã���ʳ�ã�");
			insertRecord("30", "��ҵ��", 28178925, 112936092, "��ҵ��");
			insertRecord("31", "���", 28178722, 112931852, "���");
			insertRecord("32", "����¥", 28173615, 112936878, "����¥");
			
			
			insertRecord("33", "��ʳ��", 28164953, 112943925, "��ʳ��");
			insertRecord("34", "һ�̣���ѧԺ��ѧԺ�����ķ�¥��", 28167015, 112943413, "һ�̣���ѧԺ��ѧԺ�����ķ�¥��");
			insertRecord("35", "���̣����˼����ѧԺ������ϰʥ�أ�", 28166769, 112941962, "���̣����˼����ѧԺ������ϰʥ�أ�");
			insertRecord("36", "���̣���ѧԺ��", 28168974, 112944055, "���̣���ѧԺ��");
			insertRecord("37", "��У��ӰԺ", 28169448, 112943372, "��У��ӰԺ");
			insertRecord("38", "��ʳ��", 28163118, 112943507, "��ʳ��");
			insertRecord("39", "��쵺", 28154408, 112949741, "��쵺");
			insertRecord("40", "����¥", 28153297, 11294684, "����¥");
			insertRecord("41", "�϶���", 28165689,112944373, "�϶���");
			
}
			count=showItems();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
		}

	}

	// �������ݿ�
	public void createDataBase(Context context) {
		_buildingDatabase = context.openOrCreateDatabase(DATABASE_NAME,
				Context.MODE_PRIVATE, null);

		System.out.println("createDataBase");
	}

	// ������
	public void createTable() {
		_buildingDatabase.execSQL(CREATE_TABLE);

		System.out.println("createTable()");
	}

	// ����һ����¼
	public void insertRecord(String id, String name, int lat, int lot,
			String intro) { // ���� id�Ѵ��ڣ���update�� id�����ڣ���insert��

		if (!IsIDExsist(id)) {// �����ڸ�ID�����

			ContentValues cv = new ContentValues();

			cv.put(BLD_ID, id); // id

			cv.put(BLD_NAME, name); // ����������
			cv.put(BLD_LAT, lat); // ����
			cv.put(BLD_LOT, lot); // γ��
			cv.put(BLD_ITR, intro); // ���������

			// ��������
			_buildingDatabase.insert(TABLE_NAME, null, cv);
			
			System.out.println("���뽨��:   " + name);
			// int aa=Integer.parseInt(sp.getValue("id")+1); //��ǰ����+1
			//
			// sp.putValue("id", String.valueOf(aa)); //��������

		} else {// �������޸�

//			updateRecord(id, name, lat, lot, intro);
		}

	}

	// ���ݽ��������޸�һ����¼
	public void updateRecord(String id, String name, int lat, int lot,
			String intro) {
		ContentValues cv = new ContentValues();

		cv.put(BLD_ID, id);
		cv.put(BLD_NAME, name);
		cv.put(BLD_LAT, lat);
		cv.put(BLD_LOT, lot);
		cv.put(BLD_ITR, intro);
		// �޸�����
		String whereClause = BLD_ID + " =?";

		// �޸���Ӳ���
		String[] whereArgs = { id };

		// �����޸�
		_buildingDatabase.update(TABLE_NAME, cv, whereClause, whereArgs);

		System.out.println("�������� " + id + "    �޸���");
	}

	// ͨ���������ֲ���һ����¼
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
					// ���ؽ��
					if (name.indexOf(buildingName) != -1) {

						// ��ӡ���ҽ��
						System.out.println("��ѯ������ \t  id:" + buildingId
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
					// ���ؽ��
					if (name.indexOf(buildingName) != -1) {

						// ��ӡ���ҽ��
						System.out.println("��ѯ������ \t  id:" + buildingId
								+ "\t name:" + buildingName + "\t lat:" + ba
								+ "\t lot:" + bo + "\t intro:" + intro);
					}
				}
				
			}
		
			c.close();
		
	}

	// ɾ��һ����¼���ݱ��
	public void deleteRecord(String id) {
		// ɾ������
		String whereClause = BLD_ID + " =?";

		// �޸���Ӳ���
		String[] whereArgs = { id };

		// �����޸�
		_buildingDatabase.delete(TABLE_NAME, whereClause, whereArgs);
		System.out.println("��ɾ��  " + id + "  �Ž���");
	}

	// ɾ��һ����¼���ݱ��
	public void deleteRecordByName(String name) {
		// ɾ������
		String whereClause = BLD_NAME + " =?";

		// �޸���Ӳ���
		String[] whereArgs = { name };

		// �����޸�
		_buildingDatabase.delete(TABLE_NAME, whereClause, whereArgs);
		_buildingDatabase.close();
		System.out.println("��ɾ������ " + name);
	}

	// ��int �и��ת��6λС����double ,int a������ڵ���6λ
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

	// ͨ�����ֲ���ID
	public int getIDByName(String name) {
		Cursor c= _buildingDatabase.query(TABLE_NAME, null, null, null, null,
					null, null);
			if (c.moveToFirst() == true) {
				if (c.getString(0).equals("0")) {// ������Ϊ0

					String buildingId = c.getString(0);
					String buildingName = c.getString(1);

					// ���ؽ��
					if (name.indexOf(buildingName) != -1) {
						return Integer.parseInt(buildingId);
						
					}
				}
				while (c.moveToNext()) { // �ӱ��1��ʼ����

					String buildingId = c.getString(0);
					String buildingName = c.getString(1);

					// ���ؽ��
					if (name.indexOf(buildingName) != -1) {
						return Integer.parseInt(buildingId);
					}
				}
			}
			c.close();
		return 0;
	}

	/**
	 * �ж�ĳ�ű��Ƿ����
	 * 
	 * @param tabName
	 *            ����
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

	// ����һ����¼
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

					// ���ؽ��
					if (id.indexOf(buildingId) != -1) {

						// ��ӡ���ҽ��
						System.out.println("��ѯ������ \t  id:" + buildingId
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
					// ���ؽ��
					if (id.indexOf(buildingId) != -1) {

						// ��ӡ���ҽ��
						System.out.println("��ѯ������ \t  id:" + buildingId
								+ "\t name:" + buildingName + "\t lat:" + ba
								+ "\t lot:" + bo);
					}
				}
			}
		
			c.close();
		
	}

	public void bianliTable() { // ��������ӡ��
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

					System.out.println("�������ţ�  " + buildingId + "   ���ƣ� "
							+ buildingName + "   ���ȣ� " + ba + "   γ�ȣ� " + bo);

				}
				while (c.moveToNext()) {

					String buildingId = c.getString(0);
					System.out.println("����buildingId" + buildingId);
					String buildingName = c.getString(1);
					int buildingLat = c.getInt(2);
					int buildingLot = c.getInt(3);
					double ba = castInttoDouble(buildingLat);
					double bo = castInttoDouble(buildingLot);
					// ���ؽ��
					System.out.println("�������ţ�  " + buildingId + "   ���ƣ� "
							+ buildingName + "   ���ȣ� " + ba + "   γ�ȣ� " + bo);
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
	
	public String getIntroByName(String name){//ͨ�����ֲ��ҽ�����
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
					// ���ؽ��
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
					// ���ؽ��
					if (name.indexOf(buildingName) != -1) {

						return intro;
					}
				}
				
			}
			c.close();
			return null;
	}
	
	
	public int searchLatByName(String name){//ͨ�����ֲ���LAT������INT
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

					// ���ؽ��
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
					// ���ؽ��
					if (name.indexOf(buildingName) != -1) {  //����ƥ��
			          return buildingLat;
					}
				}
				
			}
		
			c.close();
			
	
		return 0;
	}
	public int searchLotByName(String name){   //ͨ�����ֲ���LOT������INT
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

					// ���ؽ��
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
					// ���ؽ��
					if (name.indexOf(buildingName) != -1) {  //����ƥ��
			          return buildingLot;
					}
				}
			}
			c.close();
		return 0;
	}

	public boolean IsIDExsist(String id) { // id��Ҫ����IDλ��

		Cursor c=null ;
		
			c= _buildingDatabase.query(TABLE_NAME, null, null, null, null,
					null, null);

			if (c.moveToFirst() == true) {

				if (c.getString(0).equals("0")) { // �ʼ��������0��λ��

					String buildingId = c.getString(0);

					if (id.equals(buildingId)) {
						System.out.println("���0����");
						return true;
					}

				}
				while (c.moveToNext()) {// ����ӱ��1��ʼ������
					String buildingId = c.getString(0);
					if (id.equals(buildingId)) {
						System.out.println("���" + id + "����");
						return true;
					} else {

						if (buildingId.equals(id)) {// ��ֹѭ����;����
							System.out.println("���" + id + "������");
							return false;
						}
					}
				}
			}
			System.out.println("���" + id + "������");
			c.close();
		return false;
	}
	 private Integer showItems() {//��ѯ�ж�������¼

			String col[] = { BLD_ID, BLD_NAME,BLD_LAT,BLD_LOT,BLD_ITR };
				Cursor cur=null;
				Integer num=0;
				cur = _buildingDatabase.query(TABLE_NAME, col, null, null, null, null, null);
				num = cur.getCount();
				cur.close();
				return num;
			}
	 
}
