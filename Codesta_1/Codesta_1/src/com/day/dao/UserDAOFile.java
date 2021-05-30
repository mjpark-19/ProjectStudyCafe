package com.day.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.day.dto.User;
import com.day.exception.FindException;

public class UserDAOFile implements UserDAO {

	// txt파일 경로
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
	String path_roomNo = ".\\User_info\\roomNo.txt";
	String path_seatNo = ".\\User_info\\seatNo.txt";
	String path_startTime = ".\\User_info\\startTime.txt";
	String path_userType = ".\\User_info\\userType.txt";

	// 각 정보를 리스트에 저장
	private ArrayList<String> list_ID = new ArrayList<String>();
	private ArrayList<String> list_PW = new ArrayList<String>();
	private ArrayList<String> list_Cart = new ArrayList<String>();
	private ArrayList<String> list_paymentHistory = new ArrayList<String>();
	private ArrayList<String> list_Point = new ArrayList<String>();
	private ArrayList<String> list_freeADayPassTime = new ArrayList<String>();
	private ArrayList<String> list_freeDaysPassTime = new ArrayList<String>();
	private ArrayList<String> list_freeWeekPassLastDay = new ArrayList<String>();
	private ArrayList<String> list_groupADayPassTime = new ArrayList<String>();
	private ArrayList<String> list_freeWeekPassPeriod = new ArrayList<String>();
	private ArrayList<String> list_roomNo = new ArrayList<String>();
	private ArrayList<String> list_seatNo = new ArrayList<String>();
	private ArrayList<String> list_startTime = new ArrayList<String>();
	private ArrayList<String> list_userType = new ArrayList<String>();

	Map<String, User> mapInfo = new HashMap<String, User>();
	User User;

	public UserDAOFile() {
		System.out.println("DB 생성자");
	}

	@Override
	public Map<String, User> selectAll() throws FindException {
		System.out.println("selectAll - (readUser) 진입");

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
			BufferedReader br_roomNo = new BufferedReader(new FileReader(path_roomNo));
			BufferedReader br_seatNo = new BufferedReader(new FileReader(path_seatNo));
			BufferedReader br_startTime = new BufferedReader(new FileReader(path_startTime));
			BufferedReader br_userType = new BufferedReader(new FileReader(path_userType));

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
			String data_roomNo = "";
			String data_seatNo = "";
			String data_startTime = "";
			String data_userType = "";

			while ((data_ID = br_ID.readLine()) != null && ((data_PW = br_PW.readLine()) != null)) {

				data_Cart = br_Cart.readLine();
				data_paymentHistory = br_paymentHistory.readLine();
				data_Point = br_Point.readLine();
				data_freeADayPassTime = br_freeADayPassTime.readLine();
				data_freeDaysPassTime = br_freeDaysPassTime.readLine();
				data_freeWeekPassLastDay = br_freeWeekPassLastDay.readLine();
				data_groupADayPassTime = br_groupADayPassTime.readLine();
				data_freeWeekPassPeriod = br_freeWeekPassPeriod.readLine();

				data_roomNo = br_roomNo.readLine();
				data_seatNo = br_seatNo.readLine();
				data_startTime = br_startTime.readLine();
				data_userType = br_userType.readLine();

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

				list_roomNo.add(data_roomNo);
				list_seatNo.add(data_seatNo);
				list_startTime.add(data_startTime);
				list_userType.add(data_userType);

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

			br_roomNo.close();
			br_seatNo.close();
			br_startTime.close();
			br_userType.close();

		} catch (Exception e) {
			// TODO: handle exception
		}

		// map<ID, User> 객체 만들기
		for (int i = 0; i < list_ID.size(); i++) {

			User User = new User.Builder(list_ID.get(i), list_PW.get(i)).Cart(list_Cart.get(i))
					.PaymentHistory(list_paymentHistory.get(i)).Point(list_Point.get(i))
					.freeADayPassTime(list_freeADayPassTime.get(i)).freeDaysPassTime(list_freeDaysPassTime.get(i))
					.freeWeekPassLastDay(list_freeWeekPassLastDay.get(i))
					.groupADayPassTime(list_groupADayPassTime.get(i)).freeWeekPassPeriod(list_freeWeekPassPeriod.get(i))

					.roomNo(list_roomNo.get(i)).seatNo(list_seatNo.get(i)).startTime(list_startTime.get(i))
					.userType(list_userType.get(i)).build();

			mapInfo.put(list_ID.get(i), User);
		}

		System.out.println("selectAll - (readUser) 종료");

		return mapInfo;

	}

	@Override
	public void insertAllInfo(User userInfo) throws FindException {
		Map<String, User> mapInfo = selectAll();
		mapInfo.put(userInfo.getID(), userInfo);

		boolean bool = false;
		for (String id : mapInfo.keySet()) {
			User userInfoTemp = mapInfo.get(id);
			userInfoToTxt(userInfoTemp, bool);
			bool = true;
		}

	}

	/**
	 * 특정 userInfo를 넣으면, userInfo가 가진 필드값을 모두 txt파일에 넣어줌
	 * 
	 * @param userInfoTemp map의 모든 userInfo를 일시적으로 저장
	 * @param bool         true를 넣어주면 이어쓰기, false를 넣어주면 새로쓰기
	 */
	public void userInfoToTxt(User userInfoTemp, boolean bool) {

//	         userInfoTemp.getID();
//	         userInfoTemp.getPW();
//	         userInfoTemp.getCart();
//	         userInfoTemp.getFreeADayPassTime();
//	         userInfoTemp.getFreeDaysPassTime();
//	         userInfoTemp.getFreeWeekPassLastDay();
//	         userInfoTemp.getFreeWeekPassPeriod();
//	         userInfoTemp.getGroupADayPassTime();
//	         userInfoTemp.getPaymentHistory();
//	         userInfoTemp.getPoint();

		try {
			BufferedWriter bwId = new BufferedWriter(new FileWriter(path_ID, bool));
			BufferedWriter bwPw = new BufferedWriter(new FileWriter(path_PW, bool));

			bwId.write(userInfoTemp.getID() + "\n");
			bwPw.write(userInfoTemp.getPW() + "\n");

			bwId.close();
			bwPw.close();

			// 그 외 정보들 빈칸으로 넣어주기
			List<String> listPath = Arrays.asList(path_Cart, path_paymentHistory, path_Point, path_freeADayPassTime,
					path_freeDaysPassTime, path_freeWeekPassLastDay, path_groupADayPassTime, path_freeWeekPassPeriod,
					path_roomNo, path_seatNo, path_startTime, path_userType);

			System.out.println("userInfoTemp getCart: " + userInfoTemp.getCart());

			List<String> listInfo = Arrays.asList(userInfoTemp.getCart(), userInfoTemp.getPaymentHistory(),
					userInfoTemp.getPoint(), userInfoTemp.getFreeADayPassTime(), userInfoTemp.getFreeDaysPassTime(),
					userInfoTemp.getFreeWeekPassLastDay(), userInfoTemp.getGroupADayPassTime(),
					userInfoTemp.getFreeWeekPassPeriod(), userInfoTemp.getRoomNo(), userInfoTemp.getSeatNo(),
					userInfoTemp.getStartTime(), userInfoTemp.getUserType());

			for (int i = 0; i < listPath.size(); i++) {

				BufferedWriter bw = new BufferedWriter(new FileWriter(listPath.get(i), bool));
				bw.write(listInfo.get(i) + "\n");
				bw.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 회원가입 변경용 ( PW찾기는 새로 만들기 )
	@Override
	public void insertInfo(String id, String pw) throws FindException {
		try {
			BufferedWriter bw_ID = new BufferedWriter(new FileWriter(path_ID, true));
			BufferedWriter bw_PW = new BufferedWriter(new FileWriter(path_PW, true));

			bw_ID.write(id + "\n");
			bw_PW.write(pw + "\n");

			bw_ID.close();
			bw_PW.close();

			// 그 외 정보들 빈칸으로 넣어주기
			List<String> list_path = Arrays.asList(
					path_Cart,
					path_paymentHistory,
					path_Point,
					path_freeADayPassTime,
					path_freeDaysPassTime,
					path_freeWeekPassLastDay,
					path_groupADayPassTime, 
					path_freeWeekPassPeriod,
					
	        		path_roomNo,
	        		path_seatNo,
	        		path_startTime,
	        		path_userType);

			for (int i = 0; i < list_path.size(); i++) {

				BufferedWriter bw = new BufferedWriter(new FileWriter(list_path.get(i), true));
				bw.write("" + "\n");
				bw.close();
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

//	
	@Override
	public ArrayList<String> getIdList() throws FindException {
		return list_ID;
	}

	@Override
	public ArrayList<String> getPwList() throws FindException {
		return list_PW;
	}
}
