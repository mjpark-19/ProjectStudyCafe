package com.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Db {
	// txt파일 경로
	String path_ID = "A:\\kiosk\\projectStudyCafe\\User_info\\ID.txt";
	String path_PW = "A:\\kiosk\\projectStudyCafe\\User_info\\PW.txt";
	String path_Cart = "A:\\kiosk\\projectStudyCafe\\User_info\\Cart.txt";
	String path_paymentHistory = "A:\\kiosk\\projectStudyCafe\\User_info\\paymentHistory.txt";
	String path_Point = "A:\\kiosk\\projectStudyCafe\\User_info\\Point.txt";
	String path_freeADayPassTime = "A:\\kiosk\\projectStudyCafe\\User_info\\freeADayPassTime.txt";
	String path_freeDaysPassTime = "A:\\kiosk\\projectStudyCafe\\User_info\\freeDaysPassTime.txt";
	String path_freeWeekPassLastDay = "A:\\kiosk\\projectStudyCafe\\User_info\\freeWeekPassLastDay.txt";
	String path_groupADayPassTime = "A:\\kiosk\\projectStudyCafe\\User_info\\groupADayPassTime.txt";
	String path_freeWeekPassPeriod = "A:\\kiosk\\projectStudyCafe\\User_info\\freeWeekPassPeriod.txt";

	// 각 정보를 리스트에 저장
	ArrayList<String> list_ID = new ArrayList<String>();
	ArrayList<String> list_PW = new ArrayList<String>();
	ArrayList<String> list_Cart = new ArrayList<String>();
	ArrayList<String> list_paymentHistory = new ArrayList<String>();
	ArrayList<String> list_Point = new ArrayList<String>();
	ArrayList<String> list_freeADayPassTime = new ArrayList<String>();
	ArrayList<String> list_freeDaysPassTime = new ArrayList<String>();
	ArrayList<String> list_freeWeekPassLastDay = new ArrayList<String>();
	ArrayList<String> list_groupADayPassTime = new ArrayList<String>();
	ArrayList<String> list_freeWeekPassPeriod = new ArrayList<String>();

	// 각 정보를 map<ID, Userinfo> 에 저장. Userinfo엔 pw, cart, poin등의 정보가 모두 담긴다.
	LinkedHashMap<String, UserInfo> mapLoad = new LinkedHashMap<>();

	// Db 생성자

	public Db() {
		readUserInfo();

	}

	// 회원가입 시 txt파일에 정보 삽입
	public void createDbInfo(String id, String pw) {
		try {
			BufferedWriter bw_ID = new BufferedWriter(new FileWriter(path_ID, true));
			BufferedWriter bw_PW = new BufferedWriter(new FileWriter(path_PW, true));

			bw_ID.write(id + "\n");
			bw_PW.write(pw + "\n");

			bw_ID.close();
			bw_PW.close();

			// 그 외 정보들 빈칸으로 넣어주기
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

	// txt파일의 정보들을 모두 가져와, Db 클래스 내의 list와 map에 저장해준다.
	public void readUserInfo() {
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
				data_freeADayPassTime = br_freeADayPassTime.readLine();
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

		// map<ID, UserInfo> 객체 만들기
		for (int i = 0; i < list_ID.size(); i++) {

			UserInfo up = new UserInfo.Builder(list_ID.get(i), list_PW.get(i)).Cart(list_Cart.get(i))
					.PaymentHistory(list_paymentHistory.get(i)).Point(list_Point.get(i))
					.freeADayPassTime(list_freeADayPassTime.get(i)).freeDaysPassTime(list_freeDaysPassTime.get(i))
					.freeWeekPassLastDay(list_freeWeekPassLastDay.get(i))
					.groupADayPassTime(list_groupADayPassTime.get(i)).freeWeekPassPeriod(list_freeWeekPassPeriod.get(i))
					.build();

			mapLoad.put(list_ID.get(i), up);
		}
	}

	// 회원정보 수정 - 변경된 map의 내용을 다시 역으로 txt파일에 저장함(새로 쓰기)
	public void updateDbInfo(HashMap<String, UserInfo> inputmap) { // 수정할 pw.txt파일만 업데이트해준다.
		String path = "A:\\kiosk\\projectStudyCafe\\User_info\\PW.txt";

		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(path));

			for (String id : inputmap.keySet()) {
				bw.write(inputmap.get(id).getPW()+"\n");
				System.out.println(inputmap.get(id).getPW());
			}

			bw.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
