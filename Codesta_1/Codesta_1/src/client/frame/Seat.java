package client.frame;

import java.awt.Color;
//import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.day.dto.User;
import com.day.exception.FindException;
import com.day.service.UserService;

import client.time.TimeCal;

public class Seat extends JPanel{

	// 변수 선언
	public static Set<String> selectedBtnNums = new HashSet<String>();
	public static JButton[] arySelectedBtn = new JButton[2];
	static String tempSeatRoomNo = "-1";
	static String tempType = "-1";

	private JPanel afterLogInPanel;
//	private JPanel mainPanel;
	
	private String seatNo;
	private String roomNo;

	JLabel lblMainTime;
//	UserInfo userInfo;
	ImageIcon img1, img2;

	ArrayList<String> list_ID;
	ArrayList<String> list_PW;
	Map<String, User> mapInfo;
	User userInfo;
	UserService service;
	

	// 생성자
	public Seat(User userInfo, JLabel lblMainTime) {
		this.userInfo = userInfo;
		this.lblMainTime = lblMainTime;
		add(lblMainTime);
		
		service = UserService.getInstance();
		try {
			mapInfo = service.findAll();
		} catch (FindException e) {
			e.printStackTrace();
		} 
		this.userInfo = mapInfo.get(MainPanel.id);
		
		initialize();
	}


	/**
	 * Initialize the contents of the frame.
	 */
	
	// 메소드 시작
	private void initialize() {
		
		setBackground(Color.GRAY);
		setLayout(null);
		
		JPanel yellowBorderPanel = new JPanel();
		yellowBorderPanel.setBounds(7, 96, 669, 600);
		yellowBorderPanel.setBackground(Color.GRAY);
		yellowBorderPanel.setBorder(new LineBorder(new Color(255, 200, 0), 3));
		add(yellowBorderPanel);
		yellowBorderPanel.setLayout(null);

		JPanel seatSelectTitlePanel = new JPanel();
		seatSelectTitlePanel.setBackground(Color.ORANGE);
		seatSelectTitlePanel.setBounds(5, 10, 676, 42);
		add(seatSelectTitlePanel);
		seatSelectTitlePanel.setLayout(null);

		JLabel lblSeatSelectTitle = new JLabel("좌석 선택");
		lblSeatSelectTitle.setBackground(Color.ORANGE);
		lblSeatSelectTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeatSelectTitle.setBounds(0, 0, 156, 32);
		seatSelectTitlePanel.add(lblSeatSelectTitle);

		JButton btnSeatPanelDone = new JButton("선 택 완 료"); // 닫기


		// 상황별 좌석 패널의 제목 변경 ( 실시간좌석현황 / 좌석 이동 / 좌석 선택 ) 
		if (userInfo == null) {

			btnSeatPanelDone.setVisible(false);
			
			lblSeatSelectTitle.setText("실시간좌석현황");

		} else if (!(userInfo.getSeatNo().equals("")) || !(userInfo.getRoomNo().equals(""))) {
			System.out.println("Seat 상황별 좌석 패널의 제목 변경  - 좌석 이동 진입");
			lblSeatSelectTitle.setText("좌석 이동");

		} else {
			System.out.println("Seat 상황별 좌석 패널의 제목 변경  - 좌석 선택 진입");
			lblSeatSelectTitle.setText("좌석 선택");
		}

		// 좌석 선택 완료 메소드로 정리
		seatSelectionDone(this, btnSeatPanelDone);

		// 버튼 addActionListener 이벤트 외부 클래스화
		SelectAction select = new SelectAction(this, yellowBorderPanel, userInfo);

		
		
		// 일반 좌석 버튼
		System.out.println("일반 For문 Seat.selectedBtnNums: " + Seat.selectedBtnNums);
		for (int i = 0; i <= 4; i++) {

			JButton btnSeat = new JButton(i + 1 + "");
			btnSeat.addActionListener(select);
			btnSeat.setFont(new Font("굴림", Font.BOLD, 15));

			// 본인의 좌석
			if ((userInfo != null) && (userInfo.getSeatNo().equals(btnSeat.getText()))) {
				btnSeat.setBackground(Color.RED);
				btnSeat.setEnabled(false);

				if (Integer.parseInt(btnSeat.getText()) > 30) {

					arySelectedBtn[1] = btnSeat; // 이동 2~3번 진입시 에러 방지용 ( SelectAction 클래스 내부 )

				} else {
					arySelectedBtn[0] = btnSeat; // 이동 2~3번 진입시 에러 방지용 ( SelectAction 클래스 내부 )
				}

				// 이용중 좌석
			} else if (Seat.selectedBtnNums.contains(btnSeat.getText())) {
				btnSeat.setBackground(Color.YELLOW);
				btnSeat.setEnabled(false);

				// 빈 좌석
			} else {
				btnSeat.setBackground(Color.WHITE);
			}

			btnSeat.setBounds(27, 20 + 80 * i, 60, 60);
			yellowBorderPanel.add(btnSeat);
		}

		
		
		// 5인석 좌석 버튼
		System.out.println("Seat 5인석 For문 Seat.selectedBtnNums: " + Seat.selectedBtnNums);
		for (int i = 0; i < 2; i++) {

			JButton btnSeat = new JButton(i + 31 + "");
			btnSeat.addActionListener(select);
			btnSeat.setFont(new Font("굴림", Font.BOLD, 15));

//			System.out.println("Seat userInfo.getRoomNo() : " + userInfo.getRoomNo());
			// 본인의 좌석
			if ((userInfo != null) && (userInfo.getRoomNo().equals(btnSeat.getText()))) {
				btnSeat.setBackground(Color.RED);
				btnSeat.setEnabled(false);
//				arySelectedBtn.add(btnSeat);

				if (Integer.parseInt(btnSeat.getText()) > 30) {
					arySelectedBtn[1] = btnSeat;

				} else {
					arySelectedBtn[0] = btnSeat;
				}

				// 이용중 좌석
			} else if (Seat.selectedBtnNums.contains(btnSeat.getText())) {
				System.out.println("Seat 이용 중인 좌석 - btnSeat.getText() : " + btnSeat.getText());

				btnSeat.setBackground(Color.YELLOW);
				btnSeat.setEnabled(false);

				// 빈 좌석
			} else {
				btnSeat.setBackground(Color.WHITE);
			}

			btnSeat.setBounds(520, 300 + 150 * i, 120, 120);
			yellowBorderPanel.add(btnSeat);
		}

	}

	public void seatSelectionDone(JPanel seatPanel, JButton btnSeatPanelDone) {

		btnSeatPanelDone.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("btnSeatPanelDone - Seat.tempSeatRoomNo : " + Seat.tempSeatRoomNo);

				// 개인 고객이 선택한 좌석 번호 임시 저장소(Seat.tempSeatRoomNo) - 선택하지 않았을 때 기본값 "-1"
				if ((Seat.tempSeatRoomNo != "-1")) {

					// 개인 고객이 (시간권, 1회권, 5인실, 기간권) 상품을 선택한 것을 저장
					userInfo.setUserType(Seat.tempType);

					System.out.println("좌석 선택 userInfo.setUserType(Seat.tempType) : " + userInfo.getUserType());
					// 5인석 ( 31번 이상 )
					if (Integer.parseInt(Seat.tempSeatRoomNo) > 30) {

						
						Seat.selectedBtnNums.remove(userInfo.getRoomNo()); // 이동했으니, 기존 고객 좌석을 전체 이용 좌석에서 삭제

						Seat.selectedBtnNums.add(Seat.tempSeatRoomNo); // 새로 이동한 임시 좌석 번호를, 전체 이용 좌석에 추가
						
						userInfo.setRoomNo(Seat.tempSeatRoomNo); // 새로 이동한 임시 좌석 번호를, 고객 정보에 반영
					
						// 1인석 ( 1-30번 )
					} else if (Integer.parseInt(Seat.tempSeatRoomNo) > 0) {

						Seat.selectedBtnNums.remove(userInfo.getSeatNo()); // 이동했으니, 기존 고객 좌석을 전체 이용 좌석에서 삭제

						Seat.selectedBtnNums.add(Seat.tempSeatRoomNo); // 새로 이동한 임시 좌석 번호를, 전체 이용 좌석에 추가
						
						userInfo.setSeatNo(Seat.tempSeatRoomNo); // 새로 이동한 임시 좌석 번호를, 고객 정보에 반영
					}

			        // 입실 시간 기록
					TimeCal timeCal = new TimeCal();
					userInfo.setStartTime(timeCal.currentTime());
					
					
					// userInfo 백업
					try {
						service.backUp(userInfo);
					} catch (FindException e1) {
						e1.printStackTrace();
					}
					
					// 이동
					seatPanel.setVisible(!seatPanel.isVisible());
					
					AfterLogIn afterLogin = new AfterLogIn(userInfo, lblMainTime); 
					MainPanel.frame.move(afterLogin);

					
					System.out.println();

				}
				
				// 초기화
				Seat.tempType = "-1";
				Seat.tempSeatRoomNo = "-1";
			}
		});
		btnSeatPanelDone.setFont(new Font("굴림", Font.BOLD, 12));
		btnSeatPanelDone.setForeground(Color.WHITE);
		btnSeatPanelDone.setBounds(7, 703, 330, 45);
		add(btnSeatPanelDone);
		btnSeatPanelDone.setBackground(Color.DARK_GRAY);

		
		// 좌석 선택 큰 패널의 닫기 버튼 
		JButton btnSeatPanelClose = new JButton("취 소 / 닫 기"); // 닫기
		btnSeatPanelClose.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				// 선택 후 완료하지 않고 취소 했을 경우 - 초기화
				Seat.tempType = "-1";
				Seat.tempSeatRoomNo = "-1";
				
				if (MainPanel.id != null) {
					setVisible(false);
					
					AfterLogIn afterLogin = new AfterLogIn(userInfo, lblMainTime); 
					MainPanel.frame.move(afterLogin);
				} else {
					
					setVisible(false);
					MainPanel mainPanel = new MainPanel(MainPanel.frame);
					MainPanel.frame.move(mainPanel);
				}
				
			}
		});
		btnSeatPanelClose.setFont(new Font("굴림", Font.BOLD, 12));
		btnSeatPanelClose.setForeground(Color.WHITE);
		btnSeatPanelClose.setBounds(337, 703, 330, 45);
		add(btnSeatPanelClose);
		btnSeatPanelClose.setBackground(Color.DARK_GRAY);

		JLabel unavailable = new JLabel("이용가능", img1, JLabel.LEFT);
		unavailable.setBounds(15, 56, 93, 37);
		add(unavailable);
		unavailable.setForeground(Color.WHITE);

		JLabel available = new JLabel("이용중", img2, JLabel.LEFT);
		available.setBounds(133, 56, 93, 37);
		add(available);
		available.setForeground(Color.WHITE);

	}

}
