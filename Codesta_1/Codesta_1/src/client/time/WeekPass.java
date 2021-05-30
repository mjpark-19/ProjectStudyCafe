package client.time;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class WeekPass {

	public static void main(String[] args) {


//		calDateBetweenAandB(); // 이용권 남은 기간 출력
		
//		today_finish_pass(); // 이용권 종료일 계산 및 출력 (현재 일자 기준)
		
//		begin_finish_pass(); // 이용권 종료일 계산 및 출력 (시작 일자 기준)
		
//		lastDayCal(5);
	}
	
	public static String lastDayCal(int dayPass) {
		Calendar calender = Calendar.getInstance();

//		String inputDate = "2021-05-28";
		
		// 이용권 기간
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
		
		System.out.print(dayPass+ "일 이용권의 종료일은 "+lastYear+"년 "+lastMonth+"월 "+lastDay+"일입니다.\n");
		
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
	    
	    try{ // String Type을 Date Type으로 캐스팅하면서 생기는 예외로 인해 여기서 예외처리 해주지 않으면
	    	 // 컴파일러에서 에러가 발생해서 컴파일을 할 수 없다.
	        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
	        // date1, date2 두 날짜를 parse()를 통해 Date형으로 변환.
	        Date FirstDate = format.parse(date1);
	        Date SecondDate = format.parse(date2);
	        
	        // Date로 변환된 두 날짜를 계산한 뒤 그 리턴값으로 long type 변수를 초기화 하고 있다.
	        // getTime() : long type 으로 return 된다.
	        long calDate = FirstDate.getTime() - SecondDate.getTime(); 
	        
	        // Date.getTime() 은 해당날짜를 기준으로1970년 00:00:00 부터 몇 초가 흘렀는지를 반환해준다. 
	        // 이제 24*60*60*1000(각 시간값에 따른 차이점) 을 나눠주면 일수가 나온다.
	        long calDateDays = calDate / ( 24*60*60*1000); 
	 
	        calDateDays = Math.abs(calDateDays);
	        System.out.println("calDateBetweenAandB");
	        System.out.println("두 날짜의 날짜 차이 : "+calDateDays);
	        System.out.println("이용권 잔여 기간 : "+ (dayPass - calDateDays)+"\n");
	        
	        } catch (ParseException e){
	        	System.out.println("예외 발생");
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
		
		System.out.print("오늘 ("+year+"년 "+month+1+"월 "+date+"일) 등록하신 \n");

		// 이용권 기간
		int dayPass = 5;
		
		// 이용권 마지막일 계산
		calender.set(year, month-1, date+dayPass-1);
		
		int lastYear = calender.get(Calendar.YEAR);
		int lastMonth = calender.get(Calendar.MONTH)+1;
		int lastDay = calender.get(Calendar.DATE);
		
		System.out.print(dayPass+ "일 이용권의 종료일은 "+lastYear+"년 "+lastMonth+"월 "+lastDay+"일입니다.\n");

	}
	
	static void begin_finish_pass() {
		System.out.println();
		Calendar calender = Calendar.getInstance();


		// 이용권 기간
		int dayPass = 5;
		
		
		calender.set(2021, 3, 20);;
		
		int year = calender.get(Calendar.YEAR);
		int month = calender.get(Calendar.MONTH)+1;
		int date = calender.get(Calendar.DATE);
		
		year = 2021;
		month = 3;
		date = 20;
		
		System.out.print(dayPass+"일 이용권의 시작일은 "+year+"년 "+month+"월 "+date+"일이며 \n");

		// 이용권 마지막일 계산
		
	
		calender.set(year, month-1, date + dayPass -1);
		
		int lastYear = calender.get(Calendar.YEAR);
		int lastMonth = calender.get(Calendar.MONTH)+1;
		int lastDay = calender.get(Calendar.DATE);
		
		System.out.print(dayPass+ "일 이용권의 종료일은 "+lastYear+"년 "+lastMonth+"월 "+lastDay+"일입니다.\n");
		System.out.print(lastYear+"-"+lastMonth+"-"+lastDay);

	}
}