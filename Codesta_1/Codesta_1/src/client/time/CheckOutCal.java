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
	public void doubleCheckOut() { // �̿� ���� ��ǰ�� ���� ��, ���������� ��� ó�� �����Ѵ�. 
		
		// �ð� ��� Ŭ����
		TimeCal timeCal = new TimeCal();


		// ��� �� �ð� ���
		if ( userInfo.getUserType() == "hour" ) {
			userInfo.setFreeADayPassTime( timeCal.timeInfo( userInfo.getFreeADayPassTime(), userInfo.getStartTime() )[1] );
			
		}else if ( userInfo.getUserType() == "time" ) {
			userInfo.setFreeDaysPassTime( timeCal.timeInfo( userInfo.getFreeDaysPassTime(), userInfo.getStartTime() )[1] ); 
			
		}else if ( userInfo.getUserType() == "week" ) {

		}else if ( userInfo.getUserType() == "group") {
			userInfo.setGroupADayPassTime( timeCal.timeInfo( userInfo.getGroupADayPassTime(), userInfo.getStartTime() )[1] );
		}


		
		// ������ �ʱ�ȭ 
		userInfo.setUserType("");
		userInfo.setStartTime("");
		
		// �̿��� �¼� ������ ��Ÿ���µ� ���̴� Seat.selectedBtnNums ����Ʈ
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
		
		
		// �α��� ���̶�  null �� �� ����.
		// Main.id = null; 
		
		System.out.println("��� �Ϸ�");
	}

}
