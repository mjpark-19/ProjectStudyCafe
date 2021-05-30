package client.time;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class WeekPass {

	public static void main(String[] args) {


//		calDateBetweenAandB(); // �̿�� ���� �Ⱓ ���
		
//		today_finish_pass(); // �̿�� ������ ��� �� ��� (���� ���� ����)
		
//		begin_finish_pass(); // �̿�� ������ ��� �� ��� (���� ���� ����)
		
//		lastDayCal(5);
	}
	
	public static String lastDayCal(int dayPass) {
		Calendar calender = Calendar.getInstance();

//		String inputDate = "2021-05-28";
		
		// �̿�� �Ⱓ
//		int dayPass = 0;
		

		int year = calender.get(Calendar.YEAR);
		int month = calender.get(Calendar.MONTH)+1;
		int date = calender.get(Calendar.DATE);
		
		System.out.println(year);
		System.out.println(month);
		System.out.println(date);
		
		calender.set(year, month, date + dayPass-1);
		
		int lastYear = calender.get(Calendar.YEAR);
		int lastMonth = calender.get(Calendar.MONTH);
		int lastDay = calender.get(Calendar.DATE);
		
		System.out.print(dayPass+ "�� �̿���� �������� "+lastYear+"�� "+lastMonth+"�� "+lastDay+"���Դϴ�.\n");
		
		System.out.print(formatDate(lastYear, lastMonth, lastDay));
		
		return formatDate(lastYear, lastMonth, lastDay) ;
		
	}
	
	
	
	
	static String formatDate(int t1, int t2, int t3) {
	  	return twoDigit(t1)+"-"+twoDigit(t2)+"-"+twoDigit(t3) ;
	  }
	
	static String twoDigit(int t) {
	  	return (t<10) ? "0"+t : ""+t;
	  }

	
	public static void calDateBetweenAandB()	{
		Calendar calender = Calendar.getInstance();

		int year; 
		int month; 
		int date;
		
		year = calender.get(Calendar.YEAR);
		month = calender.get(Calendar.MONTH)+1;
		date = calender.get(Calendar.DATE);
		
	    String date1 = formatDate(year, month, date) ;
	    String date2 = "2021-05-28";
	    int dayPass = 100;
	    
	    System.out.println(date1);
	    System.out.println(date2+"\n\n");
	    
	    try{ // String Type�� Date Type���� ĳ�����ϸ鼭 ����� ���ܷ� ���� ���⼭ ����ó�� ������ ������
	    	 // �����Ϸ����� ������ �߻��ؼ� �������� �� �� ����.
	        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
	        // date1, date2 �� ��¥�� parse()�� ���� Date������ ��ȯ.
	        Date FirstDate = format.parse(date1);
	        Date SecondDate = format.parse(date2);
	        
	        // Date�� ��ȯ�� �� ��¥�� ����� �� �� ���ϰ����� long type ������ �ʱ�ȭ �ϰ� �ִ�.
	        // getTime() : long type ���� return �ȴ�.
	        long calDate = FirstDate.getTime() - SecondDate.getTime(); 
	        
	        // Date.getTime() �� �ش糯¥�� ��������1970�� 00:00:00 ���� �� �ʰ� �귶������ ��ȯ���ش�. 
	        // ���� 24*60*60*1000(�� �ð����� ���� ������) �� �����ָ� �ϼ��� ���´�.
	        long calDateDays = calDate / ( 24*60*60*1000); 
	 
	        calDateDays = Math.abs(calDateDays);
	        System.out.println("calDateBetweenAandB");
	        System.out.println("�� ��¥�� ��¥ ���� : "+calDateDays);
	        System.out.println("�̿�� �ܿ� �Ⱓ : "+ (dayPass - calDateDays)+"\n");
	        
	        } catch (ParseException e){
	        	System.out.println("���� �߻�");
	        }
	} 

		
	static void today_finish_pass() {
		System.out.println();
		Calendar calender = Calendar.getInstance();

		int year; 
		int month; 
		int date;
		
		year = calender.get(Calendar.YEAR);
		month = calender.get(Calendar.MONTH)+1;
		date = calender.get(Calendar.DATE);
		
		System.out.print("���� ("+year+"�� "+month+1+"�� "+date+"��) ����Ͻ� \n");

		// �̿�� �Ⱓ
		int dayPass = 5;
		
		// �̿�� �������� ���
		calender.set(year, month-1, date+dayPass-1);
		
		int lastYear = calender.get(Calendar.YEAR);
		int lastMonth = calender.get(Calendar.MONTH)+1;
		int lastDay = calender.get(Calendar.DATE);
		
		System.out.print(dayPass+ "�� �̿���� �������� "+lastYear+"�� "+lastMonth+"�� "+lastDay+"���Դϴ�.\n");

	}
	
	static void begin_finish_pass() {
		System.out.println();
		Calendar calender = Calendar.getInstance();


		// �̿�� �Ⱓ
		int dayPass = 5;
		
		
		calender.set(2021, 3, 20);;
		
		int year = calender.get(Calendar.YEAR);
		int month = calender.get(Calendar.MONTH)+1;
		int date = calender.get(Calendar.DATE);
		
		year = 2021;
		month = 3;
		date = 20;
		
		System.out.print(dayPass+"�� �̿���� �������� "+year+"�� "+month+"�� "+date+"���̸� \n");

		// �̿�� �������� ���
		
	
		calender.set(year, month-1, date + dayPass -1);
		
		int lastYear = calender.get(Calendar.YEAR);
		int lastMonth = calender.get(Calendar.MONTH)+1;
		int lastDay = calender.get(Calendar.DATE);
		
		System.out.print(dayPass+ "�� �̿���� �������� "+lastYear+"�� "+lastMonth+"�� "+lastDay+"���Դϴ�.\n");
		System.out.print(lastYear+"-"+lastMonth+"-"+lastDay);

	}
}