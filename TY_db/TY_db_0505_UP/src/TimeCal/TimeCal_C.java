package TimeCal;

import java.util.Calendar;

import javax.swing.JLabel;

public class TimeCal_C implements Runnable {
	
	// 기본 변수 선언
	JLabel lbl1;
	JLabel lbl2;
	JLabel lbl3;
	JLabel lbl4;
	String showCurrentTime;
	String showLoginTime;
	String showLogoutTime;
	String showRemainTime;
	String showDecreaseTime;
	
	// 현재 시간
	int cHour;
	int cMin;
	int cSec;
	
	// 당일 시작 시간(로그인 시점)
	String loginTime; 
	int iHour;
	int iMin;
	int iSec;

	// 당일 종료 시각(로그아웃 시점)
	String logoutTime;
	int oHour;
	int oMin;
	int oSec;

	// 잔여 시간 ( 데이터 보관용 )
	String remainTime;
	int rHour;
	int rMin;
	int rSec;
	
	// 차감 시간 ( 개인 정보창 일시 조회용)
	int dHour;
	int dMin;
	int dSec;
	
	
	// 생성자0
	public TimeCal_C() {	}
	
	// 생성자	1
//	public TimeCal_C(JLabel lbl) { this.lbl1 = lbl1; }
	
	// 생성자4
	public TimeCal_C(JLabel lbl1, JLabel lbl2, JLabel lbl3, JLabel lbl4) { 
		this.lbl1 = lbl1;	
		this.lbl2 = lbl2;	
		this.lbl3 = lbl3;	
		this.lbl4 = lbl4;	
	}


	@Override
	public void run() {
		try {
			while(true) {
				
				timeInfo();
				
				lbl1.setText(showCurrentTime);
				lbl2.setText(showLoginTime);
				lbl3.setText(showRemainTime);
				lbl4.setText(showDecreaseTime);
				
				Thread.sleep(1000);
								
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}	
	}
	
	
	void timeInfo () {

		// 에러 방지 : "00:00:00" 형식, "0:00:0" : substring 에러 
		Calendar currentTime = Calendar.getInstance();

		// 현재 시간
		cHour = currentTime.get(Calendar.HOUR_OF_DAY);
		cMin = currentTime.get(Calendar.MINUTE);
		cSec = currentTime.get(Calendar.SECOND);
		
		// 당일 시작 시간(로그인 시점)
		loginTime = "04:01:50"; 
		iHour = Integer.parseInt(loginTime.substring(0, 2));
		iMin = Integer.parseInt(loginTime.substring(3, 5));
		iSec = Integer.parseInt(loginTime.substring(6, 8));

		// 당일 종료 시각(로그아웃 시점)
		logoutTime = "21:10:20";
		oHour = Integer.parseInt(logoutTime.substring(0, 2));
		oMin = Integer.parseInt(logoutTime.substring(3, 5));
		oSec = Integer.parseInt(logoutTime.substring(6, 8));

		// 잔여 시간 ( 데이터 보관용 )
		remainTime = "06:25:40";
		rHour = Integer.parseInt(remainTime.substring(0, 2));
		rMin = Integer.parseInt(remainTime.substring(3, 5));
		rSec = Integer.parseInt(remainTime.substring(6, 8));
		
		// 차감 시간 ( 개인 정보창 일시 조회용)
		dHour = 0;
		dMin = 0;
		dSec = 0;
		
		// 시간 계산 및 보정
		adjustTime();	
		
		showCurrentTime = formatTime(cHour, cMin, cSec);	// 현재 시간

		showLoginTime = formatTime(iHour, iMin, iSec);		// 당일 시작 시간(로그인 시점)

		showRemainTime = formatTime(rHour, rMin, rSec);     // 잔여 시간 ( 데이터 보관용 )
		
		showDecreaseTime = formatTime(dHour, dMin, dSec);   // 차감 시간 ( 개인 정보창 일시 조회용)
		
		

		System.out.println("현재 시각 : "+showCurrentTime);
		System.out.println("시작 시각 : "+showLoginTime);
		System.out.println("잔여 시간 : "+showRemainTime);
		System.out.println("남은 시간 : "+showDecreaseTime);
		System.out.println();

	}
	
	// 시간 계산 및 보정
	void adjustTime() {
		// 차감 시간 산정(
		dSec  = rSec  - ( cSec  - iSec  );
		dMin  = rMin  - ( cMin  - iMin  );
		dHour = rHour - ( cHour - iHour );


		if (dSec < 0) {
			dSec += 60;
			dMin -= 1;
		}else if (dSec >60){
			dSec -= 60;
			dMin += 1;
		}
		
		if (dMin < 0) {
			dMin += 60;
			dHour -= 1;
		}else if (dMin >60){
			dMin -= 60;
			dHour += 1;
		}
	}
	
	// 시간 형식 설정
	String formatTime(int t1, int t2, int t3) {
		if (t2==60) t2=0;
		if (t3==60) t3=0;
	  	return twoDigit(t1)+":"+twoDigit(t2)+":"+twoDigit(t3) ;
	  }
	
	// 자릿수 설정
	String twoDigit(int t) {
	  	return (0<t && t<10) ? "0"+t : ""+t;
	  }
}









	// 시간 오류 발생 시 체크 용도
//	void errorCheck() {
//		int t1s = ( cSec  - iSec  );
//		int t1m = ( cMin  - iMin  );
//		int t1h = ( cHour - iHour );
//		if (t1s < 0) {
//			t1s += 60;
//			t1m -= 1;
//		}else if (t1s >60){
//			t1s -= 60;
//			t1m += 1;
//		}
//		if (t1m < 0) {
//			t1m += 60;
//			t1h -= 1;
//		}else if (t1m >60){
//			t1m -= 60;
//			t1h += 1;
//		}
//
//
//		System.out.println(formatTime(t1h, t1m, t1s));
//
//		int t2s = ( rSec  - t1s  );
//		int t2m = ( rMin  - t1m  );
//		int t2h = ( rHour - t1h );
//
//		if (t2s < 0) {
//			t2s += 60;
//			t2m -= 1;
//		}else if (t2s >60){
//			t2s -= 60;
//			t2m += 1;
//		}
//		if (t2m < 0) {
//			t2m += 60;
//			t2h -= 1;
//		}else if (t2m >60){
//			t2m -= 60;
//			t2h += 1;
//		}
//
//		System.out.println(formatTime(t2h, t2m, t2s));
		
		// 새로운 보유 시간 = 현재 보유 시간 - (로그아웃 시각 - 로그인 시각)
//		rSec  = rSec  - ( oSec  - iSec  );
//		rMin  = rMin  - ( oMin  - iMin  );
//		rHour = rHour - ( oHour - iHour );
//		if (rSec < 0) {
//			rSec += 60;
//			rMin -= 1;
//		}
//		if (rMin < 0) {
//			rMin += 60;
//			rHour -= 1;
//		}
//		System.out.println("보유 시간 : "+formatTime(rHour, rMin, rSec));	
//	}





	