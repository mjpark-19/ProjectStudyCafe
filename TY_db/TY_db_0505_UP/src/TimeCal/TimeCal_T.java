package TimeCal;

import javax.swing.JLabel;

public class TimeCal_T {

	
	JLabel lbl1;
	JLabel lbl2;
	JLabel lbl3;
	JLabel lbl4;

	// 레이블 4개 
	public TimeCal_T(JLabel lbl1, JLabel lbl2, JLabel lbl3, JLabel lbl4) {
		this.lbl1 = lbl1;
		this.lbl2 = lbl2;
		this.lbl3 = lbl3;
		this.lbl4 = lbl4;
		TimeCal_C Time = new TimeCal_C(lbl1, lbl2, lbl3, lbl4);
		Thread thread1 = new Thread(Time);
		thread1.start();
	}
	
	
	// 레이블 1개 
//	public TimeCal_T(JLabel lbl) {
//		this.lbl1 = lbl1;
//		TimeCal_C decreaseTime = new TimeCal_C(lbl);
//		Thread thread1 = new Thread(decreaseTime);
//		thread1.start();
//	}
}
