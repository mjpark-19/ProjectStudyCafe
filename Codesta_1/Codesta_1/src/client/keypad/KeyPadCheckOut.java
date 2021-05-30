package client.keypad;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import client.time.TimeCal;
import client.frame.MainPanel;
import client.frame.Seat;
import com.day.dto.User;
import com.day.exception.FindException;
import com.day.service.UserService;



public class KeyPadCheckOut extends JPanel{
	
	// 변수 선언
	JFrame frame;

	JPanel mainPanel;
	JPanel keypadPanel;
	JPanel checkOutPanel;
	JPanel checkOutHeadPanel;
	JPanel detailTimePanel;
	JPanel mainBtnPanel;

	public JTextField textID;
	public JTextField textOnPW;
	public JPasswordField textPW;
	JTextField textCheckOutID;
	
	JButton btnSignUp;
	JButton btnSeatMap;
	JButton btnFindPW;
	JButton btnFinish;
	JButton btnCheckOutClose;
	JButton btnCheckOut;
	JButton btnLogIn;
	
	JLabel lblCheckOutHead;
	JLabel lblStartTime1;
	JLabel lblStartTime2;
	JLabel lblCurrentTime1;
	JLabel lblCurrentTime2;
	JLabel lblUsedTime1;
	JLabel lblUsedTime2;
	JLabel lblCheckOutDone;
	
//	HashMap<String, UserInfo> mapInfo;
//	private UserInfo userInfo;
	
	
	ArrayList<String> list_ID;
	ArrayList<String> list_PW;
	Map<String, User> mapInfo;
	User userInfo;
	UserService service;
	
	int y = 150;
	int x = -140;
	int keyX = 90;
	

	// 생성자
	public KeyPadCheckOut(JPanel mainPanel, JPanel mainBtnPanel, JButton btnLogIn) {
		
		this.mainPanel = mainPanel;
		this.mainBtnPanel = mainBtnPanel;
		this.btnLogIn = btnLogIn;
		
		service = UserService.getInstance();
		try {
			mapInfo = service.findAll();
//			list_ID = service.getIdList();
//			list_PW = service.getPwList();
		} catch (FindException e) {
			e.printStackTrace();
		} 
		
		
		
		// 체크아웃 패널 생성
		checkOutPanel();
		
		// ID, PW 등 관련 메소드
		textFieldSet();
		
		// 키패드 생성 및 기능 관련 메소드
		keyPadPanel();
	
		
	}
	
	public void textFieldSet() {
		
		
		textID = new JTextField();
		textID.setFont(new Font("굴림", Font.BOLD, 15));
		textID.setBounds(200+x, 187+y+10, 270, 40);
		textID.addMouseListener(new MouseListener() {            // close 후 재클릭 시 필요한 코드 - 삭제 금지

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
				keypadPanel.setVisible(true);
				
				mainBtnPanel.setVisible(false);
//				btnSignUp.setVisible(!btnSignUp.isVisible());
//				btnFindPW.setVisible(!btnFindPW.isVisible());
//				btnSeatMap.setVisible(!btnSeatMap.isVisible());
//				btnFinish.setVisible(!btnFinish.isVisible());
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		textID.setForeground(Color.GRAY);
		textID.setText("010");
		textID.setColumns(12);
//		mainPanel.add(textID);
		
		
		textOnPW = new JTextField();
		textOnPW.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				textPW.setText("");
				textOnPW.setVisible(false);
				textPW.setVisible(true);
				keypadPanel.setVisible(true);
				mainBtnPanel.setVisible(false);

//				btnSignUp.setVisible(!btnSignUp.isVisible());
//				btnFindPW.setVisible(!btnFindPW.isVisible());
//				btnSeatMap.setVisible(!btnSeatMap.isVisible());
//				btnFinish.setVisible(!btnFinish.isVisible());
			}
			
			@Override
			public void mousePressed(MouseEvent e) {			}
			@Override
			public void mouseExited(MouseEvent e) {			}
			@Override
			public void mouseEntered(MouseEvent e) {			}
			@Override
			public void mouseClicked(MouseEvent e) {			}
		});
		textOnPW.setText("비밀번호를 입력하세요.");
		textOnPW.setBounds(200+x, 262+y, 270, 40);
//		mainPanel.add(textOnPW); //*
		
		
		textPW = new JPasswordField();
		textPW.setVisible(false);
		textPW.setFont(new Font("굴림", Font.BOLD, 15));
		textPW.setForeground(Color.GRAY);
		textPW.setBounds(200+x, 262+y, 270, 40);

		textPW.addMouseListener(new MouseListener() {            // close 후 재클릭 시 필요한 코드 - 삭제 금지

			@Override
			public void mouseReleased(MouseEvent e) {			}

			@Override
			public void mousePressed(MouseEvent e) {
				keypadPanel.setVisible(true);
				mainBtnPanel.setVisible(false);

			}

			@Override
			public void mouseExited(MouseEvent e) {					}
			
			@Override
			public void mouseEntered(MouseEvent e) {			}

			@Override
			public void mouseClicked(MouseEvent e) {			}
		});

		textPW.setColumns(6);


		// textID, pw 클릭 시 keypad load
		textID.addFocusListener(new FocusListener() { 

			@Override
			public void focusLost(FocusEvent e) {
				System.out.println("- ID Lost 진입 ");
				keypadPanel.setVisible(false);


			}

			@Override
			public void focusGained(FocusEvent e) {
				System.out.println("- ID Gained 진입");
				if (textID.isFocusOwner() == true) {
					System.out.println("- ID Gained if setVisible(true) 작동");
					keypadPanel.setVisible(true);


				}
			}
		});

		textPW.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				System.out.println("- PW Lost 진입 ");
				keypadPanel.setVisible(false);
				
			}

			@Override
			public void focusGained(FocusEvent e) {
//				keypadPanel.setVisible(true);

				if (textPW.isFocusOwner() == true) {
					System.out.println("- PW if setVisible(true) 작동");
					keypadPanel.setVisible(true);
					
				}
			}
		});
		
		mainPanel.add(textID);
		mainPanel.add(textOnPW); //*
		mainPanel.add(textPW);


	}

	public void keyPadPanel() {
		keypadPanel = new JPanel();
		keypadPanel.setBounds(100, 480, 500, 310);
		keypadPanel.setBounds(0, 480, 700, 310);
		keypadPanel.setBackground(Color.BLACK);
		keypadPanel.setLayout(null);
		keypadPanel.setVisible(false);
		mainPanel.add(keypadPanel);

		/**
		 * create keypad
		 */

		int[] btnPad = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				JButton btn_num = new JButton(btnPad[i + (j * 3)] + "");
				btn_num.setFont(new Font("굴림", Font.BOLD, 20));
				btn_num.setBackground(Color.BLACK);
				btn_num.setForeground(new Color(255, 200, 0));
				btn_num.setBounds(200 + i * 200, 30 + j * 60, 173, 39);
				btn_num.setBounds(50 + i * 100 +keyX, 30 + j * 60, 80, 39);

				btn_num.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						if (textID.isFocusOwner()) {
							textID.setText(textID.getText() + e.getActionCommand());
						} else if (checkOutPanel.isVisible()){
							textCheckOutID.setText(textCheckOutID.getText() + e.getActionCommand());
						} else {
							textPW.setText(textPW.getText() + e.getActionCommand());
						}

					}
				});
				keypadPanel.add(btn_num);
			}
		}

		JButton btn_0 = new JButton("0");
		btn_0.setForeground(Color.ORANGE);
		btn_0.setFont(new Font("굴림", Font.BOLD, 20));
		btn_0.setBackground(Color.BLACK);
		btn_0.setBounds(200 + 1 * 200, 30 + 3 * 60, 173, 39);
		btn_0.setBounds(50 + 1 * 100 +keyX, 30 + 3 * 60, 80, 39);

		btn_0.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (textID.isFocusOwner()) {
					textID.setText(textID.getText() + e.getActionCommand());
				} else if (checkOutPanel.isVisible()){
					textCheckOutID.setText(textCheckOutID.getText() + e.getActionCommand());
				} else {
					textPW.setText(textPW.getText() + e.getActionCommand());
				}

			}
		});
		keypadPanel.add(btn_0);

//		JButton btn_010 = new JButton("010");
//		btn_010.setForeground(Color.ORANGE);
//		btn_010.setFont(new Font("굴림", Font.BOLD, 20));
//		btn_010.setBackground(Color.BLACK);
//		btn_010.setBounds(25, 210, 173, 39);
//		btn_010.setBounds(50, 210, 80, 39);
//
//		btn_010.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				if (textID.isFocusOwner()) {
//					textID.setText(textID.getText() + e.getActionCommand());
//				} else {
//					textPW.setText(textPW.getText() + e.getActionCommand());
//				}
//
//			}
//		});
//		keypadPanel.add(btn_010);

//		JButton btn_00 = new JButton("00");
//		btn_00.setForeground(Color.ORANGE);
//		btn_00.setFont(new Font("굴림", Font.BOLD, 20));
//		btn_00.setBackground(Color.BLACK);
//		btn_00.setBounds(200 + 2 * 200, 30 + 3 * 60, 173, 39);
//		btn_00.setBounds(250, 210, 80, 39);
//
//		btn_00.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				if (textID.isFocusOwner()) {
//					textID.setText(textID.getText() + e.getActionCommand());
//				} else {
//					textPW.setText(textPW.getText() + e.getActionCommand());
//				}
//
//			}
//		});
//		keypadPanel.add(btn_00);

		JButton btn_back = new JButton("BACK");
		btn_back.setForeground(Color.ORANGE);
		btn_back.setFont(new Font("굴림", Font.BOLD, 20));
		btn_back.setBackground(Color.BLACK);
		btn_back.setBounds(200 + 3 * 200, 30 + 0 * 60, 173, 39);
		btn_back.setBounds(350 +keyX , 30 , 120, 39);

		btn_back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) { /// try문은 왜??
				try {
					
					if (textID.isFocusOwner()) {
						textID.setText(textID.getText().substring(0, textID.getText().length() - 1));
					} else if (checkOutPanel.isVisible()){
						textCheckOutID.setText(textCheckOutID.getText().substring(0, textCheckOutID.getText().length() - 1));
					} else {
						textPW.setText(textPW.getText().substring(0, textPW.getText().length() - 1));
					}
					
				} catch (Exception e2) {
					// TODO: handle exception
				}

			}
		});
		keypadPanel.add(btn_back);

		JButton btn_reset = new JButton("RESET");
		btn_reset.setForeground(Color.ORANGE);
		btn_reset.setFont(new Font("굴림", Font.BOLD, 20));
		btn_reset.setBackground(Color.BLACK);
		btn_reset.setBounds(200 + 3 * 200, 30 + 1 * 50, 173, 39);
		btn_reset.setBounds(350 +keyX , 90, 120, 39);

		btn_reset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (textID.isFocusOwner()) {
					textID.setText("010");
					
				} else if (checkOutPanel.isVisible()){
					textCheckOutID.setText("010");
					
				} else {
					textPW.setText("");
				}

			}
		});
		keypadPanel.add(btn_reset);

		JButton btn_close = new JButton("CLOSE");
		btn_close.setForeground(Color.ORANGE);
		btn_close.setFont(new Font("굴림", Font.BOLD, 20));
		btn_close.setBackground(Color.BLACK);
//		btn_close.setBounds(200 + 3 * 200, 30 + 2 * 60, 173, 39);
//		btn_close.setBounds(200 + 3 * 200, 30 + 1 * 60, 173, 39);
		btn_close.setBounds(350 +keyX , 150, 120, 39);

		btn_close.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("btn_close에서 setVisible(false) 작동");

				keypadPanel.setVisible(false);
				mainBtnPanel.setVisible(true);

//				btnSignUp.setVisible(!btnSignUp.isVisible());
//				btnFindPW.setVisible(!btnFindPW.isVisible());
//				btnSeatMap.setVisible(!btnSeatMap.isVisible());
//				btnFinish.setVisible(!btnFinish.isVisible());
			}
		});
		keypadPanel.add(btn_close);
		

	}
	
	
	// 퇴실 안내 미니 패널
	public void checkOutPanel() {
		
		checkOutPanel = new JPanel();
		checkOutPanel.setBackground(Color.WHITE);
//		checkOutPanel.setBounds(62, 67, 426, 313);
		checkOutPanel.setBounds(62, 100, 426, 313);
		checkOutPanel.setVisible(false);
		checkOutPanel.setLayout(null);
		mainPanel.add(checkOutPanel);
		
		detailTimePanel = new JPanel();
		detailTimePanel.setBackground(Color.WHITE);
		detailTimePanel.setBounds(62, 67, 350, 180);
		detailTimePanel.setVisible(false);
		detailTimePanel.setLayout(null);
		checkOutPanel.add(detailTimePanel);

		btnCheckOut = new JButton("퇴실");
		btnCheckOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				checkOutLabel();
				
				// 퇴실 화면
				btnFinish.setEnabled(true); // 퇴실 처리 버튼 비활성화

				checkOutPanel.setVisible(!checkOutPanel.isVisible());
				mainBtnPanel.setVisible(false);
				btnLogIn.setVisible(false);
				keypadPanel.setVisible(true);

				textPW.setVisible(false);
				textID.setVisible(false);
				textOnPW.setVisible(false);
				
				
			}
		});
		btnCheckOut.setBackground(Color.LIGHT_GRAY);
		btnCheckOut.setBounds(76, 340+y, 150, 40);
		btnCheckOut.setBounds(-2, 10, 170, 90);
//		checkOutPanel.add(btnCheckOut);
		mainBtnPanel.add(btnCheckOut);
		
		
		
		btnFinish = new JButton("퇴실 처리");
		btnFinish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
				calTime(); // 퇴실 시간 계산
				
				
			}
		});
		btnFinish.setBackground(new Color(51, 204, 204));
		btnFinish.setBounds(59, 263, 150, 40);
		checkOutPanel.add(btnFinish);
		
		btnCheckOutClose = new JButton("닫기");
		btnCheckOutClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				checkOutPanel.setVisible(!checkOutPanel.isVisible());
				textID.setVisible(!textID.isVisible());
//				btnCheckOut.setVisible(!btnCheckOut.isVisible());
//				btnSignUp.setVisible(!btnSignUp.isVisible());
//				btnSeatMap.setVisible(!btnSeatMap.isVisible());
//				btnFindPW.setVisible(!btnFindPW.isVisible());
				keypadPanel.setVisible(!keypadPanel.isVisible());
				mainBtnPanel.setVisible(true);
				
				btnLogIn.setVisible(true);
				
				detailTimePanel.setVisible(false);
				textOnPW.setVisible(true);
				textPW.setVisible(false);  
				
				textCheckOutID.setText("010");
				textPW.setText("");
				lblCurrentTime2.setText("");
				textOnPW.setText("비밀번호를 입력해주세요.");

			}
		});
		btnCheckOutClose.setBackground(new Color(128, 128, 128));
		btnCheckOutClose.setBounds(229, 263, 150, 40);
		checkOutPanel.add(btnCheckOutClose);
		
		textCheckOutID = new JTextField();
		textCheckOutID.setText("010");
		textCheckOutID.setForeground(Color.GRAY);
		textCheckOutID.setFont(new Font("굴림", Font.BOLD, 15));
		textCheckOutID.setColumns(12);
		textCheckOutID.setBounds(65, 195, 270, 40);
		checkOutPanel.add(textCheckOutID);
		
		JLabel lblCheckOut = new JLabel("핸드폰번호를 입력해주세요.");
		lblCheckOut.setHorizontalAlignment(SwingConstants.CENTER);
		lblCheckOut.setBounds(49, 53, 330, 40);
		checkOutPanel.add(lblCheckOut);
		
		lblCheckOutHead = new JLabel("퇴실 처리");
		lblCheckOutHead.setBackground(new Color(0, 191, 255));
		lblCheckOutHead.setBounds(0, 0, 426, 43);
		checkOutPanel.add(lblCheckOutHead);
		
		checkOutHeadPanel = new JPanel();
		checkOutHeadPanel.setBackground(Color.ORANGE);

		checkOutHeadPanel.setBounds(0, 0, 426, 43);
		checkOutPanel.add(checkOutHeadPanel);
	}

	
	
	public void calTime() {
		System.out.println("catTime 퇴실 처리 진입");
		
		if (!mapInfo.containsKey(textCheckOutID.getText())) {
			System.out.println("존재하지 않는 이용자");
			return;
		}
		
		userInfo = mapInfo.get(textCheckOutID.getText());
		System.out.println("시작 시간 :" + userInfo.getStartTime());
		
		if (userInfo.getStartTime().equals("")) {
			
	         JOptionPane.showMessageDialog(null, "이용중인 내역에 없습니다.", "login", JOptionPane.DEFAULT_OPTION);
	         
			return;
			
		} else {
			
			TimeCal timeCal = new TimeCal();
			System.out.println("catTime timeCal 생성");
			
			// 입실 시간
			detailTimePanel.setVisible(true);
			lblStartTime2.setText(userInfo.getStartTime());
			//lblCurrentTime2.setText(timeCal.timeInfo( userInfo.getFreeADayPassTime(), userInfo.getStartTime() )[0]);
			
			// 퇴실 시 시간 계산
			if ( userInfo.getUserType().equals("hour") ) {
				userInfo.setFreeADayPassTime( timeCal.timeInfo( userInfo.getFreeADayPassTime(), userInfo.getStartTime() )[1] );
				lblUsedTime2.setText(userInfo.getFreeADayPassTime());
				
			}else if ( userInfo.getUserType().equals("time") ) {
				System.out.println("catTime getUserType()"+ userInfo.getUserType());
				System.out.println("catTime getFreeDaysPassTime()(1)"+ userInfo.getFreeDaysPassTime());
				userInfo.setFreeDaysPassTime( timeCal.timeInfo( userInfo.getFreeDaysPassTime(), userInfo.getStartTime() )[1] ); 
				lblUsedTime2.setText(userInfo.getFreeDaysPassTime());
				System.out.println("catTime getFreeDaysPassTime()(2)"+ userInfo.getFreeDaysPassTime());
			}else if ( userInfo.getUserType().equals("week") ) {

			}else if ( userInfo.getUserType().equals("group") ) {
				userInfo.setGroupADayPassTime( timeCal.timeInfo( userInfo.getGroupADayPassTime(), userInfo.getStartTime() )[1] );
				lblUsedTime2.setText(userInfo.getGroupADayPassTime());
			}
			
			// 퇴실 시간 표시
			lblCurrentTime2.setText(timeCal.currentTime());


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
			
			Seat.arySelectedBtn[0] = null; // 1인석 - 좌석 이동 시 1개 좌석만 색상 변경 위해 필요
			Seat.arySelectedBtn[1] = null; // 5인석 - 좌석 이동 시 1개 좌석만 색상 변경 위해 필요
			
			// DoubleCheckOut(로그인 중 다른 상품을 이중으로 사용하려고 할 때 퇴실 처리) 클래스와는 다르게 아래 부분이 null되어야 한다.
			
			try {
				service.backUp(userInfo);
			} catch (FindException e1) {
				e1.printStackTrace();
			}
			
			MainPanel.id = null;
			userInfo = null;
			
			
			
			System.out.println("퇴실 완료");
		}

		
		
		
	}
		
	public void checkOutLabel() {
		

		lblCheckOutDone = new JLabel("퇴실처리가 완료되었습니다.");
		lblCheckOutDone.setBounds(68, 55, 200, 27);
		detailTimePanel.add(lblCheckOutDone);
		
		
		lblStartTime1 = new JLabel("입실 시간");
		lblStartTime1.setBounds(68, 80, 97, 27);
		detailTimePanel.add(lblStartTime1);
				
		lblCurrentTime1 = new JLabel("퇴실 시간");
		lblCurrentTime1.setBounds(68, 100, 97, 27);
		detailTimePanel.add(lblCurrentTime1);
		
		lblUsedTime1 = new JLabel("총 보유 시간");
		lblUsedTime1.setBounds(68, 120, 97, 27);
		detailTimePanel.add(lblUsedTime1);
		
		
		lblStartTime2 = new JLabel("00:00:00");
		lblStartTime2.setBounds(164, 80, 137, 27);
		detailTimePanel.add(lblStartTime2);

		
		lblCurrentTime2 = new JLabel("00:00:00");
		lblCurrentTime2.setBounds(164, 100, 137, 27);
		detailTimePanel.add(lblCurrentTime2);
				
		lblUsedTime2 = new JLabel("00:00:00");
		lblUsedTime2.setBounds(164, 120, 137, 27);
		detailTimePanel.add(lblUsedTime2);

	}
	
	public void Visible() {
		checkOutPanel.setVisible(!checkOutPanel.isVisible());
		textID.setVisible(!textID.isVisible());
		btnCheckOut.setVisible(!btnCheckOut.isVisible());
		btnSignUp.setVisible(!btnSignUp.isVisible());
		btnSeatMap.setVisible(!btnSeatMap.isVisible());
		btnFindPW.setVisible(!btnFindPW.isVisible());
		keypadPanel.setVisible(!keypadPanel.isVisible());
		mainPanel.setEnabled(false);
		textOnPW.setVisible(true);
	}
	
}
