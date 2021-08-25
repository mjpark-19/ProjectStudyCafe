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

				// ���� �ð�
				mainCurrentTime1();

				// ���� �ð� + Ÿ�̸�
//				mainCurrentTime2();
				
//				// �ð� ���� �ڵ� �α׾ƿ�
//				if ( lblTimer.getText().equals("Auto Logout : 00�� 00��" )) {
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

		// ���� �ð�
		int cHour = currentTime.get(Calendar.HOUR_OF_DAY);
		int cMin = currentTime.get(Calendar.MINUTE);
		int cSec = currentTime.get(Calendar.SECOND);

		showCurrentTime = formatTime(cHour, cMin, cSec); // ���� �ð�
		lblMainTime.setText(showCurrentTime);
	}
	
	void mainCurrentTime2() {

		Calendar currentTime = Calendar.getInstance();

		// ���� �ð�
		int cHour = currentTime.get(Calendar.HOUR_OF_DAY);
		int cMin = currentTime.get(Calendar.MINUTE);
		int cSec = currentTime.get(Calendar.SECOND);

		showCurrentTime = formatTime(cHour, cMin, cSec); // ���� �ð�
		lblMainTime.setText(showCurrentTime);
		
		
		// 2�� �α׾ƿ� Ÿ�̸�
		String time;
		if ((timerSec) > 60) {
			time = "01�� " + twoDigit((timerSec--) - 60)+ "��";
		} else if (timerSec == 60) {
			time = "01�� 00��";
			timerSec--;
		} else if (timerSec > 0) {
			time = "00�� " + twoDigit(timerSec--)+ "��";
		} else {
			time = "00�� 00��";
		}
//		lblTimer.setText( "Auto Logout : "+time);
		TimeCal.limitTime =  "Auto Logout : "+time;
		lblTimer.setText( TimeCal.limitTime);
	}
	
	String currentTime() {
		String showCurrentTime;
		currentTime = Calendar.getInstance();

		// ���� �ð�
		int cHour = currentTime.get(Calendar.HOUR_OF_DAY);
		int cMin = currentTime.get(Calendar.MINUTE);
		int cSec = currentTime.get(Calendar.SECOND);

		showCurrentTime = formatTime(cHour, cMin, cSec); // ���� �ð�

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

		// ���� ���� : "00:00:00" ����, "0:00:0" : substring ����
//		Calendar currentTime = Calendar.getInstance();

		// ���� �ð�
		int cHour = currentTime.get(Calendar.HOUR_OF_DAY);
		int cMin = currentTime.get(Calendar.MINUTE);
		int cSec = currentTime.get(Calendar.SECOND);

		// ���� ���� �ð�(�α��� ����)
//		loginTime = "04:01:50"; 
		String[] aryStartTime = startTime.split(":"); // substring(0, 2)�� ���ڸ��� �ð� ����
		int sHour = Integer.parseInt(aryStartTime[0]);
		int sMin = Integer.parseInt(aryStartTime[1]);
		int sSec = Integer.parseInt(aryStartTime[2]);

		// ���� ���� �ð�(�α׾ƿ� ����)
//		logoutTime = "21:10:20";
//		int fHour = Integer.parseInt(finishTime.substring(0, 2));
//		int fMin = Integer.parseInt(finishTime.substring(3, 5));
//		int fSec = Integer.parseInt(finishTime.substring(6, 8));

		// �ܿ� �ð� ( ������ ������ )
//		remainTime = "06:25:40";
		String[] aryRemainTime = remainTime.split(":");
		int rHour = Integer.parseInt(aryRemainTime[0]);
		int rMin = Integer.parseInt(aryRemainTime[1]);
		int rSec = Integer.parseInt(aryRemainTime[2]);

		// ���� �ð� ( ���� ����â �Ͻ� ��ȸ��)
		int oHour = 0;
		int oMin = 0;
		int oSec = 0;

		// �ð� ��� �� ����
//		calTime();	
		rSec = rSec - (cSec - sSec);
		rMin = rMin - (cMin - sMin);
		rHour = rHour - (cHour - sHour);

		// ��, ��, �� ����
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

		showCurrentTime = formatTime(cHour, cMin, cSec); // ���� �ð�

		showStartTime = formatTime(sHour, sMin, sSec); // ���� ���� �ð�(�α��� ����)

		showFinishTime = formatTime(rHour, rMin, rSec); // �ܿ� �ð� ( ������ ������ )

		showResultTime = formatTime(rHour, rMin, rSec); // �̿� �ð� ( ���� ���� �̿� �ð� )

//		System.out.println("���� �ð� : "+showCurrentTime);
//		System.out.println("���� �ð� : "+showStartTime);
		System.out.println("�ܿ� �ð� : " + showResultTime);
//		System.out.println("�̿� �ð� : "+showResultTime);
		System.out.println();

//		return showResultTime;
		Times[0] = showCurrentTime;
		Times[1] = showResultTime;
		return Times;
	}

	// �ð� ���� ����
	static String formatTime(int t1, int t2, int t3) {
		if (t2 == 60)
			t2 = 0;
		if (t3 == 60)
			t3 = 0;
		return twoDigit(t1) + ":" + twoDigit(t2) + ":" + twoDigit(t3);
	}

	// �ڸ��� ����
	static String twoDigit(int t) {
		return (0 <= t && t < 10) ? "0" + t : "" + t;
	}
}

//// �⺻ ���� ����
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
// // ���� �ð�
//int cHour;
//int cMin;
//int cSec;
//
//// ���� ���� �ð�(�α��� ����)
//String loginTime; 
//int iHour;
//int iMin;
//int iSec;
//
//// ���� ���� �ð�(�α׾ƿ� ����)
//String logoutTime;
//int oHour;
//int oMin;
//int oSec;
//
//// �ܿ� �ð� ( ������ ������ )
//String remainTime;
//int rHour;
//int rMin;
//int rSec;
//
//// ���� �ð� ( ���� ����â �Ͻ� ��ȸ��)
//int dHour;
//int dMin;
//int dSec;

//// ������0
//public TimeCal_C() {	}
//
//// ������	1
////public TimeCal_C(JLabel lbl) { this.lbl1 = lbl1; }
//
//// ������4
//public TimeCal_C(JLabel lbl1, JLabel lbl2, JLabel lbl3, JLabel lbl4) { 
//	this.lbl1 = lbl1;	
//	this.lbl2 = lbl2;	
//	this.lbl3 = lbl3;	
//	this.lbl4 = lbl4;	
//}

//// �ð� ��� �� ����
//void calTime() {
//	// ���� �ð� ����(
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
//	// ���� ���� : "00:00:00" ����, "0:00:0" : substring ���� 
//	Calendar currentTime = Calendar.getInstance();
//
//	// ���� �ð�
//	cHour = currentTime.get(Calendar.HOUR_OF_DAY);
//	cMin = currentTime.get(Calendar.MINUTE);
//	cSec = currentTime.get(Calendar.SECOND);
//	
//	// ���� ���� �ð�(�α��� ����)
////	loginTime = "04:01:50"; 
//	iHour = Integer.parseInt(loginTime.substring(0, 2));
//	iMin = Integer.parseInt(loginTime.substring(3, 5));
//	iSec = Integer.parseInt(loginTime.substring(6, 8));
//
//	// ���� ���� �ð�(�α׾ƿ� ����)
////	logoutTime = "21:10:20";
//	oHour = Integer.parseInt(logoutTime.substring(0, 2));
//	oMin = Integer.parseInt(logoutTime.substring(3, 5));
//	oSec = Integer.parseInt(logoutTime.substring(6, 8));
//
//	// �ܿ� �ð� ( ������ ������ )
//	remainTime = "06:25:40";
//	rHour = Integer.parseInt(remainTime.substring(0, 2));
//	rMin = Integer.parseInt(remainTime.substring(3, 5));
//	rSec = Integer.parseInt(remainTime.substring(6, 8));
//	
//	// ���� �ð� ( ���� ����â �Ͻ� ��ȸ��)
//	dHour = 0;
//	dMin = 0;
//	dSec = 0;
//	
//	// �ð� ��� �� ����
//	adjustTime();	
//	
//	showCurrentTime = formatTime(cHour, cMin, cSec);	// ���� �ð�
//
//	showLoginTime = formatTime(iHour, iMin, iSec);		// ���� ���� �ð�(�α��� ����)
//
//	showRemainTime = formatTime(rHour, rMin, rSec);     // �ܿ� �ð� ( ������ ������ )
//	
//	showDecreaseTime = formatTime(dHour, dMin, dSec);   // ���� �ð� ( ���� ����â �Ͻ� ��ȸ��)
//	
//	
//
//	System.out.println("���� �ð� : "+showCurrentTime);
//	System.out.println("���� �ð� : "+showLoginTime);
//	System.out.println("�ܿ� �ð� : "+showRemainTime);
//	System.out.println("���� �ð� : "+showDecreaseTime);
//	System.out.println();
//
//}
//
//// �ð� ��� �� ����
//void adjustTime() {
//	// ���� �ð� ����(
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
