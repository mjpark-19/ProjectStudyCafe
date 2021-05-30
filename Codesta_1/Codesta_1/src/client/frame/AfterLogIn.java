package client.frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import com.day.dto.User;
import com.day.exception.FindException;
import com.day.service.UserService;

import client.time.CheckOutCal;
import client.time.TimeCal;

public class AfterLogIn extends JPanel {

	// 변수 선언
	JFrame frame;
	JPanel afterLogInPanel;
	JPanel purchasePanel;
	JPanel paymentPanel;
	JPanel mainPanel;
	JPanel bigPanel;

	JButton btnCheckIn;

	JLabel lblMainTime;
	JLabel lblTimer;

//	HashMap<String, UserInfo> mapInfo;
//	UserInfo userInfo;

	Map<String, User> mapInfo;
	User userInfo;
	UserService service;

	static int pageCnt = 0;

	// 생성자 ( 메인 로그인, 구입 이후 접근 시)

	public AfterLogIn(User userInfo, JLabel lblMainTime) {
		
		this.lblMainTime = lblMainTime;
		add(lblMainTime);
		
		service = UserService.getInstance();
		try {
			mapInfo = service.findAll();
		} catch (FindException e) {
			e.printStackTrace();
		} 
		this.userInfo = mapInfo.get(MainPanel.id);
		
		
		System.out.println("로그인 후 진입");
		initialize();
	}

	// 메소드 시작
	public void initialize() {

		setBackground(Color.GRAY);
		setLayout(null);
		
		

		JLabel lblID = new JLabel(MainPanel.id + " 님 반갑습니다.");
		lblID.setFont(new Font("굴림", Font.BOLD, 17));
		lblID.setForeground(Color.WHITE);
		lblID.setHorizontalAlignment(SwingConstants.CENTER);
		lblID.setBounds(160, 0, 300, 30);
		add(lblID);


		// 시간권 구입으로 이동
		JButton btnTimePurchase = new JButton("시간권 구입");
		btnTimePurchase.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				
				Payment payment = new Payment(userInfo, lblMainTime);
				MainPanel.frame.move(payment);
				
			}
		});
		btnTimePurchase.setBackground(Color.ORANGE);
		btnTimePurchase.setBounds(342, 420, 170, 132);
		add(btnTimePurchase);

		// 기간권 구입으로 이동
		JButton btnPeriodPurchase = new JButton("기간권 구입");
		btnPeriodPurchase.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				
				Payment payment = new Payment(userInfo, lblMainTime);
				MainPanel.frame.move(payment);
//				new Payment(frame, mainPanel, userInfo);
			}
		});
		btnPeriodPurchase.setBackground(Color.WHITE);
		btnPeriodPurchase.setBounds(513, 420, 170, 132);
		add(btnPeriodPurchase);

		// 시간권 이용 패널 띄우기
		JButton btnUseTime = new JButton("시간권 이용");
		btnUseTime.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				JPanel useTimePanel = new JPanel();
				useTimePanel.setVisible(false);
				System.out.println("userInfo.getUserType().equals(\"\")"+ userInfo.getUserType());
				System.out.println("userInfo.getUserType().equals(\"\")"+ userInfo.getID());
				if ((userInfo.getUserType().equals(""))) {
					useSmallPanelOpen(useTimePanel, "time");
				} else {
					doubleUseCheck(useTimePanel);
				}

			}
		});
		btnUseTime.setBackground(Color.ORANGE);
		btnUseTime.setBounds(0, 420, 170, 132);
		add(btnUseTime);

		// 기간권 이용 패널 띄우기
		JButton btnUsePeriod = new JButton("기간권 이용");
		btnUsePeriod.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				JPanel useTimePanel = new JPanel();
				useTimePanel.setVisible(false);

				if ((userInfo.getUserType().equals(""))) {
					useSmallPanelOpen(useTimePanel, "week");
				} else {
					doubleUseCheck(useTimePanel);
				}

			}
		});
		btnUsePeriod.setBackground(Color.WHITE);
		btnUsePeriod.setBounds(171, 420, 170, 132);
		add(btnUsePeriod);

		JButton btnMoveSeat = new JButton("좌석이동");
		btnMoveSeat.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("좌석 이동 userInfo.getUserType() : " + userInfo.getUserType());
				if (userInfo.getUserType() != "") {
					Seat.tempType = userInfo.getUserType();
					setVisible(false);

					Seat seat = new Seat(userInfo, lblMainTime); // 좌석 배치도, 좌석 이동, 선택 관련
					MainPanel.frame.move(seat);
					
				} else {
					JPanel useTimePanel = new JPanel();
					useTimePanel.setVisible(false);

					emptyUserType(useTimePanel);
				}

			}
		});
		btnMoveSeat.setForeground(new Color(255, 255, 255));
		btnMoveSeat.setBackground(Color.LIGHT_GRAY);
		btnMoveSeat.setBounds(1, 629, 227, 55);
		add(btnMoveSeat);

		// 내정보 확인
		JButton btnMyInfo = new JButton("내 정보");
		btnMyInfo.setForeground(new Color(255, 255, 255));
		btnMyInfo.setBackground(Color.LIGHT_GRAY);
		btnMyInfo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				System.out.println("내 정보 클릭");
//				new MyInfo(lblMainTime);

				MyInfo myInfo = new MyInfo(userInfo, lblMainTime); // 좌석 배치도, 좌석 이동, 선택 관련
				MainPanel.frame.move(myInfo);

			}
		});
		btnMyInfo.setBounds(229, 629, 227, 55);
		add(btnMyInfo);

		JButton btnNewButton_2_1_1 = new JButton("관리자 호출");
		btnNewButton_2_1_1.setForeground(Color.WHITE);
		btnNewButton_2_1_1.setBackground(Color.LIGHT_GRAY);
		btnNewButton_2_1_1.setBounds(457, 629, 227, 55);
		add(btnNewButton_2_1_1);

		LogOut();

	}

	// 입실 중인 상품이 없는 상태에서 좌석 이동 패널 열 때 - 현재 입실중인 상품이 없습니다.
	public void emptyUserType(JPanel useTimePanel) {
		useTimePanel.setBounds(106, 87, 494, 294);
		useTimePanel.setBackground(Color.WHITE);
		useTimePanel.setLayout(null);
		add(useTimePanel);
		useTimePanel.setVisible(true);

		JPanel checkInTitlePanel = new JPanel();
		checkInTitlePanel.setLayout(null);
		checkInTitlePanel.setBackground(Color.ORANGE);
		checkInTitlePanel.setBounds(0, 0, 494, 36);
		useTimePanel.add(checkInTitlePanel);

		JLabel lblCheckIntitle = new JLabel("좌석 이동");
		lblCheckIntitle.setBounds(12, 0, 76, 36);
		checkInTitlePanel.add(lblCheckIntitle);

		JButton btnCheckInClose = new JButton("닫기");
		btnCheckInClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				useTimePanel.setVisible(false);
			}
		});
		btnCheckInClose.setBackground(new Color(192, 192, 192));
		btnCheckInClose.setBounds(266, 246, 195, 38);
		useTimePanel.add(btnCheckInClose);

		// 체크인 완료 패널
		JPanel checkInDonePanel = new JPanel();
		checkInDonePanel.setBounds(10, 46, 472, 185);
		checkInDonePanel.setLayout(null);
		checkInDonePanel.setVisible(true);
		useTimePanel.add(checkInDonePanel);

		JLabel lblEmptyCart = new JLabel("현재 입실중인 상품이 없습니다.");
		lblEmptyCart.setBounds(36, 45, 380, 98);
		checkInDonePanel.add(lblEmptyCart);

	}

	// 시간권/기간권 이용 패널 클릭 시
	public void useSmallPanelOpen(JPanel useTimePanel, String strTimeWeek) {

		useTimePanel.setBounds(106, 87, 494, 294);
		useTimePanel.setBackground(Color.WHITE);
		useTimePanel.setLayout(null);
		add(useTimePanel);
		useTimePanel.setVisible(true);

		JPanel checkInTitlePanel = new JPanel();
		checkInTitlePanel.setLayout(null);
		checkInTitlePanel.setBackground(Color.ORANGE);
		checkInTitlePanel.setBounds(0, 0, 494, 36);
		useTimePanel.add(checkInTitlePanel);

		JLabel lblCheckIntitle = new JLabel("입실");
		lblCheckIntitle.setBounds(12, 0, 76, 36);
		checkInTitlePanel.add(lblCheckIntitle);

		btnCheckIn = new JButton("입실"); // 입 실
		btnCheckIn.setEnabled(false);
		btnCheckIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				setVisible(false);
				useTimePanel.setVisible(false);
//				new Seat();
				Seat seat = new Seat(userInfo, lblMainTime); // 좌석 배치도, 좌석 이동, 선택 관련
				MainPanel.frame.move(seat);
				
				try {
					service.backUp(userInfo);
				} catch (FindException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnCheckIn.setBackground(new Color(32, 178, 170));
		btnCheckIn.setBounds(38, 246, 195, 38);
		useTimePanel.add(btnCheckIn);

		JButton btnCheckInClose = new JButton("닫기");
		btnCheckInClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				useTimePanel.setVisible(false);
			}
		});
		btnCheckInClose.setBackground(new Color(192, 192, 192));
		btnCheckInClose.setBounds(266, 246, 195, 38);
		useTimePanel.add(btnCheckInClose);

		JLabel lblCheckInQuestion = new JLabel("보유하신 상품으로 입실하시겠습니까?");
		lblCheckInQuestion.setBounds(23, 55, 306, 26);
		lblCheckInQuestion.setVisible(true);

		useTimePanel.add(lblCheckInQuestion);

		JPanel checkInDonePanel = new JPanel();
		checkInDonePanel.setBounds(10, 46, 472, 185);
		checkInDonePanel.setLayout(null);
		checkInDonePanel.setVisible(true);
		useTimePanel.add(checkInDonePanel);

		JLabel lblEmptyCart = new JLabel("이용중인 상품이 없습니다. 시간권/기간권을 구입해주세요.");
		lblEmptyCart.setVisible(false);
		checkInDonePanel.add(lblEmptyCart);

		// 시간권/기간권 이용 패널에서 상세 상품 선택하기
		useTimePanelSelect(checkInDonePanel, lblCheckInQuestion, lblEmptyCart, strTimeWeek);

	}

	// 시간권/기간권 이용 패널에서 상세 상품 선택하기
	public void useTimePanelSelect(JPanel checkInDonePanel, JLabel lblCheckInQuestion, JLabel lblEmptyCart,
			String strTimeWeek) {
		System.out.println(" useTimePanelSelect ");


		System.out.println("userInfo.getCart().length():"+ userInfo.getCart().length() + " 끝 ");
		// 보유한 상품이 있으면 아래 코드 진행
		if (!userInfo.getCart().equals("")) {

			// 시간권과 기간권 보유 상품을 String[]로 분리하기
			String[] aryCart = regExr(userInfo.getCart(), strTimeWeek);
			System.out.println(aryCart.length);

			ButtonGroup btnGroupRadio = new ButtonGroup();

			for (int i = 0; i < aryCart.length; i++) {

				JRadioButton rdbtn = new JRadioButton(aryCart[i]);
				rdbtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						if (e.getActionCommand().contains("5인실")) {
							Seat.tempType = "group";
						} else if (e.getActionCommand().contains("시간권")) {
							Seat.tempType = "time";
						} else if (e.getActionCommand().contains("1회권")) {
							Seat.tempType = "hour";
						} else if (e.getActionCommand().contains("기간권")) {
							Seat.tempType = "week";
						}

						// 상품 선택 없이 입실 불가(버튼 비활성화)
						btnCheckIn.setEnabled(true);

					}
				});
				rdbtn.setBounds(45, 10 + 30 * (i + 1), 151, 34);
				checkInDonePanel.add(rdbtn);

				btnGroupRadio.add(rdbtn);
			}

			// 보유한 상품이 없을 경우 아래 코드 진행
		} else {

			lblEmptyCart.setBounds(36, 45, 380, 98);
			lblEmptyCart.setVisible(true);
			lblCheckInQuestion.setVisible(false);

		}

	}

	// 시간권과 기간권 보유 상품을 String[]로 분리하기
	public String[] regExr(String strCart, String strTimeWeek) {

		String regEx = "";
		if (strTimeWeek == "time")
			regEx = ".{3}\\s기.{5,9}\\)+;?"; // 비기간권만 남기기 replaceAll

		else if (strTimeWeek == "week")
			regEx = ".{3}\\s(?!기).{5,9}\\);?"; // 기간권만 남기기 replaceAll

		String[] ary = strCart.replaceAll(regEx, "").split(";");

		return ary;
	}

	public void LogOut() {

		JPanel bottomLogOutPanel = new JPanel();
		bottomLogOutPanel.setLayout(null);
		bottomLogOutPanel.setBackground(Color.ORANGE);
		bottomLogOutPanel.setBounds(0, 713, 686, 48);
		add(bottomLogOutPanel);
//		 bottomLogOutPanel.add(lblTimer); // 자동 로그아웃

		JButton btnLogOut = new JButton("LogOut");
		btnLogOut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("\n\n          로그아웃 ");
				setVisible(false);

				try {
					service.backUp(userInfo);
				} catch (FindException e1) {
					e1.printStackTrace();
				}
				
				MainPanel.id = null;
				userInfo = null;
			
				
				MainPanel mainPanel = new MainPanel(MainPanel.frame); 
				MainPanel.frame.move(mainPanel);
			}
		});
		btnLogOut.setBounds(204, 10, 200, 28);
		btnLogOut.setBounds(0, 0, 686, 48);
		btnLogOut.setBackground(Color.ORANGE);
		bottomLogOutPanel.add(btnLogOut);
	}

	public void doubleUseCheck(JPanel useTimePanel) {

		useTimePanel.setBounds(106, 87, 494, 294);
		useTimePanel.setBackground(Color.WHITE);
		useTimePanel.setLayout(null);
		add(useTimePanel);
		useTimePanel.setVisible(true);

		JPanel checkInTitlePanel = new JPanel();
		checkInTitlePanel.setLayout(null);
		checkInTitlePanel.setBackground(Color.ORANGE);
		checkInTitlePanel.setBounds(0, 0, 494, 36);
		useTimePanel.add(checkInTitlePanel);

		JLabel lblCheckIntitle = new JLabel("퇴실");
		lblCheckIntitle.setBounds(12, 0, 76, 36);
		checkInTitlePanel.add(lblCheckIntitle);

		JButton btnCheckIn = new JButton("퇴실");
		btnCheckIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				useTimePanel.setVisible(false);
				setVisible(true);

				// 입실 중 상품이 있는데, 시간권/기간권 이용 메뉴에 접근할 경우 - 기존 상품 퇴실 안내
				new CheckOutCal(userInfo);
				
				try {
					service.backUp(userInfo);
				} catch (FindException e1) {
					e1.printStackTrace();
				}

			}
		});
		btnCheckIn.setBackground(new Color(32, 178, 170));
		btnCheckIn.setBounds(38, 246, 195, 38);
		useTimePanel.add(btnCheckIn);

		JButton btnCheckInClose = new JButton("닫기");
		btnCheckInClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				useTimePanel.setVisible(false);
			}
		});
		btnCheckInClose.setBackground(new Color(192, 192, 192));
		btnCheckInClose.setBounds(266, 246, 195, 38);
		useTimePanel.add(btnCheckInClose);

		JLabel lblCheckInQuestion = new JLabel("입실 중인 상품이 있습니다. 퇴실 처리하시겠습니까?");
		lblCheckInQuestion.setBounds(23, 55, 306, 26);
		lblCheckInQuestion.setVisible(true);

		useTimePanel.add(lblCheckInQuestion);

		JPanel checkInDonePanel = new JPanel();
		checkInDonePanel.setBounds(10, 46, 472, 185);
		checkInDonePanel.setLayout(null);
		checkInDonePanel.setVisible(true);
		useTimePanel.add(checkInDonePanel);

		JLabel lblCheckInDone = new JLabel("퇴실 처리가 완료되었습니다.");
		lblCheckInDone.setBounds(36, 45, 380, 98);
		lblCheckInDone.setVisible(false);
		checkInDonePanel.add(lblCheckInDone);

	}
}
