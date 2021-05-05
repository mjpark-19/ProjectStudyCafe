package TimeCal;

import java.util.Calendar;

import javax.swing.JLabel;

public class TimeCal_C implements Runnable {
	
	// �⺻ ���� ����
	JLabel lbl1;
	JLabel lbl2;
	JLabel lbl3;
	JLabel lbl4;
	String showCurrentTime;
	String showLoginTime;
	String showLogoutTime;
	String showRemainTime;
	String showDecreaseTime;
	
	// ���� �ð�
	int cHour;
	int cMin;
	int cSec;
	
	// ���� ���� �ð�(�α��� ����)
	String loginTime; 
	int iHour;
	int iMin;
	int iSec;

	// ���� ���� �ð�(�α׾ƿ� ����)
	String logoutTime;
	int oHour;
	int oMin;
	int oSec;

	// �ܿ� �ð� ( ������ ������ )
	String remainTime;
	int rHour;
	int rMin;
	int rSec;
	
	// ���� �ð� ( ���� ����â �Ͻ� ��ȸ��)
	int dHour;
	int dMin;
	int dSec;
	
	
	// ������0
	public TimeCal_C() {	}
	
	// ������	1
//	public TimeCal_C(JLabel lbl) { this.lbl1 = lbl1; }
	
	// ������4
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

		// ���� ���� : "00:00:00" ����, "0:00:0" : substring ���� 
		Calendar currentTime = Calendar.getInstance();

		// ���� �ð�
		cHour = currentTime.get(Calendar.HOUR_OF_DAY);
		cMin = currentTime.get(Calendar.MINUTE);
		cSec = currentTime.get(Calendar.SECOND);
		
		// ���� ���� �ð�(�α��� ����)
		loginTime = "04:01:50"; 
		iHour = Integer.parseInt(loginTime.substring(0, 2));
		iMin = Integer.parseInt(loginTime.substring(3, 5));
		iSec = Integer.parseInt(loginTime.substring(6, 8));

		// ���� ���� �ð�(�α׾ƿ� ����)
		logoutTime = "21:10:20";
		oHour = Integer.parseInt(logoutTime.substring(0, 2));
		oMin = Integer.parseInt(logoutTime.substring(3, 5));
		oSec = Integer.parseInt(logoutTime.substring(6, 8));

		// �ܿ� �ð� ( ������ ������ )
		remainTime = "06:25:40";
		rHour = Integer.parseInt(remainTime.substring(0, 2));
		rMin = Integer.parseInt(remainTime.substring(3, 5));
		rSec = Integer.parseInt(remainTime.substring(6, 8));
		
		// ���� �ð� ( ���� ����â �Ͻ� ��ȸ��)
		dHour = 0;
		dMin = 0;
		dSec = 0;
		
		// �ð� ��� �� ����
		adjustTime();	
		
		showCurrentTime = formatTime(cHour, cMin, cSec);	// ���� �ð�

		showLoginTime = formatTime(iHour, iMin, iSec);		// ���� ���� �ð�(�α��� ����)

		showRemainTime = formatTime(rHour, rMin, rSec);     // �ܿ� �ð� ( ������ ������ )
		
		showDecreaseTime = formatTime(dHour, dMin, dSec);   // ���� �ð� ( ���� ����â �Ͻ� ��ȸ��)
		
		

		System.out.println("���� �ð� : "+showCurrentTime);
		System.out.println("���� �ð� : "+showLoginTime);
		System.out.println("�ܿ� �ð� : "+showRemainTime);
		System.out.println("���� �ð� : "+showDecreaseTime);
		System.out.println();

	}
	
	// �ð� ��� �� ����
	void adjustTime() {
		// ���� �ð� ����(
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
	
	// �ð� ���� ����
	String formatTime(int t1, int t2, int t3) {
		if (t2==60) t2=0;
		if (t3==60) t3=0;
	  	return twoDigit(t1)+":"+twoDigit(t2)+":"+twoDigit(t3) ;
	  }
	
	// �ڸ��� ����
	String twoDigit(int t) {
	  	return (0<t && t<10) ? "0"+t : ""+t;
	  }
}









	// �ð� ���� �߻� �� üũ �뵵
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
		
		// ���ο� ���� �ð� = ���� ���� �ð� - (�α׾ƿ� �ð� - �α��� �ð�)
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
//		System.out.println("���� �ð� : "+formatTime(rHour, rMin, rSec));	
//	}





	