package client.time;


import com.day.dto.User;
import com.day.service.UserService;

import client.frame.MainPanel;
import client.frame.Seat;
import client.time.TimeCal;

public class CheckOutCal {
	User userInfo;
	
	public CheckOutCal(User userInfo) {
		
		this.userInfo = userInfo;
		initialize();
		
	}

	public void initialize() {
			
		if (MainPanel.id != null) {
			doubleCheckOut();
		}
	}
	public void doubleCheckOut() { // 이용 중인 상품이 있을 때, 내부적으로 퇴실 처리 진행한다. 
		
		// 시간 계산 클래스
		TimeCal timeCal = new TimeCal();


		// 퇴실 시 시간 계산
		if ( userInfo.getUserType() == "hour" ) {
			userInfo.setFreeADayPassTime( timeCal.timeInfo( userInfo.getFreeADayPassTime(), userInfo.getStartTime() )[1] );
			
		}else if ( userInfo.getUserType() == "time" ) {
			userInfo.setFreeDaysPassTime( timeCal.timeInfo( userInfo.getFreeDaysPassTime(), userInfo.getStartTime() )[1] ); 
			
		}else if ( userInfo.getUserType() == "week" ) {

		}else if ( userInfo.getUserType() == "group") {
			userInfo.setGroupADayPassTime( timeCal.timeInfo( userInfo.getGroupADayPassTime(), userInfo.getStartTime() )[1] );
		}


		
		// 데이터 초기화 
		userInfo.setUserType("");
		userInfo.setStartTime("");
		
		// 이용중 좌석 색상을 나타내는데 쓰이는 Seat.selectedBtnNums 리스트
		if (userInfo.getSeatNo() != "") {
			Seat.selectedBtnNums.remove(userInfo.getSeatNo());
			userInfo.setSeatNo("");
		}
		else if (userInfo.getRoomNo() != "") {
			Seat.selectedBtnNums.remove(userInfo.getRoomNo());
			userInfo.setRoomNo("");
		}
		Seat.arySelectedBtn[0] = null;
		Seat.arySelectedBtn[1] = null;
		
		
		// 로그인 중이라  null 일 수 없음.
		// Main.id = null; 
		
		System.out.println("퇴실 완료");
	}

}
