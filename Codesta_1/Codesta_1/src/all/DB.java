package all;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class DB {
	// txt���� ���
	String path_ID = ".\\User_info\\ID.txt";
	String path_PW = ".\\User_info\\PW.txt";
	String path_Cart = ".\\User_info\\Cart.txt";
	String path_paymentHistory = ".\\User_info\\paymentHistory.txt";
	String path_Point = ".\\User_info\\Point.txt";
	String path_freeADayPassTime = ".\\User_info\\freeADayPassTime.txt";
	String path_freeDaysPassTime = ".\\User_info\\freeDaysPassTime.txt";
	String path_freeWeekPassLastDay = ".\\User_info\\freeWeekPassLastDay.txt";
	String path_groupADayPassTime = ".\\User_info\\groupADayPassTime.txt";
	String path_freeWeekPassPeriod = ".\\User_info\\freeWeekPassPeriod.txt";
	
	// �� ������ ����Ʈ�� ����
	ArrayList<String> list_ID  = new ArrayList<String>();
	ArrayList<String> list_PW = new ArrayList<String>();
	ArrayList<String> list_Cart = new ArrayList<String>();
	ArrayList<String> list_paymentHistory = new ArrayList<String>();
	ArrayList<String> list_Point = new ArrayList<String>();
	ArrayList<String> list_freeADayPassTime = new ArrayList<String>();
	ArrayList<String> list_freeDaysPassTime = new ArrayList<String>();
	ArrayList<String> list_freeWeekPassLastDay = new ArrayList<String>();
	ArrayList<String> list_groupADayPassTime = new ArrayList<String>();
	ArrayList<String> list_freeWeekPassPeriod = new ArrayList<String>();
	
	HashMap<String, UserInfo> mapInfo = new HashMap<String, UserInfo>();

	
//	ArrayList<String> list_ID ;
//	ArrayList<String> list_PW;
//	ArrayList<String> list_Cart;
//	ArrayList<String> list_paymentHistory;
//	ArrayList<String> list_Point;
//	ArrayList<String> list_freeADayPassTime;
//	ArrayList<String> list_freeDaysPassTime;
//	ArrayList<String> list_freeWeekPassLastDay;
//	ArrayList<String> list_groupADayPassTime;
//	ArrayList<String> list_freeWeekPassPeriod;
	
	// �� ������ map<ID, Userinfo> �� ����. Userinfo�� pw, cart, poin���� ������ ��� ����. 
//	HashMap<String, UserInfo> mapInfo;
//	UserInfo userInfo;
	
	// Db ������
	public DB() {
		System.out.println("DB ������ ���� - readUserInfo() ȣ��");
		readUserInfo();

	}

	public void textList() {
		list_ID = new ArrayList<String>();
		list_PW = new ArrayList<String>();
		list_Cart = new ArrayList<String>();
		list_paymentHistory = new ArrayList<String>();
		list_Point = new ArrayList<String>();
		list_freeADayPassTime = new ArrayList<String>();
		list_freeDaysPassTime = new ArrayList<String>();
		list_freeWeekPassLastDay = new ArrayList<String>();
		list_groupADayPassTime = new ArrayList<String>();
		list_freeWeekPassPeriod = new ArrayList<String>();
	}


	// txt������ �������� ��� ������, Db Ŭ���� ���� list�� map�� �������ش�.
	public HashMap<String, UserInfo> readUserInfo() {
//		mapInfo = new HashMap<String, UserInfo>();
//		HashMap<String, UserInfo> mapInfo = new HashMap<String, UserInfo>();
		System.out.println("readUserInfo ����");

		try {
			BufferedReader br_ID = new BufferedReader(new FileReader(path_ID));
			BufferedReader br_PW = new BufferedReader(new FileReader(path_PW));
			BufferedReader br_Cart = new BufferedReader(new FileReader(path_Cart));
			BufferedReader br_paymentHistory = new BufferedReader(new FileReader(path_paymentHistory));
			BufferedReader br_Point = new BufferedReader(new FileReader(path_Point));
			BufferedReader br_freeADayPassTime = new BufferedReader(new FileReader(path_freeADayPassTime));
			BufferedReader br_freeDaysPassTime = new BufferedReader(new FileReader(path_freeDaysPassTime));
			BufferedReader br_freeWeekPassLastDay = new BufferedReader(new FileReader(path_freeWeekPassLastDay));
			BufferedReader br_groupADayPassTime = new BufferedReader(new FileReader(path_groupADayPassTime));
			BufferedReader br_freeWeekPassPeriod = new BufferedReader(new FileReader(path_freeWeekPassPeriod));

			String data_ID = "";
			String data_PW = "";
			String data_Cart = "";
			String data_paymentHistory = "";
			String data_Point = "";
			String data_freeADayPassTime = "";
			String data_freeDaysPassTime = "";
			String data_freeWeekPassLastDay = "";
			String data_groupADayPassTime = "";
			String data_freeWeekPassPeriod = "";

			while ((data_ID = br_ID.readLine()) != null && ((data_PW = br_PW.readLine()) != null)) {

				data_Cart = br_Cart.readLine();
				data_paymentHistory = br_paymentHistory.readLine();
				data_Point = br_Point.readLine();
				data_freeADayPassTime = br_freeADayPassTime.readLine();
				data_freeDaysPassTime = br_freeDaysPassTime.readLine();
				data_freeWeekPassLastDay = br_freeWeekPassLastDay.readLine();
				data_groupADayPassTime = br_groupADayPassTime.readLine();
				data_freeWeekPassPeriod = br_freeWeekPassPeriod.readLine();

				list_ID.add(data_ID);
				list_PW.add(data_PW);
				list_Cart.add(data_Cart);
				list_paymentHistory.add(data_paymentHistory);
				list_Point.add(data_Point);
				list_freeADayPassTime.add(data_freeADayPassTime);
				list_freeDaysPassTime.add(data_freeDaysPassTime);
				list_freeWeekPassLastDay.add(data_freeWeekPassLastDay);
				list_groupADayPassTime.add(data_groupADayPassTime);
				list_freeWeekPassPeriod.add(data_freeWeekPassPeriod);

			}

			br_ID.close();
			br_PW.close();
			br_Cart.close();
			br_paymentHistory.close();
			br_Point.close();
			br_freeADayPassTime.close();
			br_freeDaysPassTime.close();
			br_freeWeekPassLastDay.close();
			br_freeWeekPassPeriod.close();
			br_groupADayPassTime.close();

		} catch (Exception e) {
			// TODO: handle exception
		}

		// map<ID, UserInfo> ��ü �����
		for (int i = 0; i < list_ID.size(); i++) {

			UserInfo Info = new UserInfo.Builder(list_ID.get(i), list_PW.get(i))
					.Cart(list_Cart.get(i))
					.PaymentHistory(list_paymentHistory.get(i))
					.Point(list_Point.get(i))
					.freeADayPassTime(list_freeADayPassTime.get(i))
					.freeDaysPassTime(list_freeDaysPassTime.get(i))
					.freeWeekPassLastDay(list_freeWeekPassLastDay.get(i))
					.groupADayPassTime(list_groupADayPassTime.get(i))
					.freeWeekPassPeriod(list_freeWeekPassPeriod.get(i))
					.build();

			mapInfo.put(list_ID.get(i), Info);
		}
//		System.out.println("Db | userInfo.getID() :"+ userInfo.getID());
//		System.out.println("Db | readUserInfo map.get(01011111111).hashCode() :"+ mapInfo.get("01011111111").hashCode());
//		userInfo = mapInfo.get("01011111111");
//		System.out.println("Db | userInfo.getID() :" + userInfo.getID());
//		System.out.println("Db | userInfo.hashCode() :" + userInfo.hashCode());
//		System.out.println("Db | userInfo.getID().hashCode() :" + userInfo.getID().hashCode());

		return mapInfo;

	}
	
	
	// ȸ������ �� txt���Ͽ� ���� ����
	public void createDbInfo(String id, String pw) {
		try {
			BufferedWriter bw_ID = new BufferedWriter(new FileWriter(path_ID, true));
			BufferedWriter bw_PW = new BufferedWriter(new FileWriter(path_PW, true));

			bw_ID.write(id + "\n");
			bw_PW.write(pw + "\n");

			bw_ID.close();
			bw_PW.close();

			// �� �� ������ ��ĭ���� �־��ֱ�
			List<String> list_path = Arrays.asList(path_Cart, path_paymentHistory, path_Point, path_freeADayPassTime,
					path_freeDaysPassTime, path_freeWeekPassLastDay, path_groupADayPassTime, path_freeWeekPassPeriod);

			for (int i = 0; i < list_path.size(); i++) {

				BufferedWriter bw = new BufferedWriter(new FileWriter(list_path.get(i), true));
				bw.write("" + "\n");
				bw.close();
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	
	// ȸ������ ����(�̿�)
		public void changePWInfo(String ID, String PW) {
			
			list_ID.indexOf(ID);
			list_PW.set(list_ID.indexOf(ID), PW);
			System.out.println("list_ID.indexOf(PW) : "+list_ID.indexOf(ID));
			System.out.println("list_ID.indexOf(list_ID.indexOf(ID)) : "+list_PW.indexOf(list_ID.indexOf(ID)));
			System.out.println("list_PW.get(list_ID.indexOf(ID)) : "+list_PW.get(list_ID.indexOf(ID)));;
			
		}
		
		
	// ȸ������ ����(�̿�)
	public void updateInfo(String filename, String userIndex, String update) {
		String path = ".\\User_info\\" + filename;

		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(path, true));

			bw.close();
		} catch (Exception e) {
			System.out.println("����");
		}

	}
	
}
