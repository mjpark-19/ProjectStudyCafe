package all;

import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


//public class TimeCal {
public class TimeCal implements Runnable {
	static String limitTime = "";
	JFrame frame;
	
	Calendar currentTime;
	JLabel lblMainTime;
	JLabel lblTimer;
	String showCurrentTime;
	JPanel mainPanel;
	JPanel afterLogInPanel;

	JPanel purchasePanel; 
	JPanel paymentPanel;
	
	int timerSec = 5;

	public TimeCal(JLabel lblMainTime) {
		this.lblMainTime = lblMainTime;
	}

	public TimeCal(JLabel lblMainTime, JLabel lblTimer, JPanel mainPanel, JPanel afterLogInPanel) {
		this.lblMainTime = lblMainTime;
		this.lblTimer = lblTimer;
		this.mainPanel = mainPanel;
		this.afterLogInPanel = afterLogInPanel;
	}

	
	public TimeCal(	JFrame frame, JLabel lblMainTime, JLabel lblTimer, JPanel mainPanel, JPanel afterLogInPanel) {
		this.frame = frame;
		this.lblMainTime = lblMainTime;
		this.lblTimer = lblTimer;
		this.mainPanel = mainPanel;
		this.afterLogInPanel = afterLogInPanel;
	}

	public TimeCal(JLabel lblMain, JLabel lblTimer, JPanel mainPanel, JPanel afterLogInPanel, JPanel purchasePanel,JPanel paymentPanel) {
		this.lblMainTime = lblMain;
		this.lblTimer = lblTimer;
		this.mainPanel = mainPanel;
		this.afterLogInPanel = afterLogInPanel;
		this.purchasePanel = purchasePanel; 
		this.paymentPanel= paymentPanel;
	}
	

	public TimeCal() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		try {
			while (true) {

				// 현재 시간
				mainCurrentTime1();

				// 현재 시간 + 타이머
//				mainCurrentTime2();
				
//				// 시간 제한 자동 로그아웃
//				if ( lblTimer.getText().equals("Auto Logout : 00분 00초" )) {
////					afterLogInPanel.setVisible(false);
////					purchasePanel.setVisible(false);
////					paymentPanel.setVisible(false);
//					mainPanel.setVisible(true);
//					frame.getContentPane().remove(afterLogInPanel);
////					frame.getContentPane().add(mainPanel);
////					
//					Main.id = null;
//					break;
//				}
				
				Thread.sleep(1000);

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	void mainCurrentTime1() {

		Calendar currentTime = Calendar.getInstance();

		// 현재 시간
		int cHour = currentTime.get(Calendar.HOUR_OF_DAY);
		int cMin = currentTime.get(Calendar.MINUTE);
		int cSec = currentTime.get(Calendar.SECOND);

		showCurrentTime = formatTime(cHour, cMin, cSec); // 현재 시간
		lblMainTime.setText(showCurrentTime);
	}
	
	void mainCurrentTime2() {

		Calendar currentTime = Calendar.getInstance();

		// 현재 시간
		int cHour = currentTime.get(Calendar.HOUR_OF_DAY);
		int cMin = currentTime.get(Calendar.MINUTE);
		int cSec = currentTime.get(Calendar.SECOND);

		showCurrentTime = formatTime(cHour, cMin, cSec); // 현재 시간
		lblMainTime.setText(showCurrentTime);
		
		
		// 2분 로그아웃 타이머
		String time;
		if ((timerSec) > 60) {
			time = "01분 " + twoDigit((timerSec--) - 60)+ "초";
		} else if (timerSec == 60) {
			time = "01분 00초";
			timerSec--;
		} else if (timerSec > 0) {
			time = "00분 " + twoDigit(timerSec--)+ "초";
		} else {
			time = "00분 00초";
		}
//		lblTimer.setText( "Auto Logout : "+time);
		TimeCal.limitTime =  "Auto Logout : "+time;
		lblTimer.setText( TimeCal.limitTime);
	}
	
	String currentTime() {
		String showCurrentTime;
		currentTime = Calendar.getInstance();

		// 현재 시간
		int cHour = currentTime.get(Calendar.HOUR_OF_DAY);
		int cMin = currentTime.get(Calendar.MINUTE);
		int cSec = currentTime.get(Calendar.SECOND);

		showCurrentTime = formatTime(cHour, cMin, cSec); // 현재 시간

		return showCurrentTime;
	}

	String[] timeInfo(String remainTime, String startTime) {
//		String timeInfo(String remainTime, String startTime) {
		currentTime = Calendar.getInstance();
		String showCurrentTime;
		String showResultTime;
		String showFinishTime;
		String showStartTime;
		String[] Times = new String[2];

		// 에러 방지 : "00:00:00" 형식, "0:00:0" : substring 에러
//		Calendar currentTime = Calendar.getInstance();

		// 현재 시간
		int cHour = currentTime.get(Calendar.HOUR_OF_DAY);
		int cMin = currentTime.get(Calendar.MINUTE);
		int cSec = currentTime.get(Calendar.SECOND);

		// 당일 시작 시간(로그인 시점)
//		loginTime = "04:01:50"; 
		String[] aryStartTime = startTime.split(":"); // substring(0, 2)는 한자리수 시각 에러
		int sHour = Integer.parseInt(aryStartTime[0]);
		int sMin = Integer.parseInt(aryStartTime[1]);
		int sSec = Integer.parseInt(aryStartTime[2]);

		// 당일 종료 시각(로그아웃 시점)
//		logoutTime = "21:10:20";
//		int fHour = Integer.parseInt(finishTime.substring(0, 2));
//		int fMin = Integer.parseInt(finishTime.substring(3, 5));
//		int fSec = Integer.parseInt(finishTime.substring(6, 8));

		// 잔여 시간 ( 데이터 보관용 )
//		remainTime = "06:25:40";
		String[] aryRemainTime = remainTime.split(":");
		int rHour = Integer.parseInt(aryRemainTime[0]);
		int rMin = Integer.parseInt(aryRemainTime[1]);
		int rSec = Integer.parseInt(aryRemainTime[2]);

		// 차감 시간 ( 개인 정보창 일시 조회용)
		int oHour = 0;
		int oMin = 0;
		int oSec = 0;

		// 시간 계산 및 보정
//		calTime();	
		rSec = rSec - (cSec - sSec);
		rMin = rMin - (cMin - sMin);
		rHour = rHour - (cHour - sHour);

		// 시, 분, 초 보정
		if (rSec < 0) {
			rSec += 60;
			rMin -= 1;
		} else if (rSec > 60) {
			rSec -= 60;
			rMin += 1;
		}

		if (rMin < 0) {
			rMin += 60;
			rHour -= 1;
		} else if (rMin > 60) {
			rMin -= 60;
			rHour += 1;
		}

		showCurrentTime = formatTime(cHour, cMin, cSec); // 현재 시간

		showStartTime = formatTime(sHour, sMin, sSec); // 당일 시작 시간(로그인 시점)

		showFinishTime = formatTime(rHour, rMin, rSec); // 잔여 시간 ( 데이터 보관용 )

		showResultTime = formatTime(rHour, rMin, rSec); // 이용 시간 ( 당일 순수 이용 시간 )

//		System.out.println("현재 시각 : "+showCurrentTime);
//		System.out.println("시작 시각 : "+showStartTime);
		System.out.println("잔여 시간 : " + showResultTime);
//		System.out.println("이용 시간 : "+showResultTime);
		System.out.println();

//		return showResultTime;
		Times[0] = showCurrentTime;
		Times[1] = showResultTime;
		return Times;
	}

	// 시간 형식 설정
	static String formatTime(int t1, int t2, int t3) {
		if (t2 == 60)
			t2 = 0;
		if (t3 == 60)
			t3 = 0;
		return twoDigit(t1) + ":" + twoDigit(t2) + ":" + twoDigit(t3);
	}

	// 자릿수 설정
	static String twoDigit(int t) {
		return (0 <= t && t < 10) ? "0" + t : "" + t;
	}
}

//// 기본 변수 선언
//JLabel lbl1;
//JLabel lbl2;
//JLabel lbl3;
//JLabel lbl4;
//String showCurrentTime;
//String showLoginTime;
//String showLogoutTime;
//String showRemainTime;
//String showDecreaseTime;
//
// // 현재 시간
//int cHour;
//int cMin;
//int cSec;
//
//// 당일 시작 시간(로그인 시점)
//String loginTime; 
//int iHour;
//int iMin;
//int iSec;
//
//// 당일 종료 시각(로그아웃 시점)
//String logoutTime;
//int oHour;
//int oMin;
//int oSec;
//
//// 잔여 시간 ( 데이터 보관용 )
//String remainTime;
//int rHour;
//int rMin;
//int rSec;
//
//// 차감 시간 ( 개인 정보창 일시 조회용)
//int dHour;
//int dMin;
//int dSec;

//// 생성자0
//public TimeCal_C() {	}
//
//// 생성자	1
////public TimeCal_C(JLabel lbl) { this.lbl1 = lbl1; }
//
//// 생성자4
//public TimeCal_C(JLabel lbl1, JLabel lbl2, JLabel lbl3, JLabel lbl4) { 
//	this.lbl1 = lbl1;	
//	this.lbl2 = lbl2;	
//	this.lbl3 = lbl3;	
//	this.lbl4 = lbl4;	
//}

//// 시간 계산 및 보정
//void calTime() {
//	// 차감 시간 산정(
//	dSec  = rSec  - ( cSec  - iSec  );
//	dMin  = rMin  - ( cMin  - iMin  );
//	dHour = rHour - ( cHour - iHour );
//
//
//	if (dSec < 0) {
//		dSec += 60;
//		dMin -= 1;
//	}else if (dSec >60){
//		dSec -= 60;
//		dMin += 1;
//	}
//	
//	if (dMin < 0) {
//		dMin += 60;
//		dHour -= 1;
//	}else if (dMin >60){
//		dMin -= 60;
//		dHour += 1;
//	}
//}
//
//static void timeInfo1 (String loginTime, String logoutTime) {
//	
//	
//	// 에러 방지 : "00:00:00" 형식, "0:00:0" : substring 에러 
//	Calendar currentTime = Calendar.getInstance();
//
//	// 현재 시간
//	cHour = currentTime.get(Calendar.HOUR_OF_DAY);
//	cMin = currentTime.get(Calendar.MINUTE);
//	cSec = currentTime.get(Calendar.SECOND);
//	
//	// 당일 시작 시간(로그인 시점)
////	loginTime = "04:01:50"; 
//	iHour = Integer.parseInt(loginTime.substring(0, 2));
//	iMin = Integer.parseInt(loginTime.substring(3, 5));
//	iSec = Integer.parseInt(loginTime.substring(6, 8));
//
//	// 당일 종료 시각(로그아웃 시점)
////	logoutTime = "21:10:20";
//	oHour = Integer.parseInt(logoutTime.substring(0, 2));
//	oMin = Integer.parseInt(logoutTime.substring(3, 5));
//	oSec = Integer.parseInt(logoutTime.substring(6, 8));
//
//	// 잔여 시간 ( 데이터 보관용 )
//	remainTime = "06:25:40";
//	rHour = Integer.parseInt(remainTime.substring(0, 2));
//	rMin = Integer.parseInt(remainTime.substring(3, 5));
//	rSec = Integer.parseInt(remainTime.substring(6, 8));
//	
//	// 차감 시간 ( 개인 정보창 일시 조회용)
//	dHour = 0;
//	dMin = 0;
//	dSec = 0;
//	
//	// 시간 계산 및 보정
//	adjustTime();	
//	
//	showCurrentTime = formatTime(cHour, cMin, cSec);	// 현재 시간
//
//	showLoginTime = formatTime(iHour, iMin, iSec);		// 당일 시작 시간(로그인 시점)
//
//	showRemainTime = formatTime(rHour, rMin, rSec);     // 잔여 시간 ( 데이터 보관용 )
//	
//	showDecreaseTime = formatTime(dHour, dMin, dSec);   // 차감 시간 ( 개인 정보창 일시 조회용)
//	
//	
//
//	System.out.println("현재 시각 : "+showCurrentTime);
//	System.out.println("시작 시각 : "+showLoginTime);
//	System.out.println("잔여 시간 : "+showRemainTime);
//	System.out.println("남은 시간 : "+showDecreaseTime);
//	System.out.println();
//
//}
//
//// 시간 계산 및 보정
//void adjustTime() {
//	// 차감 시간 산정(
//	dSec  = rSec  - ( cSec  - iSec  );
//	dMin  = rMin  - ( cMin  - iMin  );
//	dHour = rHour - ( cHour - iHour );
//
//
//	if (dSec < 0) {
//		dSec += 60;
//		dMin -= 1;
//	}else if (dSec >60){
//		dSec -= 60;
//		dMin += 1;
//	}
//	
//	if (dMin < 0) {
//		dMin += 60;
//		dHour -= 1;
//	}else if (dMin >60){
//		dMin -= 60;
//		dHour += 1;
//	}
//}
