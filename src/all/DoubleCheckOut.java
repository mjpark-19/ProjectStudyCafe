package all;

public class DoubleCheckOut {

	
	UserInfo userInfo ;
	public DoubleCheckOut(UserInfo userInfo) {
		
		this.userInfo = userInfo;
		initialize();
		
	}

	public void initialize() {
			
		if (Main.id != null) {
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
